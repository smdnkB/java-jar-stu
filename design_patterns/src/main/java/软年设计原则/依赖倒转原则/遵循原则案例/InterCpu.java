package 软年设计原则.依赖倒转原则.遵循原则案例;

public class InterCpu extends Cpu{
    @Override
    public void work() {
        System.out.println("inter cpu working...");
    }
}
