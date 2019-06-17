package com.wadedwyane.www.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kui.liu
 * @time 2019/4/9 15:05
 * 注解
 */

//注解的使用对象是:方法
//运行时找到这个注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
     ThreadMode threadMode() default ThreadMode.MAIN;
}
