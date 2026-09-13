package com.papercheck.io;

import com.papercheck.exception.FileAccessException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 以 UTF-8 编码读取输入文件。
 */
public final class FileTextReader {

    private FileTextReader() {
    }

    public static String read(Path path) throws FileAccessException {
        if (path == null) {
            throw new IllegalArgumentException("文件路径不能为 null");
        }

        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException | SecurityException e) {
            throw new FileAccessException("无法读取文件：" + path, e);
        }
    }
}
