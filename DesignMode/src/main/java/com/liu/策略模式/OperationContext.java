package com.liu.策略模式;

/**
 *  策略模式上下文类
 */
public class OperationContext {

    private Operation operation;

    public OperationContext(Operation operation){
        this.operation = operation;
    }

    public Integer execute(){
        return this.operation.getResult();
    }

}
