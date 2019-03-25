package com.own.order.infrastructure.util;

import javax.validation.ValidationException;
import java.util.Date;

public class ValidationUtil {
    /**
     * 验证不允许为空
     */
    public static void validateNotNullOrEmpty(String v, String msg) {
        if (v == null || v.length() <= 0) {
            throw new ValidationException(msg);
        }
    }

    /**
     * 验证不允许为空
     */
    public static void validateNotNullOrEmpty(Date v, String msg) {
        if (v == null) {
            throw new ValidationException(msg);
        }
    }
}