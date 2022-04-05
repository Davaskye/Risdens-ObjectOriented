package OrderController;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ProductManager.Product;
import ProductManager.StockController;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OrderUserInterface extends JFrame implements ActionListener {
    // creating buttons for interface
    public JButton addOrderBtn;
    public JButton editOrderBtn;
    public JButton serveOrderBtn;
    public JButton closeBtn;

    // Creating layout for interface
    public JPanel pnlCommand1;
    public JPanel pnlCommand2;

    public JScrollPane scrollPane;
    public JTable table;
    public DefaultTableModel model;
    public ArrayList<Order> tablelst = new ArrayList<Order>();

    public static int id = 0;

    public OrderUserInterface() {
        String[] columnNames = { "Customer Name",
                "Telephone",
                "Customer Address",
                "Delivery Address",
                "Delivery Time",
                "Product" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        readData();
        table.setPreferredScrollableViewportSize(new Dimension(500, tablelst.size() * 15 + 50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
        showTable(tablelst);

        addOrderBtn = new JButton("Add Order");
        editOrderBtn = new JButton("Edit Order");
        serveOrderBtn = new JButton("Serve Order");
        closeBtn = new JButton("Close");

        addOrderBtn.addActionListener(this);
        editOrderBtn.addActionListener(this);
        serveOrderBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        pnlCommand1 = new JPanel();
        pnlCommand1.add(scrollPane);
        pnlCommand1.add(addOrderBtn);
        pnlCommand1.add(editOrderBtn);
        pnlCommand1.add(serveOrderBtn);
        pnlCommand1.add(closeBtn);
        // pnlCommand1.setLayout(new GridLayout(3, 1));

        add(pnlCommand1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        id++;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addOrderBtn) {
            new OrderEntry();
            this.dispose();
        }
    }

    public void readData() {
        try {
            File file = new File("Orders.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] lst = data.split("@", 0);
                String data2 = lst[5];
                String[] p = data2.split(" ", 0);
                Product prod = new Product(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]), p[3]);
                Order order = new Order(lst[0], lst[1], lst[2], lst[3], lst[4], prod);
                this.tablelst.add(order);
                // System.out.println(Arrays.toString(lst));
            }
        } catch (Exception e) {
            System.out.println("Something wrong : " + e);
        }
    }

    public void showTable(ArrayList<Order> plist) {
        int i = 0;
        while (plist.size() > i) {
            addToTable(plist.get(i));
            i++;
        }

    }

    public void addToTable(Order p) {
        OrderManager om = new OrderManager(p, id);
        String[] item = { "" + p.getName(), p.getTele(), p.getAddress(), "" + p.getDeliveryAdd(),
                "" + p.getTOD(), "" + p.getProduct().toString() };

        model.addRow(item);
    }

    public static void main(String[] args) {
        OrderUserInterface ui = new OrderUserInterface();
        // ui.readData();

    }
}

class OrderEntry extends JFrame implements ActionListener {

    public JTextField txtName; // Customer name
    public JTextField txtTele; // Customer Telephone
    public JTextField txtAddress; // Customer Address
    public JButton cmdSave;
    public JButton cmdClose;
    public JTextField txtDeliveryAddress; // Customer Delivery Address
    public JTextField txtDeliveryTime; // Customer Time of Delivery
    public JComboBox txtProduct; // Customer Product
    public JTextField txtQuantity;
    public JTextField txtPrice;
    public JTextField txtSupplier;

    public JPanel pnlCommand1;
    public JPanel pnlDisplay;

    // List for orders to be entered into the Orders.txt file to be later displayed
    // on the screen
    public ArrayList<Order> orders = new ArrayList<Order>();

    public OrderEntry() {
        setTitle("Create an Order");
        pnlCommand1 = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Customer Name:"));
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Telephone:"));
        txtTele = new JTextField(3);
        pnlDisplay.add(txtTele);
        pnlDisplay.add(new JLabel("Customer Address:"));
        txtAddress = new JTextField(20);
        pnlDisplay.add(txtAddress);
        pnlDisplay.add(new JLabel("DeliverAddress:"));
        txtDeliveryAddress = new JTextField(20);
        pnlDisplay.add(txtDeliveryAddress);
        pnlDisplay.add(new JLabel("Time of Delivery:"));
        txtDeliveryTime = new JTextField(20);
        pnlDisplay.add(txtDeliveryTime);

        // test string options

        String[] options = { "Allah", "Dalla" };

        pnlDisplay.add(new JLabel("Product:"));
        txtProduct = new JComboBox<>(options);
        pnlDisplay.add(txtProduct);

        pnlDisplay.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField(20);
        pnlDisplay.add(txtQuantity);
        pnlDisplay.add(new JLabel("Price of Item:"));
        txtPrice = new JTextField(20);
        pnlDisplay.add(txtPrice);
        pnlDisplay.add(new JLabel("Supplier:"));
        txtSupplier = new JTextField(20);
        pnlDisplay.add(txtSupplier);

        pnlDisplay.setLayout(new GridLayout(10, 5));

        cmdSave = new JButton("Create");
        cmdClose = new JButton("Cancel");

        cmdSave.addActionListener(this);
        cmdClose.addActionListener(this);

        pnlCommand1.add(cmdSave);
        pnlCommand1.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand1, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdSave) {
            // There should be a check here for Data Validation
            String name = txtName.getText();
            String tel = txtTele.getText();
            String addr = txtAddress.getText();
            String dAddr = txtDeliveryAddress.getText();
            String tod = txtDeliveryTime.getText();
            Product p = new Product((String) txtProduct.getSelectedItem(),
                    Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtPrice.getText()),
                    txtSupplier.getText());
            Order order = new Order(name, tel, addr, dAddr, tod, p);
            if (orders.add(order)) {
                addOrderToDatabase();
                this.dispose();
                new OrderUserInterface();

            }
        }
        if (e.getSource() == cmdClose) {
            this.dispose();

        }
    }

    public void addOrderToDatabase() {
        try {
            File file = new File("Orders.txt");
            FileWriter writer = new FileWriter(file, true);
            for (Order o : orders) {
                writer.write(o.getName() + "@" + o.getTele() + "@" + o.getAddress() + "@" + o.getDeliveryAdd() + "@"
                        + o.getTOD() + "@" + o.getProduct() + "\n");
            }
            writer.close();
        } catch (IOException error) {
            JFrame popupError = new JFrame();
            JOptionPane.showMessageDialog(popupError, "Error adding to file.");
        }
    }

    public ArrayList<Order> giveTable() {
        return this.orders;
    }

    // public static void main(String[] args) {
    // new OrderEntry();
    // }

}
