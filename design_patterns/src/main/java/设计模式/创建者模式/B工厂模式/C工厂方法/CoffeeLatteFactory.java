package 设计模式.创建者模式.B工厂模式.C工厂方法;

/**
 * @author: liu long fei
 * @description: 拿铁咖啡工厂对应的用来创建拿铁咖啡
 * @date: 2023/5/28 21:01
 * @version: 1.0
 */
public class CoffeeLatteFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new CoffeeLatte();
    }
}
