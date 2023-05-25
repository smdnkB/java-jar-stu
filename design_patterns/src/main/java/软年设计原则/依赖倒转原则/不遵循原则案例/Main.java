package 软年设计原则.依赖倒转原则.不遵循原则案例;

/**
 * 计算机对象不应该直接依赖具体的 cpu和memory。不利于拓展
 */
public class Main {
    public static void main(String[] args) {

        Cpu cpu = new Cpu();
        Memory memory = new Memory();

        Computer computer = new Computer(cpu, memory);
        computer.run();
    }
}
