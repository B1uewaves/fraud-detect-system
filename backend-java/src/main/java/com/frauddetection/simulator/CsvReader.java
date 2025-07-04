package com.frauddetection.simulator;

import java.io.BufferedReader;
import java.io.FileReader;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.frauddetection.model.Transaction;

public class CsvReader {
    public List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                int step = Integer.parseInt(values[0]);
                String type = values[1];
                double amount = Double.parseDouble(values[2]);
                String nameOrg = values[3];
                double oldbalanceOrg = Double.parseDouble(values[4]);
                double newbalanceOrg = Double.parseDouble(values[5]);
                String nameDest = values[6];
                double oldbalanceDest = Double.parseDouble(values[7]);
                double newbalanceDest = Double.parseDouble(values[8]);
                int isFraud = Integer.parseInt(values[9]);
                int isFlaggedFraud = Integer.parseInt(values[10]);

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
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}