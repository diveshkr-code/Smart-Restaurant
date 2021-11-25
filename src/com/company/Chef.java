package com.company;


import static javax.swing.JOptionPane.showMessageDialog;

class Chef extends Thread {
    private Restaurant restaurant;
    private int chefCapacity;
    Customer currentCustomer;
    Order currentOrder;
    public Chef(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        while (!(restaurant.state == Restaurant.State.CLOSED)) {
            if (!restaurant.customerList.isEmpty()) {
                currentCustomer=restaurant.customerList.get(0);
                currentOrder=currentCustomer.order;
                while(currentOrder!=null) {
                    try {
//                        Parameter: time taken to prepare a meal is propotional to number of Items
                        sleep(currentOrder.numOfItems*15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    showMessageDialog(null, "Order Up for Order No: "+currentOrder.orderNo);
                    synchronized (currentCustomer) {
                        currentCustomer.notify();
                    }
                    break;
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
