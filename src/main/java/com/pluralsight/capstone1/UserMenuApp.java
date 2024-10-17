package com.pluralsight.capstone1;

import java.io.IOException;
import java.util.Scanner;

public class UserMenuApp
{
    static Scanner scan;

    public static void main(String[] args) throws IOException
    {
        ReadSystem readSystem = new ReadSystem();
        boolean menuRunning = true;
        scan = new Scanner(System.in);
        // Menu topper to avoid reprinting

        System.out.println("""
                    *********************
                    **Accounting Ledger**
                    *********************
                
                """);

        // Main menu function
        while (menuRunning) {
            System.out.println("""
                    
                    Please Select an option.
                    
                    D) Add Deposit
                    
                    P) Make Payment
                    
                    L) View Ledger
                    
                    X) Exit
                    """);
            String userInput = scan.nextLine();

            switch (userInput) {
                case "D":

                    //call makeDeposit method

                    return;

                case "P":

                    return;

                case "L":
                    System.out.println(readSystem.readData());


                    return ;

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

    static void makeDeposit()
    {

        System.out.println("deposit amount");
        double depo = Double.parseDouble(scan.nextLine().trim());

        //vendor

        //description

        //using these inuts from the user to create an entry for the csv file.
        //


    }

}
