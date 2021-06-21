package com.mycompany.app.task1;

public class BankAccount {

    String bankName;
    String accountNumber;
    double balance;
    double loanSize;
    boolean isLocked;

    public void setIsLocked( boolean paramLocked ){ isLocked = paramLocked; }
    public void setBalance( double balance) { this.balance = balance; }
    public void setLoanSize( double loanSize ) { this.loanSize = loanSize; }
    public void setBankName( String bankName ) { this.bankName = bankName; }
    public void setAccountNumber( String setAccountNumber ) { this.accountNumber = accountNumber; }

    public boolean getIsLocked() { return isLocked;  }
    public double getBalance() { return balance; }
    public double getLoanSize() { return loanSize; }
    public String getBankName() { return bankName; }
    public String getAccountNumber() { return accountNumber; }
}
