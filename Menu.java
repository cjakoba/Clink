import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Drink> drinks = new ArrayList<>();

    // Readded the save so that the menu file can be read into the GUI
    public void save() {
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
    }

    public void printDrinks() {
        for (Drink d : drinks) {
            System.out.println(d.getName());
            System.out.println(d.getDescription());
            System.out.println(d.getPrice());
        }
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }
}