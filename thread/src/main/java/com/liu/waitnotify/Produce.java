package com.liu.waitnotify;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * 生产者
 */
public class Produce implements Callable<Boolean> {

    private final ArrayList<String> dataBase;
    private final ArrayList<String> dataBaseBak;

    public Produce(ArrayList<String> dataBase, ArrayList<String> dataBaseBak){
        this.dataBase = dataBase;
        this.dataBaseBak = dataBaseBak;
    }

    @Override
    public Boolean call() throws Exception {

        while (true) synchronized (dataBase){
            System.out.println("开始生产："+dataBase.size());
            dataBase.add(String.valueOf( System.currentTimeMillis()));
            Thread.sleep(500);

            if (dataBase.size()>=10 && dataBaseBak.size()<=10){
                dataBaseBak.add(String.valueOf( System.currentTimeMillis()));
            }

            if (dataBase.size()>=10 && dataBaseBak.size()>=10){
                System.out.println("生产者休息-----数据库容量："+dataBase.size());
                dataBase.notify();
                dataBase.wait();
            }
        }
    }
}
