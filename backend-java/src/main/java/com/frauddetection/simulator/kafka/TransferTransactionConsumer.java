package com.frauddetection.simulator.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frauddetection.rules.TransferLayer1;
import com.frauddetection.simulator.model.Transaction;

public class TransferTransactionConsumer implements Runnable {
    private final KafkaConsumer<String, String> consumer;
    private final ObjectMapper mapper = new ObjectMapper();

    public TransferTransactionConsumer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "transfer-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList("transaction-transfer"));
    }

    @Override
    public void run() {
        Set<String> dualRoleAccounts = new HashSet<>(); // TODO: populate if needed
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    Transaction tx = mapper.readValue(record.value(), Transaction.class);
                    boolean fraud = TransferLayer1.isFraudulent(tx, dualRoleAccounts);
                    System.out.println("[Transfer] Fraud check: " + fraud + " | " + tx);
                } catch (Exception e) {
                    System.err.println("Error parsing record: " + e.getMessage());
                }
            }
        }
    }
}
