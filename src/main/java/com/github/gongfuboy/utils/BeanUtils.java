package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.enums.TransformEnum;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * POJO简单处理的工具
 *
 * @author GongFuBoy
 * @date 2017/12/5
 * @time 10:01
 */
public class BeanUtils {

    private static final String XML_HEAD = "<xml>";
    private static final String XML_TAIL = "</xml>";
    private static final String SIGN = "_";

    /**
     * 将bean根据需要的命名法则转化为xml
     * @param source 需要转化的bean，这里的bean必须是一个POJO
     * @param type xml字符串命名的规则，比如CAMAL_CASE匈牙利命名法
     * @return
     */
    public static String transformBeanToXml(Object source, TransformEnum type) throws NoSuchFieldException, IllegalAccessException {
        if (type == null) {
            throw new NullPointerException("需要转化的命名类型为null");
        }
        return transformBeanToXmlByIgnoreFields(type, source, null);
    }

    /**
     * 根据忽略的成员变量转化为xml
     * @param source 目标对象
     * @param strings 需要忽略的成员变量
     */
    public static String transformBeanToXmlByIgnoreFields(TransformEnum type, Object source, String... strings) throws NoSuchFieldException, IllegalAccessException {
        StringBuffer stringBuffer = new StringBuffer();
        Field[] afterRemoveFields = null;
        if (isNotEmpty(strings)) {
            afterRemoveFields = removeFieldByIgnores(getFieldsFromObject(source), source, strings);
        } else  {
            afterRemoveFields = getFieldsFromObject(source);
        }
        if (isNotEmpty(afterRemoveFields)) {
            stringBuffer.append(XML_HEAD);
            for (int i=0; i < afterRemoveFields.length; i++) {
                Field afterRemoveField = afterRemoveFields[i];
                String xmlNode = createXmlNode(afterRemoveField, source, type);
                stringBuffer.append(xmlNode);
            }
            stringBuffer.append(XML_TAIL);
        }
        return stringBuffer.toString();
    }

    public static String createXmlNode(Field field, Object source, TransformEnum type) throws IllegalAccessException {
        String name = getStyleName(field, type);
        String head = "<" + name + ">";
        String tail = "</" + name + ">";
        field.setAccessible(true);
        Object value = field.get(source);
        String middle = null;
        if (value != null) {
            middle = value.toString();
        } else {
            middle = "";
        }
        return head + middle + tail;
    }

    /**
     * 根据相应的类型转化name
     * @param type
     * @return
     */
    private static String getStyleName(Field field, TransformEnum type) {
        StringBuffer result = new StringBuffer();
        String name = field.getName();
        if (TransformEnum.HUNGARIAN_NOTATION.equals(type)) {
            if (!name.contains(SIGN)) {
                String[] temp = name.split("");
                for (int i=1; i < temp.length; i++) {
                    if (i == 1) {
                        result.append(temp[i]);
                        continue;
                    }
                    if (isUpperString(temp[i])) {
                        result.append(SIGN).append(temp[i].toLowerCase());
                        continue;
                    }
                    result.append(temp[i]);
                }
            } else {
                result.append(name);
            }
        }
        if (TransformEnum.CAMAL_CASE.equals(type)) {
            if (!name.contains(SIGN)) {
                result.append(name);
            } else {
                String[] temp = name.split("");
                boolean flag = false;
                for (int i=1; i < temp.length; i++) {
                    if (i == 1) {
                        result.append(temp[i]);
                        continue;
                    }
                    if (temp[i].equals(SIGN)) {
                        flag = true;
                        continue;
                    }
                    if (flag) {
                        result.append(temp[i].toUpperCase());
                        flag = false;
                        continue;
                    }
                    result.append(temp[i]);
                }

            }
        }
        return result.toString();
    }

    /**
     * 判断目标字符串是否为大写字符串
     * @param string
     * @return
     */
    public static boolean isUpperString(String string) {
        boolean result = false;
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            result = true;
        }
        return result;
    }

    /**
     * 判断数组是否为空；不为空返回true，为空返回false
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(Object[] objects) {
        if (objects != null && objects.length > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据忽略规则移除不需要转化的成员变量
     *
     * @param fields 成员变量
     * @param source 源对象
     * @param ignores 需要忽略的成员变量
     * @return
     */
    private static Field[] removeFieldByIgnores(Field[] fields, Object source, String[] ignores) throws NoSuchFieldException {
        List<Field> temp = Arrays.asList(fields);
        if (isNotEmpty(ignores)) {
            for (int i=0; i < ignores.length; i++) {
                if (temp.contains(source.getClass().getDeclaredField(ignores[i]))) {
                    temp.remove(source.getClass());
                }
            }
        }
        return (Field[]) temp.toArray();
    }

    /**
     * 获取source的所有成员变量
     * @param source 目标源
     * @return
     */
    private static Field[] getFieldsFromObject(Object source) {
        Field[] fields = source.getClass().getDeclaredFields();
        if (!isNotEmpty(fields)) {
            throw new NullPointerException("当前对象成员为空");
        }
        return fields;
    }

}
