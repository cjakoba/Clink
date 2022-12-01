import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Order extends JPanel {

    private int getId;
    private String status;

    private static String TEXTFIELD_BACKGROUND_COLOR = "#173d56";
    private static String TEXTFIELD_BORDER_COLOR = "#224a6c";
    private static String TEXTFIELD_TEXT_COLOR = "#FFFFFF";
    private static final String BOTTOM_BUTTON_BACKGROUND_COLOR = "#173d56";
    private static final int PANEL_HEIGHT = 630;
    private static final int WIDTH = 410;
    private static final String FONT = "helvetica";

    JTextField textField;
    JLabel statusLabel;

    public Order() throws IOException {
        // JPanel Sizing
        setPreferredSize(new Dimension(WIDTH, PANEL_HEIGHT));

        initComponents();
    }

    public void initComponents() throws IOException {
        removeAll();
        // JPanel Layout Manger
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        // Bottom Padding for Each Component Using Constraint
        c.insets = new Insets(0, 0, 15, 0);

        JLabel title = new JLabel("Order Settings");
        JLabel numberLabel = new JLabel("Order ID:");
        textField = new JTextField(20);
        JButton submit = new JButton("Submit");
        statusLabel = new JLabel("Status: ");

        // Image label position and addition to panel
        c.gridy = 0;
        c.gridx = 0;
        add(title, c);

        // Name label position and addition to panel
        c.gridy = 1;
        c.gridx = 0;
        add(numberLabel, c);

        // Description label position and addition to panel
        c.gridy = 2;
        c.gridx = 0;
        add(textField, c);

        // Price label position and addition to panel
        c.gridy = 3;
        c.gridx = 0;
        add(submit, c);

        // Price label position and addition to panel
        c.gridy = 4;
        c.gridx = 0;
        add(statusLabel, c);

        // JLabel styling
        title.setForeground(Color.WHITE);
        title.setFont(new Font(FONT, Font.PLAIN, 30));
        numberLabel.setForeground(Color.WHITE);
        numberLabel.setFont(new Font(FONT, Font.PLAIN, 20));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font(FONT, Font.PLAIN, 20));

        // Textfield styling
        textField.setBackground(Color.decode(TEXTFIELD_BACKGROUND_COLOR));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode(TEXTFIELD_BORDER_COLOR)));
        textField.setForeground(Color.decode(TEXTFIELD_TEXT_COLOR));

        // Button Sizing
        submit.setPreferredSize(new Dimension(100, 45));

        // Button styling
        submit.setBackground(Color.decode(BOTTOM_BUTTON_BACKGROUND_COLOR));
        submit.setForeground(Color.decode("#e6b37a"));
        submit.setFocusPainted(false);

        // Button Action
        submit.addActionListener(new SubmitActionListener());

        c.gridy = 5;
        c.gridx = 0;
        // Required to place panel components starting at the top left corner
        c.weightx = 1;
        c.weighty = 1;
        add(new JLabel(" "), c);
    }

    public String getOrderStatus() throws IOException {
        int find = getIdToFind();
        System.out.println(find);
        File directoryPath = new File("orders");
        String line;
        String status = "";


        FilenameFilter textFileFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");
        for (String s : Arrays.asList(Objects.requireNonNull(directoryPath.list(textFileFilter)))) {
            boolean next = false;
            boolean hasID = false;
            int intID = -1;
            String id = "";
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader("orders/" + s);
                br = new BufferedReader(fr);
            } catch (FileNotFoundException e) {
                System.out.println("File loading error!");
            }
            while ((line = br.readLine()) != null) {
                if (next) {
                    status = line;
                    next = false;
                }
                if (line.contains("ID: | ")) {
                    id = line;
                    hasID = true;
                }
                if (hasID) {
                    id = id.replaceAll("[^\\d.]", "");
                    intID = Integer.parseInt(id);
                    hasID = false;
                }
                if (find == intID) {
                    next = true;
                }
            }
            br.close();
            fr.close();
        }
        return status;
    }

    public void setFindId(int id) {
        getId = id;
    }

    public int getIdToFind() {
        return getId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    private class SubmitActionListener implements ActionListener {
        private String status;
        @Override
        public void actionPerformed(ActionEvent e) {
            // Extract order Id from the text field
            setFindId(Integer.parseInt(textField.getText()));
            try {
                status = getOrderStatus();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            setStatus(status);
            if (status.equals("")) {
                statusLabel.setText("Invalid order ID");
            } else {
                statusLabel.setText("Status: " + getStatus());
            }
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


