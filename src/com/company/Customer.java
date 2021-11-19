package com.company;

import java.util.Scanner;

class Customer extends Thread {
    private Restaurant restaurant;
    public enum CustomerStatus {ARRIVED, SEATED, ORDERED, SERVED,LEFT};
    public CustomerStatus customerStatus= CustomerStatus.ARRIVED;
    int numOfPeople;
    Order order;
    int NO_TWO=0;
    int NO_FOUR=0;
    int NO_SIX=0;


    public Customer(Restaurant r) {
        restaurant = r;
        Scanner in=new Scanner(System.in);
        System.out.println("WELCOME!! How many people are there?");
        numOfPeople=in.nextInt();
    }

    public void run() {
        while (this.customerStatus==CustomerStatus.ARRIVED) {
            synchronized (this) {
                if(restaurant.state==Restaurant.State.OPEN ) {
                    this.customerStatus=CustomerStatus.SEATED;
                }
            }
            order=new Order(restaurant.menu);
            this.customerStatus=CustomerStatus.ORDERED;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.customerStatus=CustomerStatus.SERVED;
            synchronized (this) {
                restaurant.tables.leaveTable(numOfPeople);
            }
        }
    }
}