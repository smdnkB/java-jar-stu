package 软年设计原则.接口隔离原则;

/**
 * 防盗门类
 * 防盗门的功能有 防水 防盗
 * 如果需要新增功能，只要新增一个实现接口
 */
public class MySafetyDoor implements Waterproof,AntiTheft{
    @Override
    public void antiTheft() {
        System.out.println("安全门支持防盗");
    }

    @Override
    public void waterproof() {
        System.out.println("安全门支持防水");
    }
}
