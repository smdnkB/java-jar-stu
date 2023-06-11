package 设计模式.结构型模式.适配器模式.sd;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/5 21:11
 * @version: 1.0
 */
public class SDCardImpl implements SDCard{
    @Override
    public void read() {
        System.out.println("SD卡读取数据");
    }

    @Override
    public void write() {
        System.out.println("写数据到 SD卡");
    }
}
