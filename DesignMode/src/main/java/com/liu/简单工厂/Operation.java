package com.liu.简单工厂;

/**
 * 计算类的父类
 */
public abstract class Operation {
    private Integer a;
    private Integer b;

    public Operation(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }
    public Integer getA() {
        return a;
    }
    public Integer getB() {
        return b;
    }

    /**
     * 获取计算结果
     * @return
     */
    public abstract Integer getResult();
}
