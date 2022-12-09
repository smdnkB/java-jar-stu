package com.liu.IO;

/**
 * 格式化输入输出工具类
 */
public class PrintIO {

    /**
     * 自测用的方法
     * @param args
     */
        private static void main(String[] args) {

            String format = getFormat("red",31,0);
            System.out.println(format);

        }

    /**
     * 使控制台输出内容有颜色
     */
        public static String getFormat(String content,int color,int type){
            boolean hasType = type!=1 && type !=3 && type != 4;
            String format = "";
            if (hasType){
                format = String.format("\033[%dm%s\033[0m",color,content);

            }else {
                format = String.format("\033[%d;%dm%s\033[0m",color,type,content);
            }

            return format;
        }




}
