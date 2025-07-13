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
import com.frauddetection.rules.CashoutLayer1;
import com.frauddetection.simulator.model.Transaction;

public class CashoutTransactionConsumer implements Runnable {
    private final KafkaConsumer<String, String> consumer;
    private final ObjectMapper mapper = new ObjectMapper();

    // Example: an empty set for now
    private final Set<String> dualRoleAccounts = new HashSet<>();

    public CashoutTransactionConsumer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "cashout-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList("transaction-cash_out"));
    }

    @Override
    public void run() {
        CashoutLayer1 logic = new CashoutLayer1(dualRoleAccounts);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    Transaction tx = mapper.readValue(record.value(), Transaction.class);
                    boolean fraud = logic.isFraudulent(tx);
                    System.out.println("[CashOut] Fraud check: " + fraud + " | " + tx);
                } catch (Exception e) {
                    System.err.println("Error parsing record: " + e.getMessage());
                }
            }
        }
    }
}
