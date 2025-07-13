package com.frauddetection.simulator.model;

public class Transaction {
    private final int step;
    private final String type;
    private final double amount;
    private final String nameOrg;
    private final double oldbalanceOrg;
    private final double newbalanceOrg;
    private final String nameDest;
    private final double oldbalanceDest;
    private final double newbalanceDest;
    private final int isFraud;
    private final int isFlaggedFraud;

    public Transaction(int step, String type, double amount, String nameOrg,
                       double oldbalanceOrg, double newbalanceOrg,
                       String nameDest, double oldbalanceDest,
                       double newbalanceDest, int isFraud, int isFlaggedFraud) {
        this.step = step;
        this.type = type;
        this.amount = amount;
        this.nameOrg = nameOrg;
        this.oldbalanceOrg = oldbalanceOrg;
        this.newbalanceOrg = newbalanceOrg;
        this.nameDest = nameDest;
        this.oldbalanceDest = oldbalanceDest;
        this.newbalanceDest = newbalanceDest;
        this.isFraud = isFraud;
        this.isFlaggedFraud = isFlaggedFraud;
    }

    public int getStep() {
        return step;
    }   

    public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }

    public String getNameOrg() {
        return nameOrg;
    }

    public double getOldbalanceOrg() {
        return oldbalanceOrg;
    }

    public double getNewbalanceOrg() {
        return newbalanceOrg;
    }
    
    public String getNameDest() {
        return nameDest;
    }

    public double getOldbalanceDest() {
        return oldbalanceDest;
    }

    public double getNewbalanceDest() {
        return newbalanceDest;
    }

    public int getIsFraud() {
        return isFraud;
    }

    public int getIsFlaggedFraud() {
        return isFlaggedFraud;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", nameOrig='" + nameOrg + '\'' +
                ", oldbalanceOrg=" + oldbalanceOrg +
                ", newbalanceOrig=" + newbalanceOrg +
                ", nameDest='" + nameDest + '\'' +
                ", oldbalanceDest=" + oldbalanceDest +
                ", newbalanceDest=" + newbalanceDest +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }
}