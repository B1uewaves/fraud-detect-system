// package com.frauddetection.rules;

// import com.frauddetection.model.Transaction;

// public class AmountRule implements FraudRule {
//     private static final double AMOUNT_THRESHOLD = 10000.0;


//     @Override
//     public boolean isSuspicious(Transaction tx) {
//         return tx.getAmount() > AMOUNT_THRESHOLD;
//     }

//     @Override
//     public String getRuleName() {
//         return "AmountRule: Transactions over " + AMOUNT_THRESHOLD + " are suspicious";
//     }
// }


