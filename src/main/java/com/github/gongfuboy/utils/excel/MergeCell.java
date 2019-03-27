package com.github.gongfuboy.utils.excel;

import com.github.gongfuboy.utils.enums.MergeCellEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 合并单元格注解
 *
 * Created by ZhouLiMing on 2019/3/26.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MergeCell {

    MergeCellEnum value() default MergeCellEnum.FIRST_LEVEL;
}
