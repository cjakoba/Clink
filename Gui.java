
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Gui extends javax.swing.JFrame {
    GridBagLayout layout = new GridBagLayout();
    Discovery discovery;
    Cart cart;

    public Gui() {
        discovery = new Discovery();
        cart = new Cart();
        initComponents();
        mainPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(discovery, c);
        mainPanel.add(cart, c);
        discovery.setVisible(false);
        cart.setVisible(false);
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(550, 1000));
        setPreferredSize(new java.awt.Dimension(550, 1000));
        setResizable(false);

        jButton1.setText("Cart");
        jButton1.setMaximumSize(new java.awt.Dimension(100, 75));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 75));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 75));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(61, 11, 234));
        mainPanel.setMaximumSize(new java.awt.Dimension(550, 900));
        mainPanel.setMinimumSize(new java.awt.Dimension(550, 900));
        mainPanel.setPreferredSize(new java.awt.Dimension(550, 900));

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

        jButton2.setText("Discovery");
        jButton2.setActionCommand("discoveryButton");
        jButton2.setMaximumSize(new java.awt.Dimension(100, 75));
        jButton2.setMinimumSize(new java.awt.Dimension(100, 75));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 75));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
    }

    // When clicking the cart button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        cart.refresh();
        cart.setVisible(true);
        discovery.setVisible(false);
    }

    // When clicking the discovery button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        discovery.refresh();
        discovery.setVisible(true);
        cart.setVisible(false);
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel mainPanel;
}
