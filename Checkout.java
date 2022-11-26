import javax.swing.*;
import java.awt.*;

public class Checkout extends JPanel{
    Customer customer;

    // UI COMPONENTS
    // JLabels
    JLabel shipTo;
    JLabel itemsCost;
    JLabel sAndH;
    JLabel beforeTax;
    JLabel estTax;
    JLabel orderTotal;

    // JButtons
    JButton submitOrder;

    public Checkout(Customer customer) {
        this.customer = customer;
        initComponents();
    }

    // Saves the customers order to two text files,
    // one which lists orders waiting to be fulfilled
    // another which lists all orders for tax purposes
    public void saveOrderToDB() {

    }

    private void initComponents() {
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        // JLabels
        shipTo = new JLabel(String.format("Shipping to: %s %s %s", customer.getFirstName(), customer.getLastName(), customer.getAddress()));
        itemsCost = new JLabel(String.format("Items: $%.2f", Cart.getSubtotal()));
        sAndH = new JLabel("Shipping & handling: $5.99");
        beforeTax = new JLabel(String.format("Total before tax: $%.2f", Cart.getSubtotal() + 5.99));
        estTax = new JLabel(String.format("Estimated tax to be collected: $%.2f", (Cart.getSubtotal() + 5.99) * 0.053));
        orderTotal = new JLabel(String.format("Order total: $%.2f", Cart.getSubtotal() + 5.99 + (Cart.getSubtotal() + 5.99) * 0.053));

        // JButtons
        submitOrder = new JButton("Place your order");

        // Adding components to panel
        add(submitOrder);
        add(shipTo);
        add(itemsCost);
        add(sAndH);
        add(beforeTax);
        add(estTax);
        add(orderTotal);

        // COULD POSSIBLY ADD:
        // Form to sign
        // Login button
    }

    // GRADIENT BACKGROUND COLOR
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
