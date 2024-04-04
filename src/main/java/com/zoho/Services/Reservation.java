package com.zoho.Services;

import com.zoho.models.Ticket;
import com.zoho.models.TicketType;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    int globalIdIncrement = 0;
    int capacityReal = 6;
    int capacityRAC = 6;
    List<Ticket> realTicket = new ArrayList<>();
    List<Ticket> RACTicket = new ArrayList<>();
    List<Ticket> waitingListTicket = new ArrayList<>();
    public void BookTiket(Ticket ticket){
        if(realTicket.size() < capacityReal){
            realTicket.add(ticket);
            System.out.println();
            System.out.println("Ticket Confirmed and Ticket Id is " + ticket.getId());
        } else if (RACTicket.size() < capacityRAC) {
            RACTicket.add(ticket);
            System.out.println();
            System.out.println("Ticket Confirmed as RAC Ticket and Ticket Id is " + ticket.getId());
        }else{
            waitingListTicket.add(ticket);
            System.out.println();
            System.out.println("Ticket is in the waitingList and Ticket Id is " + ticket.getId());
        }
    }

    public Ticket createTicket(String name, char berth){
        if(name == null || name.isBlank() || berth == '\0'){
            //might be better to throw an exception
            return null;
        }
        Ticket ticket = new Ticket();
        globalIdIncrement++;
        ticket.setId(globalIdIncrement);
        ticket.setName(name);
        ticket.setBerth(berth);
        if(realTicket.size() < capacityReal){
            ticket.setTicketType(TicketType.REALTICKET);
            realTicket.add(ticket);
        } else if (RACTicket.size() < capacityRAC) {
            ticket.setTicketType(TicketType.RAC);
            RACTicket.add(ticket);
        }else{
            ticket.setTicketType(TicketType.WAITINGLIST);
            waitingListTicket.add(ticket);
        }
        return ticket;
    }

    public Ticket CancelTicket(int ticketId){
        for(int i = 0; i< realTicket.size(); i++){
            if(ticketId == realTicket.get(i).getId()){
                Ticket ticket = realTicket.get(i);
                realTicket.remove(i);
                System.out.println("Your Ticket with Id : " + ticketId + " has been canclled");
                //check and fill vacant
                return ticket;
            }
        }
        for(int i = 0; i< RACTicket.size(); i++){
            if(ticketId == RACTicket.get(i).getId()){
                Ticket ticket = RACTicket.get(i);
                RACTicket.remove(i);
                System.out.println("Your Ticket with Id : " + ticketId + " has been canclled");
                //check and fill vacant
                return ticket;
            }
        }
        for(int i = 0; i< waitingListTicket.size(); i++){
            if(ticketId == waitingListTicket.get(i).getId()){
                Ticket ticket = waitingListTicket.get(i);
                waitingListTicket.remove(i);
                System.out.println("Your Ticket with Id : " + ticketId + " has been canclled");
                //check and fill vacant
                return ticket;
            }
        }
        System.out.println();
        System.out.println("Invalid Id, Please provide valid confirmed Id ");
        //should return to ip section of getting the id to cancel ticket
        return null;
    }

    public void viewConfirmedTickets(){
        for(int i = 0; i<realTicket.size();i++){
            System.out.println("Ticket id : " + realTicket.get(i).getId());
            System.out.println("Name : " + realTicket.get(i).getName());
            System.out.println("Berth : " + realTicket.get(i).getBerth());
            System.out.println();
        }
    }

    public void viewRACTickets(){
        for(int i = 0; i<RACTicket.size();i++){
            System.out.println("Ticket id : " + RACTicket.get(i).getId());
            System.out.println("Name : " + RACTicket.get(i).getName());
            System.out.println("Berth : " + RACTicket.get(i).getBerth());
            System.out.println();
        }
    }

    public void viewWaitingListTickets(){
        for(int i = 0; i<waitingListTicket.size();i++){
            System.out.println("Ticket id : " + waitingListTicket.get(i).getId());
            System.out.println("Name : " + waitingListTicket.get(i).getName());
            System.out.println("Berth : " + waitingListTicket.get(i).getBerth());
            System.out.println();
        }
    }

    public void rearrangeTicket(Ticket ticket){
        if(ticket.getTicketType().equals(TicketType.REALTICKET)){
            if(realTicket.size() < capacityReal){
                if(RACTicket.size() > 0){
                    Ticket changedTicket = RACTicket.remove(0);
                    realTicket.add(changedTicket);
                    System.out.println("RAC-Ticket with id : "+ changedTicket.getId() + " is now confirmedTicket");
                } else if (waitingListTicket.size() > 0) {
                    Ticket changedTicket = waitingListTicket.remove(0);
                    realTicket.add(changedTicket);
                    System.out.println("WaitingList-Ticket with id : "+ changedTicket.getId() + " is now confirmed RAC-Ticket");
                }
            }

        }else if(ticket.getTicketType().equals(TicketType.RAC)){
            if(waitingListTicket.size() > 0){
                Ticket changedTicket = waitingListTicket.remove(0);
                RACTicket.add(changedTicket);
                System.out.println("WaitingList-Ticket with id : " + changedTicket.getId() + " is now confirmed RAC-Ticket");
            }else{
                //do nothing
            }

        } else if (ticket.getTicketType().equals(TicketType.WAITINGLIST)) {
            //do nothing
        }
    }


}
