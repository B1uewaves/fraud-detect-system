package com.frauddetection.rules;

import java.util.Set;

import com.frauddetection.simulator.model.Transaction;

public class TransferLayer1 {

    public static boolean isFraudulent(Transaction tx, Set<String> dualRoleAccounts) {
        int score = 0;

        double amount = tx.getAmount();
        double diffOrig = tx.getOldbalanceOrg() - tx.getNewbalanceOrg();

        // Rule 1: Extremely High Amount (100% of such transactions are suspicious)
        if (amount >= 1_000_000) {
            score += 3;
        }

        // Rule 2: Moderately High Amount (starting from 300k to 1M)
        else if (amount >= 300_000) {
            score += 2;
        }

        // Rule 3: Balance drops significantly
        if (diffOrig > 500_000) {
            score += 1;
        }

        // Rule 4: Account involved in both origin and destination (common fraud indicator)
        if (dualRoleAccounts.contains(tx.getNameOrg()) && dualRoleAccounts.contains(tx.getNameDest())) {
            score += 2;
        }

        return score >= 3;
    }
}
