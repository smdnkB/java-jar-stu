package 设计模式.结构型模式.适配器模式;

import 设计模式.结构型模式.适配器模式.sd.SDCard;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/5 21:12
 * @version: 1.0
 */
public class Computer {

    private SDCard sdCard;

    public Computer(SDCard sdCard){
        this.sdCard = sdCard;
    }

    public void read(){
        sdCard.read();
    }
}
