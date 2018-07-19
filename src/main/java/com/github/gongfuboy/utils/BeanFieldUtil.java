package com.github.gongfuboy.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhouLiMing on 2018/5/3.
 */
public class BeanFieldUtil {

    // String 为需要改变的类型，Object 为需要改变的类型默认值
    private static Map<String, Object> matchDate = new HashMap<String, Object>();

    static {
        matchDate.put("java.lang.String", "");
    }

    public static void main(String[] args) {
//        List<TCrmCompanyMaterialInfoCreateOrModifyRequest> list = new ArrayList<TCrmCompanyMaterialInfoCreateOrModifyRequest>();
//        TCrmCompanyMaterialInfoCreateOrModifyRequest ts = new TCrmCompanyMaterialInfoCreateOrModifyRequest();
//        fillData(ts);
//        System.out.println(ts);
        switch ("tempString") {
            case "tempString" :
                System.out.println("tempString");
                break;
            case "Other" :
                System.out.println("other");
                break;
        }
    }


    /**
     * 处理所有没有值的数据
     * @param object
     */
    public static void fillData(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            List<Field> getNeedDealFields = getNeedDealFields(fields);
            if (getNeedDealFields != null && !getNeedDealFields.isEmpty()){
                for (Field temp : getNeedDealFields) {
                    temp.setAccessible(true);
                    if (temp.get(object) == null){
                        temp.set(object, matchDate.get(temp.getType().getName()));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Field> getNeedDealFields(Field[] fields) {
        if (fields == null || fields.length == 0) {
            return null;
        } else {
            List result = new ArrayList<Field>();
            for (Field temp : fields) {
                Class clazz = temp.getType();
                matchDate.containsKey(clazz.getName());
                result.add(temp);
            }
            return result;
        }
    }



}
