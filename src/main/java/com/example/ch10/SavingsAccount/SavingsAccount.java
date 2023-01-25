
package com.example.ch10.SavingsAccount;
import com.example.ch10.BanckAccount.BankAccountClass;
import javax.swing.JOptionPane;

public class SavingsAccount extends BankAccountClass {
    
     boolean accountStatus; 
    
    //Constructor that sets balance and interest rate
    //Check balance to determine account status
    public SavingsAccount(double balance, double interestRate)
    {
        super(balance, interestRate);
        System.out.println((balance));
        accountStatus = !(balance < 25);
        
    }
    
     @Override
    //Make a withdrawal if the balance at 25 or above
    public void makeWithdrawal(double withdrawalAmount){
        //Get current balance 
        double oldBalance = super.getBalance();
        double currentBalance = super.getBalance();
        //If the account status is good and the withdrawal will not take the
        //balance under 25, let the user withdrawal the money
        if(getAccountStatus() && (currentBalance - withdrawalAmount) >= 25 ){
            super.makeWithdrawal(withdrawalAmount);
        }
        //If the account is inactive and do not let the user withdrawal money
        //If the withdrawal takes
        else
            JOptionPane.showMessageDialog(null,"Account balance is below $25.\n"
                    + "Account is frozen until balance is above $25");
    
        JOptionPane.showMessageDialog(null,"Old account balance: $"
                + String.format("%.2f", oldBalance)
                + "\nNew account balance: $" + String.format("%.2f", getBalance()));
    }

    
    
     @Override
     //Make a deposit into the users savings account
    public void makeDeposit(double depositAmount) {
        double oldBalance = super.getBalance();
        double currentBalance = super.getBalance();

        //If status is good, let the user deposit the money
        if (getAccountStatus()) {
            super.makeDeposit(depositAmount);
        }
        //If the account is not in good standing but the deposit brings the
        //balance above $25, reactivate the account
        else if (!getAccountStatus() && currentBalance + depositAmount >= 25) {
            accountStatus = true;
            super.makeDeposit(depositAmount);
            JOptionPane.showMessageDialog(null, "Account has been reactivated!");
        } 
        //If the account is inactive and the withdrawal does NOT bring the balance
        //up to $25, accept the deposit but let the user know that the account
        //is still inactive
        else {
            super.makeDeposit(depositAmount);
            currentBalance = super.getBalance();
            JOptionPane.showMessageDialog(null, "Deposit is accepted but account "
                    + "is still frozen.\nYou will need to deposit $" 
                    + String.format("%.2f", (25 - currentBalance))
                    + " to reactivate your account.");
        }
        JOptionPane.showMessageDialog(null,"Old account balance: $"
                + String.format("%.2f", oldBalance)
                + "\nNew account balance: $" + String.format("%.2f", getBalance()));
        
        
    }

    
    
    //call the monthly process method in the bank account class
    public void accountMonthlyProcess() {
        double currentBalance;
        double oldBalance = super.getBalance();
        int withdrawalTransaction = super.getWithdrawalsForMonth();
        //Get withdrawal for the money
        //If the withdrawal transaction number is above 4, charge the user
        //$1 for each transaction over 4
        if (withdrawalTransaction > 4) {
            withdrawalTransaction -= 4;
            super.setMonthlyCharge(withdrawalTransaction);
        }
        currentBalance = super.monthlyProcess();
        if(currentBalance < 25){
            JOptionPane.showMessageDialog(null,"Account balance is below $25.\n"
                    + "Account is frozen until balance is above $25");
            accountStatus = false;
        }
        JOptionPane.showMessageDialog(null,"Old account balance: $"
                + String.format("%.2f", oldBalance)
                + "\nNew account balance: $" + String.format("%.2f", getBalance()));
    }
    
    
     @Override
    public double getBalance(){
        return super.getBalance();
    }
    
    
    public boolean getAccountStatus()
        {return accountStatus;}

    public void getInfo(){
        String stringAccountStatus;
        
        if (accountStatus)
            stringAccountStatus = "Active";
        else
            stringAccountStatus = "Inactive";
        
        JOptionPane.showMessageDialog(null,"Balance: $"
        + String.format("%.2f", getBalance())
        + "\nAccount status: " + stringAccountStatus
        + "\nDeposits for the month: " + super.getDepositsForMonth()
        + "\nWithdrawals for the month: " + super.getWithdrawalsForMonth()
        + "\nAnnual interest rate: " + super.getInterestRate() + "%");
    }

}
