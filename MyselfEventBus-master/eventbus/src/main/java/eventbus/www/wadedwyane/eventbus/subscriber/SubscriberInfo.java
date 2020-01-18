package eventbus.www.wadedwyane.eventbus.subscriber;

public interface SubscriberInfo {


    // 订阅所述的类
    Class<?> getSubscriberClass();

    // 订阅方法数组
    SubscriberMethod[] getSubscriberMethods();

}
