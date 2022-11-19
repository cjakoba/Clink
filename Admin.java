import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Admin
{
    private String menu;

    Arraylist<Order>mocktails;


    public Admin(String menu)
    {
        this.menu = menu;

    }

    public AdminOrder(String name,String description, double price){
        mocktails = new Arraylist<>();
        this.name = name;
        this.descripton = description;
        this.price = price;
    }

    public Orders(){
        mocktails = new Araylist<>();
    }

    public static void addDrinks(){
//add products to the menu
    }

    public static void updateMenu(){
        //remove items
        //change name of item
        //change description of item
        //change price of item
    }

    public static void calculateProfits()
    {
        //add total amount of money from each sale
    }
}
