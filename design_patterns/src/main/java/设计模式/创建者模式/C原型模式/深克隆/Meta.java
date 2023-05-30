package 设计模式.创建者模式.C原型模式.深克隆;

import java.io.Serializable;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/30 21:06
 * @version: 1.0
 */
public class Meta implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "name='" + name + '\'' +
                '}';
    }
}
