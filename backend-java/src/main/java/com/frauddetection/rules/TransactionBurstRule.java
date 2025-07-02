// package com.frauddetection.rules;
// import java.util.LinkedList;
// import java.util.List;

// import com.frauddetection.model.Transaction;

// public class TransactionBurstRule implements FraudRule {
//     private static final int BURST_THRESHOLD = 5;  // e.g., 5 transactions in 10 seconds window
    
//     private final List<Double> recentTransactionTimes = new LinkedList<>();
    
//     @Override
//     public boolean isSuspicious(Transaction tx) {
//         double currentTime = tx.getTime();  // time in seconds elapsed
//         // remove old timestamps older than 10 seconds window
//         recentTransactionTimes.removeIf(t -> currentTime - t > 10);
//         recentTransactionTimes.add(currentTime);
//         return recentTransactionTimes.size() >= BURST_THRESHOLD;
//     }
    
//     @Override
//     public String getRuleName() {
//         return "TransactionBurstRule";
//     }
// }
