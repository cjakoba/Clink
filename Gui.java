import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Gui {
    JFrame frame;
    JButton nextButton = new JButton ("Next Screen");
    TitleScreen titleScreen = new TitleScreen();

    Gui() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(titleScreen);
    }

    public class TitleScreen extends JPanel {
        {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(new JLabel("Label One"));
            this.add(new JLabel("Label Two"));
            this.add(new JLabel("Label Three"));
            this.add(new JLabel("Label Four"));
            this.add(new JLabel("Label Five"));
            JButton button = new JButton("Next Screen");
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    swapPanel();
                }
            });
            this.add(button);
        }
    }

    public class Discovery extends JPanel {

        JLabel x;

        Discovery() {
            x = new JLabel();
        }

        {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            Menu menu = new Menu();
            ArrayList<String> drinks = new ArrayList<>();
            //for (Drink d : menu.getDrinks()) {
                //drinks.add(d.getName());
            //}
            //for (int j = 0; j < drinks.size(); j++) {
                //this.add (new JLabel(drinks.get(j)));
            //}
            this.invalidate();
            this.revalidate();
            this.repaint();
            frame.invalidate();
            frame.revalidate();
        }
    }

    protected void swapPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.remove(titleScreen);
                frame.add(new Discovery());
                frame.invalidate();
                frame.revalidate();
            }
        });
    }
}
