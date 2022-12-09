package com.liu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Random;

public class MTest {
    // Spy不会模拟出新对象, 而是直接创建对象
    @Spy
    private Random ran;

    @Mock
    private Random random;
    @BeforeEach
    void setUp(){
        System.out.println("测试开始前执行");
        // 注解模式要加这句代码
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test(){

        random.nextInt();
        random.nextInt();
        // verify 验证是否调用了 random对象的nextInt方法  times指定期望的调用次数
        Mockito.verify(random,Mockito.times(2)).nextInt();

        // 打桩, 使nextInt返回值为 100
        Mockito.when(random.nextInt()).thenReturn(100);
        // 断言, nextInt返回值是否为 100
        Assertions.assertEquals(100,random.nextInt());
    }

    @AfterEach
    void down(){
        System.out.println("测试结束执行");
    }
}
