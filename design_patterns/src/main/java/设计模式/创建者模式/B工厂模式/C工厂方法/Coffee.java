package 设计模式.创建者模式.B工厂模式.C工厂方法;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/28 20:58
 * @version: 1.0
 */
public abstract class Coffee {

    public abstract String getName();

    public void addSuger(){
        System.out.println("加糖");
    }
}
