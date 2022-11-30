import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class Main extends JLabel implements ActionListener {
    static JButton b;
    //static JButton n;
    static JFrame f;
    static JLabel l;
    static JTextField t;
    private static final String PANEL_BACKGROUND_COLOR = "#67497c";


    Main()
    {

    }
    public static void main(String[] args) {
        Cart cart = new Cart();

        // GUI INITIALIZATION
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        f = new JFrame("Admin?");
        b = new JButton("Enter");
        //n = new JButton("No");
        l = new JLabel();
        Main te = new Main();
        b.addActionListener(te);
        t = new JTextField(16);
        JPanel p = new JPanel();

        p.add(l);
        //p.add(n);
        l.setText("Run as admin? [Yes/No]");
        p.add(b);
        p.add(t);
        f.add(p);
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        p.setBackground(Color.decode(PANEL_BACKGROUND_COLOR));
        f.show();
        /* Create and display the form */
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String response = e.getActionCommand();

        l.setText(t.getText());
        String text = t.getText();


            if(text.equalsIgnoreCase("yes")) {
                try {
                    new Gui(true).setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(text.equals("no"))
            {
                JOptionPane.showMessageDialog(null, "No access to Admin View");

            }

}}
