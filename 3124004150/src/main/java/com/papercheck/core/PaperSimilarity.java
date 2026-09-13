package com.papercheck.core;

import com.papercheck.exception.EmptyTextException;

/**
 * 论文查重的核心门面类，负责文本规范化、算法选择和空文本判断。
 */
public final class PaperSimilarity {

    /**
     * LCS 动态规划的最大单元数。超过后改用 n-gram 近似算法，避免超时。
     */
    private static final long MAX_EXACT_PRODUCT = 4_000_000L;

    /**
     * 计算两个文本的重复率。
     *
     * @param original    原文
     * @param plagiarized 抄袭版论文
     * @return 0 到 1 之间的重复率
     * @throws EmptyTextException 当其中一个文本规范化后没有有效字符时抛出
     */
    public double compute(String original, String plagiarized) throws EmptyTextException {
        if (original == null || plagiarized == null) {
            throw new IllegalArgumentException("输入文本不能为 null");
        }

        String normalizedOriginal = TextNormalizer.normalize(original);
        String normalizedPlagiarized = TextNormalizer.normalize(plagiarized);

        if (normalizedOriginal.isEmpty() && normalizedPlagiarized.isEmpty()) {
            return 1.0;
        }
        if (normalizedOriginal.isEmpty() || normalizedPlagiarized.isEmpty()) {
            throw new EmptyTextException("文件中不包含有效字符，无法进行查重");
        }

        long product = (long) normalizedOriginal.length() * normalizedPlagiarized.length();
        if (product <= MAX_EXACT_PRODUCT) {
            return new LcsSimilarity().similarity(normalizedOriginal, normalizedPlagiarized);
        }
        return new NgramSimilarity().similarity(normalizedOriginal, normalizedPlagiarized);
    }
}
