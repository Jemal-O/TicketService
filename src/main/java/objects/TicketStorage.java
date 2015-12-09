package objects;

import java.util.HashMap;
import java.util.Map;

public class TicketStorage implements Storage {
    // почему Linked ?
    private Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    // класс все еще расскрывает свою реализацию
    public Ticket getTicket(int ticketId) {
        return tickets.get(ticketId);
    }

    public void setTicket(Ticket ticket) {
        tickets.put(ticket.getTicketNum(), ticket);
    }

    public void removeTicket(int ticketId) {
        tickets.remove(ticketId);
    }

}
