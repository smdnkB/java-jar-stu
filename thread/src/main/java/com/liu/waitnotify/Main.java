package com.liu.waitnotify;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // arrayList 数据仓库 生产者存放，消费者取。还要通过这个对象加锁和唤醒
        ArrayList<String> dataBase = new ArrayList<>();
        ArrayList<String> dataBaseBak = new ArrayList<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Future<Boolean> submitProduce = executor.submit(new Produce(dataBase, dataBaseBak));
        Future<Boolean> submitConsumer = executor.submit(new Consumer(dataBase,dataBaseBak));




    }
}
