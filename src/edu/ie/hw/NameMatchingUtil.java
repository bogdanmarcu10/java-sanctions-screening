package edu.ie.hw;

public class NameMatchingUtil {

    private double matchThreshold;
    private boolean ignoreCase;
    private int comparisonCount;
    private BaseTransaction lastCheckedTransaction;

    public NameMatchingUtil(double matchThreshold, boolean ignoreCase) {
        this.matchThreshold = matchThreshold;
        this.ignoreCase = ignoreCase;
        this.comparisonCount = 0;
        this.lastCheckedTransaction = null;
    }

    public NameMatchingUtil(double matchThreshold) {
        this.matchThreshold = matchThreshold;
        this.ignoreCase = true;
        this.comparisonCount = 0;
        this.lastCheckedTransaction = null;
    }

    public NameMatchingUtil() {
        this.matchThreshold = 0.8;
        this.ignoreCase = true;
        this.comparisonCount = 0;
        this.lastCheckedTransaction = null;
    }

    public double getMatchThreshold() {
        return matchThreshold;
    }

    public void setMatchThreshold(double matchThreshold) {
        this.matchThreshold = matchThreshold;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }

    public void setComparisonCount(int comparisonCount) {
        this.comparisonCount = comparisonCount;
    }
    
    public BaseTransaction getLastCheckedTransaction() {
        return lastCheckedTransaction;
    }

    public void setLastCheckedTransaction(BaseTransaction lastCheckedTransaction) {
        this.lastCheckedTransaction = lastCheckedTransaction;
    }

    public boolean isMatch(String clientName, String sanctionedName) {
        this.comparisonCount++;
        
        if (clientName == null || sanctionedName == null) {
            return false;
        }

        String str1 = this.ignoreCase ? clientName.toLowerCase() : clientName;
        String str2 = this.ignoreCase ? sanctionedName.toLowerCase() : sanctionedName;

        if (str1.equals(str2)) {
            return true;
        }

        if (str1.contains(str2) || str2.contains(str1)) {
            return true;
        }

        return calculateSimilarity(str1, str2) >= this.matchThreshold;
    }

    private double calculateSimilarity(String s1, String s2) {
        int maxLength = Math.max(s1.length(), s2.length());
        if (maxLength == 0) {
            return 1.0;
        }
        
        int matchLen = 0;
        int minLength = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                matchLen++;
            }
        }
        return (double) matchLen / maxLength;
    }
}