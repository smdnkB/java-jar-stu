package 设计模式.创建者模式.单例模式.demo1;

/**
 * @author: liu long fei
 * @description: 单例模式-饿汉
 * @date: 2023/5/28 10:56
 * @version: 1.0
 */
public class Singleton {

    // 私有构造方法
    private Singleton(){}
    // 创建一个对象
    private static Singleton instance  = new Singleton();
    // 提供获取对象的方法
    public static Singleton getInstance(){
        return instance;
    }
}
