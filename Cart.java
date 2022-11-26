import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

class Cart extends JPanel {

    private static final String PANEL_BACKGROUND_COLOR = "#224a6c";

    private static HashMap<Drink, Integer> cart = new HashMap<>();
    private static ArrayList<Drink> drinks = new ArrayList<>();

    public Cart() {
        initComponents();
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

    private void initComponents() {
        // Set the layout to column box layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(Color.decode(PANEL_BACKGROUND_COLOR));
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        for (Drink drink : cart.keySet()) {
            Icon icon = new ImageIcon("icons/" + drink.getId() + ".png");

            JLabel name = new JLabel(drink.getName());
            JLabel quantity = new JLabel(String.valueOf(cart.get(drink)));
            JLabel price = new JLabel(String.valueOf(drink.getPrice() * cart.get(drink)));

            add(name);
            add(quantity);
            add(price);

            name.setIcon(icon);
            name.setVisible(true);
            quantity.setVisible(true);
            price.setVisible(true);
        }

    }

    // Will refresh the cart panel to update new items added to cart upon clicking add to cart
    public void refresh() {
        removeAll();
        initComponents();
        repaint();
        revalidate();
    }
}
