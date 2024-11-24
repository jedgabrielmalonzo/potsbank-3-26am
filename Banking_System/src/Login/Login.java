package Login;

import java.awt.*;
import javax.swing.*;

import DepositGUI.Deposit;
import GUI.Home;
import Signup.Signup;
import Signup.UserSession;

import java.awt.event.*;

public class Login {

    private JFrame frmLog;
    private JTextField textField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frmLog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        initialize();
    }

    private void initialize() {
        frmLog = new JFrame();
        frmLog.getContentPane().setBackground(new Color(230, 245, 254));
        frmLog.setTitle("Login");
        frmLog.setBounds(100, 100, 800, 530);
        frmLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLog.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 78, 168));
        panel.setBounds(0, 0, 391, 491);
        frmLog.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome to PotsBank");
        lblWelcome.setForeground(new Color(230, 245, 255));
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblWelcome.setBounds(28, 64, 335, 64);
        panel.add(lblWelcome);

        JLabel lblSubtitle = new JLabel("Your Trust, Our Commitment");
        lblSubtitle.setForeground(new Color(252, 183, 21));
        lblSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 16));
        lblSubtitle.setBounds(86, 125, 219, 35);
        panel.add(lblSubtitle);

        JLabel lblDescription = new JLabel("<html>Log in to securely access your online bank account, manage your finances, check your balance, make transactions, and stay in control of your banking needs anytime, anywhere.</html>");
        lblDescription.setForeground(new Color(230, 245, 254));
        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDescription.setBounds(24, 222, 342, 84);
        panel.add(lblDescription);

        // Login form title
        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setForeground(new Color(0, 78, 168));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(546, 31, 102, 78);
        frmLog.getContentPane().add(lblTitle);

        JLabel lblUsername = new JLabel("User ID");
        lblUsername.setBounds(413, 132, 102, 20);
        frmLog.getContentPane().add(lblUsername);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));

        textField = new JTextField();
        textField.setBounds(413, 165, 341, 20);
        frmLog.getContentPane().add(textField);
        textField.setColumns(10);

        // Password field and label
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(413, 196, 102, 37);
        frmLog.getContentPane().add(lblPassword);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));

        passwordField = new JPasswordField();
        passwordField.setBounds(413, 244, 341, 20);
        frmLog.getContentPane().add(passwordField);

        // Login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(new Color(0, 78, 168));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setBounds(451, 288, 115, 25);
        frmLog.getContentPane().add(btnLogin);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate using database (you need to define the database and authentication methods)
                if (login_database.authenticateUser(username, password)) {
                    int pin = login_database.getUserPin(username); // Retrieve PIN for the user
                    if (pin != -1) { // Check if PIN is valid
                        JOptionPane.showMessageDialog(frmLog, "Login successful! Your PIN is: " + pin);
                        UserSession.setUserPin(pin); // Store PIN in UserSession
                        Home home = new Home(pin); // Pass PIN to the Home class
                        home.setVisible(true);
                        frmLog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frmLog, "Error retrieving PIN", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmLog, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JLabel lblNewLabel = new JLabel("Don't have an account?");
        lblNewLabel.setForeground(new Color(0, 78, 168));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(440, 363, 161, 20);
        frmLog.getContentPane().add(lblNewLabel);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSignUp.setForeground(new Color(0, 78, 168));
        btnSignUp.setBackground(Color.WHITE);
        btnSignUp.setBounds(603, 357, 120, 37);
        frmLog.getContentPane().add(btnSignUp);
        btnSignUp.addActionListener(e -> {
            Signup.main(new String[0]);  // Open Signup window
            frmLog.dispose();    // Close Home window
        });

        // Cancel button
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setForeground(new Color(0, 78, 168));
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCancel.setBounds(592, 288, 115, 25);
        frmLog.getContentPane().add(btnCancel);
    }

    public void setVisible(boolean visible) {
        frmLog.setVisible(visible);
    }
}
