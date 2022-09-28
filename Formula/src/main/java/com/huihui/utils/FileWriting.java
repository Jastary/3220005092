package com.huihui.utils;

/**
 * @author Jstarry
 * @date 2022/9/27 15:38
 **/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class FileWriting {
    //题目
    private static String filePath = "D:\\Formula\\src\\main\\resources\\Exercises.txt";
    //答案
    private static String filePath2 = "D:\\Formula\\src\\main\\resources\\Answers.txt";
    //用户作答的答案
    private static String filePath3 = "D:\\Formula\\src\\main\\resources\\Test.txt" ;
    //成绩
    private static String filePath4 = "D:\\Formula\\src\\main\\resources\\Grade.txt";

    //不存在给出来的文件就要新建一个文件
    public static boolean createFile(File fileName) throws Exception {
        try {
            if (!fileName.exists())
                fileName.createNewFile();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //读取文件内容
    public static String readTxtFile(File file) {
        String result = "";
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader br = new BufferedReader(reader);
            String s = null;
            while ((s = br.readLine()) != null) {
                result = result + s;
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //向文件写入内容
    public static boolean writeTxtFile(String content, File fileName) throws Exception {
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes("gbk"));
            fileOutputStream.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //根据文件路径，向文件写入内容
    public static void fileChaseFW(String filePath, String content) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(content + "\r\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("文件写入失败！" + e);
        }
    }

    //向题目文件写入题目
    public static void writer(String args) throws Exception {
        File file = new File(filePath);
        createFile(file);
        fileChaseFW(filePath, args);
    }

    //向答案文件写入答案
    public static void writer1(String args) throws Exception {
        File file = new File(filePath2);
        createFile(file);
        fileChaseFW(filePath2, args);
    }

    //向答题卡文件写入
    public static void writer2(String args) throws Exception {
        File file = new File(filePath3);
        createFile(file);
        fileChaseFW(filePath3, args);
    }

    //向成绩文件写入批改情况
    public static void writer3(String args) throws Exception {
        File file = new File(filePath4);
        createFile(file);
        fileChaseFW(filePath4, args);
    }

    //清空文件中原有的内容
    public static void fileClear() throws Exception {
        String s = "";
        File file = new File(filePath);
        writeTxtFile(s, file);
        File file1 = new File(filePath2);
        writeTxtFile(s, file1);
        File file2 = new File(filePath3);
        writeTxtFile(s, file2);
        File file3 = new File(filePath4);
        writeTxtFile(s, file3);
    }
}