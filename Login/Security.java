package Login;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ProductManager.StockController;

public class Security extends JFrame {

    private JTextField txtName; // product name
    private JTextField txtPassword; // quantity
    private JButton cmdLogin;
    private JButton cmdClose;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    public Security() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Username:"));
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Password:"));
        txtPassword = new JTextField(3);
        pnlDisplay.add(txtPassword);
        pnlDisplay.setLayout(new GridLayout(2, 3));

        cmdLogin = new JButton("Login");
        cmdClose = new JButton("Close");

        cmdClose.addActionListener(new Listener());
        cmdLogin.addActionListener(new Listener());

        pnlCommand.add(cmdLogin);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    public class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == cmdClose) {
                System.exit(0);
            } else if (e.getSource() == cmdLogin) {
                String username = txtName.getText();
                String password = txtPassword.getText();
                Login login = new Login(username, password);
                int auth = login.isAuthorised();
                if (runChecks(auth)) {
                    setVisible(false);
                    StockController.createAndShowGUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Your username or password is incorrect");
                }

            }

        }

    }

    public Boolean runChecks(int authentificationNumber) {

        if (authentificationNumber == 0) {
            return true;
        } else if (authentificationNumber == 1) {
            return false;
        } else if (authentificationNumber == 2) {
            return false;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        // public void run() {

        // }
        // });
        Security auth = new Security();
    }

}
