package 软年设计原则.迪米特法则;

public class Main {

    public static void main(String[] args) {


        Star 林青霞 = new Star("林青霞");
        Company 可口可乐 = new Company("可口可乐");
        Fans 粉丝A = new Fans("粉丝A");

        Agent agent = new Agent(林青霞,粉丝A,可口可乐);

        agent.meeting();
    }
}
