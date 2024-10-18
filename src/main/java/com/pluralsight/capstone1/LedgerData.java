package com.pluralsight.capstone1;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class LedgerData
{

    // Writing system
    public void dataWriter(Transaction transaction) throws IOException
    {
        try
                (FileWriter fWriter = new FileWriter("./src/main/resources/transactions.csv");
                 BufferedWriter bWriter = new BufferedWriter(fWriter);
                 PrintWriter pWriter = new PrintWriter(bWriter)) {


            pWriter.append(String.valueOf(transaction.getDate())).append(" | ").append(String.valueOf
                    (transaction.getTime())).append(" | ").append(transaction.getVendor()).append
                    (" | ").append(transaction.getDescription()).append(" | ").append(String.valueOf
                    (transaction.getAmount()));

            System.out.println("Ledger successfully updated!");
        } catch (IOException e) {
            System.out.println("Error! Unable to write to ledger" + e.getMessage());
            throw e;
        }

    }


    //Reading system
    public List<Transaction> dataReader() throws IOException
    {
        StringBuilder ledgerFile = new StringBuilder("./src/main/resources/transactions.csv");
        List<Transaction> allTrans = new ArrayList<>();

        try (BufferedReader bReader = new BufferedReader(new FileReader(String.valueOf(ledgerFile)))) {

            String line;
            while ((line = bReader.readLine()) != null) {
                String[] lineArr = line.split("\\|");

                //create a new transaction object

                if (lineArr.length == 5) {
                    Transaction newTrans = new Transaction(
                            LocalDate.parse(lineArr[0].trim()),
                            LocalTime.parse(lineArr[1].trim()),
                            lineArr[2].trim(),
                            lineArr[3].trim(),
                            Double.parseDouble(lineArr[4].trim())
                    );

                    allTrans.add(newTrans);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error! Unable to read data." + e.getMessage());
            throw e;
        }
        return allTrans;
    }

    public void viewDeposits() throws IOException
    {
        List<Transaction> allTrans = dataReader();
        for (Transaction t : allTrans) {
            if (t.getAmount() > 0) {
                System.out.println(t);
            }
        }
    }

    public void viewPayments() throws IOException
    {
        List<Transaction> allTrans = dataReader();
        for (Transaction t : allTrans) {
            if (t.getAmount() < 0) {
                System.out.println(t);
            }
        }
    }

    //Ledger reports

    static void reportMenu(LedgerData ledgerData)
    {
        boolean ledgerReporting = true;

        while (ledgerReporting) {
            System.out.println("""
                    Reports Menu:
                    
                    1: Month to Date
                    
                    2: Previous Month
                    
                    3: Year to Date
                    
                    4: Previous Year
                    
                    5: Vendor Search
                    
                    0: Back to Ledger Menu
                    
                    """);
            String userInput = UserMenuApp.scan.nextLine();
            try {
                switch (userInput) {
                    case "1":
                        ledgerData.viewMonthToDate();
                        break;

                    case "2":
                        ledgerData.viewPreviousMonth();
                        break;

                    case "3":
                        ledgerData.viewYearToDate();
                        break;

                    case "4":
                        ledgerData.viewPreviousYear();
                        break;

                    case "5":
                        System.out.println("Enter vendor name: ");
                        String vendor = UserMenuApp.scan.nextLine();
                        ledgerData.vendorSearch(vendor);
                        break;

                    case "0":
                        ledgerReporting = false;
                        break;

                    default:
                        System.out.println("I don't understand." +
                                           "\nPlease select a valid option.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Ledger data sorting
    // Replace "get" with "fetch"


    public void viewMonthToDate() throws IOException
    {
        List<Transaction> allTrans = dataReader();
        LocalDate now = LocalDate.now();
        List<Transaction> monthToDate = new ArrayList<>();
        for (Transaction t : allTrans) {
            if (t.getDate().getMonth() == now.getMonth() && t.getDate().getYear() == now.getYear()) {
                monthToDate.add(t);
            }
        }
        if (monthToDate.isEmpty()) {
            System.out.println("No transactions found for this month.");
        } else {
            for (Transaction t : monthToDate) {
                System.out.println(t);
            }
        }
    }

    public void viewPreviousMonth() throws IOException
    {
        List<Transaction> allTrans = dataReader();
        LocalDate now = LocalDate.now();
        LocalDate startOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfLastMonth = now.withDayOfMonth(1).minusDays(1);
        List<Transaction> previousMonth = new ArrayList<>();
        for (Transaction t : allTrans) {
            if (!t.getDate().isBefore(startOfLastMonth) && !t.getDate().isAfter(endOfLastMonth)) {
                previousMonth.add(t);
            }
        }
        if (previousMonth.isEmpty()) {
            System.out.println("No transactions found for the previous month.");
        } else {
            for (Transaction t : previousMonth) {
                System.out.println(t);
            }
        }
    }

    public void viewYearToDate() throws IOException
    {
        List<Transaction> allTrans = dataReader();
        LocalDate now = LocalDate.now();
        List<Transaction> yearToDate = new ArrayList<>();
        for (Transaction t : allTrans) {
            if (t.getDate().getYear() == now.getYear()) {
                yearToDate.add(t);
            }
        }
        if (yearToDate.isEmpty()) {
            System.out.println("No transactions found for this year.");
        } else {
            for (Transaction t : yearToDate) {
                System.out.println(t);
            }
        }
    }

    public void viewPreviousYear() throws IOException
    {
        List<Transaction> allTrans = dataReader();
        LocalDate now = LocalDate.now();
        int lastYear = now.getYear() - 1;
        List<Transaction> previousYear = new ArrayList<>();
        for (Transaction t : allTrans) {
            if (t.getDate().getYear() == lastYear) {
                previousYear.add(t);
            }
        }
        if (previousYear.isEmpty()) {
            System.out.println("No transactions found for the previous year.");
        } else {
            for (Transaction t : previousYear) {
                System.out.println(t);
            }
        }
    }

    public void vendorSearch(String vendor) throws IOException
    {
        List<Transaction> allTrans = dataReader();
        List<Transaction> vendorTransactions = new ArrayList<>();
        for (Transaction t : allTrans) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                vendorTransactions.add(t);
            }
        }
        if (vendorTransactions.isEmpty()) {
            System.out.println("No transactions found for vendor: " + vendor);
        } else {
            for (Transaction t : vendorTransactions) {
                System.out.println(t);
            }
        }
    }
}