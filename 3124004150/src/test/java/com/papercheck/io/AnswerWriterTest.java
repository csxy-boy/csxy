package com.papercheck.io;

import com.papercheck.exception.AnswerWriteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnswerWriterTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldWriteTwoDecimalPlaces() throws Exception {
        Path answer = tempDir.resolve("ans.txt");
        AnswerWriter.write(answer, 0.8421052631);
        assertEquals("0.84", new String(Files.readAllBytes(answer), StandardCharsets.UTF_8));
    }

    @Test
    void directoryAsTargetShouldThrowAnswerWriteException() {
        assertThrows(AnswerWriteException.class, () -> AnswerWriter.write(tempDir, 0.5));
    }

    @Test
    void nullPathShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AnswerWriter.write(null, 0.5));
    }
}
