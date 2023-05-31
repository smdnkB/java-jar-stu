package 设计模式.创建者模式.D建造者模式.director;

import 设计模式.创建者模式.D建造者模式.builder.Builder;
import 设计模式.创建者模式.D建造者模式.entity.Bike;

/**
 * @author: liu long fei
 * @description: 装配者
 * @date: 2023/5/30 21:32
 * @version: 1.0
 */
public class Director {
    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public Bike construct(){
        builder.buildFrame();
        builder.buildSeat();
        return builder.createBike();
    }
}
