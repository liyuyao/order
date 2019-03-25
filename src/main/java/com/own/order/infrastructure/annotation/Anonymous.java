package com.own.order.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 将 Controller 或 Action 标注为可匿名访问，也就是不需要身份验证
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Anonymous {
}