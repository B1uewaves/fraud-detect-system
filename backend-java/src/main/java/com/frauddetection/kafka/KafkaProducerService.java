package com.frauddetection.kafka;

import java.util.Properties; //pass configuration settings

import org.apache.kafka.clients.producer.KafkaProducer; //Lets you run logic after the message is sent (success/failure)
import org.apache.kafka.clients.producer.ProducerRecord; //temporarily buffers messages before sending them to topic partitions
import org.apache.kafka.clients.producer.RecordMetadata; // a message with topic, key, and value

public class KafkaProducerService {
    private final KafkaProducer<String, String> producer; //<key, value>
    private final String topic; //ordered collections of messages in partition

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
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value); //defined method to sent a message
        
        producer.send(record, (RecordMetadata metadata, Exception exception) -> {
            if (exception != null) {
                System.err.println("Failed to send message: " + exception.getMessage());
            } else {
                System.out.println("Message sent successfully to topic " + metadata.topic() +
                        " partition " + metadata.partition() + //block which the message was stored
                        " offset " + metadata.offset()); //position within a partition
            }
        });
    }
    // Close the producer to release resources
    public void close() {
        producer.close();
    }
}