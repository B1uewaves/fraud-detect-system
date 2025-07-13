
package com.frauddetection.simulator.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frauddetection.simulator.model.Transaction;


public class KafkaTransactionProducer {
    private final KafkaProducer<String, String> producer;
    private final ObjectMapper mapper = new ObjectMapper();

    public KafkaTransactionProducer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "5");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        this.producer = new KafkaProducer<>(props);
    }

    public void sendTransaction(Transaction tx) {
        try {
            String topic = "transaction-" + tx.getType().toLowerCase(); // e.g. transaction-transfer
            String key = tx.getNameOrg(); // Can use hash for partitioning
            String value = mapper.writeValueAsString(tx);
            System.out.println("Producing to topic: " + topic + ", value: " + value);

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            producer.send(record, (metadata, exception) -> {
                if (exception != null)
                    System.err.println("Send failed: " + exception.getMessage());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close() {
        producer.close();
    }
}