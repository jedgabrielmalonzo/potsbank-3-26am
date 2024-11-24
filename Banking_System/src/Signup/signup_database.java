package Signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signup_database {

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

    public static boolean insertUser(String FullName, String Email, int Phone, String Address, int UserID, String Password, String ConfirmPassword, String AccountType, int pin) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO users (FullName, Email, Phone, Address, UserID, Password, ConfirmPassword, AccountType, pin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, FullName);
            stmt.setString(2, Email);
            stmt.setInt(3, Phone);
            stmt.setString(4, Address);
            stmt.setInt(5, UserID);
            stmt.setString(6, Password);
            stmt.setString(7, ConfirmPassword);
            stmt.setString(8, AccountType);
            stmt.setInt(9, pin);

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

}
