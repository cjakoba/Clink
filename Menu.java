import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Drink> drinks = new ArrayList<>();

    public Menu() throws IOException {
        read();
    }

    // Read in menu into the GUI
    public void read() throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader("./menu.txt");
        } catch (IOException e) {
            System.out.println("Menu file not found.");
        }
        Scanner file = new Scanner(fr);
        while (file.hasNext()) {
            drinks.add(new Drink(file));
        }
        fr.close();
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }
}