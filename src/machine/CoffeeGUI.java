package machine;

import Barista.Barista;
import Coffee.Coffee;
import Database.DBCoffee;
import Order.OrderCoffee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

//GUI for an app
public class CoffeeGUI extends JFrame {
    //Check1 Check2 Check3 for choosing coffee
    private final JButton button = new JButton("Order!");
    private final JLabel label = new JLabel("Choose:");
    private JCheckBox check1 ;
    private JCheckBox check2 ;
    private JCheckBox check3 ;

    //Int this inputs we write  how many cup of each coffee we want
    private final JFormattedTextField input1 = new JFormattedTextField();
    private final JFormattedTextField input2 = new JFormattedTextField();
    private final JFormattedTextField input3 = new JFormattedTextField();


    //DBCoffee
    private DBCoffee database = null;



    public CoffeeGUI() {
        super("Coffee Machine");

        //Connecting to DB
        try {
            database = new DBCoffee("jdbc:mysql://localhost:3306/coffee_bar?autoReconnect=true&useSSL=false", "root", "idealcode");
        } catch (SQLException | ClassNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "Something went wrong, try run app later", "Error", JOptionPane.ERROR_MESSAGE);
        }


        double cost = 0;

        // get cost for Americano from database
        try {
            cost = database.getCost(1);
            check1 = new JCheckBox("Americano $" + cost);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Something went wrong, try run app later", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // get cost for Latte from database
        try {
            cost = database.getCost(2);
            check2 = new JCheckBox("Latte  $" + cost);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Something went wrong, try run app later", "Error", JOptionPane.ERROR_MESSAGE);
        }


        // get cost for Cappuccino from database
        try {
            cost = database.getCost(3);
            check3 = new JCheckBox("Cappuccino  $" + cost);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Something went wrong, try run app later", "Error", JOptionPane.ERROR_MESSAGE);
        }


        //making open a windows in the middle
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2 - 250 , dimension.height/2 - 200, 500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making layout
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(15, 1, 2, 2));
        container.add(label);
        //adding input and check
        container.add(check1);
        container.add(input1);

        //Checking if input is an integer
        JLabel label1 = new JLabel();
        label1.setForeground(Color.RED);
        container.add(label1);
        input1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isLetter(c)) {
                    label1.setText("Invalid Input");
                } else {
                    label1.setText(" ");
                }
            }
        });

        //adding input and check
        container.add(check2);
        container.add(input2);
        //Checking if input is an integer
        JLabel label2 = new JLabel();
        label2.setForeground(Color.RED);
        container.add(label2);
        input2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isLetter(c)) {
                    label2.setText("Invalid Input");
                } else {
                    label2.setText("");
                }
            }
        });

        //adding input and check
        container.add(check3);
        container.add(input3);
        //Checking if input is an integer
        JLabel label3 = new JLabel();
        label3.setForeground(Color.RED);
        container.add(label3);
        input3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isLetter(c)) {
                    label3.setText("Invalid Input");
                } else {
                    label3.setText("");
                }
            }
        });
        //adding button
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            /* k1, k2, k3 are used to display how many times was coffee purchased */
            int k1 = 0;
            int k2 = 0;
            int k3 = 0;

            /* message will show massage  */
            StringBuilder message = new StringBuilder(" ");
            //Order is a builder, barista is Director, coffee is an arraylist of Coffee
            OrderCoffee order = new OrderCoffee();
            Barista barista = new Barista();
            ArrayList<Coffee> coffee = new ArrayList<Coffee>();

            //String for error
            String err = " ";
            //i is used to upload new purchases to database
            //cost just calculates overall cost of an order
            int i =0;
            double cost = 0.0;

            //calculating if is selected
            if (check1.isSelected()){
                order.MakeAmericano(barista);
                coffee.add(barista.getResult());
                try {
                    if(Integer.parseInt(input1.getText()) > 0) {
                        i = database.getPurchased(1) + Integer.parseInt(input1.getText());
                        cost += database.getCost(1) * Double.parseDouble(input1.getText());
                        database.setNewPurchase(i, 1);
                        k1 = database.getPurchased(1);
                    } else {
                        err = "You have ordered with invalid input, please write positive integer number";
                    }
                } catch (SQLException | NumberFormatException exception) {
                    err = "You have ordered with invalid input, please write positive integer number";
                }
            }

            //calculating if is selected
            if(check2.isSelected()){
                order.MakeLatte(barista);
                coffee.add(barista.getResult());
                try {
                    if(Integer.parseInt(input2.getText()) > 0) {
                        i = database.getPurchased(2) + Integer.parseInt(input2.getText());
                        cost += database.getCost(2) * Double.parseDouble(input2.getText());
                        database.setNewPurchase(i, 2);
                        k2 = database.getPurchased(2);
                    } else {
                        err = "You have ordered with invalid input, please write positive integer number";
                    }
                } catch (SQLException | NumberFormatException exception) {
                    err = "You have ordered with invalid input, please write positive integer number";
                }
            }

            //calculating if is selected
            if(check3.isSelected()){
                order.MakeCappuccino(barista);
                coffee.add(barista.getResult());
                try {
                    if(Integer.parseInt(input3.getText()) > 0) {
                        i = database.getPurchased(3) + Integer.parseInt(input3.getText());
                        cost += database.getCost(3)  * Double.parseDouble(input3.getText());
                        database.setNewPurchase(i, 3);
                        k3 =database.getPurchased(3);
                    } else {
                        err = "You have ordered with invalid input, please write positive integer number";
                    }
                } catch (SQLException | NumberFormatException exception) {
                    err = "You have ordered with invalid input, please write positive integer number";
                }
            }
            if(err.equals(" ")) {

                message.append(check1.isSelected() ? " You ordered " + input1.getText() + " Americano(Overall in our coffee bar it was purchased " + k1 + " times)\n\n" : "");
                message.append(check2.isSelected() ? " You ordered " + input2.getText() + " Latte(Overall in our coffee bar it was purchased " + k2 + " times)\n\n" : "");
                message.append(check3.isSelected() ? " You ordered " + input3.getText() + " Cappuccino(Overall in our coffee bar it was purchased " + k3 + " times)\n\n" : "");

                for (Coffee c : coffee) {
                    message.append(c.toString()).append("\n\n");
                }
                // to round a "cost" variable
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);


                message.append((check1.isSelected() || check2.isSelected() || check3.isSelected()) ? "\nCost: $" : "You didn't ordered anything, pick checkboxes ");
                message.append((check1.isSelected() || check2.isSelected() || check3.isSelected()) ? df.format(cost) : " ");

                JOptionPane.showMessageDialog(null, message.toString(), "Your order", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}