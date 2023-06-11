package 设计模式.结构型模式.适配器模式.tf;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/5 21:12
 * @version: 1.0
 */
public class TFCardImpl implements TFCard{
    @Override
    public void read() {
        System.out.println("TF 卡读取数据");
    }

    @Override
    public void write() {
        System.out.println("写数据到 TF卡");
    }
}
