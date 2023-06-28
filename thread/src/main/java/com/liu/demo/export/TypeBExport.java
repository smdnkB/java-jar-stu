package com.liu.demo.export;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/24 10:24
 * @version: 1.0
 */
public class TypeBExport implements Runnable{
    @Override
    public void run() {

        int i = 0;
        while (i<30){
            System.out.println("任务 B :"+i);
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
