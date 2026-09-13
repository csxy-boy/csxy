package com.papercheck.core;

import com.papercheck.exception.EmptyTextException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaperSimilarityTest {

    private final PaperSimilarity similarity = new PaperSimilarity();

    @Test
    void identicalTextShouldReturnOne() throws EmptyTextException {
        assertEquals(1.0, similarity.compute("今天天气好", "今天天气好"), 1e-9);
    }

    @Test
    void modifiedSampleShouldHaveHighSimilarity() throws EmptyTextException {
        double value = similarity.compute(
                "今天是星期天，天气晴，今天晚上我要去看电影。",
                "今天是周天，天气晴朗，我晚上要去看电影。");
        assertTrue(value > 0.7 && value <= 1.0, "期望得到较高相似度，实际为 " + value);
    }

    @Test
    void completelyDifferentEnglishTextShouldReturnZero() throws EmptyTextException {
        assertEquals(0.0, similarity.compute("abcdefg", "hijklmn"), 1e-9);
    }

    @Test
    void bothEmptyTextShouldReturnOne() throws EmptyTextException {
        assertEquals(1.0, similarity.compute("", ""), 1e-9);
    }

    @Test
    void oneEmptyTextShouldThrow() throws EmptyTextException {
        assertThrows(EmptyTextException.class, () -> similarity.compute("今天天气好", ""));
    }

    @Test
    void punctuationOnlyBothSidesShouldReturnOne() throws EmptyTextException {
        assertEquals(1.0, similarity.compute("，。！？", "!!??"), 1e-9);
    }

    @Test
    void punctuationOnlyOneSideShouldThrow() throws EmptyTextException {
        assertThrows(EmptyTextException.class, () -> similarity.compute("今天天气好", "。。。"));
    }

    @Test
    void englishCaseShouldBeIgnored() throws EmptyTextException {
        assertEquals(1.0, similarity.compute("Hello World", "hello world"), 1e-9);
    }

    @Test
    void knownLcsLengthShouldMatchExpectedValue() throws EmptyTextException {
        double value = similarity.compute("ABCBDAB", "BDCABA");
        assertEquals(2.0 * 4 / (7 + 6), value, 1e-9);
    }

    @Test
    void largeInputShouldUseFallbackAndReturnBoundedValue() throws EmptyTextException {
        String original = repeat("甲乙丙丁", 700);
        String plagiarized = repeat("乙丙丁戊", 700);
        double value = similarity.compute(original, plagiarized);
        assertTrue(value >= 0.0 && value <= 1.0);
    }

    private static String repeat(String unit, int times) {
        StringBuilder builder = new StringBuilder(unit.length() * times);
        for (int i = 0; i < times; i++) {
            builder.append(unit);
        }
        return builder.toString();
    }
}
