package com.liu.neure;

import com.liu.AIMenu;
import com.liu.in.In;
import com.liu.input.Input;
import com.liu.output.Output;

/**
 * 单个神经元
 */
public class Neure {

    private static final int meditateLayer = 2; // 冷静层级

    /**
     * 外界原始输入
     * 处理后返回 三情况输入
     */
    public Input inputZ(String in){
        Output output;
        if (In.activeMenu.contains(in)){
            output = input(new Input(AIMenu.active));
        }
        if (In.inactiveMenu.contains(in)){
            input(new Input(AIMenu.inactive));
        }
        if (In.meditate.contains(in)){
            input(new Input(AIMenu.meditate));
        }

        return null;
    }

    /**
     * 外界刺激
     * @return 对刺激的反应
     */
    public Output input(Input in){
        AIMenu in1 = in.getIn();
        // 积极的刺激
        if (in1 == AIMenu.active){
            for (int i = 0; i < meditateLayer; i++) {
                Output m = meditate(in);
            }

        }

        // 消极的刺激
        if (in1 == AIMenu.inactive){
            for (int i = 0; i < meditateLayer; i++) {
                meditate(in);
            }
        }

        // 正常刺激
        if (in1 == AIMenu.meditate){
            for (int i = 0; i < meditateLayer; i++) {
                meditate(in);
            }
        }

        return null;
    }

    /**
     * 生命活动和基本需求
     */
    private void eat(){}

    /**
     * 智能行为
     */
    /**
     * 行动
     */
    private void active(){}

    /**
     * 退缩
     */
    private Output meditate(Input in){
        // todo 这里应该有一个经验仓库，每次思考都去比对之前的经验
        return null;
    }

    /**
     * 思考
     */
    private void inactive(){}
}
