package 设计模式.创建者模式.E建造者模式拓展;

/**
 * @author: liu long fei
 * @description: 链式编程
 * @date: 2023/5/31 20:38
 * @version: 1.0
 */
public class After {

    private String name;
    private String age;

    private After(Builder builder){
        this.age = builder.age;
        this.name = builder.name;
    }

    public static class Builder{
        private String name;
        private String age;

        public Builder name(String name){
            this.name = name;
            return  this;
        }
        public Builder age(String age){
            this.age = age;
            return this;
        }

        public After build(){
            return new After(this);
        }
    }
}
