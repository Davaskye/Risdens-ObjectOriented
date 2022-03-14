package proj;
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
import java.util.Collections;
//import java.awt.Color;

import java.text.SimpleDateFormat; //used for date formatting
import java.util.Date; //used to calculate current date

/** Creates window with containment */
public class PersonListing extends JPanel {
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortBudget;
    private JButton     cmdSortName;
    private JButton     cmdEditPerson;
    private JButton     cmdRemovePerson;
    private JButton     cmdChangelog;
  
    private JPanel      pnlCommand;
    //private JPanel      pnlDisplay;
    private ArrayList<Person> plist; //promoter/person listing
    private PersonListing thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public PersonListing() {
        super(new GridLayout(2,1));
        thisForm = this;
        

        pnlCommand = new JPanel();
        //pnlDisplay = new JPanel();

        plist= loadPersons("person.dat");
        String[] columnNames=  {"ID",
                "First Name",
                "Last Name",
                "Age",
                "Budget"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(plist);

        table.setPreferredScrollableViewportSize(new Dimension(500, plist.size()*15 +50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
       
        add(scrollPane);

       //pnlCommand.setBackground(Color.blue); //sets the color of the bottom window with buttons background to blue
       //pnlDisplay.setBackground(Color.ORANGE); //sets color of top window
        

        cmdAddPerson  = new JButton("Add Promoter");
        cmdEditPerson = new JButton("Edit Promoter");
        cmdRemovePerson = new JButton("Remove Promoter");
        cmdSortBudget  = new JButton("Sort by Budget");
        cmdSortName = new JButton("Sort by First Name");
        cmdChangelog = new JButton("Changelog");
        cmdClose   = new JButton("Close");

        cmdAddPerson.addActionListener(new AddPersonListener());
        cmdEditPerson.addActionListener(new EditPersonListener());
        cmdRemovePerson.addActionListener(new RemovePersonListener());
        cmdSortName.addActionListener(new SortNameListener());
        cmdSortBudget.addActionListener(new SortButtonListener());
        cmdChangelog.addActionListener(new ChangelogListener());
        cmdClose.addActionListener(new CloseButtonListener());
        
        
        pnlCommand.add(cmdAddPerson);
        pnlCommand.add(cmdEditPerson);
        pnlCommand.add(cmdRemovePerson);
        pnlCommand.add(cmdSortBudget);
        pnlCommand.add(cmdSortName);
        pnlCommand.add(cmdChangelog);
        pnlCommand.add(cmdClose);
       
        add(pnlCommand);
    }

    private void showTable(ArrayList<Person> plist)
    {
        int i = 0;
       while (plist.size()>i)
       {
         addToTable(plist.get(i));
         i++;
       }

    }

    private void addToTable(Person p)
    {
        String[] name= p.getName().split(" ");
        String[] item={""+p.getId(),name[0],name[1],""+ p.getAge(),""+p.getBudget()};
        
        model.addRow(item);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Promoter Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        PersonListing newContentPane = new PersonListing();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
    }

    // this updates the file storing information when a promoter is added/edited/deleted
    public void updateRecord()
    {
        try
        {
            PrintStream writer=new PrintStream(new FileOutputStream("person.dat"));
            for (Person p : plist) 
            {
                writer.println(p.getName()+ " "+ p.getAge() + " "+ p.getBudget());
            }
            writer.close(); 
        }
        catch(IOException error)
        {
            JFrame popupError = new JFrame();
            JOptionPane.showMessageDialog(popupError, "Error adding to file.");
        }      

    }
    public void updateChangelog(Person p, String change)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = formatter.format(new Date());
        String [] date = dateString.split(" ");
        try
        {
            PrintStream writer=new PrintStream(new FileOutputStream("changelog.dat",true));
            writer.println(p.getName()+ " "+ change + " "+ date[0] + " " + date[1]);
            writer.close(); 
        }
        catch(IOException error)
        {
            JFrame popupError = new JFrame();
            JOptionPane.showMessageDialog(popupError, "Error adding to file.");
        }      

    }


    public void addPerson(Person p)
    {
        plist.add(p);
        addToTable(p);
        updateRecord();
        updateChangelog(p, "Added");
        
    }

    public void editPerson(Person p, int ID)
    {
        plist.set(ID, p);
        model.setRowCount(0);
        for(Person person: plist)
        {
            addToTable(person);
        }
        updateRecord();
        updateChangelog(p, "Edited");
    }
    //This loadPersons section loads the file with the promoters to add them to the display
    private ArrayList<Person> loadPersons(String pfile)
    {
        Scanner pscan = null;
        ArrayList<Person> plist = new ArrayList<Person>();
        try
        {
            pscan  = new Scanner(new File(pfile));
            while(pscan.hasNext())
            {
                String [] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0]+ " " + nextLine[1];
                int age = Integer.parseInt(nextLine[2]);
                double budget = Double.parseDouble(nextLine[3]);
               
                Person p = new Person(name, age, budget);
                plist.add(p);
            }

            pscan.close();
        }
        catch(IOException e)
        {
            JFrame errorMessage = new JFrame();
            JOptionPane.showMessageDialog(errorMessage, "Loading failed!");
        }
        return plist;
    }

    
    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }

    }

    private class AddPersonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            new PersonEntry(thisForm);
        }

    }

    private class EditPersonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int ID = -1;
            JFrame popup=new JFrame();
            String input = JOptionPane.showInputDialog(popup,"Enter the ID of person to be edited."); //brings up dialog box to enter ID.
            if (input == null)
            {
                return;
            }

            try
            {
                ID = Integer.parseInt(input);
            }
            catch(NumberFormatException error)
            {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "Make sure the ID you entered is a number!");
                return;
            }

            if (plist.size() <= ID || ID < 0)//This will check list using ID to see if it's in range
            {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "ID number not found!");
                return;
            }
            Person p = thisForm.plist.get(ID);
            new PersonEntry(thisForm, p, ID);
        }
    }

    private class RemovePersonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int ID;

            JFrame popup=new JFrame();
            String input = JOptionPane.showInputDialog(popup,"Enter ID of person to be removed");
            if (input == null)
            {
                return;
            }

            try
            {
                ID = Integer.parseInt(input);
            }
            catch(NumberFormatException error)
            {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "Make sure the ID you entered is a number!");
                return;
            }

            if (plist.size() <= ID || ID < 0)
            {
                JFrame errorMessage = new JFrame();
                JOptionPane.showMessageDialog(errorMessage, "ID number not found!");
                return;
            }

            Person p = thisForm.plist.get(ID);
            JFrame f = new JFrame();
            int response = JOptionPane.showConfirmDialog(f, "Do you want to remove this person?\nName: "+ p.getName() + 
                                                        "\nAge: "+p.getAge()+ 
                                                        "\nBudget: "+p.getBudget(),
                                                        "Choose one",JOptionPane.YES_NO_OPTION);
            if(response==0)
            {
                thisForm.plist.remove(ID);
                model.setRowCount(0);
                for(Person person: plist)
                {
                    addToTable(person);
                }
                updateRecord();
                updateChangelog(p, "Removed");
            }
        }
    }

    private class ChangelogListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            new ChangelogScreen();
        }

    }

    private class SortButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            Collections.sort(plist);
            for (Person p : plist) 
            {
                addToTable(p);
            }
        }

    }

    private class SortNameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            Collections.sort(plist, new SortName());
            for (Person p : plist) 
            {
                addToTable(p);
            }
        }

    }

}