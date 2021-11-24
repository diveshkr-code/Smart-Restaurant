package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    List<String> itemsOrdered=new ArrayList<>();
    int numOfItems=0;
    double orderCost;
    static int lastOrderNo=1;
    int orderNo;
    public Order(Menu menu) {
        orderCost=0;
        System.out.println("-----   MENU    -----");
        menu.displayMenu();
        System.out.println("---------------------");

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
        orderNo=lastOrderNo++;
        System.out.println("Your order Cost  is "+ orderCost);
        System.out.println("Your order Number  is "+ orderNo);
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