package edu.ie.hw;

public class DomesticTransaction extends BaseTransaction {

    private String bankCode;
    private boolean isUrgent;
    private double fee;

    public DomesticTransaction(String transactionId, double amount, String currency, String bankCode, boolean isUrgent) {
        super(transactionId, amount, currency);
        this.bankCode = bankCode;
        this.isUrgent = isUrgent;
        this.fee = calculateInitialFee();
    }

    public DomesticTransaction(String transactionId, double amount, String currency, String bankCode) {
        super(transactionId, amount, currency);
        this.bankCode = bankCode;
        this.isUrgent = false;
        this.fee = calculateInitialFee();
    }

    public DomesticTransaction() {
        super();
        this.bankCode = "BTRL";
        this.isUrgent = false;
        this.fee = 0.0;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean isUrgent) {
        this.isUrgent = isUrgent;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    private double calculateInitialFee() {
        if (isUrgent) {
            return 15.5;
        }
        return 5.0;
    }

    @Override
    public void processTransaction() {
        if (this.amount > 10000) {
            this.fee += 50.0;
        } else {
            this.fee += 10.0;
        }
    }
    
    public double calculateTotalCost() {
        double baseCost = this.amount + this.fee;
        if (this.bankCode.equals("ROIN")) {
            return baseCost * 0.95;
        }
        return baseCost;
    }
}