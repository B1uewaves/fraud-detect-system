package com.frauddetection.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerService {
    private KafkaProducer<String, String> producer;
    private String topic;

    public KafkaProducerService(String bootstrapServers, String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);  // Kafka broker addresses
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");  // how to serialize keys
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");  // how to serialize values
        props.put("acks", "all");  // ensure message durability by waiting for all replicas to acknowledge
        
        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void sendMessage(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.err.println("Failed to send message: " + exception.getMessage());
                } else {
                    System.out.println("Message sent successfully to topic " + metadata.topic() +
                                       " partition " + metadata.partition() +
                                       " offset " + metadata.offset());
                }
            }
        });
    }
    // Close the producer to release resources
    public void close() {
        producer.close();
    }
}