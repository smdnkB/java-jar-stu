package 软年设计原则.依赖倒转原则.不遵循原则案例;

public class Computer {

    private Cpu cpu;
    private Memory memory;

    public Computer(Cpu cpu, Memory memory) {
        this.cpu = cpu;
        this.memory = memory;
    }

    public void run(){
        cpu.work();
        memory.work();
        System.out.println("计算机运行");
    }
}
