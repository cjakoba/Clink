import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Discovery extends JPanel {

    private static final String DRINK_BUTTON_BACKGROUND_COLOR = "#3c4975";
    private static final String DRINK_BUTTON_TEXT_COLOR = "#FFFFFF";
    private static final int WIDTH = 410;

    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();
    GridBagConstraints gbc = new GridBagConstraints();
    JSeparator separator;
    Menu menu;
    JLabel panelTitle;

    public Discovery() throws IOException {
        // JPanel Sizing
        setPreferredSize(new Dimension(WIDTH, 3050));

        initComponents();
    }

    private void initComponents() throws IOException {
        removeAll();
        // Load up the current menu
        menu = new Menu();

        // Reads through menu, adds items as buttons to Discovery panel
        for (Drink drink : menu.getDrinks()) {
            drinks.add(drink);
            buttons.add(new JButton(drink.getName()));
        }

        // Set to grid layout for buttons
        // Set styling for all the buttons
        for (int i = 0; i < drinks.size(); i++) {
            // Add panel to this panel
            Icon icon = new ImageIcon("icons/" + drinks.get(i).getId() + ".png");
            buttons.get(i).setIcon(icon);
            buttons.get(i).setVisible(true);
            buttons.get(i).setBackground(Color.decode(DRINK_BUTTON_BACKGROUND_COLOR));
            buttons.get(i).setForeground(Color.decode(DRINK_BUTTON_TEXT_COLOR));
            buttons.get(i).setBorder(null);
            buttons.get(i).setBorderPainted(false);
            buttons.get(i).setFocusPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setPreferredSize(new Dimension(WIDTH, 65));
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(buttons.get(i), gbc);
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setPreferredSize(new Dimension(400,3));
            separator.setVisible(true);
            add(separator);

            // When clicking on a drink, enter its information screen
            buttons.get(i).addActionListener(new ItemActionListener(drinks.get(i)));
        }
        repaint();
        revalidate();
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

            // Get all the components within the panel
            Component[] components = getComponents();

            // If a component is scheduled to die, remove it
            for (Component component : components) {
                if (component instanceof JSeparator || component instanceof JButton) {
                    remove(component);
                }
            }

            // Add the specific drink information panel after killing previous panel's blocking components
            add(drink);
            drink.setVisible(true);

            // Revalidation and repainting after removal and addition of components
            revalidate();
            repaint();
        }
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


    // Sets the state of the panel to when the app was first started
    public void refresh() throws IOException {
        removeAll();
        for (JButton button : buttons) {
            button.setVisible(true);
        }
        initComponents();
        repaint();
        revalidate();
    }
}


