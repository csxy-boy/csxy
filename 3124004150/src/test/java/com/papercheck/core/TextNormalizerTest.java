package com.papercheck.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextNormalizerTest {

    @Test
    void shouldRemovePunctuationAndWhitespace() {
        assertEquals("今天天气晴", TextNormalizer.normalize("今天，天气晴。"));
        assertEquals("abc", TextNormalizer.normalize(" a b c "));
    }

    @Test
    void shouldKeepChineseLettersAndDigits() {
        assertEquals("论文2026", TextNormalizer.normalize("论文 2026"));
    }

    @Test
    void shouldLowerCaseEnglishLetters() {
        assertEquals("papercheck", TextNormalizer.normalize("PaperCheck"));
    }

    @Test
    void nullTextShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> TextNormalizer.normalize(null));
    }
}
