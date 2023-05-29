package 设计模式.创建者模式.工厂模式.B简单工厂;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/28 20:41
 * @version: 1.0
 */
public abstract class Coffee {

    public abstract String getName();

    public void addSuger(){
        System.out.println("加糖");
    }
}
