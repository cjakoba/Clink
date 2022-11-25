import java.util.Scanner;

public class Drink {
    private String name;
    private String description;
    private double price;

    // Reads in and creates drinks based on already defined information such as a menu file
    // Can also be used to create new drinks from an admin interface
    public Drink(Scanner in) {
        name = in.nextLine();
        description = in.nextLine();
        price = Double.parseDouble(in.nextLine());
        in.nextLine();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
