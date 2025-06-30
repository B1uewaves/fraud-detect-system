package com.frauddetection.simulator;

import java.io.BufferedReader;
import java.io.FileReader;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
                double time = Double.parseDouble(values[0]);
                double[] features = Arrays.stream(values, 1, 29)
                                          .mapToDouble(Double::parseDouble)
                                          .toArray();
                double amount = Double.parseDouble(values[29]);
                String cleanLabel = values[30].trim().replaceAll("^\"|\"$", "");
                int label = Integer.parseInt(cleanLabel);
                transactions.add(new Transaction(time, features, amount, label));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }


}