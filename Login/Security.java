package Login;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ProductManager.StockController;
import ProductManager.ProductEntry.*;
import ProductManager.StockController.*;

public class Security extends JFrame {

    private static final long serialVersionUID = 1; // Version of software? Dunno. Jpanel asks for it though.
    private JTextField txtName; // product name
    private JTextField txtQuantity; // quantity
    private JTextField txtPrice; // price
    private JTextField txtSupplier; // supplier
    private JButton cmdSave;
    private JButton cmdClose;
    // private JButton cmdClearAll;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    // private StockController productListingVar;
    // private Product editP;
    private int editID;

    public Security() {
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Username:"));
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Password:"));
        txtQuantity = new JTextField(3);
        pnlDisplay.add(txtQuantity);
        // pnlDisplay.add(new JLabel("Price:"));
        // txtPrice = new JTextField(20);
        // pnlDisplay.add(txtPrice);
        pnlDisplay.setLayout(new GridLayout(2, 3));
        // pnlDisplay.add(new JLabel("Supplier:"));
        // txtSupplier = new JTextField(20);
        // pnlDisplay.add(txtSupplier);

        cmdSave = new JButton("Save");
        cmdClose = new JButton("Close");

        // cmdClose.addActionListener(new ButtonListener());
        // cmdSave.addActionListener(new ButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        // public void run() {

        // }
        // });
        StockController.createAndShowGUI();

    }

}
