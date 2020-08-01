package com.codedifferently.bankaccountlab;

public class BankAccount {
    import java.util.Random;
import java.util.Scanner;

public abstract class BankAccount
{
    private int accountNumber;
    private double fees;
    private double balance;
    private String password;

    public BankAccount()
    {
        this.accountNumber = 123456789;
    }
    public BankAccount(int accountNumber, double balance, double fees)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.fees = fees;
        this.password = password;
    }

    public double getFees()
    {
        return this.fees;
    }
    public void setFees(double fees)
    {
        this.fees = fees;
    }

    public abstract int getAccountNumber();
    public abstract void setAccountNumber(int acctNumber);


    public double getBalance()
    {
        return balance;
    }
    public String getPassword()
    {
        return password;
    }

    public void deposit(double num)
    {
        this.balance += num;
    }

    public boolean withdraw(double num)
    {
        if(num > getBalance())
        {
            System.out.println("Sorry, the desired amount is greater than user's available funds.");
            return false;
        }
        else
        {
            this.balance -= num;
            return true;
        }
    }

    @Override
    public String toString()
    {
        return getAccountNumber() + "";
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.toString().equals(obj.toString());
    }
}
