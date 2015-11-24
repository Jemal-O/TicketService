package objects;

import java.util.Calendar;

public class TicketCreation {

	public Ticket initTicket(Person person, String departCity, String arrivalCity, Calendar departDate,
			Calendar arrivalDate, Calendar birthDate) {
		Ticket ticket = new Ticket();
		ticket.setTicketNum(ticket.getNewNum());
		ticket.setPerson(person);
		ticket.setDepartCity(departCity);
		ticket.setArrivalCity(arrivalCity);
		ticket.setDepartDate(departDate);
		ticket.setArrivalDate(arrivalDate);
		ticket.setTicketStatus(TicketStatus.RESERVE);
		ticket.setTicketPrice(ticket.getDefaultPrice());
		return ticket;
	}
}
