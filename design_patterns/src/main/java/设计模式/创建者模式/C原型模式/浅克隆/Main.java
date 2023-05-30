package 设计模式.创建者模式.C原型模式.浅克隆;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/29 21:16
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Student student = new Student();
        student.setName("liu");
        Student clone = student.clone();
        clone.setName("long");

        System.out.println((student==clone)+student.getName()+clone.getName());

    }
}
