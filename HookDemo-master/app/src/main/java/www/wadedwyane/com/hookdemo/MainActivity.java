package www.wadedwyane.com.hookdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button mBtnHook = findViewById(R.id.btn_hook);
        mBtnHook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ((Button) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        try {
            hook(mBtnHook);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hook(View view) throws Exception {
        //通过反射获取到View.OnClickListener对象
        Class mViewClass = Class.forName("android.view.View");
        Method getListenerInfo = mViewClass.getDeclaredMethod("getListenerInfo");
        //给方法授权
        getListenerInfo.setAccessible(true);
        //获取到ListenerInfo对象
        Object mListenerInfo = getListenerInfo.invoke(view);

        //静态变量用￥加载出来
        //获取到ListenerInfo类文件
        Class mListenerInfoClass = Class.forName("android.view.View$ListenerInfo");
        //获取到mOnClickListener这个属性
        Field mOnClickListenerField = mListenerInfoClass.getField("mOnClickListener");
        //获取到能执行这个方法的对象
        final Object mClickListenerObj = mOnClickListenerField.get(mListenerInfo);

        Object mOnClickListenerProxy = Proxy.newProxyInstance(MainActivity.class.getClassLoader(),

                new Class[]{View.OnClickListener.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //加上自己的逻辑
                        Button button = new Button(MainActivity.this);
                        button.setText("这是hook出来的逻辑");

                        //执行hook部分的代码
                        return method.invoke(mClickListenerObj, button);
                    }
                }
        );

        mOnClickListenerField.set(mListenerInfo, mOnClickListenerProxy);
    }
}
