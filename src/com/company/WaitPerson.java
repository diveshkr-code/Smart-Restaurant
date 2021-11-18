package com.company;

import java.util.Scanner;

class WaitPerson extends Thread {
    private Restaurant restaurant;
    public enum CustomerStatus {ARRIVED, SEATED, ORDERED, SERVED};
    private CustomerStatus customerStatus= CustomerStatus.ARRIVED;
    int numOfPeople;
    public WaitPerson(Restaurant r) {
        restaurant = r;
        Scanner in=new Scanner(System.in);
        System.out.println("WELCOME!! How many people are there?");
        numOfPeople=in.nextInt();
    }

    public void run() {

        while (restaurant.getState(numOfPeople)== Restaurant.State.OPEN) {
            this.customerStatus=CustomerStatus.SEATED;
            while (restaurant.order == null)
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            System.out.println("Waitperson got " + restaurant.order);
            restaurant.order = null;
        }
    }
}