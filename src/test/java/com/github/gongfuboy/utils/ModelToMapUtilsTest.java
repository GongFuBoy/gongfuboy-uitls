package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.pojo.Human;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author GongFuBoy
 * @date 2017/12/12
 * @time 10:25
 */
public class ModelToMapUtilsTest {

    private final Log logger = LogFactory.getLog(getClass());

    @Test
    public void testModelToMap() {
        Human human = new Human();
        human.setAge("age");
        human.setName("name");
        logger.info(ModelToMapUtils.modelToMap(human));
    }
}
