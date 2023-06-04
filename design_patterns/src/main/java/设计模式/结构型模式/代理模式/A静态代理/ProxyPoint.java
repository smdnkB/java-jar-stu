package 设计模式.结构型模式.代理模式.A静态代理;

/**
 * @author: liu long fei
 * @description: 静态代理
 * @date: 2023/6/4 14:48
 * @version: 1.0
 */
public class ProxyPoint {

    private SellTickets sellTickets;

    public ProxyPoint(SellTickets sellTickets){
        this.sellTickets =sellTickets;
    }

    public void sell(){
        System.out.println("代理增强");
        sellTickets.sell();
    }
}
