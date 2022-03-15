package proj;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
//import java.io.IOError;
import java.awt.event.ActionEvent;

public class ProductEntry extends JFrame
{
    private static final long serialVersionUID = 1; //Version of software? Dunno. Jpanel asks for it though.
    private JTextField  txtName;      //product name
    private JTextField  txtQuantity;  //quantity
    private JTextField  txtPrice;     //price
    private JTextField  txtSupplier;  //supplier
    private JButton     cmdSave;
    private JButton     cmdClose;
    //private JButton     cmdClearAll;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private ProductListing productListingVar;
    private Product editP;
    private int editID;
  /** @param ProductListing Lets you enter a product listing and sets up the panels and buttons */
    public ProductEntry(ProductListing productListingVar)
    {
        this.productListingVar = productListingVar;
        setTitle("Add a New Product");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Product Name:")); 
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Quantity:"));
        txtAge = new JTextField(3);
        pnlDisplay.add(txtQuantity);
        pnlDisplay.add(new JLabel("Price:"));
        txtBudget = new JTextField(20);
        pnlDisplay.add(txtPrice);
        pnlDisplay.setLayout(new GridLayout(3,4));
        pnlDisplay.add(new JLabel("Supplier:")); 
        txtSupplier = new JTextField(20);
        pnlDisplay.add(txtSupplier);
       
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

    public ProductEntry(ProductListing productListingVar, Product p, int ID)
    {
        this.productListingVar = productListingVar;
        editP = p;
        editID = ID;
        setTitle("Editing a Product");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Product Name:")); 
        txtName = new JTextField(20);
        txtName.setText(p.getProductName());
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField(3);
        txtQuantity.setText(""+p.getQuantity());
        pnlDisplay.add(txtQuantity);
        pnlDisplay.add(new JLabel("Price:"));
        txtPrice = new JTextField(20);
        txtPrice.setText(""+p.getPrice());
        pnlDisplay.add(txtPrice);
        pnlDisplay.setLayout(new GridLayout(3,4));
        pnlDisplay.add(new JLabel("Supplier:")); 
        txtSupplier = new JTextField(20);
        txtSupplier.setText(p.getSupplier());
        pnlDisplay.add(txtSupplier);
       
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
                int checkQuantity = 0;
                double checkPrice = 0;
                try
                {
                    checkQuantity = Integer.parseInt(txtQuantity.getText());
                    checkPrice = Double.parseDouble(txtPrice.getText());

                }
                catch (NumberFormatException error)
                {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Make sure your quantity or price is a number!");
                    return;
                    //System.out.println("Make sure your quanity or price is a number!");
                }
                if((checkName.length == 2)&&(checkQuantity != 0))
                {
                    Product p = new Product(txtName.getText(), checkQuantity, checkPrice);
                    if (editP == null)
                    productListingVar.addProduct(p);
                    else
                    productListingVar.editProduct(p, editID);
                    
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