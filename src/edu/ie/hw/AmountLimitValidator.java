package edu.ie.hw;

public class AmountLimitValidator implements ITransactionValidator {

    private double maxAllowedAmount;
    private String currencyCode;
    private boolean requiresManagerApproval;

    public AmountLimitValidator(double maxAllowedAmount, String currencyCode, boolean requiresManagerApproval) {
        this.maxAllowedAmount = maxAllowedAmount;
        this.currencyCode = currencyCode;
        this.requiresManagerApproval = requiresManagerApproval;
    }

    public AmountLimitValidator(double maxAllowedAmount, String currencyCode) {
        this.maxAllowedAmount = maxAllowedAmount;
        this.currencyCode = currencyCode;
        this.requiresManagerApproval = false;
    }

    public AmountLimitValidator() {
        this.maxAllowedAmount = 10000.0;
        this.currencyCode = "EUR";
        this.requiresManagerApproval = false;
    }

    public double getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(double maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public boolean isRequiresManagerApproval() {
        return requiresManagerApproval;
    }

    public void setRequiresManagerApproval(boolean requiresManagerApproval) {
        this.requiresManagerApproval = requiresManagerApproval;
    }

    @Override
    public boolean validate(BaseTransaction transaction) {
        double currentAmount = transaction.getAmount();
        if (transaction.getCurrency().equals(this.currencyCode)) {
            if (currentAmount > this.maxAllowedAmount) {
                this.requiresManagerApproval = true;
                return false;
            }
        }
        return true;
    }

    @Override
    public int calculateRiskScore(BaseTransaction transaction) {
        double ratio = transaction.getAmount() / this.maxAllowedAmount;
        if (ratio > 0.8) {
            return 8;
        } else if (ratio > 0.5) {
            return 4;
        }
        return 1;
    }

    public void adjustLimitBasedOnRisk(int riskFactor) {
        if (riskFactor > 7) {
            this.maxAllowedAmount = this.maxAllowedAmount * 0.5;
            this.requiresManagerApproval = true;
        } else {
            this.maxAllowedAmount = this.maxAllowedAmount * 1.2;
        }
    }
}