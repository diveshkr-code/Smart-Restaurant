package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static javax.swing.JOptionPane.showMessageDialog;

public class Restaurant extends Thread {
    Menu menu;
    Tables tables;
    List<Customer> customerList;
    protected double revenue;
    public static Scanner in = new Scanner(System.in);

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
//            Parameter: time after which resturant closes if no customer is there
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true){
            if (customerList.isEmpty()) {
                showMessageDialog(null, "Sorry Folks we are closing, see you tommorow \n " +
                        "The Revenue for this session was: "+revenue);

                state=State.CLOSED;
                break;
            }
            else {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    Restaurant() {
        tables=new Tables();
        menu=new Menu();
        customerList=new ArrayList<>();
        revenue=0.0;
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
//        TODO revneue from bill
    }
    public void exitCustomer(Customer c) {
        tables.NO_TWO+=c.NO_TWO;
        tables.NO_FOUR+=c.NO_FOUR;
        tables.NO_SIX+=c.NO_SIX;

        c.NO_TWO=0;
        c.NO_FOUR=0;
        c.NO_SIX=0;
        this.customerList.remove(c);
        c.customerStatus= Customer.CustomerStatus.LEFT;

    }

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        Restaurant restaurant = new Restaurant();
        restaurant.start();

        Chef chef=new Chef(restaurant);
        chef.start();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = null;
//        Parameters
//        intialDelay
//        period
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
//                Parameter: TODO
                if(restaurant.customerList.size()>10)
                    scheduledFuture.cancel(true);
                Customer customer = new Customer(restaurant);
                customer.start();
            }
        }, 15, 25, TimeUnit.SECONDS);


    }
}