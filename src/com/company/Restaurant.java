package com.company;

public class Restaurant {
    Order order; // Package access
    Menu menu;
    Tables tables;

    public enum State{CLOSED, FULL, OPEN};
    State state;

    protected void setState(State state) {
        this.state = state;
    }

    protected State getState(int numOfPeople) {
        if(this.tables.checkEmpty(numOfPeople))
            return State.OPEN;
        else
            return State.FULL;
    }

    Restaurant() {
        tables=new Tables();
        menu=new Menu();
    }
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        restaurant.state=State.OPEN;



        WaitPerson waitPerson = new WaitPerson(restaurant);
        Chef chef = new Chef(restaurant, waitPerson);

        waitPerson.start();
        chef.start();
    }
}