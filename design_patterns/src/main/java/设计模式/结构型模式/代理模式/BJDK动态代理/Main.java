package 设计模式.结构型模式.代理模式.BJDK动态代理;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/4 15:03
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        Eat proxyObject = proxyFactory.getProxyObject();
        proxyObject.eat();
    }
}
