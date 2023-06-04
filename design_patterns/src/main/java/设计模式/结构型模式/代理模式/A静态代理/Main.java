package 设计模式.结构型模式.代理模式.A静态代理;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/4 14:51
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {

        ProxyPoint proxyPoint = new ProxyPoint(new TrainStation());
        proxyPoint.sell();
    }
}
