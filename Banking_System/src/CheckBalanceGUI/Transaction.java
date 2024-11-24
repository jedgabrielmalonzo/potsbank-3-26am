package CheckBalanceGUI;

import java.util.Date;

public class Transaction {
    private double balance;
    private double deposit;
    private double withdrawal;
    private Date date;
    private String type; // "deposit" or "withdrawal"

    // Constructor
    public Transaction(double balance, double deposit, double withdrawal, Date date, String string) {
        this.balance = balance;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.date = date;
        
    }

    // Getters and Setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
