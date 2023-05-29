package 设计模式.创建者模式.单例模式.枚举方式;

/**
 * @author: liu long fei
 * @description: 利用枚举的特征。此方式属于饿汉。在不考虑内存浪费的情况下，首推这种方式
 * @date: 2023/5/28 11:37
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {

        Singleton instance = Singleton.INSTANCE;
        Singleton instance1 = Singleton.INSTANCE;
    }
}
