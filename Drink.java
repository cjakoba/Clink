import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Drink extends JPanel {
    private String name;
    private String description;
    private double price;
    private int id;

    // Reads in and creates drinks based on already defined information such as a menu file
    // Can also be used to create new drinks from an admin interface

    public Drink() {
        this.setLayout(new GridLayout(0,1));
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

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setBackground(new java.awt.Color(159, 8, 234));
        setMaximumSize(new java.awt.Dimension(650, 900));
        setMinimumSize(new java.awt.Dimension(650, 900));
        setPreferredSize(new java.awt.Dimension(650, 900));

        JLabel jl1 = new JLabel(String.valueOf(this.name));
        JLabel jl2 = new JLabel(description);
        JLabel jl3 = new JLabel(String.valueOf(price));

        add(jl1, BorderLayout.NORTH);
        add(jl2, BorderLayout.SOUTH);
        add(jl3, BorderLayout.CENTER);

        jl1.setVisible(true);
        jl2.setVisible(true);
        jl3.setVisible(true);

        JButton addToCart = new JButton("Add To Cart");
        add(addToCart);
        addToCart.setVisible(true);
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
}
