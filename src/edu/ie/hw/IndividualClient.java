package edu.ie.hw;

public class IndividualClient extends AccountEntity {

    private String cnp;
    private String occupation;
    private boolean isPoliticallyExposed;

    public IndividualClient(String accountId, String clientName, int riskLevel, String cnp, String occupation, boolean isPoliticallyExposed) {
        super(accountId, clientName, riskLevel);
        this.cnp = cnp;
        this.occupation = occupation;
        this.isPoliticallyExposed = isPoliticallyExposed;
    }

    public IndividualClient(String accountId, String clientName, String cnp) {
        super(accountId, clientName, 1);
        this.cnp = cnp;
        this.occupation = "Unknown";
        this.isPoliticallyExposed = false;
    }

    public IndividualClient() {
        super();
        this.cnp = "0000000000000";
        this.occupation = "Unemployed";
        this.isPoliticallyExposed = false;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public boolean isPoliticallyExposed() {
        return isPoliticallyExposed;
    }

    public void setPoliticallyExposed(boolean politicallyExposed) {
        this.isPoliticallyExposed = politicallyExposed;
    }

    @Override
    public void evaluateRisk() {
        if (this.isPoliticallyExposed) {
            this.riskLevel += 5;
        }
        if (this.occupation.equals("Student")) {
            this.riskLevel -= 1;
        }
    }

    public boolean requiresAdditionalVerification() {
        int score = this.riskLevel;
        if (this.cnp.startsWith("9")) {
            score += 2;
        }
        return score > 7;
    }
}