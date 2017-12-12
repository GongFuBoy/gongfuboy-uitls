package com.github.gongfuboy.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在转化的时候需要忽略的成员变量
 *
 * @author GongFuBoy
 * @date 2017/11/14
 * @time 11:27
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IgnoreField {
}
