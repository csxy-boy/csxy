package com.papercheck.core;

/**
 * 基于最长公共子序列（LCS）的字符级相似度。
 *
 * <p>相似度采用 Dice 系数：2 * LCS长度 / (文本A长度 + 文本B长度)。</p>
 *
 * <p>动态规划使用滚动数组，空间复杂度从 O(n*m) 降到 O(min(n,m))。</p>
 */
public final class LcsSimilarity {

    /**
     * 计算两个规范化文本的相似度。
     *
     * @param first  规范化后的原文
     * @param second 规范化后的抄袭文本
     * @return 0 到 1 之间的相似度
     */
    public double similarity(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("输入文本不能为 null");
        }
        if (first.equals(second)) {
            return 1.0;
        }

        int[] firstPoints = first.codePoints().toArray();
        int[] secondPoints = second.codePoints().toArray();
        if (firstPoints.length == 0 || secondPoints.length == 0) {
            return 0.0;
        }

        int lcsLength = longestCommonSubsequenceLength(firstPoints, secondPoints);
        return 2.0 * lcsLength / (firstPoints.length + secondPoints.length);
    }

    private static int longestCommonSubsequenceLength(int[] first, int[] second) {
        int[] shorter = first.length <= second.length ? first : second;
        int[] longer = first.length <= second.length ? second : first;
        int columns = shorter.length;

        int[] previous = new int[columns + 1];
        int[] current = new int[columns + 1];

        for (int longerPoint : longer) {
            for (int j = 1; j <= columns; j++) {
                if (longerPoint == shorter[j - 1]) {
                    current[j] = previous[j - 1] + 1;
                } else {
                    current[j] = Math.max(previous[j], current[j - 1]);
                }
            }

            int[] temp = previous;
            previous = current;
            current = temp;
        }

        return previous[columns];
    }
}
