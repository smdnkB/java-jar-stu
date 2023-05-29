package 设计模式.创建者模式.工厂模式.B简单工厂;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/28 20:49
 * @version: 1.0
 */
public class SimpleCoffeeFactory {
    public Coffee createCoffee(String name){
        if ("american".equals(name)) return new CoffeeAmerican();
        if ("latte".equals(name)) return new CoffeeLatte();
        throw new RuntimeException("咖啡不存在");
    }

}
