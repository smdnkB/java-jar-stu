package 设计模式.创建者模式.C原型模式.浅克隆;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/29 21:15
 * @version: 1.0
 */
public class Student implements Cloneable{

    private String name;

    public Student(){
        System.out.println("Student 创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        System.out.println("执行clone");
        return  (Student)super.clone();
    }
}
