package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    List<String> itemsOrdered=new ArrayList<>();
    int numOfItems=0;
    double orderCost;

    public Order(Menu menu) {
        orderCost=0;
        menu.displayMenu();

        Scanner in=new Scanner(System.in);
        System.out.println("List the number of items you want to order");
        numOfItems=in.nextInt();
        in.nextLine();          // As nextInt doesn't take newline

        for(int i=0; i<numOfItems; i++) {
            System.out.println("Enter the name of the dish no: "+(i+1));
            String item=in.nextLine();
            itemsOrdered.add(item);
            orderCost+=menu.itemPriceList.get(item);
        }

        System.out.println("Your orderCost  is "+ orderCost);

    }

    public String toString() {
        int i=1;
        String str="---Order---\n";
        for(String item:itemsOrdered) {
            str+="Item:"+i+" "+item+"\n";
            i++;
        }
        return str;
    }
}