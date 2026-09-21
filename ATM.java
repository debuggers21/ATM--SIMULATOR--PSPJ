public class Account {
    private String accountNumber;
    private String pin;
    private String name;
    private double balance;

    public Account(String accountNumber, String pin,
                   String name, double balance) {

        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }
}



import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Transaction> transactions =
            new ArrayList<>();

    public static void main(String[] args) {

        Account account = new Account(
                "1234567890",
                "1234",
                "Student",
                5000.00
        );

        System.out.println("================================");
        System.out.println("       WELCOME TO ATM");
        System.out.println("================================");

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (accountNumber.equals(account.getAccountNumber())
                && pin.equals(account.getPin())) {

            System.out.println("\nLogin Successful!");

            boolean running = true;

            while (running) {

                System.out.println("\n--------- ATM MENU ---------");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Mini Statement");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println(
                                "Balance: Rs. "
                                + account.getBalance());
                        break;

                    case 2:
                        System.out.print(
                                "Enter withdrawal amount: ");

                        double withdraw =
                                scanner.nextDouble();

                        if (account.withdraw(withdraw)) {

                            transactions.add(
                                    new Transaction(
                                            "Withdrawal",
                                            withdraw));

                            System.out.println(
                                    "Withdrawal successful.");

                        } else {
                            System.out.println(
                                    "Insufficient balance.");
                        }

                        break;

                    case 3:
                        System.out.print(
                                "Enter deposit amount: ");

                        double deposit =
                                scanner.nextDouble();

                        if (deposit > 0) {

                            account.deposit(deposit);

                            transactions.add(
                                    new Transaction(
                                            "Deposit",
                                            deposit));

                            System.out.println(
                                    "Deposit successful.");

                        } else {
                            System.out.println(
                                    "Invalid amount.");
                        }

                        break;

                    case 4:
                        System.out.println(
                                "\n------ MINI STATEMENT ------");

                        if (transactions.isEmpty()) {
                            System.out.println(
                                    "No transactions.");
                        } else {

                            for (Transaction t :
                                    transactions) {

                                System.out.println(t);
                            }
                        }

                        break;

                    case 5:
                        running = false;

                        System.out.println(
                                "Thank you for using ATM.");

                        break;

                    default:
                        System.out.println(
                                "Invalid choice.");
                }
            }

        } else {

            System.out.println(
                    "Invalid account number or PIN.");
        }

        scanner.close();
    }
}