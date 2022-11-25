import javax.swing.*;

public class Cart extends JPanel {

    public Cart() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Set the layout to column box layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        setBackground(new java.awt.Color(9, 234, 158));
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 550, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 900, Short.MAX_VALUE)
        );
    }
}
