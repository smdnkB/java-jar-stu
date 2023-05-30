package 设计模式.创建者模式.C原型模式.深克隆;

import java.io.*;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/29 21:16
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        Student student = new Student();

        Meta meta = new Meta();
        meta.setName("liu");
        student.setMeta(meta);

        // 序列化对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./student"));
        oos.writeObject(student);
        oos.close();

        // 反序列化对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./student" ));
        Student o = (Student) ois.readObject();
        o.getMeta().setName("long");

        System.out.println(student);
        System.out.println(o);

    }
}
