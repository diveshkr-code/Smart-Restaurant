package com.company;

import java.util.*;

import static com.company.Restaurant.in;

public class Menu {
    HashMap<String,Double> itemPriceList=new HashMap<>();
    static int noOfItems=0;

    Menu() {
        System.out.println("Hey we are creating the Menu for the resturatnt");
        System.out.println("How many of items do you want to add?");
        int noOfItems=in.nextInt();

        for(int i = 0; i< noOfItems; i++) {
            System.out.println("Enter the name of the Dish");
            String name=in.next();

            System.out.println("Enter the price of the Dish");
            Double price=in.nextDouble();
            // Not using addItem in the constructor that method can be called when
            // additional items will be added
            itemPriceList.put(name,price);
        }
    }

    private void addItem(String name, Double price) {
        itemPriceList.put(name,price);
        noOfItems++;
    }
    private void changePrice(String name, Double price) {
//        TODO
    }

    public void displayMenu() {
        for (Map.Entry mapElement : itemPriceList.entrySet()) {
            String itemName = (String)mapElement.getKey();
            Double itemPrice = (Double)mapElement.getValue();
            System.out.println(itemName + " : " + itemPrice);
        }
    }
}
