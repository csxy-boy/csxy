package com.papercheck.core;

import java.util.HashSet;
import java.util.Set;

/**
 * 基于字符 n-gram 集合 Jaccard 系数的快速相似度。
 *
 * <p>当文本较大、LCS 动态规划开销过高时使用。时间复杂度接近 O(n+m)。</p>
 */
public final class NgramSimilarity {

    /**
     * 综合二元组和三元组的 Jaccard 相似度。
     *
     * @param first  规范化后的原文
     * @param second 规范化后的抄袭文本
     * @return 0 到 1 之间的相似度
     */
    public double similarity(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("输入文本不能为 null");
        }

        double bigram = jaccard(first, second, 2);
        double trigram = jaccard(first, second, 3);
        return (bigram + trigram) / 2.0;
    }

    private static double jaccard(String first, String second, int n) {
        Set<String> firstGrams = ngrams(first, n);
        Set<String> secondGrams = ngrams(second, n);

        if (firstGrams.isEmpty() && secondGrams.isEmpty()) {
            return 1.0;
        }

        Set<String> intersection = new HashSet<>(firstGrams);
        intersection.retainAll(secondGrams);

        Set<String> union = new HashSet<>(firstGrams);
        union.addAll(secondGrams);

        return (double) intersection.size() / union.size();
    }

    private static Set<String> ngrams(String text, int n) {
        int[] codePoints = text.codePoints().toArray();
        Set<String> grams = new HashSet<>();
        for (int i = 0; i + n <= codePoints.length; i++) {
            StringBuilder gram = new StringBuilder(n);
            for (int j = 0; j < n; j++) {
                gram.appendCodePoint(codePoints[i + j]);
            }
            grams.add(gram.toString());
        }
        return grams;
    }
}
