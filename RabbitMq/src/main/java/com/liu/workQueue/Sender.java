package com.liu.workQueue;

import com.liu.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获得连接
        Connection connection = RabbitUtils.getConnection();
        // 在连接中创建信道
        Channel channel = connection.createChannel();
        // 创建队列(队列名称  队列数据是否持久化  队列是否只许自己用  队列连接为0时是否销毁  队列参数)
        channel.queueDeclare("rabbitmqtest_queue1", false, false, false, null);
        // 发消息 (交换机名称(简单模式不用交换机  目标队列名称  消息属性  消息内容))
        int i = 0;
        while (i<10000){
            channel.basicPublish("","rabbitmqtest_queue1",null,("hallo rabbit"+i).getBytes(StandardCharsets.UTF_8));
            i++;
            Thread.sleep(500);
        }


        channel.close();
        connection.close();


    }
}
