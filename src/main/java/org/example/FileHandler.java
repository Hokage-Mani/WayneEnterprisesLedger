package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<Transaction> getTransactions() throws FileNotFoundException {

        List<Transaction> TransactionList = new ArrayList<>();

        try {
            java.io.FileReader fileReader = new java.io.FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;

            while ((input = bufferedReader.readLine()) != null) {
                String[] csvRow = input.split("\\|");
                LocalDate date = LocalDate.parse(csvRow[0]);
                LocalTime time = LocalTime.parse(csvRow[1]);
                String description = csvRow[2];
                String vendor = csvRow[3];
                double amount = Double.parseDouble(csvRow[4]);

                // Trying to make it so that Java figures out the boolean automatically
                // without needing it in the text file with a isDeposited
                boolean isDeposited = amount > 0;

                Transaction tx = new Transaction(date, time, description, vendor, amount, isDeposited);
                TransactionList.add(tx);
            }
        } catch (IOException e) {
            System.out.println("There was an error with the file");
        }


        return TransactionList;
    }
    public static void saveTransaction(Transaction newTx) {


        String lineToWrite = newTx.getDate() + "|" +
                newTx.getTime() + "|" +
                newTx.getDescription() + "|" +
                newTx.getVendor() + "|" +
                newTx.getAmount();

        try (FileWriter fw = new FileWriter("src/main/resources/transactions.csv", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(lineToWrite);

        } catch (IOException e) {
            System.out.println("Error saving transaction to file.");
        }
    }

}

