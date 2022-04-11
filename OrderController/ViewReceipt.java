package OrderController;

import javax.swing.JFrame;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class ViewReceipt extends JFrame implements ActionListener {

    public Order served; // the order to be stored to a receipt database and displayed at a later date if
                         // necessary

    public ViewReceipt(Order servedOrder) {
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
    }

    public Order getServed() {
        return this.served;
    }

    public void createFile() {
        // JADA
        // this function should create the receipt database if it doesnt already exist
        // and store the data received from the first constructor
    try {
        File file = new File("Receipts.txt");
        FileWriter myWriter = new FileWriter(file, false);
            myWriter.write(this.served.toString() + "@" +  "\n");
    
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
    }

}
