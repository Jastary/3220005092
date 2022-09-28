package com.huihui.utils;

/**
 * @author Jstarry
 * @date 2022/9/27 15:39
 **/

public class Exercise {
    static String str="";
    public void Exam(int count,int range) throws Exception {
        int re=0;
        String[] strr = new String[count+1];
        String[] strr2 = new String[count+1];
        String[] repeat =new String[count+1];
        FileWriting.fileClear();
        //题目
        String subject;
        for(int i=0;i<count;i++) {
            CreateFormula create=new CreateFormula();
            //根据传入的数值范围生成最终的表达式
            subject=create.Creating(range);
            //去掉多余括号
            subject=deleteKuoHao(subject);
            //检查生成的题目是否存在重复
            str=Check.chaChong(subject);

            strr[i]=str;
            strr2[i]=subject;
            //标志
            int xy=1;
            for(int h=1;h<i;h++){
                if(strr[h].equals(str)) {
                    xy=0;
                    repeat[re]=h+","+strr2[h]+"  Repeat  "+i+","+subject;
                    re++;
                    break;
                }
            }
            if(xy==0) {
                i--;
                continue;
            }
            //没有重复就直接写入文档
            else {
                String result=Calculate.getResult(subject);
                FileWriting.writer("第"+(i+1)+"题: "+subject+"=");
                FileWriting.writer1("第"+(i+1)+"题: "+subject+"="+result);
                FileWriting.writer2("第"+(i+1)+"题: "+subject+"=");
                System.out.print("第"+(i+1)+"题: "+subject+"=\n");
            }
        }
    }

    //特殊情况
    //去除部分题目开头和结尾为括号的表达式
    static String deleteKuoHao(String str) {
        int i=0,j=0;
        String temp;
        if (str.substring(0, 1).equals("(") && str.substring(str.length() - 1, str.length()).equals(")")) {
            temp = str.substring(1, str.length() - 1);
            i=temp.indexOf(")");
            j=temp.indexOf("(");
            if(i>=j){
                str=temp;
            }
        }
        return str;
    }
}