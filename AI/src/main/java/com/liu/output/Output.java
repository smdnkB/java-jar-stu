package com.liu.output;

import com.liu.AIMenu;

/**
 * 神经元的输出（最终行为）
 */
public class Output {

    private final AIMenu out;
    public Output(AIMenu out){
        this.out = out;
    }
    public AIMenu getOut(){
        return this.out;
    }
}
