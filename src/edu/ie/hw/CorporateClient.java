package edu.ie.hw;

public class CorporateClient extends AccountEntity {

    private String companyRegistrationNumber;
    private int numberOfEmployees;
    private double annualRevenue;

    public CorporateClient(String accountId, String clientName, int riskLevel, String companyRegistrationNumber, int numberOfEmployees, double annualRevenue) {
        super(accountId, clientName, riskLevel);
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.numberOfEmployees = numberOfEmployees;
        this.annualRevenue = annualRevenue;
    }

    public CorporateClient(String accountId, String clientName, String companyRegistrationNumber) {
        super(accountId, clientName, 3);
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.numberOfEmployees = 1;
        this.annualRevenue = 0.0;
    }

    public CorporateClient() {
        super();
        this.companyRegistrationNumber = "J00/00/0000";
        this.numberOfEmployees = 1;
        this.annualRevenue = 0.0;
    }

    public String getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    @Override
    public void evaluateRisk() {
        if (this.annualRevenue > 1000000.0) {
            this.riskLevel += 3;
        }
        if (this.numberOfEmployees < 5 && this.annualRevenue > 500000.0) {
            this.riskLevel += 5;
        }
    }

    public double calculateTaxComplianceScore() {
        double score = 100.0;
        if (this.companyRegistrationNumber.startsWith("J40")) {
            score -= 5.5;
        }
        if (this.annualRevenue > 0) {
            score += (this.numberOfEmployees / this.annualRevenue) * 1000;
        }
        return score;
    }
}