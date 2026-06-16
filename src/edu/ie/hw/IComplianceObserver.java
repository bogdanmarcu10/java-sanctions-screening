package edu.ie.hw;

public interface IComplianceObserver {
    
    void updateOnSuspiciousActivity(BaseTransaction transaction, String reason);
    
    void resetDailyAlerts();
}