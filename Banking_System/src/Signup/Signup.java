package Signup;

import java.awt.EventQueue;
import javax.swing.*;
import Login.Login;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Signup {

    private JFrame frmSignup;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JTextField textFieldAddress;
    private JTextField textFieldUserID;
    public static int generatedPin = 12345; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Signup window = new Signup();
                    window.frmSignup.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Signup() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmSignup = new JFrame();
        frmSignup.getContentPane().setBackground(new Color(229, 245, 255));
        frmSignup.setTitle("Signup");
        frmSignup.setBounds(100, 100, 800, 677);
        frmSignup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSignup.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("SIGNUP");
        lblTitle.setForeground(new Color(0, 78, 168));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(535, 21, 128, 80);
        frmSignup.getContentPane().add(lblTitle);

        String[] accountTypes = {"Savings", "Checking"};
        
        JLabel lblUsername = new JLabel("Full Name");
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(427, 88, 98, 25);
        frmSignup.getContentPane().add(lblUsername);
        
        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(426, 124, 320, 20);
        frmSignup.getContentPane().add(textFieldUsername);
        textFieldUsername.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.BLACK);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(427, 152, 98, 25);
        frmSignup.getContentPane().add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(426, 184, 320, 20);
        frmSignup.getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);
        
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setForeground(Color.BLACK);
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPhone.setBounds(427, 215, 98, 25);
        frmSignup.getContentPane().add(lblPhone);
        
        textFieldPhone = new JTextField();
        textFieldPhone.setBounds(427, 240, 320, 20);
        frmSignup.getContentPane().add(textFieldPhone);
        textFieldPhone.setColumns(10);
        
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setForeground(Color.BLACK);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAddress.setBounds(427, 266, 98, 25);
        frmSignup.getContentPane().add(lblAddress);
        
        textFieldAddress = new JTextField();
        textFieldAddress.setBounds(427, 302, 320, 20);
        frmSignup.getContentPane().add(textFieldAddress);
        textFieldAddress.setColumns(10);
        
        JLabel lblUserID = new JLabel("User ID");
        lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUserID.setBounds(427, 333, 89, 25);
        frmSignup.getContentPane().add(lblUserID);
        
        textFieldUserID = new JTextField();
        textFieldUserID.setBounds(427, 373, 319, 20);
        frmSignup.getContentPane().add(textFieldUserID);
        textFieldUserID.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(427, 446, 320, 20);
        frmSignup.getContentPane().add(passwordField);
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(427, 506, 320, 20);
        frmSignup.getContentPane().add(confirmPasswordField);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(427, 404, 89, 31);
        frmSignup.getContentPane().add(lblPassword);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setForeground(Color.BLACK);
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblConfirmPassword.setBounds(427, 477, 159, 25);
        frmSignup.getContentPane().add(lblConfirmPassword);
        
        JLabel lblAccountType = new JLabel("Account Type");
        lblAccountType.setForeground(Color.BLACK);
        lblAccountType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAccountType.setBounds(427, 526, 137, 25);
        frmSignup.getContentPane().add(lblAccountType);
        
        JComboBox<String> comboAccountType = new JComboBox<>(accountTypes);
        comboAccountType.setBackground(Color.WHITE);
        comboAccountType.setForeground(Color.BLACK);
        comboAccountType.setBounds(427, 562, 320, 31);
        frmSignup.getContentPane().add(comboAccountType);
        comboAccountType.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        JButton btnSubmit = new JButton("Signup");
        btnSubmit.setForeground(new Color(0, 78, 168));
        btnSubmit.setBackground(Color.WHITE);
        btnSubmit.setBounds(475, 604, 89, 23);
        frmSignup.getContentPane().add(btnSubmit);
        
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fullName = textFieldUsername.getText();
                String email = textFieldEmail.getText();
                String phoneStr = textFieldPhone.getText();
                String address = textFieldAddress.getText();
                String userIDStr = textFieldUserID.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String accountType = (String) comboAccountType.getSelectedItem();  
                
                if (fullName.isEmpty() || email.isEmpty() || phoneStr.isEmpty() || address.isEmpty() || userIDStr.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frmSignup, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frmSignup, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    int phone = Integer.parseInt(phoneStr);
                    int userID = Integer.parseInt(userIDStr);

                    // Use fixed PIN
                    JOptionPane.showMessageDialog(frmSignup, "Your PIN is set to: " + generatedPin, "PIN Generated", JOptionPane.INFORMATION_MESSAGE);
                    
                    UserSession.setUserPin(generatedPin); // Store PIN in the global session

                    boolean success = signup_database.insertUser(fullName, email, phone, address, userID, password, confirmPassword, accountType, generatedPin);

                    if (success) {
                        JOptionPane.showMessageDialog(frmSignup, "Account created successfully!");
                        Login login = new Login();
                        login.setVisible(true);
                        frmSignup.dispose();
                        
                    } else {
                        JOptionPane.showMessageDialog(frmSignup, "Error creating account", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frmSignup, "Invalid phone or user ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setForeground(new Color(0, 78, 168));
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCancel.setBounds(598, 604, 89, 23);
        frmSignup.getContentPane().add(btnCancel);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 78, 168));
        panel.setBounds(0, 0, 391, 638);
        frmSignup.getContentPane().add(panel);
        
        JLabel lblNewLabel_1 = new JLabel("Welcome to PotsBank");
        lblNewLabel_1.setForeground(new Color(230, 245, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(28, 64, 335, 64);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Your Trust, Our Commitment");
        lblNewLabel_1_1.setForeground(new Color(252, 183, 21));
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
        lblNewLabel_1_1.setBounds(86, 125, 219, 35);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblsignUpTo = new JLabel("<html>Sign up to create your online bank account and enjoy secure access to manage your finances, check balances, transfer funds, and more convenient banking at your fingertips.</html>");
        lblsignUpTo.setForeground(new Color(230, 245, 254));
        lblsignUpTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblsignUpTo.setBounds(38, 277, 314, 84);
        panel.add(lblsignUpTo);
    }

    public void setVisible(boolean visible) {
        frmSignup.setVisible(visible);
    }
}