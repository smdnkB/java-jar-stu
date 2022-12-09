package com.liu.事务;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class Demo {

    @Autowired
    private UserMapper userMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ) // 运行异常时回滚
    public void change(){
        User user = new User();
        user.setUser_id(Integer.valueOf ( String.valueOf(System.currentTimeMillis()%1000000)));
        user.setCreate_time(Integer.valueOf ( String.valueOf(System.currentTimeMillis()/1000)));
        user.setUser_name("liu");
        user.setUser_uuid(System.currentTimeMillis());
        userMapper.insert(user);
        System.out.println(1/0);
    }
}
