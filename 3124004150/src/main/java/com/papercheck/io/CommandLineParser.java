package com.papercheck.io;

import com.papercheck.exception.CommandLineArgumentException;

/**
 * 校验命令行参数：必须是三个非空的文件绝对路径。
 */
public final class CommandLineParser {

    private static final int REQUIRED_ARGUMENT_COUNT = 3;

    private CommandLineParser() {
    }

    public static void validate(String[] args) throws CommandLineArgumentException {
        if (args == null || args.length != REQUIRED_ARGUMENT_COUNT) {
            throw new CommandLineArgumentException(
                    "参数数量不正确，应提供：原文文件绝对路径、抄袭版文件绝对路径、答案文件绝对路径");
        }

        for (String argument : args) {
            if (argument == null || argument.trim().isEmpty()) {
                throw new CommandLineArgumentException("文件路径不能为空");
            }
        }
    }
}
