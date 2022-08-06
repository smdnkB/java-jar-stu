package com.liu.Thread;

public class Mythread extends Thread{
    private String taskName;
    @Override
    public void run(){
        System.out.println(taskName+"启动");
        for (int i=0;i<10;i++){
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Mythread(String taskName) {
        this.taskName = taskName;
    }
    public Mythread() {
    }
}
