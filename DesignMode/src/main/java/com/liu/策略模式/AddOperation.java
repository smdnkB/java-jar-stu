package com.liu.策略模式;

/**
 * 加法计算的实现类
 */
public class AddOperation extends Operation {
    public AddOperation(Integer a, Integer b) {
        super(a, b);
    }

    /**
     * 加法类的算法实现
     * @return
     */
    @Override
    public Integer getResult() {
        return this.getA()+this.getB();
    }
}
