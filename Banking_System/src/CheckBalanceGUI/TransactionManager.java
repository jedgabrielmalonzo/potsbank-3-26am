package CheckBalanceGUI;

import java.util.LinkedList;
import java.util.Queue;

public class TransactionManager {
    private static TransactionManager instance;
    private Queue<Transaction> transactionQueue;
    private double currentBalance;

    private TransactionManager(double initialBalance) {
        this.currentBalance = initialBalance;
        this.transactionQueue = new LinkedList<>();
    }

    public static TransactionManager getInstance(double initialBalance) {
        if (instance == null) {
            instance = new TransactionManager(initialBalance);
        }
        return instance;
    }

    public synchronized void enqueueTransaction(Transaction transaction) {
        transactionQueue.add(transaction);
    }

    public synchronized void processTransactions() {
        while (!transactionQueue.isEmpty()) {
            Transaction transaction = transactionQueue.poll();
            if (transaction.getType().equals("deposit")) {
                currentBalance += transaction.getDeposit();
            } else if (transaction.getType().equals("withdrawal")) {
                if (transaction.getWithdrawal() <= currentBalance) {
                    currentBalance -= transaction.getWithdrawal();
                } else {
                    System.out.println("Insufficient balance for withdrawal of " + transaction.getWithdrawal());
                    continue; // Skip recording insufficient transactions
                }
            }

            // Log to database
            Account_Statement_Database.insertAccountstatement(
                currentBalance,
                transaction.getDeposit(),
                transaction.getWithdrawal(),
                transaction.getDate().getTime()
            );
        }
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
}
