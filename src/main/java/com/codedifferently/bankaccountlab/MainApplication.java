package com.codedifferently.bankaccountlab;

public class MainApplication {
    import java.util.ArrayList;
import java.util.Scanner;

public class MainApplication
{

    private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    int currentAccountIndex = 0;

    Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        new MainApplication().mainMenu();
    }

    public void mainMenu()
    {
        System.out.println("Welcome to the Bank of Allen, select the following options: ");

        System.out.println("1) Create an Account");
        System.out.println("2) Login to your account");
        System.out.println("3) Created Accounts ");
        System.out.println("4) Exit ATM");

        int inputNumber = scan.nextInt();

        switch(inputNumber)
        {
            case 1:
                createAccount();
                break;
            case 2:
                loginToAccount();
                break;
            case 3:
                listMyAccounts();
                break;
            case 4:
                System.out.println("Come again!");
                break;
        }
    }

    public void listMyAccounts()
    {
        if(accounts.size() == 0)
        {
            System.out.println("No accounts created, going back to main menu");
            System.out.println();
            mainMenu();
        }
        else
        {
            System.out.println("These are your accounts");
            System.out.println(accounts);
            System.out.println();

            mainMenu();
        }
    }

    public void createAccount()
    {
        System.out.println("Welcome, since you are new here, create an account!");

        System.out.println("1) Checking Account");
        System.out.println("2) Savings Account");
        System.out.println("3) Business Account");
        System.out.println("4) Back to Menu / Exit");

        int inputNumber = scan.nextInt();
        switch(inputNumber)
        {
            case 1:
                createCheckingAccount();
                break;
            case 2:
                createSavingsAccount();
                break;
            case 3:
                createBusinessAccount();
                break;
            case 4:
                System.out.println("Returning to Main Menu");
                mainMenu();
                break;
            default:
                System.out.println("Not an available selection, select again");
                createAccount();
                break;
        }
    }

    public void createCheckingAccount()
    {
        System.out.println("Awesome! Now enter the information below: ");
        System.out.println("Enter your name");
        String name = scan.next();

        System.out.println("Create your account number");
        int acctNumber = scan.nextInt();

        System.out.println("Password for account: ");
        String password = scan.next();

        System.out.println("What will your first deposit be?");
        double initalDeposit = scan.nextDouble();

        BankAccount checkingAccount = new CheckingAccount(name, password, acctNumber, initalDeposit);

        accounts.add(checkingAccount);

        System.out.println("Your account has been successfully created! Returning to Main Menu");
        mainMenu();
    }

    public void createSavingsAccount()
    {
        System.out.println("Enter your name: ");
        String name = scan.next();

        System.out.println("Create your account number");
        int acctNumber = scan.nextInt();

        System.out.println("Password for account: ");
        String password = scan.next();

        System.out.println("What will your first deposit be?");
        double initialDeposit = scan.nextDouble();

        BankAccount savingsAccount = new SavingsAccount(name, password, acctNumber, initialDeposit);
        accounts.add(savingsAccount);

        System.out.println("Your account has been successfully created! Returning to main menu");
        mainMenu();
    }

    public void createBusinessAccount()
    {
        System.out.println("Enter Business Name: ");
        String businessName = scan.next();

        System.out.println("Create your account number");
        int acctNumber = scan.nextInt();

        System.out.println("Enter password: ");
        String password = scan.next();

        System.out.println("What is your taxId");
        int taxId = scan.nextInt();

        System.out.println("What will your first deposit be?");
        double initialDeposit = scan.nextDouble();

        BankAccount businessAccount = new BusinessAccount(businessName, taxId, initialDeposit, password, acctNumber);
        accounts.add(businessAccount);

        System.out.println("Your account has been successfully created! Returning to the main menu");
        mainMenu();
    }


    public void loginToAccount()
    {
        System.out.println("What's your accountNumber");
        int accountNumber = scan.nextInt();

        if(searchAccountsAccountNumber(accountNumber))
        {
            System.out.println("Enter account password: ");
            String inputPassword = scan.next();

            if(checkPassword(inputPassword))
            {
                System.out.println("Awesome! Login success");
                loginChoices();
            }
            else
            {
                System.out.println("Wrong password, for the account's safety, we are returning you to the menu.");
                mainMenu();
            }
        }
        else
        {
            System.out.println("This account doesn't exist, going back home");
            mainMenu();
        }
    }

    public void loginChoices()
    {
        System.out.println("Welcome! Here is a list of options to chose from");

        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Check Balance");
        System.out.println("4) Delete Account");
        System.out.println("5) Log out");

        int userChoice = scan.nextInt();

        //non plural
        loginChoice(userChoice);
    }

    public void loginChoice(int userChoice)
    {
        switch(userChoice)
        {
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                accountBalance();
                break;
            case 4:
                removeAccount();
                break;
            case 5:
                logout();
                break;
        }
    }

    public void logout()
    {
        System.out.println("You have logged out, goodbye");
        currentAccountIndex = 0;
        mainMenu();
    }

    public void deposit()
    {
        System.out.println("Enter deposit amount");
        double depositAmt = scan.nextDouble();

        //gets the most recent account in use, and deposits
        accounts.get(currentAccountIndex).deposit(depositAmt);

        System.out.println("Your new balance is: " + accounts.get(currentAccountIndex).getBalance());
        loginChoices();
    }
    public void withdraw()
    {
        System.out.println("Enter withdraw amount");
        double withdrawAmt = scan.nextDouble();

        //makes a depostit utilizing the most recent account
        if(accounts.get(currentAccountIndex).withdraw(withdrawAmt))
        {
            System.out.println("Your new balance is: " + accounts.get(currentAccountIndex).getBalance());
        }
        else
        {
            //Withdraw error message
            accounts.get(currentAccountIndex).withdraw(withdrawAmt);
        }

        loginChoices();
    }

    public void accountBalance()
    {
        System.out.println("Account balance is: " +
                accounts.get(currentAccountIndex).getBalance());
        loginChoices();
    }

    public void removeAccount()
    {
        System.out.println("Are you certain you would like to delete your Account?");
        String input = scan.next();

        if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("Y"))
        {
            accounts.remove(accounts.get(currentAccountIndex));
            System.out.println("The account has been deleted");
            mainMenu();
        }
        else
        {
            System.out.println("Account not deleted");
            System.out.println("If you would like to delete your account");
            System.out.println("Make sure you typed 'yes' or 'y' ");
            mainMenu();
        }

    }

    public boolean searchAccountsAccountNumber(int accountNumber)
    {
        for(int i = 0; i < accounts.size(); i++)
        {
            BankAccount b = accounts.get(i);
            if(b.getAccountNumber() == accountNumber)
            {
                currentAccountIndex = i;
                return true;
            }
        }

        return false;
    }

    public boolean checkPassword(String password)
    {
        for(int i = 0; i < accounts.size(); i++)
        {
            String pass = accounts.get(i).getPassword();
            if(pass.equals(password))
            {

                currentAccountIndex = i;
                return true;
            }
            System.out.print(pass);
        }
        return false;
    }


}
