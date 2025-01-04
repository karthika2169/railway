package org.example;

import java.util.Arrays;
import java.util.Scanner;

import static org.example.trainMethods.bookTicket;
import static org.example.trainMethods.cancelTicket;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("***********************************************************");
        System.out.println("***********WELCOME TO TRAIN RESERVATION SYSTEM*************");
        System.out.println("***********************************************************");
        while(true)
        {
            try {
                System.out.println("1--->BookTicket \n2--->CancelTicket" +
                        " \n3--->ViewAvailableSeats \n4--->ExitFromApplication \nChoose option "
                );
                int option = sc.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("Enter source point /A/B/C/D/E");
                        char s=sc.next().charAt(0);
                        s=Character.toUpperCase(s);
                        if(!(s>='A' && s<='E'))
                        {
                            System.out.println("Invalid source point:" +
                                    " Source destination should between A to E");
                        }
                        System.out.println("Enter destination point A/B/C/D/E");
                        char d=sc.next().charAt(0);
                        d=Character.toUpperCase(d);
                        if(!((d > s) && ((d >= 'A') || (d <= 'E'))))
                        {
                            System.out.println("Invalid destination point: " +
                                    "Destination should be greater than source point and between A to E");
                        }
                        System.out.println("Enter how many tickets you want to book");
                        int tickets=sc.nextInt();
                        bookTicket(s,d,tickets);
                    }
                    break;
                    case 2: {
                        System.out.println("Enter pnr no to cancel ticket ");
                        int pnr=sc.nextInt();
                        cancelTicket(pnr);
                        break;
                    }
                    case 3: {
                        printChart.printchart();
                        break;
                    }
                    case 4: {
                        System.out.println("Exiting from application.....");
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Enter valid option");
                        break;
                    }
                }
            }
            catch (RuntimeException e)
            {
                System.out.println("unexpected output "+e);
                break;
            }
        }

    }
}