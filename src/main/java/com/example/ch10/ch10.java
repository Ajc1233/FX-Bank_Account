package com.example.ch10;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.ch10.SavingsAccount.SavingsAccount;
import javax.swing.JOptionPane;

public class ch10 extends Application {

    public static void main(String[] args) {

        String lookGood, stringBalance, stringInterest;
        boolean isNumbers = false;
        double balance, interestRate;
        do{
            do {
                stringBalance = JOptionPane.showInputDialog("Enter the balance "
                        + "for your new savings account.");
                if (stringChecker(stringBalance)) {
                    isNumbers = true;
                }
            } while (!isNumbers);

            isNumbers = false;

            do {
                stringInterest = JOptionPane.showInputDialog("Enter the annual "
                        + "interest rate for your new savings account.");
                if (stringChecker(stringInterest)) {
                    isNumbers = true;
                }
            } while (!isNumbers);

            double number = Double.parseDouble(stringBalance);
            balance =  Math.round(number * 100.0) / 100.0;

            lookGood = JOptionPane.showInputDialog("Is this the "
                    + "correct balance and interest rate\nyou would like to enter?\n"
                    + "\nBalance: $" + balance
                    + "\nInterest Rate: " + stringInterest + "%"
                    + "\n\n(Y)es or any key for No");

            interestRate = Double.parseDouble(stringInterest);

            if (lookGood == null)
                System.exit(0);

        }while(!lookGood.equalsIgnoreCase("Y"));

        SavingsAccount savings = new SavingsAccount(balance, interestRate);

        int option;
        do{
            option = showMenu();

            switch (option) {
                case 1 -> {
                    double deposit = checkDepositWithdrawal(1);
                    savings.makeDeposit(deposit);
                }
                case 2 -> {
                    double withdrawal = checkDepositWithdrawal(2);
                    savings.makeWithdrawal(withdrawal);
                }
                case 3 -> JOptionPane.showMessageDialog(null, "Account balance: $" +
                        String.format("%.2f", savings.getBalance()));
                case 4 -> savings.getInfo();
                case 5 -> savings.accountMonthlyProcess();
                case 6 -> System.exit(0);
            }
        }while(true);
    }

    //Check string to make sure the input is a digit and nothing else
    public static boolean stringChecker(String str){
        if (str == null)
            System.exit(0);

        if (str.length() == 0)
            return false;

        char[] chArray = str.toCharArray();
        for (char ch : chArray) {
            if ((int) ch == 46)
                continue;
            if (((int) ch > 58 || (int) ch < 47))
                return false;
        }
        return true;
    }


    //Show the menu and ask the user to select an option.
    public static int showMenu(){
        boolean isNumber = false;
        String stringOption;
        do{
            stringOption = JOptionPane.showInputDialog("""
                    Savings Account Menu:
                    1. Make a deposit.
                    2. Make a withdrawal.
                    3. Get account balance.
                    4. Get account info.
                    5. Generate the end of month statement for the account.
                    6. Close menu and end program.""");
            if (stringChecker(stringOption))
                isNumber = true;
        }while(!isNumber);
        return Integer.parseInt(stringOption);
    }


    //Use the method to ask the user for a deposit or withdrawal and check to
    //make sure they are digits and nothing else
    public static double checkDepositWithdrawal(int caseChoice){
        double withdrawal = 0, deposit = 0;
        boolean isNumbers = false;

        if (caseChoice == 1) {
            do {
                String stringDeposit = JOptionPane.showInputDialog("Enter "
                        + "the deposit amount.");
                if (stringChecker(stringDeposit)) {
                    deposit = Double.parseDouble(stringDeposit);
                    isNumbers = true;
                }
            } while (deposit < 0 || !isNumbers);
            return deposit;
        }
        if (caseChoice == 2) {
            do {
                String stringWithdrawal = JOptionPane.showInputDialog("Enter "
                        + "the withdrawal amount.");
                if (stringChecker(stringWithdrawal)) {
                    withdrawal = Double.parseDouble(stringWithdrawal);
                    isNumbers = true;
                }
            } while (withdrawal < 0 || !isNumbers);
            return withdrawal;
        }
        return 0;
    }

    @Override
    public void start(Stage stage)  {

    }
}
