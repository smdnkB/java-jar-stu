package 设计模式.创建者模式.C原型模式.深克隆;

import java.io.Serializable;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/29 21:15
 * @version: 1.0
 */
public class Student implements Serializable {

    private Meta meta;

    public Student(){
        System.out.println("Student 创建");
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        System.out.println("执行clone");
        return  (Student)super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "meta=" + meta +
                '}';
    }
}
