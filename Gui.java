import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends JFrame {
    private boolean adminMode;

    private static final String PANEL_BACKGROUND_COLOR = "#224a6c";
    private static final String TITLE_COLOR = "#e6b37a";
    private static final String BOTTOM_BAR_BACKGROUND_COLOR = "#173d56";
    private static final String BOTTOM_BUTTON_BACKGROUND_COLOR = "#173d56";
    private static final String BOTTOM_BUTTON_TEXT_COLOR = "#e6b37a";
    private static final int SCROLL_SPEED = 16;
    private static final int WIDTH = 410;
    private static final int PANEL_HEIGHT = 630;

    GridBagLayout layout = new GridBagLayout();
    JLabel title = new JLabel("CLINK");
    JButton adminTab;
    Discovery discovery;
    Admin admin;
    JScrollPane scrollPane;
    Cart cart;
    Order order;

    // Panels
    JPanel mainPanel;
    JPanel toolbar;

    // Toolbar Buttons
    JButton discoveryButton;
    JButton orderButton;
    JButton cartButton;
    JButton adminButton;

    // Swing Timer
    private Timer timer;
    private int counter = 3; // the duration in seconds
    private int delay = 1000; // every 1 second

    public Gui(boolean adminMode) throws IOException {
        // JFrame Sizing
        getContentPane().setPreferredSize(new Dimension(WIDTH, 690));
        setResizable(false);

        // JFrame Close Operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JFrame Layout Manager
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0); // Removes the gap in between panels
        setLayout(layout);

        // JFrame Icon
        ImageIcon icon = new ImageIcon("icons/1.png");
        setIconImage(icon.getImage());

        // JFrame Title
        setTitle("CLINK");

        // JFrame Background Color
        getContentPane().setBackground(Color.decode(BOTTOM_BAR_BACKGROUND_COLOR));

        // For running as admin
        this.adminMode = adminMode;

        discovery = new Discovery();
        cart = new Cart();
        order = new Order();

        initComponents();

        ActionListener action = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if(counter == 0)
                {
                    timer.stop();
                    title.setVisible(false);
                    try {
                        discovery.refresh();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    mainPanel.setPreferredSize(new Dimension(WIDTH, PANEL_HEIGHT));
                    scrollPane.setVisible(true);
                    discovery.setVisible(true);
                    toolbar.setVisible(true);
                }
                else
                {
                    counter--;
                }
            }
        };
        timer = new Timer(delay, action);
        timer.setInitialDelay(0);
        timer.start();
        pack();
    }

    private void initComponents() throws IOException {
        // MAIN FRAME CONTENT PANEL ----------------------------------------------------------------------------------
        mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
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

        // Content panel sizing
        // Main panel takes up full size of JFrame until splash screen is done then shares space with toolbar panel
        mainPanel.setPreferredSize(new Dimension(WIDTH, 700));

        // Adding main content panel to JFrame
        add(mainPanel);

        // SPLASH PAGE TITLE STYLING
        mainPanel.add(title, BorderLayout.CENTER);
        title.setVisible(true);
        title.setFont(new Font("Helvetica", Font.ITALIC, 40));
        title.setForeground(Color.decode(TITLE_COLOR));

        // TOOL BAR PANEL ---------------------------------------------------------------------------------------------
        toolbar = new JPanel();
        toolbar.setVisible(false); // Hides the toolbar from splash screen until timer ends
        toolbar.setPreferredSize(new Dimension(WIDTH, 60));
        toolbar.setBackground(Color.decode("#173d56"));

        // Toolbar layout
        toolbar.setLayout(new GridBagLayout());
        GridBagConstraints toolbarConstraints = new GridBagConstraints();

        // Toolbar main navigation buttons
        discoveryButton = newButton("Discovery");
        orderButton = newButton("Order");
        cartButton = newButton("Cart");

        // Adding action listeners to toolbar main navigation buttons
        discoveryButton.addActionListener(new DiscoveryActionListener());
        orderButton.addActionListener(new OrderActionListener());
        cartButton.addActionListener(new CartActionListener());

        // Toolbar administrative access button - disabled by default
        adminButton = newButton("Admin");
        adminButton.setVisible(false);

        // Adding main navigation buttons to toolbar
        toolbar.add(discoveryButton, toolbarConstraints);
        toolbar.add(orderButton, toolbarConstraints);
        toolbar.add(cartButton, toolbarConstraints);

        // Logic for displaying or keeping hidden the toolbar administrative button
        if (adminMode) {
            admin = new Admin();
            admin.setVisible(false);
            mainPanel.add(admin);
            adminButton.setVisible(true);
            toolbar.add(adminButton, toolbarConstraints);
            adminButton.addActionListener(new AdminActionListener());
        }

        mainPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;

        // Scroll panel for drinks
        scrollPane = new JScrollPane(discovery, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        mainPanel.add(scrollPane);

        scrollPane.setMinimumSize(new Dimension(WIDTH, PANEL_HEIGHT));

        // Adding classes to main panel for display
        mainPanel.add(cart, c);
        mainPanel.add(order);

        // Hide everything until the timer is done
        discovery.setVisible(false);
        order.setVisible(false);
        cart.setVisible(false);
        scrollPane.setVisible(false);

        add(toolbar);
    }

    private class DiscoveryActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                discovery.refresh();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            discovery.setVisible(true);
            scrollPane.setVisible(true);
            order.setVisible(false);
            cart.setVisible(false);
            admin.setVisible(false);
            orderButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            cartButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            adminButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            discoveryButton.setBackground(Color.decode("#0e2535"));
        }
    }

    private class OrderActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            order.setVisible(true);

            discovery.setVisible(false);
            scrollPane.setVisible(false);
            cart.setVisible(false);
            admin.setVisible(false);
            discoveryButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            cartButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            adminButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            orderButton.setBackground(Color.decode("#0e2535"));
        }
    }

    private class CartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cart.refresh();
            cart.setVisible(true);
            discovery.setVisible(false);
            order.setVisible(false);
            scrollPane.setVisible(false);
            admin.setVisible(false);
            discoveryButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            orderButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            adminButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            cartButton.setBackground(Color.decode("#0e2535"));
        }
    }

    private class AdminActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            admin.setVisible(true);
            discovery.setVisible(false);
            order.setVisible(false);
            scrollPane.setVisible(false);
            cart.setVisible(false);
            discoveryButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            orderButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            cartButton.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
            adminButton.setBackground(Color.decode("#0e2535"));
        }
    }

    public JButton newButton(String text) {
        JButton button = new JButton(text);

        // Button size
        button.setPreferredSize(new Dimension(100, 45));

        // Button styling
        button.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
        button.setForeground(Color.decode("#e6b37a"));
        button.setFocusPainted(false);

        return button;
    }
}
