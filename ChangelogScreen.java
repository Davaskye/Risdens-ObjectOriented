package proj;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException; //outputfiles
import java.io.PrintStream; //outputfiles
import java.io.FileOutputStream; //output files

public class ChangelogScreen extends JFrame {
    private static final long serialVersionUID = 1; // JFrame asks for a serialVersionUID via [warning]. Added but not
                                                    // used.
    private JButton cmdClose;
    private JPanel pnlCommand;
    // private JPanel pnlDisplay;

    // private ChangelogScreen thisForm;
    private ArrayList<Changelog> clist;

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public ChangelogScreen() {
        setTitle("Changelog");
        pnlCommand = new JPanel();
        // thisForm = this;
        clist = loadChangelog("changelog.dat");

        String[] columnNames = { "Date Changed",
                "Time Changed",
                "First Name",
                "Last Name",
                "Status" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(clist);

        table.setPreferredScrollableViewportSize(new Dimension(500, clist.size() * 15 + 50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        cmdClose = new JButton("Close");

        cmdClose.addActionListener(new cmdCloseListener());

        pnlCommand.add(cmdClose);

        add(pnlCommand, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void showTable(ArrayList<Changelog> clist) {
        int i = 0;
        while (clist.size() > i) {
            addToTable(clist.get(i));
            i++;
        }

    }

    private void addToTable(Changelog c) {
        String[] name = c.getName().split(" ");
        String[] item = { c.getDate(), c.getTime(), name[0], name[1], c.getChange() };

        model.addRow(item);
    }

    private ArrayList<Changelog> loadChangelog(String cfile) {
        Scanner cscan = null;
        ArrayList<Changelog> clist = new ArrayList<Changelog>();
        try {
            cscan = new Scanner(new File(cfile));
            while (cscan.hasNext()) {
                String[] nextLine = cscan.nextLine().split(" ");
                String promoterInfo = nextLine[0] + " " + nextLine[1];
                String whatHappened = nextLine[2];
                String changeDate = nextLine[3];
                String changeTime = nextLine[4];

                Changelog c = new Changelog(changeDate, changeTime, promoterInfo, whatHappened);
                clist.add(c);
            }

            cscan.close();
        } catch (IOException e) {
            JFrame errorMessage = new JFrame();
            JOptionPane.showMessageDialog(errorMessage, "Loading failed!");
        }
        return clist;
    }

    private class cmdCloseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
