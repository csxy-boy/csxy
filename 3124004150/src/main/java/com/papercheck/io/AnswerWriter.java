package com.papercheck.io;

import com.papercheck.exception.AnswerWriteException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * 将重复率写入答案文件，保留两位小数。
 */
public final class AnswerWriter {

    private AnswerWriter() {
    }

    public static void write(Path path, double similarity) throws AnswerWriteException {
        if (path == null) {
            throw new IllegalArgumentException("答案文件路径不能为 null");
        }

        String answer = String.format(Locale.ROOT, "%.2f", similarity);
        try {
            Path absolutePath = path.toAbsolutePath();
            Path parent = absolutePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.write(absolutePath, answer.getBytes(StandardCharsets.UTF_8));
        } catch (IOException | SecurityException e) {
            throw new AnswerWriteException("无法写入答案文件：" + path, e);
        }
    }
}
