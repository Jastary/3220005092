package com.huihui.utils;

/**
 * @author Jstarry
 * @date 2022/9/27 15:37
 **/
import java.util.Stack;

public class Check {
    static String str="";
    public static String chaChong(String s){
        str=Calculate.generate(s) ;
        //后缀表达式转换为二叉表达式树
        suffixExpressionTree(s);
        return str;
    }
    //结点
    static class Node {
        String value;
        Node leftchild;
        Node rightchild;
        Node(String value) {
            this.value = value;
            leftchild = null;
            rightchild = null;
        }
    }

    /**
     * 后缀表达式转二叉表达式树
     */
    public static void suffixExpressionTree(String suffixStr) {
        String[] chs=suffixStr.split(" ");
        // 用于临时存储节点的栈
        Stack<Node> stack=new Stack<Node>();
        // 遍历所有字符，不是运算符的入栈，是运算符的，将栈中两个节点取出，合成一颗树然后入栈
        for(int i=0;i<chs.length;i++) {
            String c=chs[i]+"";
            if(c.equals(" "))
                continue;
            //是运算符
            if(isOperator(c)) {
                if(stack.isEmpty()||stack.size()<2) {
                    System.err.println("输入的后缀表达式不正确");
                    return;
                }

                else if(c.equals("+")||c.equals("*")){
                    Node root=new Node(c);
                    Node a=stack.pop();
                    Node b=stack.pop();

                    //都是运算符
                    if(isOperator(a.value)&&isOperator(b.value)) {
                        //比较运算符的优先级：先乘除后加减
                        if(Calculate.opValue(a.value)>Calculate.opValue(b.value)) {
                            root.leftchild=a;
                            root.rightchild=b;
                        }

                        else if(Calculate.opValue(a.value)<Calculate.opValue(b.value)) {
                            root.leftchild=b;
                            root.rightchild=a;
                        }

                        //运算符同级的情况
                        else {
                            //a>b
                            if(compare(a.leftchild.value,b.leftchild.value)==1) {
                                root.leftchild=a;
                                root.rightchild=b;
                            }

                            //a<b
                            else if(compare(a.leftchild.value,b.leftchild.value)==-1) {
                                root.leftchild=b;
                                root.rightchild=a;
                            }

                            //a=b
                            else {
                                //a>b
                                if(compare(a.rightchild.value,b.rightchild.value)==1) {
                                    root.leftchild=a;
                                    root.rightchild=b;
                                }
                                //a<b
                                else if(compare(a.rightchild.value,b.rightchild.value)==-1) {
                                    root.leftchild=b;
                                    root.rightchild=a;
                                }
                                //a=b
                                else {
                                    root.leftchild=a;
                                    root.rightchild=b;
                                }
                            }
                        }
                    }

                    //左边运算符 右边非运算符
                    else if(isOperator(a.value)&&(!isOperator(b.value))) {
                        root.leftchild=a;
                        root.rightchild=b;
                    }

                    //左边非运算符 右边运算符
                    else if((!isOperator(a.value))&&isOperator(b.value)) {
                        root.leftchild=b;
                        root.rightchild=a;
                    }

                    else{
                        if(compare(a.value,b.value)==1||compare(a.value,b.value)==0) {
                            root.leftchild=a;
                            root.rightchild=b;
                        }
                        else if(compare(a.value,b.value)==-1) {
                            root.leftchild=b;
                            root.rightchild=a;
                        }
                    }
                    stack.push(root);
                }

                else {
                    Node root=new Node(c);
                    root.leftchild=stack.pop();
                    root.rightchild=stack.pop();
                    stack.push(root);
                }
            }

            else {
                Node root=new Node(c);
                stack.push(root);
            }
        }

        if(stack.isEmpty()||stack.size()>1) {
            System.err.println("输入的后缀表达式不正确");
            return;
        }
        PostOrderTravels(stack.pop());
    }


    //判断是否为操作运算符，是就返回true不是就返回false
    public static boolean isOperator(String s) {
        if (s.equals("+")) {
            return true;
        } else if (s.equals("-")) {
            return true;
        } else if (s.equals("*")) {
            return true;
        } else if (s.equals("÷")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 访问节点：将节点的值取出来并打印。
     *  node：需访问的节点。
     */
    private static void visit(Node node) {
        //当前结点为空时返回
        if (node == null) {
            return;
        }
        String value = node.value;
        str = str + value + " ";
    }


    /**
     * 从指定节点作为根节点开始递归对树进行后序遍历。
     * node：指定节点。
     */
    private static void PostOrderTravels(Node node) {
        if (node == null) {
            return;
        } else {
            PostOrderTravels(node.leftchild);
            PostOrderTravels(node.rightchild);
            visit(node);
        }
    }


    //比较两个分数的大小
    public static int compare(String a,String b) {
        int fenmu;
        int fenzi;
        // 全部转换为分子分母的形式
        fenshu f1 = new fenshu();
        f1 = Calculate.OperandChange(a);
        fenshu f2 = new fenshu();
        f2 = Calculate.OperandChange(b);
        int fenmu1 = f1.getFenmu();
        int fenzi1 = f1.getFenzi();
        int fenmu2 = f2.getFenmu();
        int fenzi2 = f2.getFenzi();
        //得到2个分母的最小公倍数
        int LCM = Calculate.getLCM(fenmu1, fenmu2);
        fenzi1 = LCM * fenzi1 / fenmu1;
        fenzi2 = LCM * fenzi2 / fenmu2;
        //通分之后，通过比较分子的大小来比较判断原本数值大小
        if(fenzi1>fenzi2)
            return 1;
        else if(fenzi1<fenzi2)
            return -1;
        else
            return 0;
    }
}
