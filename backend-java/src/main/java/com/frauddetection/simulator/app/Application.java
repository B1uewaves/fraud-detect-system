package com.frauddetection.simulator.app;

import com.frauddetection.simulator.TransactionSimulator;
import com.frauddetection.simulator.kafka.CashoutTransactionConsumer;
import com.frauddetection.simulator.kafka.KafkaTransactionProducer;
import com.frauddetection.simulator.kafka.TransferTransactionConsumer;

public class Application {
    public static void main(String[] args) {
    System.out.println("Hello from Fraud Detection App!");
    // Use the path where Docker copies the file
    String filePath = "/app/mobileMoneyTransactions.csv";
    String bootstrapServers = System.getenv().getOrDefault("KAFKA_BOOTSTRAP_SERVERS", "localhost:9092");

    // Start Transfer consumer in its own thread
    new Thread(new TransferTransactionConsumer(bootstrapServers)).start();

    // Start Cashout consumer in its own thread
    new Thread(new CashoutTransactionConsumer(bootstrapServers)).start();

    // Start simulating transactions
    KafkaTransactionProducer producer = new KafkaTransactionProducer(bootstrapServers);
    TransactionSimulator simulator = new TransactionSimulator(producer);
    simulator.simulateFromCsv(filePath);

    System.out.println("Transaction simulation completed.");
    }
}
