import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class Checkout extends JPanel{
    private Customer customer;
    private LocalDateTime orderDate;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDate today = LocalDate.now();

    // UI COMPONENTS
    // JLabels
    JLabel shipTo;
    JLabel itemsCost;
    JLabel sAndH;
    JLabel beforeTax;
    JLabel estTax;
    JLabel orderTotal;

    // JButtons
    JButton submitOrder;

    public Checkout(Customer customer) {
        this.customer = customer;
        initComponents();
    }

    // Saves the customers order to two text files,
    // one which lists orders waiting to be fulfilled (DONE)
    // another which lists all orders for tax purposes (NEEDS TO BE IMPLEMENTED STILL)
    public void createOrder() throws IOException {
        String fileName = customer.getFirstName() + customer.getLastName();
        orderDate = LocalDateTime.now();
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            Files.createDirectories(Path.of("orders"));
            fw = new FileWriter("orders/" + fileName + ".txt");
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
        } catch (IOException e) {
            System.out.println("Order cannot be saved.");
        }
        // Date
        pw.println(dtf.format(orderDate));
        // Customer information
        pw.printf("%s | %s \n", customer.getFirstName(), customer.getLastName());
        pw.printf("%s | %s |%s\n", customer.getAddress(), customer.getEmail(), customer.getPhoneNumber());

        // Order information
        for (Drink drink : Cart.getCart().keySet()) {
            pw.printf("Item: | %s | Quantity: | %d | Base Price: | %.2f\n", drink.getName(), Cart.getCart().get(drink), drink.getPrice());
        }
        pw.printf("Order total: | %.2f\n", Cart.getSubtotal() + 5.99 + (Cart.getSubtotal() + 5.99) * 0.053);
        pw.printf("ID: | %s\n", String.valueOf(getNextID()));
        pw.println();
        pw.flush();
        pw.close();
    }

    private void initComponents() {
        setMaximumSize(new java.awt.Dimension(550, 900));
        setMinimumSize(new java.awt.Dimension(550, 900));
        setPreferredSize(new java.awt.Dimension(550, 900));

        // JLabels
        shipTo = new JLabel(String.format("Shipping to: %s %s %s", customer.getFirstName(), customer.getLastName(), customer.getAddress()));
        itemsCost = new JLabel(String.format("Items: $%.2f", Cart.getSubtotal()));
        sAndH = new JLabel("Shipping & handling: $5.99");
        beforeTax = new JLabel(String.format("Total before tax: $%.2f", Cart.getSubtotal() + 5.99));
        estTax = new JLabel(String.format("Estimated tax to be collected: $%.2f", (Cart.getSubtotal() + 5.99) * 0.053));
        orderTotal = new JLabel(String.format("Order total: $%.2f", Cart.getSubtotal() + 5.99 + (Cart.getSubtotal() + 5.99) * 0.053));

        // JButtons
        submitOrder = new JButton("Place your order");
        submitOrder.addActionListener(new SubmitActionListener());

        // Adding components to panel
        add(submitOrder);
        add(shipTo);
        add(itemsCost);
        add(sAndH);
        add(beforeTax);
        add(estTax);
        add(orderTotal);

        // COULD POSSIBLY ADD:
        // Form to sign
        // Login button
    }

    // Finds the appropriate ID number to assign new customer order by going through previous orders
    public int getNextID() throws IOException {
        int orderID = -1;
        File directoryPath = new File("orders");
        boolean hasID = false;
        String line;
        String id = "";
        int intID;
        FilenameFilter textFileFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");
        for (String s : Arrays.asList(Objects.requireNonNull(directoryPath.list(textFileFilter)))) {
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader("orders/" + s);
                br = new BufferedReader(fr);
            } catch (FileNotFoundException e) {
                System.out.println("File loading error!");
            }
            while ((line = br.readLine()) != null) {
                if (line.contains("ID: | ")) {
                    id = line;
                    hasID = true;
                    System.out.println("line is: " + line);
                }
            }
            if (hasID) {
                id = id.replaceAll("[^\\d.]", "");
                intID = Integer.parseInt(id);
                if (intID > orderID) {
                    orderID = intID;
                }
            }
            br.close();
        }
        orderID++;
        return orderID;
    }

    public void addProfitsToFile() throws IOException {
        Files.createDirectories(Path.of("order_history"));
        File profits = new File("order_history/profits.txt");
        boolean foundDate = false;
        String date = String.valueOf(today);
        profits.createNewFile();
        double currentProfits = 0.0;
        String line = "";
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer input = new StringBuffer();
        try {
            fr = new FileReader("order_history/profits.txt");
            br = new BufferedReader(fr);
        } catch (IOException e) {
            System.out.println("Profits file not found.");
        }
        boolean nextLine = false;
        while ((line = br.readLine()) != null) {
            if (nextLine) {
                nextLine = false;
                currentProfits = Double.parseDouble(line);
            }
            if (line.equals(date)) {
                nextLine = true;
                foundDate = true;
            }
            input.append(line);
            input.append("\n");
        }
        br.close();
        String buffer = input.toString();
        if (!foundDate) {
            buffer = date + "\n" + String.valueOf(Cart.getSubtotal() + 5.99 + ((Cart.getSubtotal() + 5.99) * 0.053)) + "\n" + buffer;
        } else {
            buffer = buffer.replace(String.valueOf(currentProfits), String.valueOf(currentProfits + Cart.getSubtotal() + 5.99 + ((Cart.getSubtotal() + 5.99) * 0.053)));
        }
        FileOutputStream fileOut = new FileOutputStream("order_history/profits.txt");
        fileOut.write(buffer.getBytes());
        fileOut.close();
    }


    private class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                createOrder();
                addProfitsToFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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

