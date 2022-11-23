import java.util.Scanner;

public class Customer
{
    private String name;
    private String contactNumber;
    private String address;

    public Customer(String name, String contactNumber, String address)
    {
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }
    public Customer()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        name = input.nextLine();
        System.out.println("Contact Number: ");
        contactNumber = input.nextLine();
        System.out.println("Address: ");
        address = input.nextLine();
    }
    public void myInfo()
    {
        System.out.println("Name: " + name);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Address: " + address);
    }

    //just an example of how receipt will look, I know we need to print it on gui later
    public void printReceipt()
    {

          System.out.println(
                    "--------------------------------"
                            + "-----------FEE RECEIPT----"
                            + "--------------------------"
                            + "--------------------------"
                            + "-------------------\n");

            System.out.println("Name: " + name);
            System.out.println("Contact Number: " + contactNumber);
            System.out.println("Address: " + address);

    }
}
