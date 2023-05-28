package 软年设计原则.迪米特法则;

/**
 * 经纪人类
 */
public class Agent {

    private Star star;
    private Fans fans;
    private Company company;


    public Agent(Star star, Fans fans, Company company) {
        this.star = star;
        this.fans = fans;
        this.company = company;
    }

    public void meeting(){
        System.out.println(star.getName()+":"+fans.getName());
    }


}
