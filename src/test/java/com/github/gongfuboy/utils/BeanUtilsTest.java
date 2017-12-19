package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.enums.TransformEnum;
import com.github.gongfuboy.utils.pojo.Human;
import org.junit.Before;
import org.junit.Test;

/**
 * BeanUtils测试类
 *
 * @author GongFuBoy
 * @date 2017/12/5
 * @time 14:43
 */
public class BeanUtilsTest {

    private Human human;

    @Before
    public void init() {
        human = new Human();
        human.setName("name");
        human.setAge("age");
    }

    @Test
    public void testTransformBeanToXml() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(BeanUtils.transformBeanToXml(human, TransformEnum.CAMAL_CASE));
    }

}
