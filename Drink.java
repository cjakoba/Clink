import java.util.Scanner;

public class Drink {
    private String name;
    private String description;
    private double price;

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
