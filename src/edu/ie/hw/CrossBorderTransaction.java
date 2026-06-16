package edu.ie.hw;

public class CrossBorderTransaction extends BaseTransaction {

    private String swiftCode;
    private String destinationCountry;
    private double exchangeRate;

    public CrossBorderTransaction(String transactionId, double amount, String currency, String swiftCode, String destinationCountry, double exchangeRate) {
        super(transactionId, amount, currency);
        this.swiftCode = swiftCode;
        this.destinationCountry = destinationCountry;
        this.exchangeRate = exchangeRate;
    }

    public CrossBorderTransaction(String transactionId, double amount, String currency, String swiftCode) {
        super(transactionId, amount, currency);
        this.swiftCode = swiftCode;
        this.destinationCountry = "UNKNOWN";
        this.exchangeRate = 1.0;
    }

    public CrossBorderTransaction() {
        super();
        this.swiftCode = "UNKNOWN";
        this.destinationCountry = "UNKNOWN";
        this.exchangeRate = 1.0;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public void processTransaction() {
        if (this.amount > 50000) {
            this.exchangeRate -= 0.05;
        } else if (this.amount < 100) {
            this.exchangeRate += 0.02;
        }
    }

    public double calculateEquivalentInRon() {
        double baseEquivalent = this.amount * this.exchangeRate;
        if (this.destinationCountry.equals("US")) {
            return baseEquivalent + 15.0;
        }
        return baseEquivalent + 5.0;
    }
}