import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.aviasales.domain.Ticket;
import ru.netology.aviasales.manager.AviaSalesManager;
import ru.netology.aviasales.repository.AviaSalesRepository;

import java.util.Arrays;

public class AviaSalesTest {
    AviaSalesRepository repository = new AviaSalesRepository();
    AviaSalesManager manager = new AviaSalesManager(repository);

    Ticket ticket1 = new Ticket(1,4600, "SVO","LED",95);
    Ticket ticket2 = new Ticket(2,4700, "SVO", "LED", 100);
    Ticket ticket3 = new Ticket(3,7600, "VKO","MSQ",85);
    Ticket ticket4 = new Ticket(4,5990, "MSQ", "VKO", 90);
    Ticket ticket5 = new Ticket(5,11200, "KZN","IST",300);
    Ticket ticket6 = new Ticket(6,11200, "KZN", "ATA", 215);

    @BeforeEach
    public void setUp() {
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
    }

    @Test
    public void testShowAllTickets() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = repository.showAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testFindById() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = repository.findById(3);

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testFindByNotDoesExistId() {
        Ticket[] actual = repository.findById(450);

        Assertions.assertArrayEquals(null, actual);
    }
    @Test
    public void testRemoveById() {
        repository.removeById(2);
        Ticket[] expected = {ticket1,ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = repository.showAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testRemoveByDoesNotExistId() {
        repository.removeById(22);
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = repository.showAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSearchByDeparture() {
        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = manager.searchByDeparture("SVO");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSearchByDoesNotExistDeparture() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchByDeparture("AER");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSearchByArrival() {
        Ticket[] expected = {ticket6};
        Ticket[] actual = manager.searchByArrival("ATA");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSearchByDoesNotExistArrival() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchByArrival("AER");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSearchFromToIfFewResults() {
        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = manager.findAll("SVO","LED");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSearchFromTo() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.findAll("VKO","MSQ");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSearchDoesNotExistFromTo() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("VKO","INS");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testCompareTo() {
        Arrays.sort(repository.showAllTickets());

        Ticket[] expected = {ticket1, ticket2, ticket4, ticket3, ticket5, ticket6};
        Ticket[] actual = repository.showAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }
}
