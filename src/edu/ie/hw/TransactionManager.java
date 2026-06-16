package edu.ie.hw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {

    private List<BaseTransaction> transactionList;
    private List<IComplianceObserver> observers;
    private String managerName;
    private int maxCapacity;
    private BaseTransaction lastProcessedTransaction;

    public TransactionManager(String managerName, int maxCapacity) {
        this.transactionList = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.managerName = managerName;
        this.maxCapacity = maxCapacity;
        this.lastProcessedTransaction = null;
    }

    public TransactionManager(String managerName) {
        this.transactionList = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.managerName = managerName;
        this.maxCapacity = 1000;
        this.lastProcessedTransaction = null;
    }

    public TransactionManager() {
        this.transactionList = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.managerName = "DEFAULT_MANAGER";
        this.maxCapacity = 1000;
        this.lastProcessedTransaction = null;
    }

    public List<BaseTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<BaseTransaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public BaseTransaction getLastProcessedTransaction() {
        return lastProcessedTransaction;
    }

    public void setLastProcessedTransaction(BaseTransaction lastProcessedTransaction) {
        this.lastProcessedTransaction = lastProcessedTransaction;
    }
    
    public void addObserver(IComplianceObserver observer) {
        this.observers.add(observer);
    }

    public void addTransaction(BaseTransaction t) throws SuspiciousActivityException {
        if (transactionList.size() >= maxCapacity) {
            throw new RuntimeException("Capacity exceeded");
        }
        if (t.getAmount() > 1000000) {
            notifyObservers(t, "Amount exceeds maximum threshold");
            throw new SuspiciousActivityException("Alert: Amount exceeds maximum threshold.");
        }
        transactionList.add(t);
        this.lastProcessedTransaction = t;
    }

    private void notifyObservers(BaseTransaction t, String reason) {
        for (IComplianceObserver obs : observers) {
            obs.updateOnSuspiciousActivity(t, reason);
        }
    }

    public void loadSanctionsFromFile(String filename) throws ComplianceReportException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("STRICT")) {
                    this.maxCapacity -= 10;
                }
            }
        } catch (IOException e) {
            throw new ComplianceReportException("Error reading sanctions file: " + filename);
        }
    }
    
    public double calculateTotalVolume() {
        double total = 0;
        for (BaseTransaction t : transactionList) {
            total += t.getAmount();
        }
        return total;
    }
}