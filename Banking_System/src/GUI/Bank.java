package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Login.Login;
import Signup.Signup;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bank {

	private JFrame frmWelcomeToPotsdam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank window = new Bank();
					window.frmWelcomeToPotsdam.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Bank() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeToPotsdam = new JFrame();
		frmWelcomeToPotsdam.getContentPane().setBackground(new Color(230, 245, 255));
		frmWelcomeToPotsdam.setTitle("Welcome to PotsBank\r\n");
		frmWelcomeToPotsdam.setBounds(100, 100, 876, 540);
		frmWelcomeToPotsdam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeToPotsdam.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to PotsBank");
		lblNewLabel.setForeground(new Color(0, 78, 168));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(259, 71, 335, 64);
		frmWelcomeToPotsdam.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your Trust, Our Commitment");
		lblNewLabel_1.setForeground(new Color(252, 183, 21));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_1.setBounds(320, 124, 219, 35);
		frmWelcomeToPotsdam.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login\r\n");
		btnNewButton.setForeground(new Color(0, 78, 168));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginScreen = new Login();
				loginScreen.setVisible(true);
				frmWelcomeToPotsdam.dispose(); 
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(139, 238, 198, 70);
		frmWelcomeToPotsdam.getContentPane().add(btnNewButton);
		
		JButton btnSignup = new JButton("Signup\r\n");
		btnSignup.setForeground(new Color(0, 78, 168));
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup signupScreen = new Signup();
				signupScreen.setVisible(true);
				frmWelcomeToPotsdam.dispose();
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignup.setBackground(Color.WHITE);
		btnSignup.setBounds(484, 240, 198, 70);
		frmWelcomeToPotsdam.getContentPane().add(btnSignup);
	}
}