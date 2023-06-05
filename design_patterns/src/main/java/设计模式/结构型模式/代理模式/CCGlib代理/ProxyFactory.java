package 设计模式.结构型模式.代理模式.CCGlib代理;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/4 16:24
 * @version: 1.0
 */
public class ProxyFactory{

    private Student student = new Student();

    public Student getProxyObject(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class); // 设置父类对象


        Student o = (Student) Enhancer.create(Student.class, (MethodInterceptor) (o1, method, objects, methodProxy) -> {
            System.out.println("增强");
            method.invoke(student,objects);
            return null;
        });

        return o;

    }

}
