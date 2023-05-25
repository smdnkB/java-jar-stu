package 软年设计原则.依赖倒转原则.遵循原则案例;

public class AmdCpu extends Cpu{
    @Override
    public void work() {
        System.out.println("AMD cpu working...");
    }
}
