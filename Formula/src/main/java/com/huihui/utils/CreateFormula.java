package com.huihui.utils;

/**
 * @author Jstarry
 * @date 2022/9/27 15:38
 **/
import java.util.Random;

public class CreateFormula {// 生成算式
    //定义运算符号
    private static char[] operate = { '+', '-', '*', '÷' };

    //生成操作符和数字，考虑是否需要添加括号，生成最终的表达式
    public String Creating(int range){
        String subject = "";
        //随机生成操作符的个数
        Random r = new Random();
        int  operator = r.nextInt(3)+1;//操作符个数
        int kuohao=0;
        //标志
        boolean flag=true;
        for(int i = 1; i<=operator; i++) {
            //随机生成1个数字，决定是否添加括号
            Random rand=new Random();
            int temp=rand.nextInt(3)+1;
            //添加左括号
            if(temp==1){
                subject+="(";
                kuohao++;
                //数字
                subject+=this.getNum(range);
                //操作符
                subject += this.getOperate();
            }
            //添加左括号
            else if (temp == 2 && flag) {
                flag=false;
                subject="("+subject;
                kuohao++;
                subject+=this.getNum(range);
                subject += this.getOperate();
            }
            //添加右括号
            else if(temp==3&& kuohao!=0){
                subject+=this.getNum(range);
                subject+=")";
                //右括号添加进去之后就和前面的左括号匹配，未匹配的--
                kuohao--;
                subject += this.getOperate();
            }
            //其余情况不添加
            else{
                subject+=this.getNum(range);
                subject+= this.getOperate();
            }
        }
        subject+=this.getNum(range);

        //最后检验是否还有未匹配的左括号，有则生成对应数量的右括号
        for(int i=kuohao;i>0;i--) {
            subject=subject+")";
        }
        return subject;
    }

    //随机获得数字
    private String getNum(int num) {
        String str = "";
        int fz=0;//分子
        int fm=1;//分母，分母的初始不能为0
        int zs=0;//带分数左边的整数
        //随机生成
        Random rand = new Random();
        int type=rand.nextInt(4);

        //带分数
        if(type==0){
            fm= (rand.nextInt(num)) + 1 ;
            fz=(rand.nextInt(fm));
            if(fz==0) {
                fz=fz+1;
            }
            zs=(rand.nextInt(fm))+1;
            //注意写成带分数的形式
            str=zs+"'"+fz+"/"+fm+"";
        }
        //分数
        else if(type==1){
            fm= (rand.nextInt(num)) + 1 ;
            fz=(rand.nextInt(fm));
            if(fz==0) {
                fz=fz+1;
            }
            str=fz+"/"+fm+"";
        }
        //自然数
        else{
            str = ((int) (rand.nextInt(num)) + 1) + "";
        }
        return str;
    }

    //随机生成操作符
    private  String getOperate() {
        Random rand = new Random();
        int temp = rand.nextInt(4);
        String str=operate[temp]+"";
        return str;
    }
}