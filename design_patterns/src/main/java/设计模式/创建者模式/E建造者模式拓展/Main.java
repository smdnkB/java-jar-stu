package 设计模式.创建者模式.E建造者模式拓展;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/31 20:40
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {

        // 使用链式构造优化后，每一个属性都可以精准控制
        // 当一个类的属性过多时，可以使用链式构造
        After liu = new After.Builder()
                .age("20")
                .name("liu")
                .build();

        System.out.println(liu);
    }
}
