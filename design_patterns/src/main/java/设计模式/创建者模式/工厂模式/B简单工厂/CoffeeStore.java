package 设计模式.创建者模式.工厂模式.B简单工厂;

/**
 * @author: liu long fei
 * @description: 咖啡抽象类
 * @date: 2023/5/28 20:43
 * @version: 1.0
 */
public class CoffeeStore {

    public Coffee createCoffee(String name){

        SimpleCoffeeFactory simpleCoffeeFactory = new SimpleCoffeeFactory();
        return simpleCoffeeFactory.createCoffee(name);
    }
}
