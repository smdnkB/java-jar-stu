package 设计模式.创建者模式.E建造者模式拓展;

/**
 * @author: liu long fei
 * @description: 正常代码示例 用来和优化代码对比
 * @date: 2023/5/31 20:35
 * @version: 1.0
 */
public class Before {

    private String name;
    private String age;


    public Before(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Before before = new Before("liu", "20"); // 通过构造方法创建对象，当参数很多时不利于使用，因为并不是每个参数都必须
    }
}
