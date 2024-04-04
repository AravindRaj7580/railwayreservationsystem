package com.zoho.models;

public class Ticket {
    int id;
    char berth;
    String name;
    TicketType ticketType;

    public void setId(int id) {
        this.id = id;
    }

    public void setBerth(char berth) {
        this.berth = berth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public char getBerth() {
        return berth;
    }

    public String getName() {
        return name;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

}
