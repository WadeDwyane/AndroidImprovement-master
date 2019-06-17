package com.wadedwyane.www.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author kui.liu
 * @time 2019/4/9 15:00
 */
public class EventBus {

    public static volatile EventBus instance;

    private Map<Object, List<SubscribeMethod>> cacheMap;

    private Handler handler;

    private EventBus() {
        cacheMap = new HashMap<>();
        handler = new Handler();
    }

    public static EventBus getDefault() {
        if (null == instance) {
            synchronized (EventBus.class) {
                if (null == instance) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    public void register(Object object) {
        List<SubscribeMethod> list = cacheMap.get(object);
        //判断是否重复注册
        if (null == list) {
            list = findSubscribeMethods(object);
            cacheMap.put(object, list);
        }
    }

    private List<SubscribeMethod> findSubscribeMethods(Object object) {
        List<SubscribeMethod> list = new ArrayList<>();
        Class<?> clazz = object.getClass();
        while (null != clazz) {
            // 凡是系统级别的父类,直接省略
            String name = clazz.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }

            //只找到自己本类或者自定义父类的声明方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method: methods) {
                //找到带有subscribe注解的方法
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (null == subscribe) {
                    continue;
                }
                //如果找到,判断带有注解的方法的参数
                Class<?>[] types = method.getParameterTypes();
                if (types.length != 1) {
                    Log.e("error", "eventbus only accept one param");
                }
                ThreadMode threadMode = subscribe.threadMode();
                //TODO:获取是否有黏性事件sticky的注解
                SubscribeMethod subscribeMethod = new SubscribeMethod(method, threadMode, types[0]);
                list.add(subscribeMethod);
            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }

    public void post(final Object type) {
        //直接去循环cachemap找到对应的方法进行回调
        Set<Object> set = cacheMap.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            final Object obj = iterator.next();
            List<SubscribeMethod> list = cacheMap.get(obj);
            for (final SubscribeMethod method: list) {
                // A对象所对应的的类是不是B对象的对应的类信息的父类或者接口
                if (method.getType().isAssignableFrom(type.getClass())) {
                    //判断
                    switch (method.getThreadMode()) {
                        case MAIN:
                            // 主 -- 主
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                invoke(method, obj, type);
                            }else {
                                //子 -- 主
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(method, obj, type);
                                    }
                                });
                            }
                            break;
                        case BACKGROUND:
                            //TODO:主 -- 子(ExecutorService)

                            //TODO:子 -- 子

                            break;
                        default:
                            break;
                    }
                    //直接调用方法
                    invoke(method, obj, type);
                }
            }
        }
    }

    private void invoke(SubscribeMethod subscribeMethod, Object obj, Object type) {
        Method method = subscribeMethod.getMethod();
        try {
            method.invoke(obj, type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //TODO:unregister()方法

    //TODO:粘性机制的实现方式

}
