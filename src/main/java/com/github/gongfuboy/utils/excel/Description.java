package com.github.gongfuboy.utils.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 成员变量的中文描述
 * 
 * @author zhoulm18864
 * @version $Id: Description.java, v 0.1 2017年3月2日 下午6:58:14 zhoulm18864 Exp $
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

	String value() default "";
}
