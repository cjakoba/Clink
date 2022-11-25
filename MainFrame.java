import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel splash;
    private JLabel appName;
    private JLabel subtitle;

    public MainFrame() {
        setContentPane(splash);
        setTitle("Clink");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
