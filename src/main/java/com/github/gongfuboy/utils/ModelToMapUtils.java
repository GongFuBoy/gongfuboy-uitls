package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.annotation.IgnoreField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * model转为为相应的map
 *
 * @author GongFuBoy
 * @date 2017/11/10
 * @time 15:26
 */
public class ModelToMapUtils {


    private static final Log log = LogFactory.getLog(ModelToMapUtils.class);

    /**
     * 将object中的值按规则全部put到Map中
     *
     * @param object
     * @return
     */
    public static Map<String, String> modelToMap(Object object) {
        Map<String, String> resultMap = null;
        try {
            if (Objects.equals(null, object)) {
                throw new NullPointerException("object parameters is not null");
            }
            Object[] fields = getFields(object.getClass());
            if (fields.length > 0) {
                resultMap = new HashMap<String, String>();
                setMap(resultMap, fields, object);
            }
        } catch (Exception e) {
            log.error("data transform fail" + e);
            throw new RuntimeException(e);
        }
        return resultMap;
    }

    /**
     * map中的值转化为model
     * @param clazz
     * @param sourceMap
     * @return
     * @throws Exception
     */
    public static <T> T mapToModel(Class<T> clazz, Map<String, String> sourceMap) {
        T t = null;
        try {
            if (sourceMap == null) {
                throw new NullPointerException("sourceMap could not be null");
            }
            t = clazz.newInstance();
            setModel(sourceMap, t, clazz);
        } catch (Exception e) {
            log.error("map transform to model is failed, failed due to", e);
            throw new RuntimeException(e);
        }
        return t;
    }

    /**
     * 获取clazz的所有成员变量
     * @param clazz
     * @return
     */
    private static Object[] getFields(Class<?> clazz) {
        List<Field> result = new ArrayList<Field>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i=0; i < declaredFields.length; i++) {
            IgnoreField ignoreField = declaredFields[i].getAnnotation(IgnoreField.class);
            if (ignoreField == null) {
                result.add(declaredFields[i]);
            }
        }
        return result.toArray();
    }

    /**
     * 设置目标对象中的值
     * @param sourceMap
     * @param fields
     */
    private static void setMap(Map<String, String> sourceMap, Object[] fields, Object object) throws IllegalAccessException {
        for (Object temp : fields) {
            Field tempField = (Field) temp;
            String key = tempField.getName();
            tempField.setAccessible(true);
            // 判断当前成员变量是不是被忽略
            IgnoreField ignoreField = tempField.getAnnotation(IgnoreField.class);
            if (ignoreField != null) {
                continue;
            }
            String value = (String) tempField.get(object);
            if (!Objects.equals(null, value)) {
                sourceMap.put(key, value);
            }
        }
    }

    /**
     * 设置model中的成员变量的值
     * @param sourceMap
     * @param object
     */
    private static void setModel(Map<String, String> sourceMap, Object object, Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {
        for (String key : sourceMap.keySet()) {
            Field field = null;
            try {
              field = clazz.getDeclaredField(key);
            } catch (Exception e) {
                // 什么也不做
            }
            if (field != null) {
                field.setAccessible(true);
                field.set(object, sourceMap.get(key));
            }
        }
    }
}
