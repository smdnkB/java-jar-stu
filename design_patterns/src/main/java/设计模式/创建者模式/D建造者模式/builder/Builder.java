package 设计模式.创建者模式.D建造者模式.builder;

import 设计模式.创建者模式.D建造者模式.entity.Bike;

/**
 * 自行车 构建者接口
 */
public abstract class Builder {

    protected Bike bike = new Bike();

    // 构建车架
    public abstract void buildFrame();
    // 构建车座
    public abstract void buildSeat();

    public Bike createBike(){
        return bike;
    }
}
