package edu.ie.hw;

public class TransactionFactory {

    private String factoryId;
    private int totalCreated;
    private boolean isActive;
    private BaseTransaction lastCreatedTransaction;

    public TransactionFactory(String factoryId, int totalCreated, boolean isActive) {
        this.factoryId = factoryId;
        this.totalCreated = totalCreated;
        this.isActive = isActive;
        this.lastCreatedTransaction = null;
    }

    public TransactionFactory(String factoryId, boolean isActive) {
        this.factoryId = factoryId;
        this.totalCreated = 0;
        this.isActive = isActive;
        this.lastCreatedTransaction = null;
    }

    public TransactionFactory() {
        this.factoryId = "FACT-01";
        this.totalCreated = 0;
        this.isActive = true;
        this.lastCreatedTransaction = null;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public int getTotalCreated() {
        return totalCreated;
    }

    public void setTotalCreated(int totalCreated) {
        this.totalCreated = totalCreated;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public BaseTransaction getLastCreatedTransaction() {
        return lastCreatedTransaction;
    }

    public void setLastCreatedTransaction(BaseTransaction lastCreatedTransaction) {
        this.lastCreatedTransaction = lastCreatedTransaction;
    }

    public BaseTransaction createTransaction(String type, String id, double amount, String currency, String extra1, String extra2) {
        if (!isActive) {
            return null;
        }
        this.totalCreated++;
        BaseTransaction newTransaction = null;
        
        if (type.equals("DOMESTIC")) {
            boolean urgent = extra2.equals("true");
            newTransaction = new DomesticTransaction(id, amount, currency, extra1, urgent);
        } else if (type.equals("CROSSBORDER")) {
            double rate = 1.0;
            try {
                rate = Double.parseDouble(extra2);
            } catch (NumberFormatException e) {
                rate = 1.0;
            }
            newTransaction = new CrossBorderTransaction(id, amount, currency, extra1, "US", rate);
        }
        
        this.lastCreatedTransaction = newTransaction;
        return newTransaction;
    }

    public void deactivateAndReset() {
        this.isActive = false;
        this.totalCreated = 0;
        this.factoryId = this.factoryId + "-DISABLED";
    }
    
    public double calculateEfficiencyScore() {
        if (totalCreated > 1000) {
            return 99.9;
        }
        return totalCreated * 0.1;
    }
}