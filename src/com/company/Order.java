package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    List<String> itemsOrdered=new ArrayList<>();
    int numOfItems=0;
    int orderCost;

    public Order(Menu menu) {
        orderCost=0;
        menu.displayMenu();

        Scanner in=new Scanner(System.in);

        System.out.println("List the number of items you want to order");
        numOfItems=in.nextInt();
        for(int i=0; i<numOfItems; i++) {
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