package com.frauddetection.rules;

import java.util.Set;

import com.frauddetection.simulator.model.Transaction;

public class CashoutLayer1 {

    private final Set<String> dualRoleAccounts;

    public CashoutLayer1(Set<String> dualRoleAccounts) {
        this.dualRoleAccounts = dualRoleAccounts;
    }

    public boolean isFraudulent(Transaction transaction) {
        if (!transaction.getType().equalsIgnoreCase("CASH_OUT")) {
            return false;
        }

        int score = 0;

        // High value cashout
        if (transaction.getAmount() > 300_000 && transaction.getAmount() <= 420_000) {
            score += 2;
        }
        if (transaction.getAmount() > 420_000) {
            score += 3;
        }

        // Sender has balance depleted heavily
        double diffOrig = transaction.getOldbalanceOrg() - transaction.getNewbalanceOrg();
        if (diffOrig > 1_000_000) {
            score += 1;
        }

        // Suspicious account activity
        if (dualRoleAccounts.contains(transaction.getNameOrg())) {
            score += 2;
        }

        return score >= 3;
    }
}
