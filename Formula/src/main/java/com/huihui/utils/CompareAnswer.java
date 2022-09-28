package com.huihui.utils;

/**
 * @author Jstarry
 * @date 2022/9/27 15:37
 **/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CompareAnswer {
    //答案&用户答题卡的存放路径
    private static String filePath2 = "D:\\Formula\\src\\main\\resources\\Answers.txt";
    private static String filePath3 = "D:\\Formula\\src\\main\\resources\\Test.txt";

    //统计正确和错误的数目
    public static void compare() throws Exception {
        String right = "";
        String error = "";
        int correct=0;
        int wrong=0;
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath2), "gbk");
        BufferedReader br = new BufferedReader(reader);
        String s = null;

        InputStreamReader reader1 = new InputStreamReader(new FileInputStream(filePath3), "gbk");
        BufferedReader br1 = new BufferedReader(reader1);
        String s1 = null;
        int i = 0;
        right = "";
        error = "";
        //答案和答题卡当前行数不为空时：可以进行比较
        while (((s = br.readLine()) != null) && (s1 = br1.readLine()) != null) {
            i++;
            if (s.trim().equals(s1.trim())) {
                correct++;
                right = right + i + ",";
            } else {
                wrong++;
                error = error + i + ",";
            }
        }

        //正确的信息
        String str1 = "Correct:" + correct;
        if (right.length() <= 1)
            str1 = str1 + "(" + ")";
        else
            str1 = str1 + "(" + right.substring(0, right.length() - 1)+ ")";

        //错误的信息
        String str2 = "Wrong:" + wrong;
        if (error.length() <= 1)
            str2 = str2 + "(" + ")";
        else
            str2 = str2 + "(" + error.substring(0, error.length() - 1)+ ")";

//        FileWriting.fileClear();
        //向Grade.txt中写入正确和错误的信息
        FileWriting.writer3(str1);
        FileWriting.writer3(str2);
    }
}
