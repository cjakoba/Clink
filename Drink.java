import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

public class Drink extends JPanel {

    private static final String FONT = "helvetica";

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();

    private String name;
    private String description;
    private double price;
    private int id;

    // Reads in and creates drinks based on already defined information such as a menu file
    // Can also be used to create new drinks from an admin interface

    public Drink() {
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

    private void initComponents() {
        setLayout(layout);
        setBackground(new java.awt.Color(159, 8, 234));
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));
        String html = "<html><body style='width: %1spx'>%1s";
        Icon icon = new ImageIcon("icons/Stock.jpg");

        JLabel jl1 = new JLabel(String.valueOf(this.name));
        JLabel jl2 = new JLabel(String.format(html, 300, description));
        JLabel jl3 = new JLabel(String.valueOf(price));

        jl1.setFont(new Font("Serif", Font.BOLD, 20));

        jl1.setIcon(icon);
        jl2.setFont(new Font(FONT, Font.PLAIN, 20));
        jl3.setFont(new Font(FONT, Font.PLAIN, 20));

        jl1.setForeground(Color.WHITE);
        jl2.setForeground(Color.WHITE);
        jl3.setForeground(Color.WHITE);



        constraints.gridy = 0;
        constraints.gridx = 0;

        add(jl1, constraints);
        constraints.gridy = 1;
        constraints.gridx = 0;
        add(jl2, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 2;
        constraints.gridx = 0;

        JButton addToCart = new JButton("Add To Cart");
        addToCart.addActionListener(new ItemActionListener(this));
        add(addToCart, constraints);
        addToCart.setVisible(true);
        constraints.gridy = 3;
        constraints.gridx = 0;
        add(jl3, constraints);
        jl1.setVisible(true);
        jl2.setVisible(true);
        jl3.setVisible(true);

        // Required to place top left corner
        constraints.weightx = 1;
        constraints.weighty = 1;
        add(new JLabel(" "), constraints);
    }

    // When the button is clicked...
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

    public void offPanel() {
        setVisible(false);
    }

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
