package edu.ie.hw;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ComplianceReport implements IReportGenerator {

    private String reportId;
    private String generationDate;
    private int totalFlaggedTransactions;

    public ComplianceReport(String reportId, String generationDate, int totalFlaggedTransactions) {
        this.reportId = reportId;
        this.generationDate = generationDate;
        this.totalFlaggedTransactions = totalFlaggedTransactions;
    }

    public ComplianceReport(String reportId, String generationDate) {
        this.reportId = reportId;
        this.generationDate = generationDate;
        this.totalFlaggedTransactions = 0;
    }

    public ComplianceReport() {
        this.reportId = "REP-000";
        this.generationDate = "2026-01-01";
        this.totalFlaggedTransactions = 0;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(String generationDate) {
        this.generationDate = generationDate;
    }

    public int getTotalFlaggedTransactions() {
        return totalFlaggedTransactions;
    }

    public void setTotalFlaggedTransactions(int totalFlaggedTransactions) {
        this.totalFlaggedTransactions = totalFlaggedTransactions;
    }

    @Override
    public void generateReport(List<BaseTransaction> transactions) {
        int flagged = 0;
        for (BaseTransaction t : transactions) {
            if (t.getAmount() > 50000) {
                flagged++;
            }
        }
        this.totalFlaggedTransactions = flagged;
    }

    @Override
    public void exportToTxt(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Report ID: " + this.reportId + "\n");
            writer.write("Date: " + this.generationDate + "\n");
            writer.write("Flagged Transactions: " + this.totalFlaggedTransactions + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public double calculateSeverityIndex() {
        if (this.totalFlaggedTransactions > 100) {
            return 9.5;
        } else if (this.totalFlaggedTransactions > 50) {
            return 6.0;
        }
        return 2.5;
    }

    public void appendSuffixToId(String suffix) {
        if (!this.reportId.endsWith(suffix)) {
            this.reportId = this.reportId + "-" + suffix;
        }
    }
}