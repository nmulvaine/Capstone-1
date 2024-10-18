package com.pluralsight.capstone1;

import java.time.LocalDate;
import java.time.LocalTime;


//
// Need to have deposit and transaction
// Getting user data to plug into vars
//


public class Transaction
{
    //static LedgerData ledgerData;
    private static UserMenuApp menuApp;
    private LocalTime time;
    private LocalDate date;
    private String vendor;
    private String description;
    private double amount;


    public Transaction(LocalDate date, LocalTime time, String vendor, String description, double amount)
    {
        this.time = time;
        this.date = date;
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
    }


    // Getters and Setters
    // Clean out unused

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public LocalTime getTime()
    {
        return time;
    }

    public void setTime(LocalTime time)
    {
        this.time = time;
    }

    public String getVendor()
    {

        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

//    public Transaction userPrompt() throws InterruptedException
//    {
//        // Primary method for Transaction class
//        // Need to get vendor, description, amount from user
//        // Time and date are from the system
//
//        System.out.println("Please provide the following information for " +
//                           "\nThe provided information will be added to your ledger.");
//        Thread.sleep(1500);
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//
//        // Collect user amounts
//        System.out.println("Vendor: ");
//        this.vendor = UserMenuApp.scan.nextLine();
//
//        System.out.println("Description: ");
//        this.description = UserMenuApp.scan.nextLine();
//
//        System.out.println("Amount: ");
//        this.amount = Double.parseDouble(UserMenuApp.scan.nextLine());
//
//        this.date = LocalDate.now();
//        this.time = LocalTime.now();
//
//        return this;
//    }


    @Override
    public String toString()
    {
        return "Transaction{" +
               "time=" + time +
               ", date=" + date +
               ", vendor='" + vendor + '\'' +
               ", description='" + description + '\'' +
               ", amount=" + amount +
               '}';

    }

}
