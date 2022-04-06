package ProductManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; //output files
import java.io.IOException; //outputfiles
import java.io.PrintStream; //outputfiles
import java.io.FileOutputStream; //output files
import javax.swing.*;
import javax.swing.JButton;
//import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
//import java.util.Comparator;
//import java.util.Collections;
//import java.awt.Color;

import Logs.ChangelogScreen;
import ProductManager.*;
import OrderController.*;

import java.text.SimpleDateFormat; //used for date formatting
import java.util.Date; //used to calculate current date

/** Creates window with containment */
public class StockController extends JPanel {
    private JButton cmdAddProduct;
    private JButton cmdClose;
    private JButton cmdEditProduct;
    private JButton cmdRemoveProduct;
    private JButton cmdChangelog;
    private JButton cmdOrder; // This is where the new order button is instantiated

    private JPanel pnlCommand;
    // private JPanel pnlDisplay;
    public ArrayList<Product> plist; // Product/Product listing
    private StockController thisForm;
    private JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public StockController() {
        super(new GridLayout(2, 1));
        thisForm = this;

        pnlCommand = new JPanel();
        // pnlDisplay = new JPanel();

        plist = loadProducts("Product.txt");
        String[] columnNames = { "ID",
                "Supplier",
                "Product Name",
                "Quantity",
                "Cost" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(plist);

        table.setPreferredScrollableViewportSize(new Dimension(500, plist.size() * 15 + 50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        // pnlCommand.setBackground(Color.blue); //sets the color of the bottom window
        // with buttons background to blue
        // pnlDisplay.setBackground(Color.ORANGE); //sets color of top window

        cmdAddProduct = new JButton("Add Product");
        cmdEditProduct = new JButton("Edit Product");
        cmdRemoveProduct = new JButton("Remove Product");
        cmdChangelog = new JButton("Changelog");
        cmdOrder = new JButton("Create Order"); // Creates an Order button to on main interface
        cmdClose = new JButton("Close");

        cmdAddProduct.addActionListener(new AddProductListener());
        cmdEditProduct.addActionListener(new EditProductListener());
        cmdRemoveProduct.addActionListener(new RemoveProductListener());
        cmdChangelog.addActionListener(new ChangelogListener());
        cmdClose.addActionListener(new CloseButtonListener());
        cmdOrder.addActionListener(new OrderButtonListener()); // Adds action listener to Order button to open new
                                                               // Create Order Interface.

        pnlCommand.add(cmdAddProduct);
        pnlCommand.add(cmdEditProduct);
        pnlCommand.add(cmdRemoveProduct);
        pnlCommand.add(cmdChangelog);
        pnlCommand.add(cmdOrder);
        pnlCommand.add(cmdClose);

        add(pnlCommand);
    }

    private void showTable(ArrayList<Product> plist) {
        int i = 0;
        while (plist.size() > i) {
            addToTable(plist.get(i));
            i++;
        }

    }

    private void addToTable(Product p) {
        String[] item = { "" + p.getID(), p.getSupplier(), p.getName(), "" + p.getQuantity(), "" + p.getPrice() };

        model.addRow(item);
    }

    public static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Product Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        StockController newContentPane = new StockController();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // this updates the file storing information when a Product is
    // added/edited/deleted
    public void updateRecord() {
        try {
            PrintStream writer = new PrintStream(new FileOutputStream("Product.txt"));
            for (Product p : plist) {
                writer.println(p.getSupplier() + " " + p.getName() + " " + p.getQuantity() + " " + p.getPrice());
            }
            writer.close();
        } catch (IOException error) {
            JFrame popupError = new JFrame();
            JOptionPane.showMessageDialog(popupError, "Error adding to file.");
        }

    }

    public void updateChangelog(Product p, String change) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = formatter.format(new Date());
        String[] date = dateString.split(" ");
        try {
            PrintStream writer = new PrintStream(new FileOutputStream("changelog.txt", true));
            writer.println(p.getName() + " " + p.getQuantity() + " " + p.getPrice() + " " + change + " " + date[0] + " "
                    + date[1]);
            writer.close();
        } catch (IOException error) {
            JFrame popupError = new JFrame();
            JOptionPane.showMessageDialog(popupError, "Error adding to file.");
        }

    }

    public void addProduct(Product p) {
        plist.add(p);
        addToTable(p);
        updateRecord();
        updateChangelog(p, "Added");

    }

    public void editProduct(Product p, int ID) {
        plist.set(ID, p);
        model.setRowCount(0);
        for (Product Product : plist) {
            addToTable(Product);
        }
        updateRecord();
        updateChangelog(p, "Edited");
    }

    // This loadProducts section loads the file with the Products to add them to
    // the display
    private ArrayList<Product> loadProducts(String pfile) {
        Scanner pscan = null;
        ArrayList<Product> plist = new ArrayList<Product>();
        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[1];
                String supplier = nextLine[0];
                int quantity = Integer.parseInt(nextLine[2]);
                double price = Double.parseDouble(nextLine[3]);

                Product p = new Product(name, quantity, price, supplier);
                plist.add(p);
            }

            pscan.close();
        } catch (IOException e) {
            JFrame errorMessage = new JFrame();
            JOptionPane.showMessageDialog(errorMessage, "Loading failed!");
        }
        return plist;
    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    // Newly added ActionListener for creating orders ##########
    private class OrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new OrderUserInterface();
        }

    }

    private class AddProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new ProductEntry(thisForm);
        }

    }

    private class EditProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int ID = -1;
            JFrame popup = new JFrame();
            String input = JOptionPane.showInputDialog(popup, "Enter the ID of Product to be edited."); // brings up
                                                                                                        // dialog box to
                                                                                                        // enter ID.
            if (input == null) {
                return;
            }

            try {
                ID = Integer.parseInt(input);
            } catch (NumberFormatException error) {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "Make sure the ID you entered is a number!");
                return;
            }

            if (plist.size() <= ID || ID < 0)// This will check list using ID to see if it's in range
            {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "ID number not found!");
                return;
            }
            Product p = thisForm.plist.get(ID);
            new ProductEntry(thisForm, p, ID);
        }
    }

    private class RemoveProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int ID;

            JFrame popup = new JFrame();
            String input = JOptionPane.showInputDialog(popup, "Enter ID of Product to be removed");
            if (input == null) {
                return;
            }

            try {
                ID = Integer.parseInt(input);
            } catch (NumberFormatException error) {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "Make sure the ID you entered is a number!");
                return;
            }

            if (plist.size() <= ID || ID < 0) {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "ID number not found!");
                return;
            }

            Product p = thisForm.plist.get(ID);
            JFrame f = new JFrame();
            int response = JOptionPane.showConfirmDialog(f,
                    "Do you want to remove this Product?\nName: " + p.getName() +
                            "\nQuantity: " + p.getQuantity() +
                            "\nPrice: " + p.getPrice(),
                    "Choose one", JOptionPane.YES_NO_OPTION);
            if (response == 0) {
                thisForm.plist.remove(ID);
                model.setRowCount(0);
                for (Product Product : plist) {
                    addToTable(Product);
                }
                updateRecord();
                updateChangelog(p, "Removed");
            }
        }
    }

    private class ChangelogListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new ChangelogScreen();
        }

    }

    // private class SortButtonListener implements ActionListener
    // {
    // public void actionPerformed(ActionEvent e)
    // {
    // model.setRowCount(0);
    // Collections.sort(plist);
    // for (Person p : plist)
    // {
    // addToTable(p);
    // }
    // }

    // }

    // private class SortNameListener implements ActionListener
    // {
    // public void actionPerformed(ActionEvent e)
    // {
    // model.setRowCount(0);
    // Collections.sort(plist, new SortName());
    // for (Person p : plist)
    // {
    // addToTable(p);
    // }
    // }
    // }
    //
}