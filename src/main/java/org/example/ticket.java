package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ticket {
   ;
    static Map<Integer,ticket> bookedticketmap=new HashMap<>();
    static ConcurrentHashMap<Integer,ticket> wl_map=new ConcurrentHashMap<>();
    static Map<Integer,ticket> cancelledticketmap=new HashMap<>();
    static Map<Integer,Integer> partiallycancelledticketmap=new HashMap<>();

    static  int pnr_no=1;

    int seats;
    char source;
    char destination;
    ticketStatus ticketStatus;

    public ticket(int seats, char source, char destination, org.example.ticketStatus ticketStatus) {
        this.seats = seats;
        this.source = source;
        this.destination = destination;
        this.ticketStatus = ticketStatus;
    }
}
