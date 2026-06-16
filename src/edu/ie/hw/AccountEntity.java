package edu.ie.hw;
public abstract class AccountEntity {
    
    protected String accountId;
    protected String clientName;
    protected int riskLevel;

    public AccountEntity(String accountId, String clientName, int riskLevel) {
        this.accountId = accountId;
        this.clientName = clientName;
        this.riskLevel = riskLevel;
    }

    public AccountEntity() {
        this.accountId = "UNKNOWN";
        this.clientName = "UNNAMED";
        this.riskLevel = 0;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public abstract void evaluateRisk();
}