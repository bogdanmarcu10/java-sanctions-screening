package edu.ie.hw;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager("Central_Bank_Manager", 500);
        
        RiskDepartment riskDept = new RiskDepartment("RISK-HQ");
        manager.addObserver(riskDept);

        TransactionFactory factory = new TransactionFactory();
        ITransactionValidator sanctionsChecker = new SanctionsChecker("EU", true, "2026-06-14");
        IReportGenerator reportGen = new ComplianceReport("REP-001", "2026-06-14", 0);
        NameMatchingUtil nameUtil = new NameMatchingUtil(0.85, true);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- COMPLIANCE SYSTEM MENU ---");
            System.out.println("1. Add new transaction (Keyboard Input)");
            System.out.println("2. Validate all transactions");
            System.out.println("3. Generate and export report to TXT");
            System.out.println("4. Load sanctions from TXT file");
            System.out.println("5. Check Name Match & Evaluate Client Risk");
            System.out.println("6. Exit");
            System.out.print("Select option: ");

            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter transaction amount: ");
                    double amount = 0;
                    if (scanner.hasNextDouble()) {
                        amount = scanner.nextDouble();
                    } else {
                        scanner.next();
                    }
                    try {
                        BaseTransaction t1 = factory.createTransaction("DOMESTIC", "TX-" + System.currentTimeMillis(), amount, "RON", "BTRL", "false");
                        manager.addTransaction(t1);
                        System.out.println("Transaction successfully added. Risk alerts so far: " + riskDept.getAlertCount());
                    } catch (SuspiciousActivityException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Risk Dept Alerts updated: " + riskDept.getAlertCount());
                    }
                    break;
                    
                case 2:
                    TransactionHistoryIterator iterator = new TransactionHistoryIterator(manager.getTransactionList(), 0, "MainIter");
                    int validCount = 0;
                    while (iterator.hasNext()) {
                        BaseTransaction current = iterator.next();
                        if (sanctionsChecker.validate(current)) {
                            validCount++;
                        }
                    }
                    System.out.println("Validation complete. Valid transactions: " + validCount);
                    break;
                    
                case 3:
                    reportGen.generateReport(manager.getTransactionList());
                    reportGen.exportToTxt("compliance_report.txt");
                    System.out.println("Report exported to compliance_report.txt");
                    break;
                    
                case 4:
                    try {
                        manager.loadSanctionsFromFile("sanctions_list.txt");
                        System.out.println("Sanctions loaded successfully.");
                    } catch (ComplianceReportException e) {
                        System.out.println(e.getMessage() + " (Note: This is expected if the file doesn't exist yet)");
                    }
                    break;
                    
                case 5:
                    System.out.print("Enter client name to verify: ");
                    scanner.nextLine(); 
                    String clientName = scanner.nextLine();
                    boolean isSanctioned = nameUtil.isMatch(clientName, "Oligarh Sanctionat");
                    
                    AccountEntity client = new IndividualClient("ACC-099", clientName, 2, "1990101123456", "Engineer", isSanctioned);
                    client.evaluateRisk();
                    
                    System.out.println("Name Match with sanctions list: " + isSanctioned);
                    System.out.println("Calculated Client Risk Level: " + client.getRiskLevel());
                    break;
                    
                case 6:
                    isRunning = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}