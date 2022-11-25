import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

class Cart extends JPanel {
    private static HashMap<Drink, Integer> cart = new HashMap<>();
    private static ArrayList<Drink> drinks = new ArrayList<>();

    public Cart() {
        initComponents();
    }

    public static void addToCart(Drink drink) {
        // If the cart is empty just add the drink
        if (cart.size() == 0) {
            cart.put(drink, 1);
        } else {
            // When the cart already has items in it, only increase quantity of already existing items
            for (Drink item : cart.keySet()) {
                if (item.getName().equals(drink.getName())) {
                    cart.merge(item, 1, Integer::sum);
                }
            }
        }
    }

    private void initComponents() {
        // Set the layout to column box layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(new java.awt.Color(9, 234, 158));
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));
    }

    // Will refresh the cart panel to update new items added to cart upon clicking add to cart
    public void refresh() {
        removeAll();

        for (Drink drink : cart.keySet()) {
            Icon icon = new ImageIcon("icons/" + drink.getId() + ".png");
            JLabel name = new JLabel(drink.getName());
            JLabel quantity = new JLabel(String.valueOf(cart.get(drink)));
            JLabel price = new JLabel(String.valueOf(drink.getPrice() * cart.get(drink)));
            add(name);
            name.setIcon(icon);
            name.setVisible(true);
            add(quantity);
            quantity.setVisible(true);
            add(price);
            price.setVisible(true);
        }

        repaint();
        revalidate();
    }
}
