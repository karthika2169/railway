package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static org.example.ticket.*;

public class trainMethods {
    static Scanner sc =new Scanner(System.in);
    static int[] seatAvailablity;
    static int wl_seats = 0;
     static {
        seatAvailablity = new int[5];
        Arrays.fill(seatAvailablity, 8);
    }
    public static void bookTicket(char source,char dest,int seats)
    {
             if(checkseatavailablity(source,dest,seats))
             {

                 bookedticketmap.put(pnrNumber,new ticket(pnrNumber,seats,source,dest,ticketStatus.booked));
                 System.out.println("Ticket booked successfully.Pnr no for your booked ticket is "+pnrNumber);
                 decreaseseatavailablity(source,dest,seats);
                 pnrNumber++;
             }
             else
             {
                 if(wl_seats+seats>2)
                     System.out.println("There is no available seats from "+source +" to "+dest);
                 else
                 {
                     addtowaitinglist(source,dest,seats);
                     pnrNumber++;
                 }
             }
    }

    private static void addtowaitinglist(char source, char dest, int seats) {

         wl_map.put(pnrNumber,new ticket(pnrNumber,seats,source,dest,ticketStatus.waitingList));
        System.out.println("Ticket added to waiting list with pnr no is: "+pnrNumber);
        wl_seats+=seats;
    }

    private static void decreaseseatavailablity(char source, char dest, int seats) {
         for(int i=source-'A';i<dest-'A';i++)
         {
             seatAvailablity[i]-=seats;
         }

    }

    private static boolean checkseatavailablity(char source, char dest, int tickets) {
        for(int i=source-'A';i<dest-'A';i++)
        {
            if(seatAvailablity[i]<i)
                return false;
        }
        return true;
    }
    public static void cancelTicket(int pnr)
    {
      ticket t=getTicket(pnr);
      if(t!=null)
      {
          if(t.ticketStatus==ticketStatus.waitingList)
          {
              System.out.println("Enter how many seats you want to cancel ");
              int seats=sc.nextInt();
              waitinglistremoval(seats,pnr,t);
              return;
          }
          int booked_seats=t.seats;
          char s=t.source,d=t.destination;
          System.out.println("Enter how many seats you wanna cancel");
          int seatsToCancel= sc.nextInt();
          if(seatsToCancel>booked_seats)
          {
              System.out.println("you are try to cancel more seats than booked");
              return;
          }
          if(seatsToCancel==booked_seats)
          {
              docancelprocess(pnr,t);
              System.out.println("Ticket cancelled with pnr no "+pnr);
          }
          if(seatsToCancel<booked_seats) {
              t.seats = booked_seats - seatsToCancel;
              storepartiallycancelledseats(pnr, seatsToCancel);
              System.out.println("Ticket is partially cancelled with pnr no is " + pnr);
          }
          increaseseatavailablity(s,d,seatsToCancel);
          processWaitingList();
      }
      else
      {
          System.out.println("Ticket not found:Invalid pnr_no ");
      }

    }

    private static void processWaitingList() {
         for(Map.Entry<Integer, ticket> waiting:wl_map.entrySet())
         {
             boolean isValid;
             char source= waiting.getValue().source;
             char destination= waiting.getValue().destination;
             int seats= waiting.getValue().seats;
             int pnr_number=waiting.getKey();
             isValid=checkseatavailablity(source,destination,seats);
             if (isValid)
             {

                 decreaseseatavailablity(source,destination,seats);
                 wl_seats=wl_seats-seats;
                 updateBookedticketList(waiting);
             }
         }
    }

    private static void updateBookedticketList(Map.Entry<Integer, ticket> waiting) {

         waiting.getValue().ticketStatus=ticketStatus.booked;
         bookedticketmap.put(waiting.getKey(),waiting.getValue());
         wl_map.remove(waiting.getKey());
        System.out.println("**********ALERT***********");
        System.out.println("Booking confirmed for pnr no "+waiting.getKey());
    }

    private static void increaseseatavailablity(char source, char dest, int seats)
    {
        for(int i=source-'A';i<dest-'A';i++)
        {
            seatAvailablity[i]+=seats;
        }
    }

    private static void docancelprocess(int pnrNo, ticket t) {
         Integer getSeats=partiallycancelledticketmap.get(pnrNo);
         int seatstoadd=getSeats!=null?getSeats:0;
         t.seats=t.seats+seatstoadd;
         t.ticketStatus=ticketStatus.cancelled;
         bookedticketmap.remove(pnrNo);
         cancelledticketmap.put(pnrNo,t);
    }

    private static void storepartiallycancelledseats(int pnrNo, int seatsToCancel) {
         partiallycancelledticketmap.put(pnrNo,partiallycancelledticketmap.getOrDefault(pnrNo,0)+seatsToCancel);
    }

    private static void waitinglistremoval(int seats,int pnr, ticket t) {
        int booked_seats=t.seats;
        if(seats>booked_seats)
        {
            System.out.println("you are try to cancel more seats than booked");
            return;
        }
        if(seats==booked_seats)
        {
            wl_map.remove(pnr);
            System.out.println("your waiting list ticket is cancelled with pnr no +" +pnr);
        }
        if(seats<booked_seats)
        {
            t.seats=booked_seats-seats;
            System.out.println("Ticket is partially cancelled with pnr no "+pnr);
        }
       wl_seats=wl_seats-seats;
    }

    private static ticket getTicket(int pnrNo) {
         ticket t=bookedticketmap.get(pnrNo);
         return t!=null? t:wl_map.get(pnrNo);
    }
}
