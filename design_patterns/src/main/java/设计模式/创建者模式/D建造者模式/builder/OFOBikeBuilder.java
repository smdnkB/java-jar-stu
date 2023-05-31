package 设计模式.创建者模式.D建造者模式.builder;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/5/30 21:37
 * @version: 1.0
 */
public class OFOBikeBuilder extends Builder{
    @Override
    public void buildFrame() {
        bike.setFrame("车架");
    }

    @Override
    public void buildSeat() {

        bike.setSeat("车座");
    }
}
