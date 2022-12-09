package com.liu.redis;

import com.liu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisStu {
    @Autowired
    private RedisTemplate redisTemplate;

    public void testRedisSet(){
        Student student = new Student("liu", 18);
        redisTemplate.opsForValue().set("student",student);
    }
    public void testRedisGet(){
        System.out.println(redisTemplate.opsForValue().get("student"));
    }
}
