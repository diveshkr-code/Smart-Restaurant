package com.company;

import java.util.Scanner;

public class Tables {
    int NO_TWO=0;
    int NO_FOUR=0;
    int NO_SIX=0;

    public Tables() {
        Scanner in=new Scanner(System.in);
        System.out.println("We will decide the seating of the resturant");
        System.out.println("Input the number of tables with capacity of 2");
        NO_TWO=in.nextInt();
        System.out.println("Input the number of tables with capacity of 4");
        NO_FOUR=in.nextInt();
        System.out.println("Input the number of tables with capacity of 6");
        NO_SIX=in.nextInt();
    }

    protected void addTables() {
        Scanner in=new Scanner(System.in);
        System.out.println("What will be the capacity of the table that you will add 2/4/6(Pick One)");
        int capacity=in.nextInt();
        System.out.println("Input the number of Tables");
        int numOfNewTables=in.nextInt();
        switch (capacity) {
            case 2: NO_TWO+=numOfNewTables;
                    break;
            case 4: NO_FOUR+=numOfNewTables;
                    break;
            case 6: NO_SIX+=numOfNewTables;
                    break;
        }
    }

    public boolean checkEmpty(int numOfPeople) {
        if(numOfPeople<=2)
            return NO_TWO>0||NO_FOUR>0||NO_SIX>0;
        else if(numOfPeople<=4)
            return NO_FOUR>0||NO_SIX>0;
        else if(numOfPeople<=6)
            return NO_SIX>0;
        else
            return NO_TWO>0&&checkEmpty(numOfPeople-2) ||
                    NO_FOUR>0&&checkEmpty(numOfPeople-4)||
                    NO_SIX>0&&checkEmpty(numOfPeople-6);
    }
}
