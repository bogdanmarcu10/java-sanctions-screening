package edu.ie.hw;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class TransactionHistoryIterator implements Iterator<BaseTransaction> {

    private List<BaseTransaction> transactions;
    private int currentIndex;
    private String iteratorName;
    private BaseTransaction lastAccessed;

    public TransactionHistoryIterator(List<BaseTransaction> transactions, int currentIndex, String iteratorName) {
        this.transactions = transactions;
        this.currentIndex = currentIndex;
        this.iteratorName = iteratorName;
        this.lastAccessed = null;
    }

    public TransactionHistoryIterator(List<BaseTransaction> transactions, String iteratorName) {
        this.transactions = transactions;
        this.currentIndex = 0;
        this.iteratorName = iteratorName;
        this.lastAccessed = null;
    }

    public TransactionHistoryIterator() {
        this.transactions = new ArrayList<>();
        this.currentIndex = 0;
        this.iteratorName = "DEFAULT_ITERATOR";
        this.lastAccessed = null;
    }

    public List<BaseTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BaseTransaction> transactions) {
        this.transactions = transactions;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getIteratorName() {
        return iteratorName;
    }

    public void setIteratorName(String iteratorName) {
        this.iteratorName = iteratorName;
    }

    public BaseTransaction getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(BaseTransaction lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < transactions.size();
    }

    @Override
    public BaseTransaction next() {
        if (hasNext()) {
            BaseTransaction t = transactions.get(currentIndex);
            this.lastAccessed = t;
            currentIndex++;
            return t;
        }
        return null;
    }

    public void resetIterator() {
        this.currentIndex = 0;
        this.iteratorName = this.iteratorName + "-RESET";
    }

    public int countRemainingHighValueTransactions(double threshold) {
        int count = 0;
        for (int i = currentIndex; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() > threshold) {
                count++;
            }
        }
        return count;
    }
}