package 软年设计原则.接口隔离原则;

/**
 * 门的每一个功能都对应一个接口
 * 每种门的功能不同，就只实现相对应的接口
 */
public class Main {
    public static void main(String[] args) {
        MySafetyDoor mySafetyDoor = new MySafetyDoor();
        mySafetyDoor.antiTheft();
        mySafetyDoor.waterproof();
    }
}
