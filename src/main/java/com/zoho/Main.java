package com.zoho;
import com.zoho.Services.Reservation;
import com.zoho.models.Ticket;
import com.zoho.models.TicketType;
import java.util.Scanner;

public class Main {
    static String name;

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.println("Welcome to Railway Reservation System!");
        Reservation reservation = new Reservation();


        while(true){

            System.out.print("1 : to view confirmed list of ticket \n2 : to book new ticket \n3 : to cancel ticket \n4 : to view RAC tickets confirmed \n5 : to view waitingList ticket");

            int ip = scn.nextInt();

            if(ip == 1){
                //view confirmed list of ticket
                System.out.println("Confirmed list of tickets are:");
                reservation.viewConfirmedTickets();
            } else if (ip == 2) {
                //book new ticket
                try{
                    System.out.println("Please provide name : ");
                    name = scn.nextLine();
                    name = scn.nextLine();
                    System.out.println("Please provide Berth (note) Available Berth - U, L, M please provide available option : ");
                    String berthChar = scn.nextLine();
                    Ticket ticket = reservation.createTicket(name
                            ,berthChar.charAt(0));
                    if(ticket != null){
                        if(ticket.getTicketType().equals(TicketType.REALTICKET)){
                            System.out.println("Your ticket has been confirmed with ticked ID : " + ticket.getId());
                        }else if(ticket.getTicketType().equals(TicketType.RAC)){
                            System.out.println("Your ticket is confirmed as RAC with ticket ID : " + ticket.getId());
                        }else if(ticket.getTicketType().equals(TicketType.WAITINGLIST)){
                            System.out.println("Your ticket is now on waiting list with ticket ID : " + ticket.getId());
                        }else{
                            System.out.println("Something wrong happened");
                        }
                    }else{
                        System.out.println("Something wrong happened");
                    }
                }catch (Exception e){
                    System.out.println("Recheck input and prvide valid input");
                }
            } else if (ip == 3) {
                //cancel ticket
                System.out.println("Please provide id of your ticket to cancel : ");
                int tickedIdToCancel = scn.nextInt();
                Ticket resultTicket = reservation.CancelTicket(tickedIdToCancel);
                if(resultTicket != null){
                    //change order
                    System.out.println("Your ticket with ticketId " + tickedIdToCancel + " has been sucessfully deleted");
                    reservation.rearrangeTicket(resultTicket);
                }else{
                    System.out.println("Invalid ticket Id, please try again with valid Id");
                }
                System.out.println("cancel ticket");
            } else if (ip == 4) {
                //view RAC ticket
                System.out.println("Confirmed RAC tickets are:");
                reservation.viewRACTickets();
            } else if (ip == 5) {
                //view waitinglist ticket
                System.out.println("Waitinglist ticket");
                reservation.viewWaitingListTickets();
            }else {
                //invalid ip
                System.out.println("Invalid Option, please provide valid available options");
            }
        }
    }
}