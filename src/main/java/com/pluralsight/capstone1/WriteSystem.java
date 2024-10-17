package com.pluralsight.capstone1;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WriteSystem
{

    boolean isWriting = true;

    String writer() throws IOException
    {
        //Utilities utilities = new Utilities();

        while (isWriting) {

            try {
                FileWriter fWriter = new FileWriter("./src/main/resources/transactions.csv");

                BufferedWriter bWriter = new BufferedWriter(fWriter);
                PrintWriter pWriter = new PrintWriter(bWriter);


                // Need to confirm formatting
                pWriter.println("");

                // Does it need sample writing?

                bWriter.close();
                System.out.println("Ledger successfully updated.");

            } catch (IOException e) {
                System.out.println("Error! Unable to write to ledger");
            }

        }
        return writer();
    }
}

class ReadSystem
{
    boolean isReading = true;

    public List<Transaction> readData() throws IOException
    {
        String ledgerFile = "./src/main/resources/transactions.csv";
        List<Transaction> allTrans = new ArrayList<>();


        try (BufferedReader bReader = new BufferedReader(new FileReader(ledgerFile))) {


            String line;
            while ((line = bReader.readLine()) != null) {

                String[] lineArr = line.split("\\|");

                //create a new trasncation object
                Transaction newTrans = new Transaction();

                if (line.length() == 5) {


                    // Date,Description,Vendor,Amount

                    newTrans.setDate(LocalDateTime.from(LocalDate.parse(lineArr[0])));
                    newTrans.setTime(LocalDateTime.from(LocalTime.parse(lineArr[1])));
                    newTrans.setDescription(lineArr[2]);
                    newTrans.setVendor(lineArr[3]);
                    newTrans.setAmount(Double.parseDouble(lineArr[4]));

                    allTrans.add(newTrans);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Unable to read data.");
        }
        return allTrans;
    }
}
