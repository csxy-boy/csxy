package com.papercheck.exception;

/**
 * 输入文件规范化后不包含任何有效字符时抛出。
 */
public class EmptyTextException extends PaperCheckException {

    public EmptyTextException(String message) {
        super(message);
    }
}
