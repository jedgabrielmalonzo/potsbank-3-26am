package CheckBalanceGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import GUI.Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Account_Statement {

    private JFrame frmAccountStatement;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboBox;

    public Account_Statement() {
        initialize();
        loadTransactionsFromDatabase(); // Load transactions on initialization
    }

    private void initialize() {
        frmAccountStatement = new JFrame();
        frmAccountStatement.getContentPane().setBackground(new Color(230, 245, 255));
        frmAccountStatement.setTitle("Account Statement");
        frmAccountStatement.setBounds(100, 100, 1198, 528);
        frmAccountStatement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAccountStatement.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(57, 75, 719, 355);
        frmAccountStatement.getContentPane().add(scrollPane);

        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Balance", "Deposit", "Withdrawal", "Date"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent editing of cells
            }
        };
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        JLabel lblAccountStatement = new JLabel("Account Statement");
        lblAccountStatement.setForeground(new Color(8, 76, 172));
        lblAccountStatement.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblAccountStatement.setBounds(286, 0, 294, 80);
        frmAccountStatement.getContentPane().add(lblAccountStatement);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 78, 168));
        panel.setBounds(819, 0, 363, 489);
        frmAccountStatement.getContentPane().add(panel);

        JLabel lbltheAccountStatement = new JLabel(
            "<html><br>The Account Statement Features</br><br> allows you to sort transactions by date or amount, making it easy to organize and view your account activity<br></html>");
        lbltheAccountStatement.setHorizontalAlignment(SwingConstants.CENTER);
        lbltheAccountStatement.setForeground(Color.WHITE);
        lbltheAccountStatement.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbltheAccountStatement.setBounds(64, 88, 271, 306);
        panel.add(lbltheAccountStatement);

        JLabel lblNewLabel_1 = new JLabel("Welcome to PotsBank");
        lblNewLabel_1.setForeground(new Color(230, 245, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(18, 33, 335, 64);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Your Trust, Our Commitment");
        lblNewLabel_1_1.setForeground(new Color(252, 183, 21));
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
        lblNewLabel_1_1.setBounds(92, 90, 219, 35);
        panel.add(lblNewLabel_1_1);

        String[] options = {"Sort by Date", "Sort by Deposit Amount", "Sort by Withdrawal Amount"};
        comboBox = new JComboBox<>(options);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setBounds(139, 441, 179, 22);
        frmAccountStatement.getContentPane().add(comboBox);

        JButton btnGenerateStatement = new JButton("Generate Account Statement");
        btnGenerateStatement.setBounds(482, 441, 207, 23);
        btnGenerateStatement.addActionListener(e -> handleGenerateStatement());
        frmAccountStatement.getContentPane().add(btnGenerateStatement);
        
        JButton btnHome = new JButton("Home");
        btnHome.setForeground(new Color(0, 78, 168));
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnHome.setBackground(new Color(252,183,21));
        btnHome.setBounds(10, 11, 100, 30);
        frmAccountStatement.getContentPane().add(btnHome);
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home homeWindow = new Home(0); // Use UserSession to get the PIN
                homeWindow.setVisible(true);
                frmAccountStatement.dispose(); 
            }
        });
    }

    public void loadTransactionsFromDatabase() {
        List<Transaction> transactions = Account_Statement_Database.fetchAllTransactions();

        for (Transaction transaction : transactions) {
            tableModel.addRow(new Object[]{
                transaction.getBalance(),
                transaction.getDeposit(),
                transaction.getWithdrawal(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getDate()) // Convert Timestamp to String
            });
        }

        refreshTable();
    }

    private void handleGenerateStatement() {
        String selectedOption = (String) comboBox.getSelectedItem();
        if (selectedOption != null) {
            List<Object[]> tableData = getTableData();
            switch (selectedOption) {
                case "Sort by Date":
                    insertionSort(tableData, (row1, row2) -> {
                        try {
                            Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) row1[3]);
                            Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) row2[3]);
                            return date1.compareTo(date2); // Ascending order by date
                        } catch (ParseException e) {
                            return 0;
                        }
                    });
                    break;
                case "Sort by Deposit Amount":
                    insertionSort(tableData, (row1, row2) -> Double.compare((double) row1[1], (double) row2[1]));
                    break;
                case "Sort by Withdrawal Amount":
                    insertionSort(tableData, (row1, row2) -> Double.compare((double) row1[2], (double) row2[2]));
                    break;
            }
            updateTableData(tableData);
        }
    }

    private void insertionSort(List<Object[]> data, Comparator<Object[]> comparator) {
        for (int i = 1; i < data.size(); i++) {
            Object[] key = data.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(data.get(j), key) > 0) {
                data.set(j + 1, data.get(j));
                j = j - 1;
            }
            data.set(j + 1, key);
        }
    }

    private List<Object[]> getTableData() {
        List<Object[]> tableData = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object[] row = new Object[tableModel.getColumnCount()];
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                row[j] = tableModel.getValueAt(i, j);
            }
            tableData.add(row);
        }
        return tableData;
    }

    private void updateTableData(List<Object[]> tableData) {
        tableModel.setRowCount(0);
        for (Object[] row : tableData) {
            tableModel.addRow(row);
        }
        refreshTable();
    }

    public void refreshTable() {
        table.revalidate();
        table.repaint();
    }

    public void show() {
        frmAccountStatement.setVisible(true);
    }

    public static void main(String[] args) {
        Account_Statement accountStatement = new Account_Statement();
        accountStatement.show();
    }
    
    public void addTransaction(double balance, double deposit, double withdrawal, long timestamp) {
        tableModel.addRow(new Object[]{
            balance,
            deposit,
            withdrawal,
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp))
        });
        refreshTable();
    }
}