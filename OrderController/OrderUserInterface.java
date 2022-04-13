package OrderController;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    public JButton viewReceiptBtn;
    public JButton viewScheduleBtn;
    public JButton closeBtn;

    // Creating layout for interface
    public JPanel pnlCommand1;
    public JPanel pnlCommand2;

    public JScrollPane scrollPane;
    public JTable table;
    public DefaultTableModel model;
    public ArrayList<Order> tablelst = new ArrayList<Order>();

    public static int id = 0;

    public OrderUserInterface(Boolean normal) {
    }

    public OrderUserInterface() {
        String[] columnNames = { "ID",
                "Customer Name",
                "Telephone",
                "Customer Address",
                "Delivery Address",
                "Delivery Time",
                "Product Name",
                "Quantity",
                "Price",
                "Supplier of Product" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        readData();
        table.setPreferredScrollableViewportSize(new Dimension(100 * 12, tablelst.size() * 15 + 50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
        showTable(tablelst);

        addOrderBtn = new JButton("Add Order");
        editOrderBtn = new JButton("Edit Order");
        serveOrderBtn = new JButton("Serve Order");
        viewReceiptBtn = new JButton("View Receipt");
        viewScheduleBtn = new JButton("View Schedule");
        closeBtn = new JButton("Close");

        addOrderBtn.addActionListener(this);
        editOrderBtn.addActionListener(this);
        serveOrderBtn.addActionListener(this);
        viewReceiptBtn.addActionListener(this);
        viewScheduleBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        pnlCommand1 = new JPanel();
        pnlCommand2 = new JPanel();
        pnlCommand2.add(scrollPane);
        pnlCommand1.add(addOrderBtn);
        pnlCommand1.add(editOrderBtn);
        pnlCommand1.add(serveOrderBtn);
        pnlCommand1.add(viewReceiptBtn);
        pnlCommand1.add(viewScheduleBtn);
        pnlCommand1.add(closeBtn);

        this.setLayout(new GridLayout(2, 1));

        add(pnlCommand2);
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
        if (e.getSource() == editOrderBtn) {
            new OrderEdit(this);
            tablelst.clear();
        }
        if (e.getSource() == serveOrderBtn) {

            new ServeOrder(this);
        }
        if (e.getSource() == viewReceiptBtn) {
            new ReceiptUI();
        }
        if (e.getSource() == viewScheduleBtn) {
            new ScheduleUI();
        }
        if (e.getSource() == closeBtn) {
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
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Something wrong : " + e);
        }
    }

    public void showTable(ArrayList<Order> plist) {
        int i = 0;
        while (plist.size() > i) {
            addToTable(plist.get(i), i);
            i++;
        }

    }

    public void addToTable(Order p, int i) {
        String[] item = { "" + i, "" + p.getName(), p.getTele(), p.getAddress(),
                "" + p.getDeliveryAdd(),
                "" + p.getTOD(), "" + p.getProduct().getName(), "" + p.getProduct().getQuantity(),
                "" + p.getProduct().getPrice(), "" + p.getProduct().getSupplier() };

        model.addRow(item);
    }

    public ArrayList<Order> giveTable() {
        return this.tablelst;
    }

    public static void main(String[] args) {
        new OrderUserInterface();
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

        String[] options = productDisplay();

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

        cmdSave = new JButton("Save");
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
            new OrderUserInterface();

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

    public String[] productDisplay() {
        StockController sc = new StockController();
        ArrayList<Product> prod = sc.plist;
        String[] products = new String[prod.size()];
        for (int i = 0; i < prod.size(); i++) {
            products[i] = prod.get(i).getName();
        }
        return products;
    }

    // public static void main(String[] args) {
    // new OrderEntry();
    // }

}

class OrderEdit extends JFrame implements ActionListener {

    public JComboBox idOption;
    public JPanel pnlCommand1;
    public JLabel txtField;
    public JButton cmdSave;
    public JButton cmdClose;
    public ArrayList<Order> tablelst = new ArrayList<Order>();
    public ArrayList<String[][]> tablelst1 = new ArrayList<String[][]>();
    public JFrame frame;

    public OrderEdit(JFrame frame) {
        this.frame = frame;
        setTitle("Edit an Order");

        String[] options = productDisplayNumber();

        cmdSave = new JButton("Edit");
        cmdClose = new JButton("Cancel");
        cmdSave.addActionListener(this);
        cmdClose.addActionListener(this);

        pnlCommand1 = new JPanel();
        txtField = new JLabel("Enter id number of order: ");
        idOption = new JComboBox<>(options);
        pnlCommand1.add(txtField);
        pnlCommand1.add(idOption);
        pnlCommand1.add(cmdSave);
        pnlCommand1.add(cmdClose);
        pnlCommand1.setLayout(new GridLayout(2, 2));
        this.add(pnlCommand1);
        this.setBounds(100, 100, 350, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdSave) {
            String option = idOption.getSelectedItem().toString();
            readData();
            for (int i = 0; i < tablelst1.size(); i++) {
                String[][] order = tablelst1.get(i);
                if (String.valueOf(i).equalsIgnoreCase(option)) {

                    tablelst.remove(i);
                    String name = order[0][0];
                    String tel = order[0][1];
                    String addr = order[0][2];
                    String dAddr = order[0][3];
                    String tod = order[0][4];
                    String prod = order[1][0];
                    String quant = order[1][1];
                    String price = order[1][2];
                    String supp = order[1][3];
                    new Edit(name, tel, addr, dAddr, tod, prod, quant, price, supp, tablelst, i);
                    this.frame.dispose();
                    this.dispose();
                }

            }

        }
        if (e.getSource() == cmdClose) {
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
                String[][] order = { { lst[0], lst[1], lst[2], lst[3], lst[4] }, { p[0], p[1], p[2], p[3] } };
                tablelst1.add(order);

                // DO NOT DELETE THE COMMENT BELOW, USED TO KNNOW WHICH ELEMENT HAS WHAT
                // INFORMATIO

                Product prod = new Product(p[0], Integer.parseInt(p[1]),
                        Double.parseDouble(p[2]), p[3]);
                Order order1 = new Order(lst[0], lst[1], lst[2], lst[3], lst[4], prod);
                this.tablelst.add(order1);
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Something wrong : " + e);
        }
    }

    public String[] productDisplayNumber() {
        readData();
        ArrayList<Order> prod = tablelst;
        String[] products = new String[prod.size()];
        for (int i = 0; i < prod.size(); i++) {
            products[i] = String.valueOf(i);
        }
        tablelst.clear();
        tablelst1.clear();
        return products;
    }

    class Edit extends JFrame implements ActionListener {

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
        public int index;

        public Edit(String name, String tel, String addr, String dAddr, String tod, String prod, String quant,
                String price, String supp, ArrayList<Order> lst, int index) {
            this.orders = lst;
            this.index = index;
            setTitle("Create an Order");
            pnlCommand1 = new JPanel();
            pnlDisplay = new JPanel();
            pnlDisplay.add(new JLabel("Customer Name:"));
            txtName = new JTextField(20);
            txtName.setText(name);
            pnlDisplay.add(txtName);
            pnlDisplay.add(new JLabel("Telephone:"));
            txtTele = new JTextField(3);
            txtTele.setText(tel);
            pnlDisplay.add(txtTele);
            pnlDisplay.add(new JLabel("Customer Address:"));
            txtAddress = new JTextField(20);
            txtAddress.setText(addr);
            pnlDisplay.add(txtAddress);
            pnlDisplay.add(new JLabel("DeliverAddress:"));
            txtDeliveryAddress = new JTextField(20);
            txtDeliveryAddress.setText(dAddr);
            pnlDisplay.add(txtDeliveryAddress);
            pnlDisplay.add(new JLabel("Time of Delivery:"));
            txtDeliveryTime = new JTextField(20);
            txtDeliveryTime.setText(tod);
            pnlDisplay.add(txtDeliveryTime);

            // test string options

            String[] options = productDisplay();

            pnlDisplay.add(new JLabel("Product:"));
            txtProduct = new JComboBox<>(options);
            txtProduct.setSelectedItem(prod);
            pnlDisplay.add(txtProduct);

            pnlDisplay.add(new JLabel("Quantity:"));
            txtQuantity = new JTextField(20);
            txtQuantity.setText(quant);
            pnlDisplay.add(txtQuantity);
            pnlDisplay.add(new JLabel("Price of Item:"));
            txtPrice = new JTextField(20);
            txtPrice.setText(price);
            pnlDisplay.add(txtPrice);
            pnlDisplay.add(new JLabel("Supplier:"));
            txtSupplier = new JTextField(20);
            txtSupplier.setText(supp);
            pnlDisplay.add(txtSupplier);

            pnlDisplay.setLayout(new GridLayout(10, 5));

            cmdSave = new JButton("Save");
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
                orders.add(this.index, order);
                addOrderToDatabase();
                this.dispose();
                new OrderUserInterface();
            }
            if (e.getSource() == cmdClose) {
                this.dispose();
                new OrderUserInterface();

            }
        }

        public String[] productDisplay() {
            StockController sc = new StockController();
            ArrayList<Product> prod = sc.plist;
            String[] products = new String[prod.size()];
            for (int i = 0; i < prod.size(); i++) {
                products[i] = prod.get(i).getName();
            }
            return products;
        }

        private void addOrderToDatabase() {

            try {
                File file = new File("Orders.txt");
                FileWriter writer = new FileWriter(file, false);
                for (Order o : orders) {
                    writer.write(o.getName() + "@" + o.getTele() + "@" + o.getAddress() + "@" +
                            o.getDeliveryAdd() + "@"
                            + o.getTOD() + "@" + o.getProduct() + "\n");
                }
                writer.close();
            } catch (IOException error) {
                JFrame popupError = new JFrame();
                JOptionPane.showMessageDialog(popupError, "Error adding to file.");
            }

        }

    }

    // public static void main(String[] args) {
    // OrderEdit oe = new OrderEdit(new JFrame());
    // oe.productDisplay();
    // }
}

class ServeOrder extends JFrame implements ActionListener {

    public JComboBox idOption;
    public JPanel pnlCommand1;
    public JLabel txtField;
    public JButton cmdSave;
    public JButton cmdClose;
    public ArrayList<Order> tablelst = new ArrayList<Order>();
    public ArrayList<String[][]> tablelst1 = new ArrayList<String[][]>();
    public JFrame frame;

    public JTextField txtPayment;

    public ServeOrder(JFrame frame) {
        this.frame = frame;
        setTitle("Serve an Order");

        String[] options = productDisplayNumber();

        cmdSave = new JButton("Serve");
        cmdClose = new JButton("Cancel");
        cmdSave.addActionListener(this);
        cmdClose.addActionListener(this);

        pnlCommand1 = new JPanel();
        txtField = new JLabel("Enter id number of order: ");
        idOption = new JComboBox<>(options);

        pnlCommand1.add(txtField);
        pnlCommand1.add(idOption);

        pnlCommand1.add(new JLabel("Payment:"));
        txtPayment = new JTextField(20);
        pnlCommand1.add(txtPayment);

        pnlCommand1.add(cmdSave);
        pnlCommand1.add(cmdClose);
        pnlCommand1.setLayout(new GridLayout(3, 3));
        this.add(pnlCommand1);
        this.setBounds(100, 100, 350, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdSave) {
            this.frame.dispose();
            this.dispose();
            readData();
            int index = Integer.parseInt(idOption.getSelectedItem().toString());
            Order myOrder = tablelst.get(index);
            tablelst.remove(index);
            // Should add reference to adding to receipt here.
            Double payment = Double.parseDouble(txtPayment.getText());
            Receipt r = new Receipt(myOrder, payment);
            ReceiptUI newR = new ReceiptUI(r);
            newR.createFile();

            addOrderToDatabase();
            new OrderUserInterface();
        }
        if (e.getSource() == cmdClose) {
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
                String[][] order = { { lst[0], lst[1], lst[2], lst[3], lst[4] }, { p[0], p[1], p[2], p[3] } };
                tablelst1.add(order);

                // DO NOT DELETE THE COMMENT BELOW, USED TO KNNOW WHICH ELEMENT HAS WHAT
                // INFORMATIO

                Product prod = new Product(p[0], Integer.parseInt(p[1]),
                        Double.parseDouble(p[2]), p[3]);
                Order order1 = new Order(lst[0], lst[1], lst[2], lst[3], lst[4], prod);
                this.tablelst.add(order1);
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Something wrong : " + e);
        }
    }

    private void addOrderToDatabase() {

        try {
            File file = new File("Orders.txt");
            FileWriter writer = new FileWriter(file, false);
            for (Order o : tablelst) {
                writer.write(o.getName() + "@" + o.getTele() + "@" + o.getAddress() + "@" +
                        o.getDeliveryAdd() + "@"
                        + o.getTOD() + "@" + o.getProduct() + "\n");
            }
            writer.close();
        } catch (IOException error) {
            JFrame popupError = new JFrame();
            JOptionPane.showMessageDialog(popupError, "Error adding to file.");
        }

    }

    public String[] productDisplayNumber() {
        readData();
        ArrayList<Order> prod = tablelst;
        String[] products = new String[prod.size()];
        for (int i = 0; i < prod.size(); i++) {
            products[i] = String.valueOf(i);
        }
        tablelst.clear();
        tablelst1.clear();
        return products;
    }
}