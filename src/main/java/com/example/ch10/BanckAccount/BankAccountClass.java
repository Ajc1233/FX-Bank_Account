
package com.example.ch10.BanckAccount;

public abstract class BankAccountClass {
    
    private double balance;
    private int depositsInMonth = 0;
    private int withdrawalsInMonth = 0;
    private final double annualInterestRate;
    private int monthlyCharge = 0;

    //2 arg constructor for balance and annual interest rate
    public BankAccountClass(double balance, double annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }
    
    //Make a deposit and add one to the deposits for that month
    public void makeDeposit(double depositAmount){
        balance += depositAmount;
        depositsInMonth++;
    }
    
    //Make a withdrawal and add one to the withdrawals for that month
    public void makeWithdrawal(double withdrawalAmount){
         balance -= withdrawalAmount;
         withdrawalsInMonth++;
    }
    
    //Calculate the monthly interest rate
    public void calculateInterest(){
        double monthlyRate = annualInterestRate / 12;
        double interest = balance * monthlyRate;
        balance += interest;
    }
    
    //Get the monthly interest and add it to the balance and reset the member variables
    public double monthlyProcess (){
        balance -= monthlyCharge;
        calculateInterest();
        depositsInMonth = 0;
        withdrawalsInMonth = 0;
        monthlyCharge = 0;
        return getBalance();
    }
    
    
    //Set the monthly charge based on the number of deposits
    public void setMonthlyCharge(int chargeAmount){
        monthlyCharge += chargeAmount;
    }
    
    public int getDepositsForMonth()
        {return  depositsInMonth;}
     
    public int getWithdrawalsForMonth()
        {return withdrawalsInMonth;}
    
    public double getBalance()
        {return balance;}
    
    public double getInterestRate()
        {return annualInterestRate;}
}
