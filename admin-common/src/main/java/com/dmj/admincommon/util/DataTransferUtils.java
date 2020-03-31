package com.dmj.admincommon.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 数据转换工具类
 *
 * @author Suian
 */
@Slf4j
public class DataTransferUtils {

    /**
     * list转树形结构
     *
     * @param list
     * @param idMethod     id的属性名
     * @param parentMethod parentId的属性名
     * @param chirldMethod children的属性名
     * @param <T>
     * @return
     */
    public static <T> List<T> list2Tree(List<T> list, String idMethod, String parentMethod, String chirldMethod) {
        List<T> trees = new ArrayList<>();
        try {
            for (T treeNode : list) {
                //判断是否为根节点
                if (Objects.isNull(getFieldValueByFieldName(parentMethod, treeNode))) {
                    trees.add(treeNode);
                }
                List<T> tList = new ArrayList<>();
                for (T it : list) {
                    Object parentId = getFieldValueByFieldName(parentMethod, it);
                    Object id = getFieldValueByFieldName(idMethod, treeNode);
                    if (!Objects.isNull(parentId) && !Objects.isNull(id) && parentId.equals(id)) {
                        //将子节点的值进行插入
                        tList.add(it);
                        setValue(treeNode, treeNode.getClass(), chirldMethod, List.class, tList);
                    }
                }
            }
        } catch (Exception e) {
            log.error("数据转换异常:{}", e);
        }
        return trees;
    }


    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static Object getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            log.error("根据属性名获取属性值异常", e);
            return null;
        }
    }

    /**
     * 根据属性，拿到set方法，并把值set到对象中
     *
     * @param obj       对象
     * @param clazz     对象的class
     * @param filedName 需要设置值得属性
     * @param typeClass
     * @param value
     */
    public static void setValue(Object obj, Class<?> clazz, String filedName, Class<?> typeClass, Object value) {
        filedName = removeLine(filedName);
        String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        try {
            Method method = clazz.getDeclaredMethod(methodName, new Class[]{typeClass});
            method.invoke(obj, new Object[]{getClassTypeValue(typeClass, value)});
        } catch (Exception ex) {
            log.error("写入数据异常", ex);
        }
    }


    /**
     * 通过class类型获取获取对应类型的值
     *
     * @param typeClass class类型
     * @param value     值
     * @return Object
     */
    private static Object getClassTypeValue(Class<?> typeClass, Object value) {
        if (typeClass == int.class || value instanceof Integer) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == short.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == byte.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == double.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == long.class) {
            if (null == value) {
                return 0;
            }
            return value;
        } else if (typeClass == String.class) {
            if (null == value) {
                return "";
            }
            return value;
        } else if (typeClass == boolean.class) {
            if (null == value) {
                return true;
            }
            return value;
        } else if (typeClass == BigDecimal.class) {
            if (null == value) {
                return new BigDecimal(0);
            }
            return new BigDecimal(value + "");
        } else {
            return typeClass.cast(value);
        }
    }


    /**
     * 处理字符串 如： abc_dex ---> abcDex
     *
     * @param str
     * @return
     */
    public static String removeLine(String str) {
        if (null != str && str.contains("_")) {
            int i = str.indexOf("_");
            char ch = str.charAt(i + 1);
            char newCh = (ch + "").substring(0, 1).toUpperCase().toCharArray()[0];
            String newStr = str.replace(str.charAt(i + 1), newCh);
            String newStr2 = newStr.replace("_", "");
            return newStr2;
        }
        return str;
    }

}

