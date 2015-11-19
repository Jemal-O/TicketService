package ws;

import java.util.Calendar;

import javax.jws.WebMethod;
import javax.jws.WebService;

import objects.*;

import javax.jws.WebParam;

@WebService
public class TicketServiceImp {
	private TicketStorage tickets = new TicketStorage();

	@WebMethod
	public int reserveTicket(@WebParam(name = "name") String name, @WebParam(name = "lastName") String lastName,
			@WebParam(name = "patronymicName") String patronymicName, @WebParam(name = "departCity") String departCity,
			@WebParam(name = "arrivalCity") String arrivalCity, @WebParam(name = "departDate") Calendar departDate,
			@WebParam(name = "arrivalDate") Calendar arrivalDate, @WebParam(name = "birthDate") Calendar birthDate) {

		Person person = new Person(name, lastName, patronymicName, birthDate);
		Ticket ticket = initTicket(person, departCity, arrivalCity, departDate, arrivalDate, birthDate);
		tickets.getTickets().add(ticket);
		return ticket.getTicketNum();
	}

	private Ticket initTicket(Person person, String departCity, String arrivalCity, Calendar departDate,
			Calendar arrivalDate, Calendar birthDate) {
		Ticket ticket = new Ticket();
		TicketStatus ticketStatus = TicketStatus.reserve;
		ticket.setPerson(person);
		ticket.setDepartCity(departCity);
		ticket.setArrivalCity(arrivalCity);
		ticket.setDepartDate(departDate);
		ticket.setArrivalDate(arrivalDate);
		ticket.setTicketStatus(ticketStatus.getDescription());
		ticket.setTicketPrice(ticket.getDefaultPrice());
		return ticket;
	}

	@WebMethod
	public Ticket getTicketUsingNum(@WebParam(name = "ticketNum") int ticketNum) {
		Ticket ticket = new Ticket();
		int ticketsSize = tickets.getTickets().size();
		for (int i = 0; i < ticketsSize; i++) {
			if (ticketNum == tickets.getTickets().get(i).getTicketNum()) {
				ticket = tickets.getTickets().get(i);
			}
		}
		return ticket;
	}

	@WebMethod
	public boolean returnTicket(@WebParam(name = "ticketNum") int ticketNum) {
		int ticketsSize = tickets.getTickets().size();
		for (int i = 0; i < ticketsSize; i++) {
			if (ticketNum == tickets.getTickets().get(i).getTicketNum()) {
				tickets.getTickets().remove(i);
				return true;
			}
		}
		return false;
	}

	@WebMethod
	public Ticket payTicket(@WebParam(name = "ticketNum") int ticketNum) {
		Ticket ticket = new Ticket();
		TicketStatus ticketStatus = TicketStatus.reserve;
		int ticketsSize = tickets.getTickets().size();
		for (int i = 0; i < ticketsSize; i++) {
			if (ticketNum == tickets.getTickets().get(i).getTicketNum()) {
				ticket = tickets.getTickets().get(i);
			}
		}
		ticket.setTicketStatus(ticketStatus.getDescription());
		return ticket;
	}
}
