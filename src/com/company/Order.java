package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    private static int i = 0;
    List<String> itemsOrdered=new ArrayList<>();
    private int count = i++;
    private int capacity;


    public Order(Menu menu,int capacity) {
        int orderCost=0;
        menu.displayMenu();
        Scanner in=new Scanner(System.in);
        System.out.println("List the items you want to order");
        String item=in.nextLine();
        itemsOrdered.add(item);
        orderCost+=menu.itemPriceList.get(item);
        System.out.println("Your orderCost  is "+ orderCost);

        this.capacity=capacity;

        if (count == capacity) {
            System.out.println("Resturant Full");
            System.exit(0);
        }
    }

    public String toString() {
        return "Order " + count;
    }
}