package com.liu.简单工厂;

/**
 *  计算工厂  , 构造具体的计算类
 */
public class OperationFactory {
    private OperationFactory(){}

    public static Operation getOperation(String str,Integer a,Integer b){
        switch (str){
            case "+":{
                return new AddOperation(a,b);
            }case "-":{
                return new SubOperation(a,b);
            }
            default:{
                return null;
            }
        }
    }
}
