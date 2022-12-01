import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

class Cart extends JPanel {

    private static final String PANEL_BACKGROUND_COLOR = "#224a6c";
    private static final String FONT = "times new roman";
    private static final String TEXT_COLOR = "#e6b37a";

    private static HashMap<Drink, Integer> cart = new HashMap<>();
    private static ArrayList<Drink> drinks = new ArrayList<>();
    JScrollPane scrollPane = new JScrollPane(this);

    public Cart() {
        initComponents();
    }

    // Determines total prices of all items and quantities in current cart state
    public static double getSubtotal() {
        double subtotal = 0.0;
        for (Drink drink : cart.keySet()) {
            subtotal += drink.getPrice() * cart.get(drink);
        }
        return subtotal;
    }

    public static void addToCart(Drink drink) {
        Drink setDrink = null;
        boolean setNew = true;
        // If the cart is empty just add the drink
        if (cart.size() == 0) {
            cart.put(drink, 1);
        } else {
            // When the cart already has items in it, only increase quantity of already existing items
            for (Drink item : cart.keySet()) {
                if ((item.getName()).equals(drink.getName())) {
                    setDrink = item;
                    setNew = false;
                }
            }
            if (setNew) {
                cart.merge(drink, 1, Integer::sum);
            } else {
                cart.merge(setDrink, 1, Integer::sum);
            }
        }
    }

    // Increases the quantity of a specific drink by one from the shopping cart interface
    public static void increaseQuantity(Drink drink) {
         addToCart(drink);
    }

    // Decreases the quantity of a specific drink by one from the shopping cart interface
    public static void decreaseQuantity(Drink drink) {
        if (cart.get(drink) > 1) {
            cart.put(drink, cart.get(drink) - 1);
        } else {
            cart.remove(drink);
        }
    }

    // Removes all quantities of a specific drink from the shopping cart interface
    public static void removeFromCart(Drink drink) {
        cart.remove(drink);
    }

    // Get the entire cart
    public static HashMap<Drink, Integer> getCart() {
        return cart;
    }

    // UI METHODS
    private void initComponents() {
        // Set the layout to column box layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        setBackground(Color.decode(PANEL_BACKGROUND_COLOR));
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        // Label for an empty cart
        if (cart.size() == 0) {
            JLabel empty = new JLabel("Empty. Its lonely in here...");
            empty.setFont(new Font(FONT, Font.ITALIC, 20));
            empty.setForeground(Color.decode(TEXT_COLOR));
            add(Box.createVerticalGlue()); // For vertical centering
            add(empty);
            empty.setAlignmentX(Component.CENTER_ALIGNMENT); // For horizontal centering
            add(Box.createVerticalGlue()); // For vertical centering
        }
        else {
            // Subtotal label
            JLabel subtotal = new JLabel("Subtotal $" + String.format("%.2f", getSubtotal()));
            subtotal.setFont(new Font(FONT, Font.PLAIN, 30));
            subtotal.setForeground(Color.decode(TEXT_COLOR));
            add(subtotal);

            // Proceed to checkout
            JButton checkout = new JButton("Proceed to checkout");
            checkout.setFont(new Font(FONT, Font.PLAIN, 30));
            checkout.setForeground(Color.decode(TEXT_COLOR));
            checkout.addActionListener(new CheckoutActionListener());
            add(checkout);

            for (Drink drink : cart.keySet()) {
                Icon icon = new ImageIcon("icons/" + drink.getId() + ".png");

                JLabel name = new JLabel(drink.getName());
                name.setFont(new Font(FONT, Font.PLAIN, 30));
                name.setForeground(Color.WHITE);
                name.setIcon(icon);
                JLabel quantity = new JLabel(String.valueOf(cart.get(drink)));
                JLabel price = new JLabel(String.format("%.2f", drink.getPrice() * cart.get(drink)));
                quantity.setFont(new Font(FONT, Font.PLAIN, 30));
                price.setFont(new Font(FONT, Font.PLAIN, 30));

                // delete button
                JButton delete = new JButton("Delete");
                delete.addActionListener(new DeleteActionListener(drink));
                add(delete);
                delete.setVisible(true);

                // increase quantity button
                JButton plus = new JButton("+");
                plus.addActionListener(new PlusActionListener(drink));
                add(plus);
                plus.setVisible(true);

                // decrease quantity button
                JButton minus = new JButton("-");
                minus.addActionListener(new MinusActionListener(drink));


                // Don't show decrease quantity button unless the quantity of drinks is at least 2 or more
                minus.setVisible(false);
                if (cart.get(drink) > 1) {
                    add(minus);
                    minus.setVisible(true);
                }

                add(name);
                add(quantity);
                add(price);
            }


        }
    }

    private class DeleteActionListener implements ActionListener {
        Drink drink;

        public DeleteActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Cart.removeFromCart(drink);
            removeAll();
            initComponents();
            repaint();
            revalidate();
        }
    }

    private class PlusActionListener implements ActionListener {
        Drink drink;

        public PlusActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Cart.increaseQuantity(drink);
            removeAll();
            initComponents();
            repaint();
            revalidate();
        }
    }

    private class MinusActionListener implements ActionListener {
        Drink drink;

        public MinusActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Cart.decreaseQuantity(drink);
            removeAll();
            initComponents();
            repaint();
            revalidate();
        }
    }

    // When clicking the checkout button
    private class CheckoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Kill all components from the cart
            removeAll();
            // Create a new customer
            Customer c = new Customer();
            add(c);
            repaint();
            revalidate();
        }
    }

    // Will refresh the cart panel to update new items added to cart upon clicking add to cart
    public void refresh() {
        removeAll();
        initComponents();
        repaint();
        revalidate();
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
