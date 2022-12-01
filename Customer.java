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
    private static String TEXTFIELD_BACKGROUND_COLOR = "#173d56";
    private static String TEXTFIELD_BORDER_COLOR = "#224a6c";
    private static String TEXTFIELD_TEXT_COLOR = "#FFFFFF";
    private static String BUTTON_BACKGROUND_COLOR = "#173d56";
    private static String BUTTON_TEXT_COLOR = "#e6b37a";
    private static String LABEL_TEXT_COLOR = "#FFFFFF";

    // Layout Manager
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

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
        // Panel Size
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        // Layout
        setLayout(gbl);

        // JTextFields
        firstField = new JTextField(20);
        lastField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);

        // JTextField Background colors
        firstField.setBackground(Color.decode(TEXTFIELD_BACKGROUND_COLOR));
        lastField.setBackground(Color.decode(TEXTFIELD_BACKGROUND_COLOR));
        addressField.setBackground(Color.decode(TEXTFIELD_BACKGROUND_COLOR));
        emailField.setBackground(Color.decode(TEXTFIELD_BACKGROUND_COLOR));
        phoneField.setBackground(Color.decode(TEXTFIELD_BACKGROUND_COLOR));

        // JTextField Border colors
        firstField.setBorder(BorderFactory.createLineBorder(Color.decode(TEXTFIELD_BORDER_COLOR)));
        lastField.setBorder(BorderFactory.createLineBorder(Color.decode(TEXTFIELD_BORDER_COLOR)));
        addressField.setBorder(BorderFactory.createLineBorder(Color.decode(TEXTFIELD_BORDER_COLOR)));
        emailField.setBorder(BorderFactory.createLineBorder(Color.decode(TEXTFIELD_BORDER_COLOR)));
        phoneField.setBorder(BorderFactory.createLineBorder(Color.decode(TEXTFIELD_BORDER_COLOR)));

        // JTextField Text colors
        firstField.setForeground(Color.decode(TEXTFIELD_TEXT_COLOR));
        lastField.setForeground(Color.decode(TEXTFIELD_TEXT_COLOR));
        addressField.setForeground(Color.decode(TEXTFIELD_TEXT_COLOR));
        emailField.setForeground(Color.decode(TEXTFIELD_TEXT_COLOR));
        phoneField.setForeground(Color.decode(TEXTFIELD_TEXT_COLOR));

        // JLabels
        firstLabel = new JLabel("First name: ");
        lastLabel = new JLabel("Last name: ");
        addressLabel = new JLabel("Address: ");
        emailLabel = new JLabel("Email: ");
        phoneLabel = new JLabel("Phone number:");

        // JLabel Styling
        firstLabel.setForeground(Color.decode(LABEL_TEXT_COLOR));
        lastLabel.setForeground(Color.decode(LABEL_TEXT_COLOR));
        addressLabel.setForeground(Color.decode(LABEL_TEXT_COLOR));
        emailLabel.setForeground(Color.decode(LABEL_TEXT_COLOR));
        phoneLabel.setForeground(Color.decode(LABEL_TEXT_COLOR));

        // JButtons
        deliverToAddress = new JButton("Deliver to Address");
        deliverToAddress.addActionListener(new DeliverActionListener(this));

        // JButton Styling
        deliverToAddress.setBackground(Color.decode(BUTTON_BACKGROUND_COLOR));
        deliverToAddress.setForeground(Color.decode(BUTTON_TEXT_COLOR));

        // Adding components to panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        add(firstLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        add(firstField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        add(lastLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        add(lastField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        add(addressLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        add(addressField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        add(emailLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.5;
        add(emailField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.5;
        add(phoneLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.5;
        add(phoneField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0.5;
        add(deliverToAddress, c);
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
