package edu.ie.hw;
import java.util.List;

public interface IReportGenerator {
    
    void generateReport(List<BaseTransaction> transactions);
    
    void exportToTxt(String filename);

}