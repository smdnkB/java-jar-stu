package 设计模式.创建者模式.B工厂模式.C工厂方法;

/**
 * 工厂方法模式的核心
 * 不同类型的咖啡工厂 抽出一个接口层用来解耦。
 *
 */
public interface CoffeeFactory {
    Coffee createCoffee();
}
