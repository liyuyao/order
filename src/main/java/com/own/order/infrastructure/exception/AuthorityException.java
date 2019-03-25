package com.own.order.infrastructure.exception;

/**
 * 权限认证异常
 */
public class AuthorityException extends Exception {

    private static final long serialVersionUID = 1L;

    public AuthorityException(String message) {
        super(message);
    }
}