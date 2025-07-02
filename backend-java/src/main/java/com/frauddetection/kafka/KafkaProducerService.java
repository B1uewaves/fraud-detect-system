package com.frauddetection.kafka;

import java.util.Properties; //pass configuration settings

import org.apache.kafka.clients.producer.Callback; //Lets you run logic after the message is sent (success/failure)
import org.apache.kafka.clients.producer.KafkaProducer; //actual Kafka client for sending messages.
import org.apache.kafka.clients.producer.ProducerRecord; // a message with topic, key, and value
import org.apache.kafka.clients.producer.RecordMetadata; //info about where the message landed

public class KafkaProducerService {
    private KafkaProducer<String, String> producer; //<key, value>
    private String topic; //ordered collection of messages

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
        
        producer.send(record, new Callback() { 
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.err.println("Failed to send message: " + exception.getMessage());
                } else {
                    System.out.println("Message sent successfully to topic " + metadata.topic() +
                                       " partition " + metadata.partition() + //block which the message was stored
                                       " offset " + metadata.offset()); //position within a partition
                }
            }
        });
    }
    // Close the producer to release resources
    public void close() {
        producer.close();
    }
}