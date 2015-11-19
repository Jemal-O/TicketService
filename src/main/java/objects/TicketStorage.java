package objects;

import java.util.ArrayList;
import java.util.List;

public class TicketStorage {
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	

}
