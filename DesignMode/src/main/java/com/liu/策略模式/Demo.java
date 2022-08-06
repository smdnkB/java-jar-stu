package com.liu.策略模式;

public class Demo {
    public static void main(String[] args) {
        OperationContext operationContext = new OperationContext(new AddOperation(1, 2));
        Integer execute = operationContext.execute();
        System.out.println(execute);
    }
}
