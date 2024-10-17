package com.pluralsight.capstone1;

import java.time.LocalDateTime;

public class Transaction
{
    //fields
    LocalDateTime time;
    LocalDateTime date;
    String vendor;
    String description;
    double amount;

    public Transaction(){

    }

    public Transaction(LocalDateTime time, LocalDateTime date, String vendor, String description, double amount)
    {
        this.time = time;
        this.date = date;
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
    }

    //getter and setters

    public LocalDateTime getTime()
    {
        return time;
    }

    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
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

    // stirng

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
