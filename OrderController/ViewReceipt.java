package OrderController;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import ProductManager.Product;
import ProductManager.StockController;

public class ViewReceipt extends JFrame implements ActionListener {

    public Receipt served; // the order to be stored to a receipt database and displayed at a later date if
                         // necessary

    private JButton cmdClose;
    private JPanel pnlCommand;

    public JScrollPane scrollPane;
    public JTable table;
    public DefaultTableModel model;

    public ArrayList<Receipt> rlist = new ArrayList<Receipt>();


    public ViewReceipt(Receipt servedOrder) {
        // this constructor receives the order that was severd from the ServeOrder class
        // and saves it as an attribute to
        // be saved to a file
        // this is invoked from OrderUserInterface, class ServeOrder; line 603
        this.served = servedOrder;
    }

    public ViewReceipt() {
        
        // DAVASKYE
        // create UI that will display the order that was served
        // should include table and button that says "close/back" for now
        // this is invoked from OrderUserInterface class; line 120
        setTitle("Receipts");
        pnlCommand = new JPanel();

        rlist = loadReceipt("Receipts.txt");

        String[] columnNames = {
                "Customer Name",
                "Telephone",
                "Customer Address",
                "Delivery Address",
                "Delivery Time",
                "Product Name",
                "Quantity",
                "Price",
                "Total",
                "Payment",
                "Change"
             };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(rlist);

        table.setPreferredScrollableViewportSize(new Dimension(1200, rlist.size() * 15 + 50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        cmdClose = new JButton("Close");

        cmdClose.addActionListener(this);

        pnlCommand.add(cmdClose);

        add(pnlCommand, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }


    private void showTable(ArrayList<Receipt> rlist) {
        int i = 0;
        while (rlist.size() > i) {
            addToTable(rlist.get(i));
            i++;
        }

    }

    private void addToTable(Receipt r) {

        Order p = r.getOrder();
        String[] item = {"" + p.getName(), p.getTele(), p.getAddress(),
        "" + p.getDeliveryAdd(),
        "" + p.getTOD(), "" + p.getProduct().getName(), "" + p.getProduct().getQuantity(),
        "$" + p.getProduct().getPrice() , "$"+r.getTotal(),"$"+r.getPayment(), "$"+r.getChange()};
        model.addRow(item);
    }


    private ArrayList<Receipt> loadReceipt(String rfile) {
        Scanner rscan = null;
        ArrayList<Receipt> rlist = new ArrayList<Receipt>();
        try {
            rscan = new Scanner(new File(rfile));
            while (rscan.hasNext()) {
                String data = rscan.nextLine();
                String[] lst = data.split("@", 0);
                String data2 = lst[5];
                String[] p = data2.split(" ", 0);
                Product prod = new Product(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]), p[3]);
                Order o = new Order(lst[0], lst[1], lst[2], lst[3], lst[4], prod);
                Receipt r = new Receipt(o, Double.parseDouble(lst[7])); 
                rlist.add(r);
            }

            rscan.close();
        } catch (IOException e) {
            JFrame errorMessage = new JFrame();
            JOptionPane.showMessageDialog(errorMessage, "Loading failed!");
        }
        return rlist;
    }

    public Receipt getServed() {
        return this.served;
    }

    public void createFile() {
        // JADA
        // this function should create the receipt database if it doesnt already exist
        // and store the data received from the first constructor
        try {
            File file = new File("Receipts.txt");
            FileWriter myWriter = new FileWriter(file, true);
            Order o = served.getOrder();
                myWriter.write(o.toString() + "@" +  served.getTotal() + "@"+ served.getPayment() +"@"+ served.getChange());
                myWriter.write(System.lineSeparator());
    
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readData() {
        // DAVASKYE
        // this function should read from the file created/that already exists to be
        // displayed on the User Interface
        
    }

    public void actionPerformed(ActionEvent e) {
        // DAVASKYE
        // this function is the action listener for any buttons on the User Interface
        // can review OrderUserInterface class to see how its done if you are not
        // familiar with writing it this way
        if (e.getSource() == cmdClose) {
            this.dispose();
        }
    }

}
