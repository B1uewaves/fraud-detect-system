package com.frauddetection.app;
import java.util.List;

import com.frauddetection.model.Transaction;
import com.frauddetection.simulator.CsvReader;
import com.frauddetection.simulator.ZipUtils;


public class Application {

    @SuppressWarnings("UseSpecificCatch")
    public static void main(String[] args) {
        String zipPath = "backend-java/src/main/resources/mobileMoneyTransactions.zip";
        String extractDir = "backend-java/src/main/resources";
        String extractedCsvPath = extractDir + "/mobileMoneyTransactions.csv";

        try {
            // Unzip CSV before reading
            ZipUtils.unzip(zipPath, extractDir);

            // Read extracted CSV
            CsvReader csvReader = new CsvReader();
            List<Transaction> transactions = csvReader.readTransactions(extractedCsvPath);

            // Print first two transactions as a test
            for (int i = 0; i < Math.min(2, transactions.size()); i++) {
                System.out.println(transactions.get(i));
            }

            // Continue with simulation...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}