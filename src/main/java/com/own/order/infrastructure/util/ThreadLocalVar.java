package com.own.order.infrastructure.util;

/**
 * ThreadLocal 变量
 */
public class ThreadLocalVar {
    /**
     * 访问当前Action的用户的所属医院编码
     */
    private static final ThreadLocal<String> hospitalCode = new ThreadLocal<String>();

    public static void setHospitalCode(String v) {
        hospitalCode.set(v);
    }

    public static String getHospitalCode() {
        return hospitalCode.get();
    }
}