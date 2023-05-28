package 软年设计原则.迪米特法则;

/**
 * 粉丝类
 */
public class Fans {

    private String name;


    public Fans(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
