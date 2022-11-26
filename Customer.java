import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Customer extends JPanel {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;

    // UI COMPONENTS
    // JTextFields
    JTextField firstField;
    JTextField lastField;
    JTextField addressField;
    JTextField emailField;
    JTextField phoneField;

    // JLabels
    JLabel firstLabel;
    JLabel lastLabel;
    JLabel addressLabel;
    JLabel emailLabel;
    JLabel phoneLabel;

    // JButtons
    JButton deliverToAddress;

    public Customer() {
        initComponents();
    }

    public Customer(String name, int contactNumber, String address)
    {
        //this.name = name;
        //this.contactNumber = contactNumber;
        //this.address = address;
        initComponents();
    }
    //public String myInfo()
    //{
        //return name + " " + contactNumber + " " + address;
    //}

    //just an example of how receipt will look, I know we need to print it on gui later
    //public void printReceipt()
    //{

          //System.out.println(
                    //"--------------------------------"
                            //+ "-----------FEE RECEIPT----"
                            //+ "--------------------------"
                            //+ "--------------------------"
                            //+ "-------------------\n");

            //System.out.println("Name: " + name);
            //System.out.println("Contact Number: " + contactNumber);
            //System.out.println("Address: " + address);

    //}

    // SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // GETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Initialize panel components
    public void initComponents() {
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        // JTextFields
        firstField = new JTextField(20);
        lastField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);

        // JLabels
        firstLabel = new JLabel("First name: ");
        lastLabel = new JLabel("Last name: ");
        addressLabel = new JLabel("Address: ");
        emailLabel = new JLabel("Email: ");
        phoneLabel = new JLabel("Phone number:");

        // JButtons
        deliverToAddress = new JButton("Deliver to Address");
        deliverToAddress.addActionListener(new DeliverActionListener(this));

        // Adding components to panel
        add(firstLabel);
        add(firstField);
        add(lastLabel);
        add(lastField);
        add(addressLabel);
        add(addressField);
        add(emailLabel);
        add(emailField);
        add(phoneLabel);
        add(phoneField);
        add(deliverToAddress);
    }

    // Deliver to address button
    private class DeliverActionListener implements ActionListener {
        Customer customer;

        public DeliverActionListener(Customer customer) {
            this.customer = customer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Kill all the components of this current panel
            removeAll();

            // Receive all currently entered text and set to instance variables
            setFirstName(firstField.getText().toString());
            setLastName(lastField.getText().toString());
            setAddress(addressField.getText().toString());
            setEmail(emailField.getText().toString());
            setPhoneNumber(phoneField.getText().toString());

            Checkout checkoout = new Checkout(customer);
            add(checkoout);

            repaint();
            revalidate();
        }
    }

    // SET GRADIENT BACKGROUND COLOR FOR PANEL
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.decode("#67497c");
        Color color2 = Color.decode("#224a6c");
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
