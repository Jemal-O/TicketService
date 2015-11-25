package objects;

import java.util.Calendar;

public class TicketCreation {
	private int lastTicketNum = 1;
	private float defaultPrice = 1000;

	public Ticket initTicket(Person person, String departCity, String arrivalCity, Calendar departDate,
			Calendar arrivalDate, Calendar birthDate) {
		Ticket ticket = new Ticket();
		Price price = new Price(defaultPrice, Currency.RUR);
//		всегда один и тот же номер?
		ticket.setTicketNum(lastTicketNum);
		ticket.setPerson(person);
		ticket.setDepartCity(departCity);
		ticket.setArrivalCity(arrivalCity);
		ticket.setDepartDate(departDate);
		ticket.setArrivalDate(arrivalDate);
		ticket.setTicketStatus(TicketStatus.RESERVE);
		ticket.setPrice(price);
		return ticket;
	}

}
