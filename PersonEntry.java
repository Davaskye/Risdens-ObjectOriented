package proj;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
//import java.io.IOError;
import java.awt.event.ActionEvent;

public class PersonEntry extends JFrame
{
    private static final long serialVersionUID = 1; //Version of software? Dunno. Jpanel asks for it though.
    private JTextField  txtName;       //name
    private JTextField  txtAge;        //age
    private JTextField  txtBudget;     //budget
    private JButton     cmdSave;
    private JButton     cmdClose;
    //private JButton     cmdClearAll;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private PersonListing personListingVar;
    private Person editP;
    private int editID;
  /** @param PersonListing Lets you enter a person listing and sets up the panels and buttons */
    public PersonEntry(PersonListing personListingVar)
    {
        this.personListingVar = personListingVar;
        setTitle("Adding a New Promoter");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Age:"));
        txtAge = new JTextField(3);
        pnlDisplay.add(txtAge);
        pnlDisplay.add(new JLabel("Budget:"));
        txtBudget = new JTextField(20);
        pnlDisplay.add(txtBudget);
        pnlDisplay.setLayout(new GridLayout(3,4));
       
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        cmdClose.addActionListener(new ButtonListener());
        cmdSave.addActionListener(new ButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    public PersonEntry(PersonListing personListingVar, Person p, int ID)
    {
        this.personListingVar = personListingVar;
        editP = p;
        editID = ID;
        setTitle("Editing a Promoter");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(20);
        txtName.setText(p.getName());
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Age:"));
        txtAge = new JTextField(3);
        txtAge.setText(""+p.getAge());
        pnlDisplay.add(txtAge);
        pnlDisplay.add(new JLabel("Budget:"));
        txtBudget = new JTextField(20);
        txtBudget.setText(""+p.getBudget());
        pnlDisplay.add(txtBudget);
        pnlDisplay.setLayout(new GridLayout(3,4));
       
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        cmdClose.addActionListener(new ButtonListener());
        cmdSave.addActionListener(new ButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

/** listens for actions from button presses */
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == cmdClose) //if button click is close. it closes
                setVisible(false);
            else if (e.getSource() == cmdSave) //actions for if button clicked is save below
            {

                String[] checkName = txtName.getText().split(" ");
                int checkAge = 0;
                double checkBudget = 0;
                try
                {
                    checkAge = Integer.parseInt(txtAge.getText());
                    checkBudget = Double.parseDouble(txtBudget.getText());

                }
                catch (NumberFormatException error)
                {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Make sure your age or budget is a number!");
                    return;
                    //System.out.println("Make sure your age or budget is a number!");
                }
                if((checkName.length == 2)&&(checkAge != 0))
                {
                    Person p = new Person(txtName.getText(), checkAge, checkBudget);
                    if (editP == null)
                    personListingVar.addPerson(p);
                    else
                    personListingVar.editPerson(p, editID);
                    
                    setVisible(false);
                }
                else
                {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Make sure your name has a space!");
                    return;
                    //System.out.println("Make sure your name has a space bro!");
                }


            }
        }

    }




}