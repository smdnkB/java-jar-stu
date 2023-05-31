package 设计模式.创建者模式.D建造者模式;

import 设计模式.创建者模式.D建造者模式.builder.OFOBikeBuilder;
import 设计模式.创建者模式.D建造者模式.builder.OtherBikeBuilder;
import 设计模式.创建者模式.D建造者模式.director.Director;
import 设计模式.创建者模式.D建造者模式.entity.Bike;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/30 21:41
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {


        Director director = new Director(new OFOBikeBuilder());
//        Director director = new Director(new OtherBikeBuilder());
        Bike construct = director.construct();
        System.out.println(construct);
    }
}
