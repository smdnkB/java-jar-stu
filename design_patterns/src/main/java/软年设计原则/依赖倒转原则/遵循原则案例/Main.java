package 软年设计原则.依赖倒转原则.遵循原则案例;

public class Main {
    public static void main(String[] args) {

//        Cpu cpu = new InterCpu();
        Cpu cpu = new AmdCpu();
        Memory memory = new JsdMemory();

        Computer computer = new Computer(cpu, memory);
        computer.run();
    }
}
