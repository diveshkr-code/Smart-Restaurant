package com.company;

import java.util.Scanner;

class Chef extends Thread {
    private Restaurant restaurant;
    private WaitPerson waitPerson;
    private int chefCapacity;
    public Chef(Restaurant r, WaitPerson w) {
        restaurant = r;
        waitPerson = w;
        Scanner in=new Scanner(System.in);
        System.out.println("Input the order capacity of your chef");
        chefCapacity =in.nextInt();
    }

    public void run() {
        System.out.println("hi Chef");
        while (true) {
            if (restaurant.order == null) {
                restaurant.order = new Order(restaurant.menu, chefCapacity);
                try {
                    System.out.println("Preparing Order...");
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("Order up! ");
                synchronized (waitPerson) {
                    waitPerson.notify();
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
