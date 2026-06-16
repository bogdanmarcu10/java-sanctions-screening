package edu.ie.hw;

public class RiskDepartment implements IComplianceObserver {

    private String departmentCode;
    private int alertCount;
    private boolean requiresImmediateAction;

    public RiskDepartment(String departmentCode) {
        this.departmentCode = departmentCode;
        this.alertCount = 0;
        this.requiresImmediateAction = false;
    }

    public RiskDepartment(String departmentCode, int alertCount) {
        this.departmentCode = departmentCode;
        this.alertCount = alertCount;
        this.requiresImmediateAction = false;
    }

    public RiskDepartment() {
        this.departmentCode = "RISK-HQ";
        this.alertCount = 0;
        this.requiresImmediateAction = false;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public int getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(int alertCount) {
        this.alertCount = alertCount;
    }

    public boolean isRequiresImmediateAction() {
        return requiresImmediateAction;
    }

    public void setRequiresImmediateAction(boolean requiresImmediateAction) {
        this.requiresImmediateAction = requiresImmediateAction;
    }

    @Override
    public void updateOnSuspiciousActivity(BaseTransaction transaction, String reason) {
        this.alertCount++;
        if (transaction.getAmount() > 500000) {
            this.requiresImmediateAction = true;
        }
    }

    @Override
    public void resetDailyAlerts() {
        this.alertCount = 0;
        this.requiresImmediateAction = false;
    }
}