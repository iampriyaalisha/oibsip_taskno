import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM_Interface {
    private static int balance = 10000;
    private static ArrayList<String> transactionHistory = new ArrayList<>();
    private static int userPIN = 1234; // Set a default PIN for demonstration purposes.

    public static void printMenu() {
        System.out.println("Automated Teller Machine");
        System.out.println("Choose 1 for Transaction History");
        System.out.println("Choose 2 for Withdraw");
        System.out.println("Choose 3 for Deposit");
        System.out.println("Choose 4 for Transfer");
        System.out.println("Choose 5 to Quit");
        System.out.print("Choose the operation you want to perform:");
    }

    public static void main(String args[]) {
        int withdraw, deposit;
        Scanner sc = new Scanner(System.in);
          
        //Prompt the user to enter their PIN
        System.out.print("Enter your PIN: ");
        int enteredPIN = getUserPIN(sc);

        // Authenticate the user
        if (authenticateUser(enteredPIN)) {
            System.out.println("Authentication successful. Welcome!");

            while (true) {
                printMenu();

                int choice = getUserChoice(sc);

                switch (choice) {
                    case 1:
                        displayTransactionHistory();
                        break;

                    case 2:
                        System.out.print("Enter money to be withdrawn:");
                        withdraw = getPositiveIntegerInput(sc);
                        if (balance >= withdraw) {
                            balance -= withdraw;
                            addTransaction("Withdrawn $" + withdraw);
                            System.out.println("Please collect your money");
                        } else {
                            System.out.println("Insufficient Balance");
                        }
                        break;

                    case 3:
                        System.out.print("Enter money to be deposited:");
                        deposit = getPositiveIntegerInput(sc);
                        balance += deposit;
                        addTransaction("Deposited $" + deposit);
                        System.out.println("Your money has been successfully deposited.");
                        break;

                    case 4:
                        System.out.print("Enter the account number to transfer to: ");
                        int targetAccount = getPositiveIntegerInput(sc);

                        if (targetAccountExists(targetAccount)) {
                            System.out.print("Enter the amount to transfer: ");
                            int transferAmount = getPositiveIntegerInput(sc);

                            if (balance >= transferAmount) {
                                balance -= transferAmount;
                                updateTargetAccountBalance(targetAccount, transferAmount);
                                addTransaction("Transferred $" + transferAmount + " to Account " + targetAccount);
                                System.out.println("Transfer successful.");
                            } else {
                                System.out.println("Insufficient Balance.");
                            }
                        } else {
                            System.out.println("Target account does not exist.");
                        }
                        break;

                    case 5:
                        sc.close(); // Close the Scanner before exiting
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
            sc.close();
            System.exit(0);
        }
    }

    private static void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private static void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    private static boolean targetAccountExists(int accountNumber) {
        // Implement logic to check if the target account exists
        return true; // Placeholder, you should replace this with real logic
    }

    private static void updateTargetAccountBalance(int accountNumber, int amount) {
        // Implement logic to update the balance of the target account
    }

    private static int getUserPIN(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine(); // Clear the invalid input
                System.out.println("Invalid input. Please enter a valid PIN.");
            }
        }
    }

    private static boolean authenticateUser(int enteredPIN) {
        return enteredPIN == userPIN; // Compare the entered PIN with the user's PIN.
    }

    private static int getUserChoice(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine(); // Clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static int getPositiveIntegerInput(Scanner sc) {
        while (true) {
            try {
                int input = sc.nextInt();
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a positive number.");
                }
            } catch (InputMismatchException e) {
                sc.nextLine(); // Clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
