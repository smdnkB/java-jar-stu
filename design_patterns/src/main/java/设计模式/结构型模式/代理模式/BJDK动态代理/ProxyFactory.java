package 设计模式.结构型模式.代理模式.BJDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/4 15:04
 * @version: 1.0
 */
public class ProxyFactory {

    private Student student = new Student();

    public Eat getProxyObject(){

        // 返回值需要是接口类型
        /**
         * 参数： 要代理的类的类加载器，要代理的对象的实现的接口，被代理类内方法执行的回调方法
         */
        Eat eat = (Eat) Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            /**
             * 被代理对象，被代理对象执行的方法，被代理对象执行的方法参数
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("invoke");
                Object invoke1 = method.invoke(student,args); // 这里的参数要写被代理对象
                return invoke1;
            }
        });

        return eat;
    }
}
