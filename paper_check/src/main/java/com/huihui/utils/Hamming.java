package com.huihui.utils;

/**
 * @author Jstarry
 * @date 2022/9/18 20:48
 **/
public class Hamming {
    /**
     * 输入两个simHash值，计算它们的海明距离
     */
    public static int getHammingDistance(String simHash1, String simHash2) {
        int distance = 0;
        if (simHash1.length() != simHash2.length()) {
            // 如果两个simHash值的长度不相等，表明出错，返回-1
            distance = -1;
        } else {
            for (int i = 0; i < simHash1.length(); i++) {
                // 将两个simHash的值按位进行比较
                if (simHash1.charAt(i) != simHash2.charAt(i)) {
                    //按位比较中，若当前位的值不相等，距离+1
                    distance++;
                }
            }
        }
        /**
         * 降维之后通过按位比较的形式得到海明距离
         * 得到海明距离之后调用getSimilarity方法得到相似度并且返回
         */
        return distance;
    }

    /**
     * 输入两个simHash值，输出相似度
     */
    public static double getSimilarity(String simHash1, String simHash2) {
        // 通过 simHash1 和 simHash2 获得它们的海明距离
        int distance = getHammingDistance(simHash1, simHash2);
        // 通过海明距离计算出相似度，并返回（结果精确到小数点后2位）
        float similarity=100-distance*100/64;
        return  0.01*similarity;
    }
}