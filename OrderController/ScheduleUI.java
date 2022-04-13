package OrderController;

import java.util.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProductManager.Product;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ScheduleUI extends JFrame implements ActionListener {

    public JButton cmdClose;
    public JPanel pnlCommand;

    public JScrollPane scrollPane;
    public JTable table;
    public DefaultTableModel model;

    public ArrayList<Receipt> rlist = new ArrayList<Receipt>();
    public ArrayList<Date> dlist = new ArrayList<Date>();

    ScheduleUI() {
        setTitle("schedule");
        pnlCommand = new JPanel();

        rlist = loadReceipt("Receipts.txt");

        String[] columnNames = {
                "Customer Name",
                "Telephone",
                "Delivery Address",
                "Delivery Date",
                "Product Name",
                "Quantity" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(rlist, dlist);

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showTable(ArrayList<Receipt> rlist2, ArrayList<Date> dlist2) {
        Date d = new Date();
        List<Date> dates = d.sort(dlist2);
        int i = 0;
        while (dates.size() > i) {

            int x = 0;
            while (rlist2.size() > x) {
                Date d1 = new Date(rlist2.get(x).getOrder().getTOD());
                String date1 = d1.toString();
                String compare = dates.get(i).toString();
                //System.out.println("Compare : --> " + compare);
                //System.out.println("Date1 : --> " + date1);
                if (compare.equalsIgnoreCase(date1)) {
                    addToTable(rlist.get(x));
                    //System.out.println("They are equal : --> " + compare + " == " + date1);
                    break;
                }
                x++;
            }
            i++;
        }
    }

    private void addToTable(Receipt r) {
        Order p = r.getOrder();
        String[] item = { "" + p.getName(), "" + p.getTele(), "" + p.getDeliveryAdd(),
                "" + p.getTOD(), "" + p.getProduct().getName(), "" + p.getProduct().getQuantity() };
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
                Date date = new Date(lst[4]);
                dlist.add(date);
                rlist.add(r);
            }

            rscan.close();
        } catch (IOException e) {
            JFrame errorMessage = new JFrame();
            JOptionPane.showMessageDialog(errorMessage, "Loading failed!");
        }
        return rlist;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == cmdClose) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new ScheduleUI();
    }

}

class Date implements Comparable<Date> {
    public int month;
    public int day;
    public int year;

    Date() {
    }

    Date(String deliveryTime) {
        String devTime = deliveryTime.replaceAll(",", "");
        String[] date = devTime.split(" ");
        this.month = dict(date[0]);
        this.day = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);
        // System.out.println(toString());
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    public String toString() {
        String m = String.valueOf(getMonth());
        String d = String.valueOf(getDay());
        String y = String.valueOf(getYear());
        return d + "/" + m + "/" + y;
    }

    private int dict(String month) {
        Dictionary myDict = new Hashtable<>();
        myDict.put("JANUARY", 1);
        myDict.put("FEBRUARY", 2);
        myDict.put("MARCH", 3);
        myDict.put("APRIL", 4);
        myDict.put("MAY", 5);
        myDict.put("JUNE", 6);
        myDict.put("JULY", 7);
        myDict.put("AUGUST", 8);
        myDict.put("SEPTEMBER", 9);
        myDict.put("OCTOBER", 10);
        myDict.put("NOVEMBER", 11);
        myDict.put("DECEMBER", 12);

        String key = month.toUpperCase();

        try {
            int monthcoverted = Integer.parseInt(myDict.get(key).toString());
            return monthcoverted;

        } catch (NullPointerException e) {
            // TODO: handle exception
            e.printStackTrace();
            return 999;
        } catch (Exception f) {
            f.printStackTrace();
            return -999;
        }
    }

    public List<Date> sort(ArrayList<Date> receipts) {
        List<Date> sorted = receipts;
        Collections.sort(sorted);
        return sorted;
    }

    // public static void main(String[] args) {
    // String test = "May 12, 2022";
    // String test2 = "January 14, 2025";
    // String test3 = "January 15, 2022";
    // String test4 = "February 17, 2022";
    // String test5 = "January 19, 2022";
    // String test6 = "January 27, 2022";

    // ArrayList<Date> lst = new ArrayList<Date>();

    // Date date = new Date(test);
    // Date date2 = new Date(test2);
    // Date date3 = new Date(test3);
    // Date date4 = new Date(test4);
    // Date date5 = new Date(test5);
    // Date date6 = new Date(test6);

    // lst.add(date);
    // lst.add(date2);
    // lst.add(date3);
    // lst.add(date4);
    // lst.add(date5);
    // lst.add(date6);

    // System.out.println("This list should be sorted");
    // for (Date d : date6.sort(lst)) {
    // System.out.println(d.toString());
    // }
    // }

    @Override
    public int compareTo(Date o) {
        if (this.year > o.getYear()) {
            return 1;
        }
        if (this.year < o.getYear()) {
            return -1;
        } else {
            if (this.month > o.getMonth()) {
                return 1;
            }
            if (this.month < o.getMonth()) {
                return -1;
            } else {
                if (this.day > o.getDay()) {
                    return 1;
                }
                if (this.day < o.getDay()) {
                    return -1;
                }
            }
        }
        return 0;
    }

}
