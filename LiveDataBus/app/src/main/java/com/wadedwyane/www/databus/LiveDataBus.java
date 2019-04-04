package com.wadedwyane.www.databus;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kui.liu
 * @time 2019/4/4 15:58
 */
public class LiveDataBus {

    public static LiveDataBus instance;

    public Map<String, MutableLiveData<Object>> bus;

    public static LiveDataBus getInstance() {
        if (null == instance) {
            synchronized (LiveDataBus.class) {
                if (null == instance) {
                    instance = new LiveDataBus();
                }
            }
        }
        return instance;
    }

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    /**
     * 获取通道
     * @param channel
     * @param type
     * @param <T>
     * @return
     */
    public <T>MutableLiveData<T> getChannel(String channel, Class<T> type) {
        if (!bus.containsKey(channel)) {
            bus.put(channel, new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(channel);
    }
}
