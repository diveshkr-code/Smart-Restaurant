package com.company;

import java.util.Scanner;

class Chef extends Thread {
    private Restaurant restaurant;
    private int chefCapacity;
    Customer currentCustomer;
    public Chef(Restaurant r) {
        restaurant = r;
//        TODO Order capacity
//        Scanner in=new Scanner(System.in);
//        System.out.println("Input the order capacity of your chef");
//        chefCapacity =in.nextInt();
    }

    public void run() {
        while (true) {
            if (!restaurant.customerList.isEmpty()) {
                currentCustomer=restaurant.customerList.get(0);
                try {
                    System.out.println("Preparing Order...");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("Order up! ");
                synchronized (currentCustomer) {
                    currentCustomer.notify();
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
