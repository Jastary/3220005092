package com.huihui.utils;

import java.io.*;

/**
 * @author Jstarry
 * @date 2022/9/18 20:50
 **/
public class FileIO {
    /**
     * 逐行读取文件，传入文件的绝对路径，将文件内容转化为字符串的形式
     * 传入：文件的路径
     * 返回：String内容
     */
    public static String readTxt(String txtPath) {
        String str = "";
        String strLine;
        // 将文件按行读入 str中
        File file = new File(txtPath);
        FileInputStream fileInputStream = null;
        //抛出异常
        try {
            fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            // 对文件内容进行字符串拼接
            while ((strLine = bufferedReader.readLine()) != null) {
                str += strLine;
            }
            // 关闭资源
            inputStreamReader.close();
            bufferedReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 向文件中写入内容
     * 传入：传入的内容
     * 返回：文件路径
     */
    public static void writeTxt(double txtElem,String txtPath){
        String str = Double.toString(txtElem);
        File file = new File(txtPath);
        FileWriter fileWriter = null;
        //抛出异常
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(str, 0, (str.length() > 3 ? 4 : str.length()));
            fileWriter.write("\r\n");
            // 关闭资源
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
