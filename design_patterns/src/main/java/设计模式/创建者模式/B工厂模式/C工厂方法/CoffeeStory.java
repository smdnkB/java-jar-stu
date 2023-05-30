package 设计模式.创建者模式.B工厂模式.C工厂方法;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/28 21:02
 * @version: 1.0
 */
public class CoffeeStory {

    private CoffeeFactory factory;

    public void setFactory(CoffeeFactory factory){
        this.factory = factory;
    }

    public Coffee create(){
        if (factory == null) throw new RuntimeException("");
        return factory.createCoffee();
    }
}
