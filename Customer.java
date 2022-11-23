import java.util.ArrayList;

public class Customer
{
    private String name;
    private int contactNumber;
    private String address;

    public Customer(String name, int contactNumber, String address)
    {
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }
    public String myInfo()
    {
        return name + " " + contactNumber + " " + address;
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
