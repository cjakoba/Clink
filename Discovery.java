import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Discovery extends JPanel {

    private static final String PANEL_BACKGROUND_COLOR = "#224a6c";
    private static final String DRINK_BUTTON_BACKGROUND_COLOR = "#3c4975";

    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JScrollPane scrollPane = new JScrollPane(this);
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
        setBackground(Color.decode(PANEL_BACKGROUND_COLOR));
        setEnabled(false);

        // Panel settings
        setMaximumSize(new java.awt.Dimension(550, 2500));
        setMinimumSize(new java.awt.Dimension(550, 2500));
        setPreferredSize(new java.awt.Dimension(550, 2500));

        // Scroll pane settings
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);

        // Set to grid layout for buttons
        // Set styling for all the buttons
        for (int i = 0; i < drinks.size(); i++) {
            // Add panel to this panel
            Icon icon = new ImageIcon("icons/" + drinks.get(i).getId() + ".png");
            buttons.get(i).setIcon(icon);
            buttons.get(i).setVisible(true);
            buttons.get(i).setBackground(Color.decode(DRINK_BUTTON_BACKGROUND_COLOR));
            buttons.get(i).setBorder(null);
            buttons.get(i).setBorderPainted(false);
            buttons.get(i).setFocusPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setPreferredSize(new Dimension(550, 100));
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(buttons.get(i), gbc);
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setPreferredSize(new Dimension(400,3));
            add(separator);

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
