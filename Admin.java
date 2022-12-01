import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends JPanel {

    // UI COMPONENTS
    private static final String BOTTOM_BUTTON_BACKGROUND_COLOR = "#173d56";
    private static final String BOTTOM_BUTTON_TEXT_COLOR = "#e6b37a";
    private static String TEXTFIELD_BACKGROUND_COLOR = "#173d56";
    private static String TEXTFIELD_BORDER_COLOR = "#224a6c";
    private static String TEXTFIELD_TEXT_COLOR = "#FFFFFF";
    private static final String PANEL_BACKGROUND_COLOR = "#224a6c";
    private static final String DRINK_BUTTON_BACKGROUND_COLOR = "#3c4975";
    private static final String DRINK_BUTTON_TEXT_COLOR = "#FFFFFF";
    private static final String FONT = "helvetica";
    private static final int WIDTH = 410;
    private static final int PANEL_HEIGHT = 630;


    private String menuname;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<Drink> drinks;


    GridBagLayout layout;
    GridBagConstraints gbc;
    JScrollPane scrollPane = new JScrollPane(this);
    JSeparator separator;
    Menu menu;
    Drink drinkPanel;

    JTextArea name;
    JTextArea description;
    JTextArea price;

    ArrayList<Customer>mocktails;

    public Admin(String menuname) throws IOException {
        mocktails = new ArrayList<>();
        this.menuname = menuname;

        initComponents();
    }

    public String getMenuname(){
        return menuname;
    }

    public Admin() throws IOException {
        mocktails = new ArrayList<>();



        initComponents();
    }

    public void addOrder(Customer mocktail){
        mocktails.add(mocktail);
    }

    public void print() {
        for (Customer c : mocktails ) {
            //System.out.println(c.getOrder());
        }
    }

    public void saveOrder() throws IOException {
        FileWriter fr  = null;
        BufferedWriter br = null;
        PrintWriter pr = null;

        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Deck to save");
            menuname = in.nextLine();
            File file =  new File(menuname);

            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            pr = new PrintWriter(br);
            pr.println(file);

        } catch (FileNotFoundException e) {
            System.out.println("Not a file or directory");
            e.printStackTrace();
        } catch (IOException e) {

        }
    }


    public void removeDrink(Drink drink) {
        drinks.remove(drink);
    }

    public void editDrinkName(Drink drink, String name) {
        drink.setName(name);
    }

    public void editDescription(Drink drink, String description) {
        drink.setDescription(description);
    }

    public void editPrice(Drink drink, double price) {
        drink.setPrice(price);
    }

    public void saveMenu() {
        PrintWriter pw = null;
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("menu.txt");
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
        } catch (IOException e) {
            System.out.println("Menu not found.");
        }
        for (Drink drink : drinks) {
            drink.save(pw);
        }
        pw.close();
    }

    public void initComponents() throws IOException {
        removeAll();

        // Panel settings
        setPreferredSize(new Dimension(WIDTH, 800));

        // Panel Layout Manager
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Panel Layout Manager
        setLayout(layout);

        // Load a new menu
        menu = new Menu();
        if (drinks != null) {
            drinks.removeAll(drinks);
        }
        if (buttons != null) {
            buttons.removeAll(buttons);
        }
        drinks = menu.getDrinks();

        setBackground(Color.decode(PANEL_BACKGROUND_COLOR));
        setEnabled(false);

        // Add drink button
        JButton addDrink = createButton("Add Drink to Menu");
        addDrink.addActionListener(new EditActionListener());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(15, 0, 15, 0);
        add(addDrink, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JSeparator(JSeparator.HORIZONTAL), gbc);

        // Reads through menu, adds items as buttons to Discovery panel
        for (Drink drink : drinks) {
            buttons.add(new JButton(drink.getName()));
        }

        // Set to grid layout for buttons
        // Set styling for all the buttons
        int x = 2;
        int s = 3;
        for (int i = 0; i < drinks.size(); i++, x+=2, s +=2) {
            gbc.gridwidth = 1;
            // Add panel to this panel
            Icon icon = new ImageIcon("icons/" + drinks.get(i).getId() + ".png");
            //buttons.get(i).setIcon(icon);
            buttons.get(i).setVisible(true);
            buttons.get(i).setBackground(Color.decode(DRINK_BUTTON_BACKGROUND_COLOR));
            buttons.get(i).setForeground(Color.decode(DRINK_BUTTON_TEXT_COLOR));
            buttons.get(i).setBorder(null);
            buttons.get(i).setBorderPainted(false);
            buttons.get(i).setFocusPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setPreferredSize(new Dimension(WIDTH - 100, 25));

            // Delete button
            gbc.gridx = 1;
            gbc.gridy = x;
            gbc.weightx = 0.5;
            JButton delete = createButton("Delete");
            delete.addActionListener(new DeleteActionListener(drinks.get(i)));
            add(delete, gbc);

            // Edit button
            gbc.gridx = 2;
            gbc.gridy = x;
            gbc.weightx = 0.5;
            JButton edit = createButton("Edit");
            edit.addActionListener(new EditActionListener(drinks.get(i)));
            add(edit, gbc);

            // Drinks buttons
            gbc.gridx = 0;
            gbc.gridy = x;
            gbc.weightx = 0.5;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(buttons.get(i), gbc);

            gbc.gridx = 0;
            gbc.gridy = s;
            gbc.weightx = 1.0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(new JSeparator(JSeparator.HORIZONTAL), gbc);

            // When clicking on a drink, enter its information screen
            buttons.get(i).addActionListener(new ItemActionListener(drinks.get(i)));
        }
        // Required to place panel components starting at the top left corner
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(new JLabel(" "), gbc);
        repaint();
        revalidate();
    }

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

    // When deleting a specific drink from the menu
    private class DeleteActionListener implements ActionListener {
        Drink drink;

        public DeleteActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Remove drink from the arraylist containing drinks from original menu
            removeDrink(drink);
            // Save the arraylist containing drinks to menu.txt file
            saveMenu();
            removeAll();

            try {
                initComponents();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
            revalidate();
        }
    }

    // When editing a specific drink from the menu
    private class EditActionListener implements ActionListener {
        Drink drink;

        public EditActionListener(Drink drink) {
            this.drink = drink;
        }

        public EditActionListener() {}

        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();
            gbc.insets = new Insets(0, 0, 0, 0);
            GridBagConstraints c = new GridBagConstraints();
            JPanel editPanel = createPanel();
            JLabel nameLabel = new JLabel("Name:");
            JLabel descriptionLabel = new JLabel("Description:");
            JLabel priceLabel = new JLabel("Price:");

            // JLabel Font Style and Size
            nameLabel.setFont(new Font(FONT, Font.PLAIN, 20));
            descriptionLabel.setFont(new Font(FONT, Font.PLAIN, 20));
            priceLabel.setFont(new Font(FONT, Font.PLAIN, 20));

            // JLabel Font Colors
            nameLabel.setForeground(Color.decode("#e6b37a"));
            descriptionLabel.setForeground(Color.decode("#e6b37a"));
            priceLabel.setForeground(Color.decode("#e6b37a"));

            // JButtons
            JButton back = createButton("Cancel");
            JButton save = createButton("Save");
            back.addActionListener(new BackActionListener());


            // JTextAreas
            if (drink != null) {
                name = createTextField(drink.getName());
                description = createTextField(drink.getDescription());
                price = createTextField(String.format("%.2f", drink.getPrice()));
                save.addActionListener(new SaveActionListener(drink));
            } else {
                name = createTextField("Name");
                description = createTextField("Description");
                price = createTextField("0.00");
                save.addActionListener(new SaveActionListener(drink));
            }

            name.setPreferredSize(new Dimension(WIDTH - 50, 30));
            description.setPreferredSize(new Dimension(WIDTH - 50, 300));
            price.setPreferredSize(new Dimension(WIDTH - 50, 30));

            // Bottom Padding for Each Component Using Constraint
            c.insets = new Insets(0, 0, 15, 5);

            // Back button
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0.5;
            editPanel.add(back, c);

            // Top button
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.5;
            editPanel.add(save, c);

            // Bottom Padding for Each Component Using Constraint
            c.insets = new Insets(0, 0, 0, 0);

            // Name label
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.5;
            editPanel.add(nameLabel, c);

            // Name Textfield
            c.insets = new Insets(0, 0, 15, 0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 0.5;
            editPanel.add(name, c);

            // Description label
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3;
            c.weightx = 0.5;
            editPanel.add(descriptionLabel, c);

            // Description Textfield
            c.insets = new Insets(0, 0, 15, 0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 4;
            c.weightx = 0.5;
            editPanel.add(description, c);

            // Price label
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 5;
            c.weightx = 0.5;
            editPanel.add(priceLabel, c);

            // Price textfield
            c.insets = new Insets(0, 0, 15, 0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 6;
            c.weightx = 0.5;
            editPanel.add(price, c);

            // Adding edit panel to admin panel
            add(editPanel);

            repaint();
            revalidate();
        }
    }

    private class AddItemActionListener implements ActionListener {
        Drink drink;

        public AddItemActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Cart.removeFromCart(drink);
            removeAll();
            //initComponents();
            repaint();
            revalidate();
        }
    }

    private class BackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();
            try {
                initComponents();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
            revalidate();
        }
    }

    private class SaveActionListener implements ActionListener {
        Drink drink;

        public SaveActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();

            // Receive modified data, set as drinks instance variables
            if (drink == null) {
                drink = new Drink();
                drinks.add(drink);
            }

            drink.setName(name.getText().toString());
            drink.setDescription(description.getText().toString());
            drink.setPrice(Double.parseDouble(price.getText().toString()));
            // Then save the updated drink
            saveMenu();
            // Go back to admin screen after editing is saved to refresh updated menu
            try {
                initComponents();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
            revalidate();
        }
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


    // Creates a new panel
    public JPanel createPanel() {
        JPanel panel = new JPanel() {
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
        };

        // Panel settings
        setMaximumSize(new Dimension(WIDTH, PANEL_HEIGHT));
        setMinimumSize(new Dimension(WIDTH, PANEL_HEIGHT));
        setPreferredSize(new Dimension(WIDTH, PANEL_HEIGHT));

        // Panel layout
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);

        return panel;
    }

    // Creates a new button
    public JButton createButton(String text) {
        JButton button = new JButton(text);

        // Button size
        button.setPreferredSize(new Dimension(50, 45));

        // Button styling
        button.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
        button.setForeground(Color.decode("#e6b37a"));
        button.setFocusPainted(false);

        return button;
    }

    public JTextArea createTextField(String text) {
        JTextArea textField = new JTextArea(text);

        textField.setBackground(Color.decode(TEXTFIELD_BACKGROUND_COLOR));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode(TEXTFIELD_BORDER_COLOR)));
        textField.setForeground(Color.decode(TEXTFIELD_TEXT_COLOR));
        textField.setColumns(20);
        textField.setFont(new Font("Helvetica", Font.PLAIN, 22));
        textField.setWrapStyleWord(true);
        textField.setLineWrap(true);

        return textField;
    }

    public void refresh() throws IOException {
        // Get all the components within the panel
        Component[] components = getComponents();

        for (Component component : components) {
            if (component instanceof JSeparator || component instanceof JButton) {
                remove(component);
            }
        }
        initComponents();
        repaint();
        revalidate();
    }
}




