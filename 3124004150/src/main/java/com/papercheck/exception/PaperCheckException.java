package com.papercheck.exception;

/**
 * 项目自定义异常的公共父类，便于在入口处统一捕获和给出友好提示。
 */
public class PaperCheckException extends Exception {

    public PaperCheckException(String message) {
        super(message);
    }

    public PaperCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
