package 软年设计原则.开闭原则;

/**
 * 搜狗输入法对象类
 */
public class SouGouInput {

    private AbstractSkin skin;

    /**
     * 输入法显示皮肤
     */
    public void display(){
        skin.display();
    }

    public void setSkin(AbstractSkin skin){
        this.skin = skin;
    }
}
