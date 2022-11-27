import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Admin extends JPanel {

    private static final String PANEL_BACKGROUND_COLOR = "#224a6c";
    private static final String DRINK_BUTTON_BACKGROUND_COLOR = "#3c4975";
    private static final String DRINK_BUTTON_TEXT_COLOR = "#FFFFFF";
    private String menuname;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<Drink> drinks;
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JScrollPane scrollPane = new JScrollPane(this);
    JSeparator separator;
    Menu menu;
    Drink drinkPanel;

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
        // Load up the current menu
        menu = new Menu();
        drinks = menu.getDrinks();

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

        // Reads through menu, adds items as buttons to Discovery panel
        for (Drink drink : drinks) {
            buttons.add(new JButton(drink.getName()));
        }

        // Set to grid layout for buttons
        // Set styling for all the buttons
        for (int i = 0; i < drinks.size(); i++) {
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
            buttons.get(i).setPreferredSize(new Dimension(550, 25));

            JButton delete = new JButton("Delete");
            delete.addActionListener(new DeleteActionListener(drinks.get(i)));
            add(delete);

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
    // Edit current menu
    // Change name price, etc of item
    // Create new menu

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


    private class DeleteActionListener implements ActionListener {
        Drink drink;

        public DeleteActionListener(Drink drink) {
            this.drink = drink;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            removeDrink(drink);
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

    private class EditNameActionListener implements ActionListener {
        ArrayList<Drink> drinks;
        Drink drink;

        public EditNameActionListener(Drink drink) {
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

    private class EditDescriptionActionListener implements ActionListener {
        Drink drink;

        public EditDescriptionActionListener(Drink drink) {
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

    private class EditPriceActionListener implements ActionListener {
        Drink drink;

        public EditPriceActionListener(Drink drink) {
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




