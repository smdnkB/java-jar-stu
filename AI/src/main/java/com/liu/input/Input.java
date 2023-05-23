package com.liu.input;

import com.liu.AIMenu;

/**
 * 对神经元的输入
 * 从神经元的角度输入分两种  积极的输入  消极的输入  正常的输入
 * 具体是哪种输入 需要神经元自己判断
 */
public class Input {
    private final AIMenu in;
    public Input(AIMenu in){
        this.in = in;
    }
    public AIMenu getIn(){
        return this.in;
    }
}
