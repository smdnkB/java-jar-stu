package 软年设计原则.开闭原则;

/**
 * 主程序类
 * 开闭原则 ：面对需求变更不修改源代码，而是新写代码。通过接口和抽象类达到不更该源代码的目的
 *
 * 输入法需要更改皮肤，那输入法就只调用皮肤的抽象方法。抽象方法对应多种实现
 */
public class Main {

    public static void main(String[] args) {
        SouGouInput souGouInput = new SouGouInput();
        AbstractSkin defaultSkin = new DefaultSkin();
        /**
         * 需求迭代后 需要新的皮肤，此时实现抽象类定义的方法即可
         */
        AbstractSkin newSkin = new NewSkin();
        souGouInput.setSkin(newSkin);
        souGouInput.display();


    }
}
