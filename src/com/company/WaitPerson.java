package com.company;

class WaitPerson extends Thread {
    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        while (true) {
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