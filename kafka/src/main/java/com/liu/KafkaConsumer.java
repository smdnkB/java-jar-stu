package com.liu;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        // kafka群组集群地址
        properties.put("bootstrap.servers", "192.168.0.101:9092");
        properties.put("key.deserializer", StringDeserializer.class);
        properties.put("value.deserializer", StringDeserializer.class);

        // 消费群组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "first-group");
        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        try {
            consumer.subscribe(Collections.singletonList("first"));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("topic:%s,分区：%d,偏移量：%d," + "key:%s,value:%s%n", record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
