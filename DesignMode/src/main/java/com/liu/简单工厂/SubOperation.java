package com.liu.简单工厂;

/**
 * 减法实现
 */
public class SubOperation extends Operation {

    public SubOperation(Integer a, Integer b) {
        super(a, b);
    }

    @Override
    public Integer getResult() {
        return this.getA()-this.getB();
    }
}
