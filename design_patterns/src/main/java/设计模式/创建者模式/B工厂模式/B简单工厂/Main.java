package 设计模式.创建者模式.B工厂模式.B简单工厂;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/28 20:46
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {

        CoffeeStore coffeeStore = new CoffeeStore();
        Coffee latte = coffeeStore.createCoffee("latte");
        latte.addSuger();
        System.out.println(latte.getName());
    }
}
