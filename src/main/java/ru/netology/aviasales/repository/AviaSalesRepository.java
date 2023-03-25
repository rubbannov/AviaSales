package ru.netology.aviasales.repository;

import ru.netology.aviasales.domain.Ticket;

public class AviaSalesRepository {
    Ticket[] tickets = new Ticket[0];
    public void addTicket(Ticket ticket) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tmp.length - 1] = ticket;
        tickets = tmp;
    }
    public void removeById(int id) {
        if (findById(id) != null) {
            Ticket[] tmp = new Ticket[tickets.length - 1];
            int copyToIndex = 0;
            for (Ticket item : tickets) {
                if (item.getId() != id) {
                    tmp[copyToIndex] = item;
                    copyToIndex++;
                }
                tickets = tmp;
            }
        } else {
            return;
        }
    }

    public Ticket[] findById(int id) {
        for (Ticket item : tickets) {
            if (item.getId() == id) {
                return new Ticket[]{item};
            }
        }
        return null;
    }
    public Ticket[] showAllTickets() {
        return tickets;
    }
}
