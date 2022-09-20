package com.huihui.main;

import com.huihui.utils.FileIO;
import com.huihui.utils.Hamming;
import com.huihui.utils.SimHash;

import java.math.BigDecimal;

/**
 * @author Jstarry
 * @date 2022/9/18 20:47
 **/
public class MainTest {
    public static void main(String[] args) {
        System.out.println();
        // 根据文件路径名逐行读取对应文件，并且将文件内容转换成字符串
        System.out.println("原文件的存放路径："+args[0]);
        System.out.println("抄袭文件的存放路径："+args[1]);
        String str0 = FileIO.readTxt(args[0]);
        String str1 = FileIO.readTxt(args[1]);
        String resultFileName = args[2];

        // 由字符串得出对应的 simHash值
        String simHash0 = SimHash.getSimHash(str0);
        String simHash1 = SimHash.getSimHash(str1);
        System.out.println();
        System.out.println("原文件的simHash值："+simHash0);
        System.out.println("抄袭文件的simHash值："+simHash1);

        // 由 simHash值求出相似度
        //注意最终的结果保留到小数点后两位
        double similarity = Hamming.getSimilarity(simHash0, simHash1);
        similarity = (double) Math.round(similarity * 100) / 100;
        System.out.println("原文和抄袭文件的相似度："+similarity);

        // 把相似度写入最后的结果文件中
        FileIO.writeTxt(similarity, resultFileName);
        System.out.println("已成功将相似度写进结果文件:"+args[2]);

        // 退出程序
        System.exit(0);
    }
}