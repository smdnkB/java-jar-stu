package 设计模式.创建者模式.A单例模式.demo2;

/**
 * @author: liu long fei
 * @description: 单例模式-饿汉
 * @date: 2023/5/28 11:07
 * @version: 1.0
 */
public class Singleton {
    private Singleton(){}

    private static Singleton instance;

    static {
        instance = new Singleton();
    }

    public static Singleton getInstance(){
        return  instance;
    }
}
