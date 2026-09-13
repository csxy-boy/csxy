package com.papercheck.exception;

/**
 * 命令行参数数量或内容不合法时抛出。
 */
public class CommandLineArgumentException extends PaperCheckException {

    public CommandLineArgumentException(String message) {
        super(message);
    }
}
