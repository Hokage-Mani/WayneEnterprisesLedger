package org.example;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        printWelcomeHeader();


        List<Transaction> transactionList = FileHandler.getTransactions();
        System.out.println("System initialized. " + transactionList.size() + " transactions loaded.\n");

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- MAIN MENU ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment(Debit)");
            System.out.println("L) Enter Ledger");
            System.out.println("X) Exit");
            System.out.print("Select an option: ");

            //String input = scanner.nextLine();
            String input = scanner.nextLine().toUpperCase();


            switch (input) {
                case "D":
                    System.out.println("\n-- Deposit Menu --");
                    System.out.println("Enter Date (YYYY-MM-DD) or press Enter for today");
                    String dateInput = scanner.nextLine();
                    LocalDate date;
                    if(dateInput.isEmpty()){
                        date = LocalDate.now();
                    }else {
                        date = LocalDate.parse(dateInput);
                    }
                    LocalTime time = LocalTime.now().withNano(0);
                    System.out.println("Enter vendor (Your Name or Client Bank)");
                    String vendor = scanner.nextLine();

                    System.out.println("Enter description of deposit");
                    String description = scanner.nextLine();

                    System.out.println("Enter deposit amount: $");
                    double amount = Double.parseDouble(scanner.nextLine());

                    if(amount <0){
                        amount = amount *-1;
                    }
                    boolean isDeposited = true;
                    Transaction newDeposit = new Transaction(date, time, description, vendor, amount, isDeposited);

                  //Might cause an error at some point
                     FileHandler.saveTransaction(newDeposit);

                    transactionList.add(newDeposit);

                    System.out.println("\nSUCCESS: Deposit of $" + amount + " for " + description + " recorded.");

                    break;

                case "P":
                    System.out.println("\n=== Enter Debit Information ===");
                    System.out.println("Enter Payee/Vendor name: ");
                    String payee = scanner.nextLine();
                    System.out.println("Enter Payment Description: ");
                    String payDescription = scanner.nextLine();
                    System.out.println("Enter invoice/reference number or ENTER to skip");
                    String invoice = scanner.nextLine();
                    System.out.println("Enter payment amount: ");
                    double paymentAmount = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter payment method (Wire, ACH, Card: ");
                    String paymentMethod = scanner.nextLine();
                    System.out.println("Confirm payment? (Y/N): ");
                    String confirmChoice = scanner.nextLine();

                    if(confirmChoice.equalsIgnoreCase("y")){
                        System.out.println("\nSUCCESS: Payment of $" + paymentAmount + " From: " + payee + " By: " +
                                invoice + paymentMethod + " For: " + payDescription);
                    }




                    break;

                case "L":
                    boolean inLedgerMenu = true;

                    while (inLedgerMenu) {
                        System.out.println("\n-- LEDGER MENU --");
                        System.out.println("A) Display all entries");
                        System.out.println("D) Deposits");
                        System.out.println("P) Payments");
                        System.out.println("R) Reports");
                        System.out.println("H) Return Home");
                        System.out.print("Select an option: ");

                        String ledgerInput = scanner.nextLine().toUpperCase();

                        //switch case for the Ledger since I have a menu inside a menu
                        switch (ledgerInput) {
                            case "A":
                                System.out.println("\n[=== Showing All Entries ===]");
                                for (Transaction tx : transactionList) {
                                    // Use the exact same printf you used for the Deposits!
                                    System.out.printf("Date: %s | Time: %s | Vendor: %s | Amount: $%,.2f%n",
                                            tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount());
                                }
                                break;
                            case "D":
                                System.out.println("\n[=== Showing Only Deposits ===]");
                                for(Transaction tx : transactionList){
                                    if(tx.isDeposited()){
                                        System.out.printf("Date: %s | Time: %s | Vendor %s | Amount: %,.2f %n",
                                                tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount());
                                    }
                                }

                                break;
                            case "P":
                                System.out.println("\n[=== Showing Only Payments ===]");
                                for(Transaction tx : transactionList){
                                    if(!tx.isDeposited()){
                                        System.out.printf("Date: %s | Time: %s | Vendor: %s | Amount: $%,.2f %n",
                                                tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount(), tx.getAmount());
                                    }
                                }


                                break;

                                case "R":
                                    boolean inReportsMenu = true;

                                    while(inReportsMenu){
                                        System.out.println("\n[=== Opening Reports Menu ===]");
                                        System.out.println("1) Month To Date");
                                        System.out.println("2) Previous Month");
                                        System.out.println("3) Year To Date");
                                        System.out.println("4) Previous Year");
                                        System.out.println("5) Search by Vendor");
                                        System.out.println("0) Back");
                                        System.out.print("Select a report to run: ");

                                        String reportInput = scanner.nextLine();
                                        LocalDate today = LocalDate.now();

                                        switch (reportInput){
                                            case "1":
                                                System.out.println("\n[=== Month To Date ===]");
                                                for (Transaction tx : transactionList) {
                                                    if (tx.getDate().getYear() == today.getYear() &&
                                                            tx.getDate().getMonth() == today.getMonth()) {

                                                        System.out.printf("Date: %s | Time: %s | Vendor: %s | Amount: $%,.2f%n",
                                                                tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount());
                                                    }
                                                }
                                                break;
                                            case"2":
                                                System.out.println("\n[=== Previous Month ===]");
                                                LocalDate lastMonthDate = today.minusMonths(1);

                                                for (Transaction tx : transactionList) {
                                                    if (tx.getDate().getYear() == lastMonthDate.getYear() &&
                                                            tx.getDate().getMonth() == lastMonthDate.getMonth()) {

                                                        System.out.printf("Date: %s | Time: %s | Vendor: %s | Amount: $%,.2f%n",
                                                                tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount());
                                                    }
                                                }
                                                break;
                                            case"3":
                                                System.out.println("\n[=== Year To Date ===]");
                                                for (Transaction tx : transactionList) {
                                                    if (tx.getDate().getYear() == today.getYear()) {

                                                        System.out.printf("Date: %s | Time: %s | Vendor: %s | Amount: $%,.2f%n",
                                                                tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount());
                                                    }
                                                }
                                                break;
                                            case"4":
                                                System.out.println("\n[=== Previous Year ===]");
                                                for (Transaction tx : transactionList) {
                                                    if (tx.getDate().getYear() == today.getYear() - 1) {

                                                        System.out.printf("Date: %s | Time: %s | Vendor: %s | Amount: $%,.2f%n",
                                                                tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount());
                                                    }
                                                }
                                                break;
                                            case"5":
                                                System.out.print("\nEnter vendor name to search: ");
                                                String searchVendor = scanner.nextLine();
                                                System.out.println("\n[=== Results for: " + searchVendor + " ===]");

                                                boolean foundMatch = false;

                                                for (Transaction tx : transactionList) {
                                                    if (tx.getVendor().equalsIgnoreCase(searchVendor)) {
                                                        System.out.printf("Date: %s | Time: %s | Vendor: %s | Amount: $%,.2f%n",
                                                                tx.getDate(), tx.getTime(), tx.getVendor(), tx.getAmount());
                                                        foundMatch = true;
                                                    }
                                                }

                                                if (!foundMatch) {
                                                    System.out.println("No transactions found for that vendor.");
                                                }
                                                break;
                                            case"0":
                                                System.out.println("\nReturning to Ledger Menu...");
                                                inReportsMenu = false;
                                                break;
                                        }
                                    }


                                break;
                            case "H":
                                System.out.println("\nReturning to Main Menu...");
                                inLedgerMenu = false;
                                break;
                            default:
                                System.out.println("\nInvalid Ledger option. Please try again.");
                        }
                    }

                    break;


                    //Was tje easiest option to start with and get done being that it's simply exiting the progrgam

                    case "X":
                    System.out.println("\nLogging out of Wayne Enterprises Ledger. Goodbye.");
                    isRunning = false; // This breaks the loop and ends the program
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    // Keeping the main neat and clean for a better user experience!
    private static void printWelcomeHeader() {
        System.out.println("=====================================================================");
        System.out.println(" __        __                           ");
        System.out.println(" \\ \\      / /_ _ _   _ _ __   ___       ");
        System.out.println("  \\ \\ /\\ / / _` | | | | '_ \\ / _ \\      ");
        System.out.println("   \\ V  V / (_| | |_| | | | |  __/      ");
        System.out.println("    \\_/\\_/ \\__,_|\\__, |_| |_|\\___|      ");
        System.out.println("                 |___/                  ");
        System.out.println("     E N T E R P R I S E S   L E D G E R   S Y S T E M");
        System.out.println("=====================================================================");
        System.out.println("               *** AUTHORIZED PERSONNEL ONLY ***");
        System.out.println("=====================================================================\n");
    }
}