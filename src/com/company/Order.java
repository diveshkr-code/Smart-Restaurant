package com.company;

class Order {
    private static int i = 0;

    private int count = i++;

    public Order() {
        if (count == 10) {
            System.out.println("Out of food, closing");
            System.exit(0);
        }
    }

    public String toString() {
        return "Order " + count;
    }
}