package edu.ie.hw;
public abstract class BaseTransaction {
    
    protected String transactionId;
    protected double amount;
    protected String currency;
    
    public BaseTransaction(String transactionId, double amount, String currency) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
    }
    
    public BaseTransaction() {
        this.transactionId = "UNKNOWN_ID";
        this.amount = 0.0;
        this.currency = "RON";
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public abstract void processTransaction();
}