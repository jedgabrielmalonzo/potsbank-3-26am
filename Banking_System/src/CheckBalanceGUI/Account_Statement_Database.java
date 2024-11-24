package CheckBalanceGUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Account_Statement_Database {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/signup"; 
    private static final String USER = "root"; 
    private static final String PASS = "123jed";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertAccountstatement(double balance, double deposit, double withdrawal, long timestamp) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO accountstatement (Balance, Deposit, Withdrawal, Date) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, balance); 
            stmt.setDouble(2, deposit); 
            stmt.setDouble(3, withdrawal); 
            stmt.setTimestamp(4, new java.sql.Timestamp(timestamp));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeConnection(conn);
        }
    }

    public static List<Transaction> fetchAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT Balance, Deposit, Withdrawal, Date FROM accountstatement ORDER BY Date DESC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            double currentBalance = 0.0;
            while (rs.next()) {
                double balance = rs.getDouble("Balance");
                double deposit = rs.getDouble("Deposit");
                double withdrawal = rs.getDouble("Withdrawal");
                Timestamp date = rs.getTimestamp("Date");

                transactions.add(new Transaction(balance, deposit, withdrawal, date, sql));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeConnection(conn);
        }

        return transactions;
    }
    public static double getCurrentBalance() {
        double balance = 0.0;
       
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT Balance FROM accountstatement ORDER BY Date DESC LIMIT 1");
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                balance = rs.getDouble("Balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
    
    
}