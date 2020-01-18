package eventbus.www.wadedwyane.eventbus.subscriber;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import eventbus.www.wadedwyane.eventbus.mode.ThreadMode;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
    ThreadMode threadMode() default ThreadMode.MAIN;

    boolean sticky() default false;

    int priority() default 0;
}
