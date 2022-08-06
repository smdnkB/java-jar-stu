package com.liu.简单工厂;

public class Demo {
    public static void main(String[] args) {

        Operation operation = OperationFactory.getOperation("+",1,2);
        Integer result = operation.getResult();
        System.out.println(result); // 3


        operation = OperationFactory.getOperation("-",2,1);
        result = operation.getResult();
        System.out.println(result); // 3
    }
}
