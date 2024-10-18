package com.pluralsight.capstone1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

// Could use class to make "Utilities" class
// for future project library

public class UserMenuApp
{
    // Declarations
    static LedgerData ledgerData = new LedgerData();
    static Scanner scan = new Scanner(System.in);
    static boolean menuRunning = true;

    public static void main(String[] args) throws IOException
    {
        // Menu topper

        System.out.println("""
                    *********************
                    **Accounting Ledger**
                    *********************
                
                """);

        // Main menu function
        while (menuRunning) {
            System.out.println("""
                    
                    Please Select an option.
                    
                    D: Add Deposit
                    
                    P: Make Payment
                    
                    L: View Ledger
                    
                    X: Exit
                    
                    """);
            String userInput = scan.nextLine().toUpperCase();

            switch (userInput) {
                case "D":
                    // Deposit

                    makeDeposit();
                    break;

                case "P":
                    // Payment
                    makeTransaction("Payment");
                    break;

                case "L":
                    // Ledger
                    ledgerMenu();
                    break;

                case "X":
                    menuRunning = false;

                    break;

                default:
                    System.out.println("""
                            I am sorry, I don't understand.
                            Please select from the following.""");
                    return;
            }
        }
    }

    // Data grab for Transaction

    static void makeTransaction (String type) throws IOException
    {
        System.out.println(type + " selected." );

        System.out.println("Vendor: ");
        String vendor = scan.nextLine();

        System.out.println("Description: ");
        String description = scan.nextLine();

        System.out.println("Amount: ");
        double amount = Double.parseDouble(scan.nextLine().trim());

        Transaction newTrans = new Transaction(LocalDate.now(), LocalTime.now(),
                vendor, description, amount);
        ledgerData.dataWriter(newTrans);
    }

//    static void viewLedger() throws IOException {
//        List<Transaction> allTrans = ledgerData.dataReader();
//        if(allTrans.isEmpty()) {
//            System.out.println("Unable to find transactions.");
//        } else {
//            for (Transaction trans : allTrans) {
//                System.out.println(trans);
//            }
//        }
//    }

    // Data grab for Deposit
    // Could have combined both data
    // grabs into one method with if()

    static void makeDeposit() throws IOException
    {

        System.out.println("Vendor: ");
        String vendor = scan.nextLine();

        System.out.println("Description: ");
        String description = scan.nextLine();

        System.out.println("Amount: ");
        double amount = Double.parseDouble(scan.nextLine().trim());

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transaction newTrans = new Transaction(date, time, vendor, description, amount);

        // Save this transaction to the ledger file
        ledgerData.dataWriter(newTrans);


    }
    // Ledger menu system
    // Calls ledger data from class

    static void ledgerMenu()
    {
        boolean ledgerMenuRunning = true;

        while (ledgerMenuRunning) {
            System.out.println("""
                         Ledger Options
                    
                    Please Enter an option below:
                    
                    A: All Entries
                    
                    D: Deposits Only
                    
                    P: Payments Only
                    
                    R: Reports
                    
                    H: Home
                    
                    """);
            String userInput = scan.nextLine().toUpperCase();
            try {
                switch (userInput) {
                    case "A":
                        System.out.println(ledgerData.dataReader());
                        break;

                    case "D":
                        ledgerData.viewDeposits();
                        break;

                    case "P":
                        ledgerData.viewPayments();
                        break;

                    case "R":
                        LedgerData.reportMenu(ledgerData);
                        break;

                    case "H":
                        ledgerMenuRunning = false;
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


}
