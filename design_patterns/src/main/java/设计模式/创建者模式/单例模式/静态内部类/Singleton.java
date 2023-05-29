package 设计模式.创建者模式.单例模式.静态内部类;

/**
 * @author: liu long fei
 * @description: 懒汉  静态内部类  开源项目中比较常见，保证线程安全的同时不浪费性能和内存
 * @date: 2023/5/28 11:31
 * @version: 1.0
 */
public class Singleton {

    private Singleton(){}

    // 静态内部类，只会初始化一次，且是被调用时初始化。所以能保证线程安全，且使用时才加载
    private static class SingletonHolder{
        private static final Singleton SINGLETON = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.SINGLETON;
    }
}
