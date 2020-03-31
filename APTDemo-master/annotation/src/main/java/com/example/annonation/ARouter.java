package com.example.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 注解处理器的范围:
 *
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ARouter {

    // 详细的路由路径,例如:"/app/MainActivity"
    String path();

    // 可以不填
    String group() default "";
}
