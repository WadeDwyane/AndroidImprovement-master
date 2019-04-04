package com.wadedwyane.www.livedatabus.livedatabus;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;

public class LiveDataBus {
    private static LiveDataBus instance;

    private Map<String, MutableLiveData<Object>> bus;

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
