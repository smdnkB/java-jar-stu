package com.liu.waitnotify;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * 消费者
 */
public class Consumer implements Callable<Boolean> {

    private final ArrayList<String> dataBase;
    private final ArrayList<String> dataBaseBak;

    public Consumer(ArrayList<String> dataBase,ArrayList<String> dataBaseBak){
        this.dataBase = dataBase;
        this.dataBaseBak = dataBaseBak;
    }
    @Override
    public Boolean call() throws Exception {

        while (true) synchronized (dataBase){
            System.out.println("消费者");

            if (dataBase.size()<=0){
                dataBase.notify(); // wait和notify 必须要在同步块里面调用才有效
                dataBase.wait(); // 自己休眠之前一定要先唤醒别人
            }
            if (dataBase.size()>0){
                dataBase.remove(dataBase.size()-1);
                System.out.println("消费者消费----数据库剩余："+dataBase.size());
            }

            Thread.sleep(1000);
        }
    }
}
