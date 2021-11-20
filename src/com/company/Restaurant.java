package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Restaurant extends Thread {
    Menu menu;
    Tables tables;
    List<Customer> customerList;

    //    State of the Restaurant
    public enum State{CLOSED, FULL, OPEN};
    State state;

    protected State getState(int numOfPeople) {
        if(this.tables.checkEmpty(numOfPeople))
            return State.OPEN;
        else
            return State.FULL;
    }

    @Override
    public void run() {
//        After TODO secs it will be closed
        state=State.OPEN;
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sorry Folks we are closing, see you tommorow");
        state=State.CLOSED;
    }



    Restaurant() {
        tables=new Tables();
        menu=new Menu();
        customerList=new ArrayList<Customer>();
    }
    public boolean seatCustomer(Customer c) {
        if(c.numOfPeople<=2) {
            if(tables.NO_TWO>0) {
                tables.NO_TWO--;
                c.NO_TWO++;
                return true;
            }
            else if(tables.NO_FOUR>0) {
                tables.NO_FOUR--;
                c.NO_FOUR++;
                return true;
            }

            else if(tables.NO_SIX>0) {
                tables.NO_SIX--;
                c.NO_SIX++;
                return true;
            }
        }
        else if(c.numOfPeople<=4) {
            if(tables.NO_FOUR>0) {
                tables.NO_FOUR--;
                c.NO_FOUR++;
                return true;
            }

            else if(tables.NO_SIX>0) {
                tables.NO_SIX--;
                c.NO_SIX++;
                return true;
            }
        }
        else if(c.numOfPeople<=6) {
            if(tables.NO_SIX>0) {
                tables.NO_SIX--;
                c.NO_SIX++;
                return true;
            }
        }
        return false;
    }
    public void exitCustomer(Customer c) {
        tables.NO_TWO+=c.NO_TWO;
        tables.NO_FOUR+=c.NO_FOUR;
        tables.NO_SIX+=c.NO_SIX;

        c.NO_TWO=0;
        c.NO_FOUR=0;
        c.NO_SIX=0;
        c.customerStatus= Customer.CustomerStatus.LEFT;
    }
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        restaurant.start();

        Chef chef=new Chef(restaurant);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Customer customer = new Customer(restaurant);
                restaurant.customerList.add(customer);
                customer.start();
            }
        }, 0, 20, TimeUnit.SECONDS);


    }
}