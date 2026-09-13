package com.papercheck.exception;

/**
 * 原文或抄袭版论文文件无法读取时抛出。
 */
public class FileAccessException extends PaperCheckException {

    public FileAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
