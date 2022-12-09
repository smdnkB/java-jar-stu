package com.liu.workQueue;

import com.liu.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("rabbitmqtest_queue1", false, false, false, null);

        channel.basicQos(1); // 一次接受一个消息 (一个一个的消费)  可以打破平均分配的问题

        // 获取消息
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override //(交付处理方法 (收件人信息  信息标签  协议配置  消息))
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println("收到消息: "+s);
                // 手动消息确认(收件人信息  是否同时确认多个消息)
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        // 监听队列  true: 消息自动确认
        channel.basicConsume("rabbitmqtest_queue1",false,consumer);

    }
}
