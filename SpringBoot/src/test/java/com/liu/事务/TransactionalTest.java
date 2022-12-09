package com.liu.事务;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务测试
 */
@SpringBootTest
public class TransactionalTest {
    @Autowired
    private Demo demo;

    @Test
    public void test(){
        demo.change();
    }


}
