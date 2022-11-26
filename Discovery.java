import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Discovery extends JPanel {
    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();
    GridBagLayout layout = new GridBagLayout();
    Drink drinkPanel;

    public Discovery() {
        // Load up the current menu
        Menu menu = new Menu();
        menu.save();

        initComponents();



        // Reads through menu, adds items as buttons to Discovery panel
        for (Drink drink : menu.getDrinks()) {
            drinks.add(drink);
            System.out.println(drink.getId());
            buttons.add(new JButton(drink.getName()));
        }
    }

    private void initComponents() {
        setBackground(new java.awt.Color(10, 198, 234));
        setEnabled(false);
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        // Set to grid layout for buttons
        setLayout(new GridLayout(10, 10, 4, 4));

        // Set styling for all the buttons
        for (int i = 0; i < drinks.size(); i++) {
            // Add panel to this panel
            Icon icon = new ImageIcon("icons/" + drinks.get(i).getId() + ".png");
            buttons.get(i).setIcon(icon);
            buttons.get(i).setVisible(true);
            buttons.get(i).setBackground(new java.awt.Color(9, 234, 158));
            buttons.get(i).setBorder(null);
            buttons.get(i).setPreferredSize(new Dimension(40, 40));
            add(buttons.get(i));

            // When clicking on a drink, enter its information screen
            buttons.get(i).addActionListener(new ItemActionListener(drinks.get(i)));
        }
    }


    // When the button is clicked...
    private class ItemActionListener implements ActionListener {
        Drink drink;

        public ItemActionListener(Drink drink) {
            this.drink = drink;
        }

        public Drink getActiveDrinkPanel() {
            return drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // All buttons must be hid to allow for other panel to render properly
            for (JButton button : buttons) {
                button.setVisible(false);
            }
            setLayout(layout);

            add(drink);
            drink.setVisible(true);
        }
    }

    public void refresh() {
        removeAll();
        for (JButton button : buttons) {
            button.setVisible(true);
        }
        initComponents();
        repaint();
        revalidate();
    }
}
