package org.example;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
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

            String input = scanner.nextLine();


            switch (input) {
                case "D":
                    System.out.println("\n-- Deposit Menu --");
                    System.out.println("");

                    break;

                case "P":
                    System.out.println("\n-- Enter Debit Information --");

                    break;

                case "L":
                    // 1. Create a new boolean just for this sub-menu
                    boolean inLedgerMenu = true;

                    // 2. Start the inner loop
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
                                System.out.println("\n[ Showing All Entries ]");

                                break;
                            case "D":
                                System.out.println("\n[ Showing Only Deposits ]");

                                break;
                            case "P":
                                System.out.println("\n[ Showing Only Payments ]");

                                break;
                            case "R":
                                System.out.println("\n[ Opening Reports Menu ]");

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














                    case "X":
                    System.out.println("\nLogging out of Wayne Enterprises Ledger. Goodbye.");
                    isRunning = false; // This breaks the loop and ends the program
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
            System.out.println(); // Prints a blank line for readability before the menu repeats
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