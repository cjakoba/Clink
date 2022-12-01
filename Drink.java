import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;

public class Drink extends JPanel {

    private static final int WIDTH = 410;
    private static final String FONT = "times new roman";

    private String name;
    private String description;
    private double price;
    private int id;

    // Reads in and creates drinks based on already defined information such as a menu file
    // Can also be used to create new drinks from an admin interface

    public Drink() {
        // JPanel Sizing
        setPreferredSize(new Dimension(WIDTH, 1200));

        this.setVisible(false);
    }

    public Drink(Scanner in) {
        this();
        name = in.nextLine();
        description = in.nextLine();
        price = Double.parseDouble(in.nextLine());
        id = Integer.parseInt(in.nextLine());
        in.nextLine();
        initComponents();
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void save(PrintWriter out) {
        out.println(name);
        out.println(description);
        out.println(price);
        out.println(id);
        out.println();
        out.flush();
    }

    private void initComponents() {
        // JPanel Layout Manger
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        // Bottom Padding for Each Component Using Constraint
        c.insets = new Insets(0, 0, 15, 0);

        String html = "<html><body style='width: %1spx'>%1s";

        // JLabels
        JLabel imageLabel = new JLabel();
        JLabel nameLabel = new JLabel(String.valueOf(name));
        JLabel descriptionLabel = new JLabel(String.format(html, 300, this.description));
        JLabel priceLabel = new JLabel("$" + String.valueOf(price));

        // JLabel Setting Font Style and Size
        nameLabel.setFont(new Font(FONT, Font.PLAIN, 30));
        descriptionLabel.setFont(new Font(FONT, Font.PLAIN, 20));
        priceLabel.setFont(new Font(FONT, Font.PLAIN, 20));

        // JLabel Font Colors
        nameLabel.setForeground(Color.WHITE);
        descriptionLabel.setForeground(Color.WHITE);
        priceLabel.setForeground(Color.WHITE);

        // Display image for drink
        Icon icon = new ImageIcon("icons/Stock.jpg");
        imageLabel.setIcon(icon);

        // Image label position and addition to panel
        c.gridy = 0;
        c.gridx = 0;
        add(imageLabel, c);

        // Name label position and addition to panel
        c.gridy = 1;
        c.gridx = 0;
        add(nameLabel, c);

        // Description label position and addition to panel
        c.gridy = 2;
        c.gridx = 0;
        add(descriptionLabel, c);

        // Price label position and addition to panel
        c.gridy = 3;
        c.gridx = 0;
        add(priceLabel, c);

        // ADD TO CART JBUTTON ----------------------------------------------------------------------------------------
        JButton addToCart = new JButton("Add To Cart");

        // Button size
        addToCart.setPreferredSize(new Dimension(100, 45));

        // Button styling
        addToCart.setBackground(Color.decode("#173d56"));
        addToCart.setForeground(Color.decode("#e6b37a"));
        addToCart.setFocusPainted(false);

        // Add To Cart Button Action Listener
        addToCart.addActionListener(new ItemActionListener(this));

        // Add To Cart Button position and addition to panel
        c.gridy = 4;
        c.gridx = 0;
        add(addToCart, c);

        c.gridy = 5;
        c.gridx = 0;

        // Required to place panel components starting at the top left corner
        c.weightx = 1;
        c.weighty = 1;
        add(new JLabel(" "), c);
    }

    // ADD TO CART ACTION LISTENER
    // Adds a drink to a static cart list
    private class ItemActionListener implements ActionListener {
        Drink drink;

        public ItemActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Cart.addToCart(drink);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }


    // Panel Gradient Background Color
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
