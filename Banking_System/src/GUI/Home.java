package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ChangePinGui.Change_Pin;
import CheckBalanceGUI.Account_Statement;
import DepositGUI.Deposit;
import Signup.UserSession;
import WithdrawGUI.Withdraw;

public class Home extends JFrame {
    private int userPin; // Will hold the PIN fetched from the database

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Home window = new Home(1234); // Example PIN for testing
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Home(int pin) {
        this.userPin = UserSession.getUserPin(); // Get the PIN from UserSession
        getContentPane().setBackground(new Color(230, 245, 254));
        initialize();
    }

    private void initialize() {
        setTitle("Home");
        setBounds(100, 100, 876, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        createButtons();
        createPanel();
        createHeader();
    }

    private void createButtons() {
    	 JButton btnDeposit = createButton("Deposit", e -> verifyPinAndOpenWindow(Deposit.class));
    	    btnDeposit.setBounds(498, 174, 204, 54);
    	    getContentPane().add(btnDeposit);

    	    JButton btnWithdraw = createButton("Withdraw", e -> verifyPinAndOpenWindow(Withdraw.class));
    	    btnWithdraw.setBounds(498, 239, 204, 54);
    	    getContentPane().add(btnWithdraw);

    	    JButton btnCheckBalance = createButton("Check Balance", e -> verifyPinAndOpenWindow(Account_Statement.class));
    	    btnCheckBalance.setBounds(498, 314, 204, 54);
    	    getContentPane().add(btnCheckBalance);

        JButton btnChangePin = createButton("Change Pin", e -> openWindow(Change_Pin.class));
        btnChangePin.setBounds(498, 379, 204, 54);
        getContentPane().add(btnChangePin);
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(252, 183, 21));
        button.setForeground(new Color(0, 78, 168));
        button.setFont(new Font("Tahoma", Font.BOLD, 15));
        button.addActionListener(action);
        return button;
    }

    private void verifyPinAndOpenWindow(Class<?> clazz) {
        String inputPinStr = JOptionPane.showInputDialog(this, "Enter your PIN:");
        if (inputPinStr != null) {
            try {
                int inputPin = Integer.parseInt(inputPinStr);
                if (inputPin == userPin) {
                    openWindow(clazz);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect PIN. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid PIN format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void openWindow(Class<?> clazz) {
        try {
            clazz.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
            
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            // Consider showing an error dialog here
        }
    }

    private void createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 78, 168));
        panel.setBounds(0, 0, 363, 501);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("<html>What would you<br>like to do?</html>");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
        lblTitle.setBounds(33, 11, 298, 107);
        panel.add(lblTitle);

        JLabel lblDescription = new JLabel("<html>Choose an action from the options on the right to manage your account:<br><br>Deposit: Add money to your account securely.<br><br>Withdraw: Withdraw funds from your account.<br><br>Check Balance: View your current account balance.<br><br>Change Pin: Update your PIN for added security.<br><br>");
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescription.setForeground(Color.WHITE);
        lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDescription.setBounds(55, 158, 256, 306);
        panel.add(lblDescription);
    }

    private void createHeader() {
        JLabel lblBankTitle = new JLabel("Welcome to PotsBank");
        lblBankTitle.setBounds(441, 26, 331, 37);
        lblBankTitle.setForeground(new Color(0, 78, 168));
        lblBankTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        getContentPane().add(lblBankTitle);

        JLabel lblSubtitle = new JLabel("Your Trust, Our Commitment");
        lblSubtitle.setBounds(505, 62, 197, 35);
        lblSubtitle.setForeground(new Color(245, 185, 19));
        lblSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 15));
        getContentPane().add(lblSubtitle);
    }
}