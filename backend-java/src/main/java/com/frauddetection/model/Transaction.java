package com.frauddetection.model;

public class Transaction {
    private double time;
    private double[] features; // For V1 to V28
    private double amount;
    private int label; // 1 = fraud, 0 = legit

    public Transaction(double time, double[] features, double amount, int label) {
        this.time = time;
        this.features = features;
        this.amount = amount;
        this.label = label;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double[] getFeatures() {
        return features;
    }

    public void setFeatures(double[] features) {
        this.features = features;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    /**
     * Returns a string representation of the transaction.
     * This is useful for debugging and logging purposes.
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "time=" + time +
                ", amount=" + amount +
                ", label=" + label +
                ", features=" + java.util.Arrays.toString(features) +
                '}';
    }
}