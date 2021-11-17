package com.company;

public class Restaurant {
    Order order; // Package access
    Menu menu;

    Restaurant() {
        menu=new Menu();
    }
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        WaitPerson waitPerson = new WaitPerson(restaurant);
        Chef chef = new Chef(restaurant, waitPerson);

        waitPerson.start();
        chef.start();
    }
}