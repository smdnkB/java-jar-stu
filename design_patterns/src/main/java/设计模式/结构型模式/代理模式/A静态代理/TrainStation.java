package 设计模式.结构型模式.代理模式.A静态代理;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/4 14:47
 * @version: 1.0
 */
public class TrainStation implements SellTickets{
    @Override
    public void sell() {
        System.out.println("火车站卖票");
    }
}
