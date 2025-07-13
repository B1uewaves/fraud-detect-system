package com.frauddetection.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frauddetection.simulator.model.Transaction;

public class CsvReader {
    private static final Logger logger = LoggerFactory.getLogger(CsvReader.class);
    private static final String RESOURCE_NAME = "mobileMoneyTransactions.csv";

    /**
     * Reads transactions from the CSV bundled in the classpath.
     * @return List of parsed Transaction objects.
     * @throws IOException if resource not found or I/O error occurs.
     */
    public List<Transaction> readTransactions(String filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            // Skip header
            if ((line = br.readLine()) == null) {
                logger.warn("CSV file is empty: {}", filePath);
                return transactions;
            }
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                lineNum++;
                String[] values = line.split(",", -1);
                if (values.length < 11) {
                    logger.warn("Skipping malformed line {}: {}", lineNum, line);
                    continue;
                }
                try {
                    int step = Integer.parseInt(values[0].trim());
                    String type = values[1].trim();
                    double amount = Double.parseDouble(values[2].trim());
                    String nameOrg = values[3].trim();
                    double oldbalanceOrg = Double.parseDouble(values[4].trim());
                    double newbalanceOrg = Double.parseDouble(values[5].trim());
                    String nameDest = values[6].trim();
                    double oldbalanceDest = Double.parseDouble(values[7].trim());
                    double newbalanceDest = Double.parseDouble(values[8].trim());
                    int isFraud = Integer.parseInt(values[9].trim());
                    int isFlaggedFraud = Integer.parseInt(values[10].trim());

                    Transaction transaction = new Transaction(
                        step,
                        type,
                        amount,
                        nameOrg,
                        oldbalanceOrg,
                        newbalanceOrg,
                        nameDest,
                        oldbalanceDest,
                        newbalanceDest,
                        isFraud,
                        isFlaggedFraud
                    );
                    transactions.add(transaction);
                } catch (NumberFormatException nfe) {
                    logger.warn("Skipping line {} due to number parse error: {}", lineNum, line, nfe);
                }
            }
        }
        logger.info("Loaded {} transactions from {}", transactions.size(), filePath);
        return transactions;
    }
}