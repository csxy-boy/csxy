package com.papercheck.core;

import java.util.Locale;

/**
 * 文本预处理工具，去掉空白和标点，并把英文统一为小写。
 *
 * <p>处理基于 Unicode 码点，因此对非基本平面的字符也能正确遍历。</p>
 */
public final class TextNormalizer {

    private TextNormalizer() {
    }

    /**
     * 只保留字母和数字，其余字符（空白、标点、符号）全部移除。
     *
     * @param text 原始文本，不能为 null
     * @return 规范化后的文本
     */
    public static String normalize(String text) {
        if (text == null) {
            throw new IllegalArgumentException("输入文本不能为 null");
        }

        StringBuilder normalized = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); ) {
            int codePoint = text.codePointAt(i);
            if (Character.isLetterOrDigit(codePoint)) {
                normalized.appendCodePoint(codePoint);
            }
            i += Character.charCount(codePoint);
        }
        return normalized.toString().toLowerCase(Locale.ROOT);
    }
}
