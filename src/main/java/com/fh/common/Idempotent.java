package com.fh.common;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)//用于描述注解的使用范围，用在方法上
@Retention(RetentionPolicy.RUNTIME)//定义了该注解被保留的长短
public @interface Idempotent {
}
