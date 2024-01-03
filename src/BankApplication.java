import java.util.Scanner;

class Bank {
    private Account[] accounts;
    private int numAccounts;

    public Bank(int capacity) {
        accounts = new Account[capacity];
        numAccounts = 0;
    }

    public void createAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (numAccounts < accounts.length) {
            Account account = new Account(accountNumber, accountHolder, initialBalance);
            accounts[numAccounts] = account;
            numAccounts++;
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Cannot create more accounts. Bank is at full capacity.");
        }
    }

    public void displayAccountDetails(String accountNumber) {
        boolean accountFound = false;
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                accounts[i].displayAccountDetails();
                accountFound = true;
                break;
            }
        }
        if (!accountFound) {
            System.out.println("Account not found.");
        }
    }

    public void deposit(String accountNumber, double amount) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                accounts[i].deposit(amount);
                System.out.println("Deposit successful. Updated balance: $" + accounts[i].getBalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void withdraw(String accountNumber, double amount) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                if (accounts[i].withdraw(amount)) {
                    System.out.println("Withdrawal successful. Updated balance: $" + accounts[i].getBalance());
                } else {
                    System.out.println("Insufficient funds.");
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }
}

class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
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

    public void displayAccountDetails() {
        System.out.println("Account Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
}

public class BankApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity of the bank: ");
        int capacity = scanner.nextInt();
        Bank bank = new Bank(capacity);

        while (true) {
            System.out.println("\nBank Application Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Display Account Details");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accHolder = scanner.nextLine();
                    System.out.print("Enter initial balance: $");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(accNumber, accHolder, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String displayAccNumber = scanner.nextLine();
                    bank.displayAccountDetails(displayAccNumber);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String depositAccNumber = scanner.nextLine();
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(depositAccNumber, depositAmount);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    String withdrawAccNumber = scanner.nextLine();
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(withdrawAccNumber, withdrawAmount);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
