package com.company;


class Customer extends Thread {
    private Restaurant restaurant;
    public enum CustomerStatus {ARRIVED, SEATED, ORDERED, SERVED,LEFT}
    public CustomerStatus customerStatus= CustomerStatus.ARRIVED;
    int numOfPeople;
    Order order;
    int NO_TWO=0;
    int NO_FOUR=0;
    int NO_SIX=0;


    public Customer(Restaurant r) {
        restaurant = r;
        System.out.println("WELCOME!! How many people are there?");
        numOfPeople=restaurant.in.nextInt();
    }

    public void run() {
        while (this.customerStatus==CustomerStatus.ARRIVED) {
            synchronized (this) {
                if(restaurant.state==Restaurant.State.OPEN ) {
                    if(restaurant.seatCustomer(this)) {
                        restaurant.customerList.add(this);
                        this.customerStatus=CustomerStatus.SEATED;
                        System.out.println("Here have a seat we will be taking your order now");
                    }
                    else {
                        System.out.println("Sorry we are running low on seats please come back later");
                    }
                }
            }

            if (this.customerStatus== CustomerStatus.SEATED) {
                order=new Order(restaurant.menu);
                this.customerStatus=CustomerStatus.ORDERED;
                restaurant.revenue+=this.order.orderCost;
                System.out.println("Hang Tight we are preparing your order !!!");

                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.customerStatus=CustomerStatus.SERVED;
                synchronized (this) {
                    restaurant.exitCustomer(this);
                }
            }
            this.customerStatus= CustomerStatus.LEFT;
        }
    }
}