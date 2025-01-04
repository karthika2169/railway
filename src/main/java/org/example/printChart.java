package org.example;

import java.util.Arrays;

import static org.example.ticket.*;
import static org.example.trainMethods.seatAvailablity;

public class printChart {
    public static void printchart()
    {
        System.out.println("\n List of booked Tickets ");
        bookedticketmap.values().forEach(System.out::println);
        System.out.println("\n List of waitingList Tickets ");
        wl_map.values().forEach(System.out::println);
        System.out.println("\n List of cancelled Tickets ");
        cancelledticketmap.values().forEach(System.out::println);
        System.out.println("\n seats Availability: "+ Arrays.toString(seatAvailablity));
        System.out.println("\n seats booked chart ");
        for(int i=1;i<=8;i++)
        {
            System.out.print("\t"+i);
        }
        System.out.println("");
        for(char a='A';a<='E';a++)
        {
            System.out.print(a+"\t");
            int seat=8-seatAvailablity[a-'A'];
            for(int i=0;i<seat;i++)
            {
                System.out.print("*\t");
            }
            System.out.println("");
        }
    }
}
