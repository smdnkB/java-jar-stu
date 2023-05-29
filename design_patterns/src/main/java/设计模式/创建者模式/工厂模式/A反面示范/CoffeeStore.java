package 设计模式.创建者模式.工厂模式.A反面示范;

/**
 * @author: liu long fei
 * @description: 咖啡抽象类
 * @date: 2023/5/28 20:43
 * @version: 1.0
 */
public class CoffeeStore {

    public Coffee createCoffee(String name){
        if ("american".equals(name)) return new CoffeeAmerican();
        if ("latte".equals(name)) return new CoffeeLatte();
        throw new RuntimeException("咖啡不存在");
    }
}
