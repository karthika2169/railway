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
    static int pnrNumber=1;
    static int pnr_no;

    int seats;
    char source;
    char destination;
    ticketStatus ticketStatus;

    public ticket(int pnr_no,int seats, char source, char destination, org.example.ticketStatus ticketStatus) {
        this.pnr_no=pnr_no;
        this.seats = seats;
        this.source = source;
        this.destination = destination;
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return "ticket{" +
                "seats=" + seats +
                ", source=" + source +
                ", destination=" + destination +
                ", ticketStatus=" + ticketStatus +
                 '}';
    }
}
