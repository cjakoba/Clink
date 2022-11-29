
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends javax.swing.JFrame {
    private boolean adminView;

    private static final String PANEL_BACKGROUND_COLOR = "#224a6c";
    private static final String TITLE_COLOR = "#e6b37a";
    private static final String BOTTOM_BAR_BACKGROUND_COLOR = "#173d56";
    private static final String BOTTOM_BUTTON_BACKGROUND_COLOR = "#173d56";
    private static final String BOTTOM_BUTTON_TEXT_COLOR = "#e6b37a";
    private static final int SCROLL_SPEED = 16;

    GridBagLayout layout = new GridBagLayout();
    JLabel title = new JLabel("CLINK");
    JButton adminTab;
    Discovery discovery;
    Admin admin;
    JScrollPane scrollPane;
    Cart cart;

    // Swing Timer
    private Timer timer;
    private int counter = 3; // the duration in seconds
    private int delay = 1000; // every 1 second

    public Gui(boolean adminView) throws IOException {
        this.adminView = adminView;
        setBackground(Color.WHITE);
        setTitle("CLINK");
        ImageIcon icon = new ImageIcon("icons/1.png");
        setIconImage(icon.getImage());
        discovery = new Discovery();
        cart = new Cart();
        initComponents();

        // SPLASH PAGE TITLE STYLING
        mainPanel.add(title, BorderLayout.CENTER);
        title.setVisible(true);
        title.setFont(new Font("Helvetica", Font.ITALIC, 40));
        title.setForeground(Color.decode(TITLE_COLOR));

        mainPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        // Scroll panel for drinks
        scrollPane = new JScrollPane(discovery, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        mainPanel.add(scrollPane);
        scrollPane.setVisible(false);
        scrollPane.setMinimumSize(new Dimension(550, 900));
        mainPanel.add(cart, c);

        // Hide everything until the timer is done
        discovery.setVisible(false);
        cart.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        adminTab.setVisible(false);
        admin.setVisible(false);
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
                    adminTab.setVisible(true);
                    scrollPane.setVisible(true);
                    discovery.setVisible(true);
                    jButton1.setVisible(true);
                    jButton2.setVisible(true);
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
    }

    private void initComponents() throws IOException {
        // Sets the background color of the main JFrame
        getContentPane().setBackground(Color.decode(BOTTOM_BAR_BACKGROUND_COLOR));

        jButton1 = new javax.swing.JButton();

        mainPanel = new javax.swing.JPanel() {
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

        jButton2 = new javax.swing.JButton();
        setLayout(new BorderLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(550, 700));
        setPreferredSize(new java.awt.Dimension(550, 700));
        setResizable(false);

        // CART BUTTON STYLING
        jButton1.setText("Cart");
        jButton1.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
        jButton1.setForeground(Color.decode(BOTTOM_BUTTON_TEXT_COLOR));
        jButton1.setOpaque(true);

        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setContentAreaFilled(false);

        jButton1.setMaximumSize(new java.awt.Dimension(100, 75));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 75));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 75));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // MAIN PANEL STYLING
        mainPanel.setBackground(Color.decode(PANEL_BACKGROUND_COLOR));
        mainPanel.setMaximumSize(new java.awt.Dimension(550, 600));
        mainPanel.setMinimumSize(new java.awt.Dimension(550, 600));
        mainPanel.setPreferredSize(new java.awt.Dimension(550, 600));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);

        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 900, Short.MAX_VALUE)
        );

        // DISCOVERY BUTTON STYLING
        jButton2.setText("Discovery");
        jButton2.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
        jButton2.setForeground(Color.decode(BOTTOM_BUTTON_TEXT_COLOR));
        jButton2.setActionCommand("discoveryButton");
        jButton2.setOpaque(true);

        // Border removal - blends button into component behind it
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setContentAreaFilled(false);

        jButton2.setMaximumSize(new java.awt.Dimension(100, 75));
        jButton2.setMinimumSize(new java.awt.Dimension(100, 75));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 75));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        adminTab = new JButton("Admin");
        adminTab.setVisible(false);
        if (adminView) {
            admin = new Admin();
            adminTab.setVisible(true);
            mainPanel.add(admin);
            mainPanel.add(adminTab);

            adminTab.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminTabActionPerformed(e);
                }
            });
        }
        adminTab.setMaximumSize(new java.awt.Dimension(100, 75));
        adminTab.setMinimumSize(new java.awt.Dimension(100, 75));
        adminTab.setPreferredSize(new java.awt.Dimension(100, 75));

        // Admin button styling
        adminTab.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
        adminTab.setForeground(Color.decode(BOTTOM_BUTTON_TEXT_COLOR));
        adminTab.setActionCommand("discoveryButton");
        adminTab.setOpaque(true);
        // border removal - blends button into component behind it
        adminTab.setBorderPainted(false);
        adminTab.setFocusPainted(false);
        adminTab.setContentAreaFilled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()
                                .addComponent(adminTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE).addContainerGap()
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adminTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())

        );


    }

    // CART BUTTON ACTION PERFORMED
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        cart.refresh();
        cart.setVisible(true);
        discovery.setVisible(false);
        scrollPane.setVisible(false);
        admin.setVisible(false);
    }

    // DISCOVERY BUTTON ACTION PERFORMED
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        discovery.refresh();
        discovery.setVisible(true);
        scrollPane.setVisible(true);
        cart.setVisible(false);
        admin.setVisible(false);
    }

    private void adminTabActionPerformed(ActionEvent e) {
        //admin.refresh();
        admin.setVisible(true);
        discovery.setVisible(false);
        scrollPane.setVisible(false);
        cart.setVisible(false);
    }

    // GRADIENT BACKGROUND COLOR


    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JPanel mainPanel;
}
