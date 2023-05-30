package 设计模式.创建者模式.A单例模式.demo3;

/**
 * @author: liu long fei
 * @description: 懒汉
 * @date: 2023/5/28 11:13
 * @version: 1.0
 */
public class Singleton {

    private Singleton(){}

    private static volatile Singleton instance; // 避免JVM对指令重排序导致 获取到null的问题

    public synchronized static Singleton getInstance(){

        if (instance == null){
            synchronized (Singleton.class){
                if  (instance == null){
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
