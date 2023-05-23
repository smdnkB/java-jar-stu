package com.liu.thread;

public class MyThread extends Thread{

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

//        this.getName();// 线程名称
//        this.getId(); // 线程 id
//        this.getPriority();// 优先级
//        this.interrupt(); // 中断 (线程处于sleep  wait 阻塞状态时 可通过中断唤醒)
//        Thread.interrupted();// 判断线程是否被中断 判断后清除中断标记
//        this.isInterrupted(); // 判断线程是否被中断 判断后不会清除中断标记
//        Thread.currentThread();// 返回实际执行这句代码的线程对象
//        this.setUncaughtExceptionHandler(null); // 线程出现异常时，此方法用于建立异常控制器

        System.out.println("Thread complete!");
    }
}
