package 设计模式.创建者模式.D建造者模式.entity;

/**
 * @author: liu long fei
 * @description: 自行车类
 * @date: 2023/5/30 21:35
 * @version: 1.0
 */
public class Bike {

    private String frame;
    private String seat;

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Bike(String frame, String seat) {
        this.frame = frame;
        this.seat = seat;
    }

    public Bike() {
    }

    @Override
    public String toString() {
        return "Bike{" +
                "frame='" + frame + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }
}
