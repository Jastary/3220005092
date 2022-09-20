package com.huihui.utils;

import com.hankcs.hanlp.HanLP;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * @author Jstarry
 * @date 2022/9/18 20:49
 **/
public class SimHash {
    /**
     * 传入String，计算出它的hash值，并以字符串形式输出
     * 传入：文件内容转化成的String
     * 返回：String类型的hash
     */
    public static String getHash(String str){
        try{
            // 这里使用了MD5获得hash值
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
        }catch(Exception e){
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 传入String,计算出它的simHash值，并以字符串形式输出
     * 传入：String类型字符串
     * 返回：simHash值
     */
    public static String getSimHash(String str){
        // 抛出异常：当文件的文本长度太短时HanLp无法取得关键字
        try{
            if(str.length() < 20)
                throw new ShortStringException("文本过短！");
        }catch (ShortStringException e){
            e.printStackTrace();
            return null;
        }
        // 直接用数组表示特征向量,取64位
        int[] v = new int[64];

        // 1、分词（使用了外部依赖hankcs包提供的接口）
        List<String> keywordList = HanLP.extractKeyword(str, str.length());  //取出所有关键词
        // 2、hash
        int size = keywordList.size();
        int i = 0;//以i做外层循环
        for(String keyword : keywordList){
            String keywordHash = getHash(keyword);
            if (keywordHash.length() < 64) {
                // hash值可能少于64位，在低位以0补齐
                int dif = 64 - keywordHash.length();
                for (int j = 0; j < dif; j++) {
                    keywordHash += "0";
                }
            }
            // 3、加权、合并
            for (int j = 0; j < v.length; j++) {
                // 对keywordHash的每一位与'1'进行比较
                if (keywordHash.charAt(j) == '1') {
                    //权重分10级，由词频从高到低，取权重10~0
                    v[j] += (10 - (i / (size / 10)));
                } else {
                    v[j] -= (10 - (i / (size / 10)));
                }
            }
            i++;
        }
        // 4、降维
        String simHash = "";// 储存返回的simHash值
        for (int j = 0; j < v.length; j++) {
            // 从高位遍历到低位
            if (v[j] <= 0) {
                simHash += "0";
            } else {
                simHash += "1";
            }
        }
        //返回降维之后得到的simHash值
        return simHash;
    }
}