package com.papercheck.io;

import com.papercheck.exception.FileAccessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileTextReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldReadUtf8File() throws Exception {
        Path file = tempDir.resolve("orig.txt");
        Files.write(file, "今天天气好".getBytes(StandardCharsets.UTF_8));
        assertEquals("今天天气好", FileTextReader.read(file));
    }

    @Test
    void missingFileShouldThrowFileAccessException() {
        assertThrows(FileAccessException.class,
                () -> FileTextReader.read(tempDir.resolve("not-exist.txt")));
    }

    @Test
    void nullPathShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> FileTextReader.read(null));
    }
}
