package objects;

import java.util.LinkedHashMap;
import java.util.Map;

public class TicketStorage {
//	почему Linked ?
	private Map<Integer, Ticket> tickets = new LinkedHashMap<Integer, Ticket>();

//	класс все еще расскрывает свою реализацию
	public Map<Integer, Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Map<Integer, Ticket> tickets) {
		this.tickets = tickets;
	}

}
