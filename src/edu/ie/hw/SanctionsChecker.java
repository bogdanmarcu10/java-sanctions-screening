package edu.ie.hw;

public class SanctionsChecker implements ITransactionValidator {

    private String sanctionsListRegion;
    private boolean strictMode;
    private String lastUpdateDate;

    public SanctionsChecker(String sanctionsListRegion, boolean strictMode, String lastUpdateDate) {
        this.sanctionsListRegion = sanctionsListRegion;
        this.strictMode = strictMode;
        this.lastUpdateDate = lastUpdateDate;
    }

    public SanctionsChecker(String sanctionsListRegion, String lastUpdateDate) {
        this.sanctionsListRegion = sanctionsListRegion;
        this.strictMode = true;
        this.lastUpdateDate = lastUpdateDate;
    }

    public SanctionsChecker() {
        this.sanctionsListRegion = "GLOBAL";
        this.strictMode = true;
        this.lastUpdateDate = "2026-01-01";
    }

    public String getSanctionsListRegion() {
        return sanctionsListRegion;
    }

    public void setSanctionsListRegion(String sanctionsListRegion) {
        this.sanctionsListRegion = sanctionsListRegion;
    }

    public boolean isStrictMode() {
        return strictMode;
    }

    public void setStrictMode(boolean strictMode) {
        this.strictMode = strictMode;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean validate(BaseTransaction transaction) {
        if (strictMode && transaction.getCurrency().equals("RUB")) {
            return false;
        }
        if (transaction instanceof CrossBorderTransaction) {
            CrossBorderTransaction cbt = (CrossBorderTransaction) transaction;
            if (cbt.getDestinationCountry().equals("KP") || cbt.getDestinationCountry().equals("IR")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int calculateRiskScore(BaseTransaction transaction) {
        int score = 0;
        if (sanctionsListRegion.equals("EU")) {
            score += 2;
        } else if (sanctionsListRegion.equals("OFAC")) {
            score += 5;
        }
        if (transaction.getAmount() > 100000) {
            score += 3;
        }
        return score;
    }

    public void updateSanctionsList(String newDate, boolean forceStrict) {
        this.lastUpdateDate = newDate;
        if (forceStrict) {
            this.strictMode = true;
        } else {
            this.strictMode = !this.strictMode;
        }
    }
}