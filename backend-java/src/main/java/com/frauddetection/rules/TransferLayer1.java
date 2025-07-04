package com.frauddetection.rules;

import com.frauddetection.model.Transaction;

public class TransferLayer1 {

    public boolean isSuspicious(Transaction tx) {
        if (!"TRANSFER".equals(tx.getType())) {
            return false;
        }

        double senderDiff = tx.getOldbalanceOrg() - tx.getNewbalanceOrg();
        double recipientDiff = tx.getNewbalanceDest() - tx.getOldbalanceDest();

        // 1. High Amount threshold from your data analysis
        if (tx.getAmount() > 1_500_000) {
            return true;
        }

        // 2. Recipient's balance unchanged but large amount transferred
        if (Math.abs(senderDiff - tx.getAmount()) < 1e-2 && recipientDiff == 0) {
            return true;
        }

        // 3. Sender old balance less than amount transferred
        if (tx.getOldbalanceOrg() < tx.getAmount()) {
            return true;
        }

        return false;
    }
}