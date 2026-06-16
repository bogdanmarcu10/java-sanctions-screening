package edu.ie.hw;
public interface ITransactionValidator {
    
  
    boolean validate(BaseTransaction transaction);
    
    int calculateRiskScore(BaseTransaction transaction);

}