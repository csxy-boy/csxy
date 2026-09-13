package com.papercheck.exception;

/**
 * 答案文件无法创建或写入时抛出。
 */
public class AnswerWriteException extends PaperCheckException {

    public AnswerWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
