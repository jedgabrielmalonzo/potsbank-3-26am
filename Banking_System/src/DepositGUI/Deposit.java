package DepositGUI;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import CheckBalanceGUI.Account_Statement;
import CheckBalanceGUI.Account_Statement_Database;
import GUI.Home;

public class Deposit {

    private JFrame frame;
    private JTextField textField;
    private Account_Statement accountStatement;

    // Main method
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Account_Statement accountStatement = new Account_Statement(); // Pass account statement
                Deposit window = new Deposit(accountStatement); // No PIN needed
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor
    public Deposit(Account_Statement accountStatement) {
        this.accountStatement = accountStatement;
        initialize();
        frame.setVisible(true);
    }

    // Initialize the contents of the frame
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(229, 245, 255));
        frame.setBounds(100, 100, 876, 540);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(0, 78, 168));
        panel_1.setBounds(497, 0, 363, 501);
        frame.getContentPane().add(panel_1);

        JLabel lbltoCompleteYour = new JLabel("<html>To complete your deposit, simply enter the amount you wish to deposit and confirm. Once submitted, you will receive a confirmation message.</html>");
        lbltoCompleteYour.setHorizontalAlignment(SwingConstants.CENTER);
        lbltoCompleteYour.setForeground(Color.WHITE);
        lbltoCompleteYour.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbltoCompleteYour.setBounds(64, 88, 271, 306);
        panel_1.add(lbltoCompleteYour);

        JLabel lblNewLabel_1 = new JLabel("Welcome to PotsBank");
        lblNewLabel_1.setForeground(new Color(230, 245, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(18, 33, 335, 64);
        panel_1.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Your Trust, Our Commitment");
        lblNewLabel_1_1.setForeground(new Color(252, 183, 21));
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
        lblNewLabel_1_1.setBounds(92, 90, 219, 35);
        panel_1.add(lblNewLabel_1_1);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 78, 168));
        panel.setBounds(84, 180, 320, 140);
        frame.getContentPane().add(panel);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(26, 64, 268, 26);
        panel.add(textField);

        JLabel lblEnterAmount = new JLabel("Enter the Amount to Deposit");
        lblEnterAmount.setForeground(new Color(230, 245, 254));
        lblEnterAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEnterAmount.setBounds(66, 28, 187, 19);
        panel.add(lblEnterAmount);

        JButton btnEnter = new JButton("Enter");
        btnEnter.setBounds(115, 106, 89, 23);
        panel.add(btnEnter);

        // Action listener for "Enter" button
     // Inside the action listener for the "Enter" button in Deposit.java
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(textField.getText());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(frame, "The deposit amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (amount > 5000) {
                        JOptionPane.showMessageDialog(frame, "The deposit amount cannot exceed $5000.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Process the deposit directly
                    JOptionPane.showMessageDialog(frame, "Deposit of " + amount + " processed successfully!");
                    textField.setText("");

                    // Update the balance in the database
                    double currentBalance = Account_Statement_Database.getCurrentBalance();
                    double newBalance = currentBalance + amount;
                    Account_Statement_Database.insertAccountstatement(newBalance, amount, 0, System.currentTimeMillis());

                    // Refresh the account statement
                    if (accountStatement != null) {
                        accountStatement.loadTransactionsFromDatabase();
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JLabel lblDeposit = new JLabel("Deposit");
        lblDeposit.setForeground(new Color(0, 78, 168));
        lblDeposit.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblDeposit.setBounds(137, 78, 192, 80);
        frame.getContentPane().add(lblDeposit);

        JButton btnHome = new JButton("Home");
        btnHome.setForeground(new Color(0, 78, 168));
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnHome.setBackground(new Color(252, 183, 21));
        btnHome.setBounds(10, 11, 100, 30);
        frame.getContentPane().add(btnHome);
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home homeWindow = new Home(0); // Use UserSession to get the PIN
                homeWindow.setVisible(true);
                frame.dispose(); 
            }
        });
    }
}