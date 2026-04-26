package org.example;

import java.io.FileReader;
import java.util.List;

public class TransactionManager {
    public static void main(String[] args) {
        List<Transaction> transactionList = FileReader.getTransactions();

        for(Transaction e : transactionList){
            System.out.printf("Date: %s Time: %s Description: %s Vendor: %s Amount: %f",
                    e.getDate(), e.getTime(), e.getDescription(), e.getVendor(), e.getAmount());
        }
    }
}
