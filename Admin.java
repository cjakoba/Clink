import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Admin
{
private String menuname;

ArrayList<Customer>mocktails;

public Admin(String menuname){
    mocktails = new ArrayList<>();
    this.menuname = menuname;
}

    public String getMenuname(){
    return menuname;
}

    public Admin(){
        mocktails = new ArrayList<>();
    }

    public void addOrder(Customer mocktail){
    mocktails.add(mocktail);
    }

    public void print() {
        for (Customer c : mocktails ) {
            //System.out.println(c.getOrder());
        }
    }

    public void saveOrder() throws IOException {
        FileWriter fr  = null;
        BufferedWriter br = null;
        PrintWriter pr = null;

        try {
        Scanner in = new Scanner(System.in);
        System.out.println("Deck to save");
        menuname = in.nextLine();
        File file =  new File(menuname);

        fr = new FileWriter(file,true);
        br = new BufferedWriter(fr);
        pr = new PrintWriter(br);
        pr.println(file);

        } catch (FileNotFoundException e) {
            System.out.println("Not a file or directory");
            e.printStackTrace();
            } catch (IOException e) {

            }

    }

}




