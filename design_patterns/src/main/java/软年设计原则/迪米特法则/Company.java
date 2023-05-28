package 软年设计原则.迪米特法则;

/**
 * 广告公司类
 */
public class Company {

    private String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
