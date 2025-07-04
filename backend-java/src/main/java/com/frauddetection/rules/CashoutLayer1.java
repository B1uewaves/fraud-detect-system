package com.frauddetection.rules;

import com.frauddetection.model.Transaction;


public class CashoutLayer1 {

    public boolean isSuspicious(Transaction tx) {
        if (!"CASHOUT".equals(tx.getType())) {
            return false;
        }

        // 1. High Amount threshold from your data analysis
        if (tx.getAmount() > 420000) {
            return true;
        }

        return false;
    }
}