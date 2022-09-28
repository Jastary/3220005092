package com.huihui.main;

/**
 * @author Jstarry
 * @date 2022/9/27 15:36
 **/
import com.huihui.utils.CompareAnswer;
import com.huihui.utils.Exercise;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {
    public static void main(String[] args) throws Exception {
        System.out.println("*********************************小学四则运算自动生成系统*********************************");
        System.out.print("请输入生成的题目数量:");
        String input1 = input();
        //判断用户输入题目的数量是否为整数
        while (!isInt(input1)) {
            System.out.println("输入错误，请重新输入！");
            input1 = input();
        }

        System.out.print("请输入题目的数值范围:");
        String input2 = input();
        //判断用户输入的数值范围是否为整数
        while (!isInt(input2)) {
            System.out.println("输入错误，请重新输入！");
            input2 = input();
        }

        Exercise exam = new Exercise();
        //传入题目数量以及数值范围生成题目
        exam.Exam(Integer.parseInt(input1), Integer.parseInt(input2));
        System.out.println("出题完毕，请前往Test.txt中作答");
        System.out.println("如需批改请按‘1’，任意键退出");

        //接收用户的输入，判断是否需要批改
        String input3 = input();
        if (input3.equals("1")){
            //将用户的答题卡和答案对比
            CompareAnswer.compare();
            System.out.println("批改已完成，请到Grade.txt查看");
        }
        else
            System.exit(0);
    }

    //检验是否为整数
    public static boolean isInt(String string) {
        if (string == null) {
            return false;
        }
        //正则表达式
        String regEx1 = "[\\-|\\+]?\\d+";

        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(string);
        return m.matches();
    }

    //接收用户输入
    public static String input() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }
}