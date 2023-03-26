package ru.netology.aviasales.manager;

import ru.netology.aviasales.domain.Ticket;
import ru.netology.aviasales.repository.AviaSalesRepository;

import java.util.Arrays;

import static java.util.Arrays.*;

public class AviaSalesManager {
    AviaSalesRepository repository = new AviaSalesRepository();

    public AviaSalesManager(AviaSalesRepository repo) {
        this.repository = repo;
    }

    public void addTicket(Ticket ticket) { //Добавление билета
        repository.addTicket(ticket);
    }
    public Ticket[] findAll(String from, String to) { //Поиск билетов
        Ticket[] result = new Ticket[0];
        for (Ticket item : repository.showAllTickets()) {
            if (matchesDeparture(item,from) && matchesArrival(item, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
    private boolean matchesDeparture(Ticket ticket, String search) { //Совпадения по вылету
        return ticket.getDepartureAirport().contains(search);
    }
    private boolean matchesArrival(Ticket ticket, String search) { //Совпадения по прилету
        return ticket.getArrivalAirport().contains(search);
    }
    public Ticket[] searchByDeparture(String text) { //Поиск по вылету
        Ticket[] result = new Ticket[0];
        for (Ticket ticket: repository.showAllTickets()) {
            if (matchesDeparture(ticket, text)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }
    public Ticket[] searchByArrival(String text) { //Поиск по прилету
        Ticket[] result = new Ticket[0];
        for (Ticket ticket: repository.showAllTickets()) {
            if (matchesArrival(ticket, text)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }
}
