package 设计模式.创建者模式.单例模式.demo1;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/28 11:05
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {

        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();

        System.out.println(instance == instance1);

    }
}
