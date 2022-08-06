package com.liu.pro.forkjoinpool;

import java.util.concurrent.RecursiveTask;

// 这是一个具体任务
public class MyTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD = 49;
    private int start;
    private int end;

    public MyTask(int start, int end) {
        this.start = start; //0
        this.end = end; //50
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            int result = 0;
            for (int i = start; i <= end; i++) {
                result += i;
            }
            return result;
        } else {
            int middle = (start + end) / 2;
            MyTask firstTask = new MyTask(start, middle);
            MyTask secondTask = new MyTask(middle + 1, end);
            invokeAll(firstTask,secondTask);
            return firstTask.join() + secondTask.join();
        }
    }
}
