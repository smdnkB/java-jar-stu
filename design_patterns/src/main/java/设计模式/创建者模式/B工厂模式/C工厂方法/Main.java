package 设计模式.创建者模式.B工厂模式.C工厂方法;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/28 21:10
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {

        CoffeeStory coffeeStory = new CoffeeStory();
        coffeeStory.setFactory(new CoffeeLatteFactory());

        Coffee coffee = coffeeStory.create();
        System.out.println(coffee.getName());
    }
}
