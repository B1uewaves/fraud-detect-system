package com.frauddetection.simulator;

import java.util.List;

import com.frauddetection.simulator.kafka.KafkaTransactionProducer;
import com.frauddetection.simulator.model.Transaction;

public class TransactionSimulator {
    private final KafkaTransactionProducer producer;
    private final CsvReader csvReader;

    public TransactionSimulator(KafkaTransactionProducer producer) {
        this.producer = producer;
        this.csvReader = new CsvReader();
    }

    public void simulateFromCsv(String filePath) {
        List<Transaction> transactions = csvReader.readTransactions(filePath);

        for (Transaction tx : transactions) {
            // Only send valid types
            if (isValidType(tx.getType())) {
                producer.sendTransaction(tx);
                try {
                    Thread.sleep(1); // 1 ms delay = ~1000 TPS; adjust to match your target TPS
                } catch (InterruptedException ignored) {}
            }
        }

        producer.close();
    }

    private boolean isValidType(String type) {
        return type.equals("TRANSFER") || type.equals("CASH_OUT")
            || type.equals("CASH_IN") || type.equals("PAYMENT") || type.equals("DEBIT");
    }
}