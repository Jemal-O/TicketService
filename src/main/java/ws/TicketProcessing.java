package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import exception.IsPaidException;
import exception.NotTicketFoundException;
import objects.*;

import javax.jws.WebParam;

@WebService
public class TicketProcessing {
	private TicketStorage tickets = new TicketStorage();
	private TicketCreation ticketCreation = new TicketCreation();

	@WebMethod
	public int reserveTicket(@WebParam(name = "dataTransfer") DataTransfer dataTransfer) {

		Person person = new Person(dataTransfer.getName(), dataTransfer.getLastName(), dataTransfer.getPatronymicName(),
				dataTransfer.getBirthDate());
		Ticket ticket = ticketCreation.initTicket(person, dataTransfer.getDepartCity(), dataTransfer.getArrivalCity(),
				dataTransfer.getDepartDate(), dataTransfer.getArrivalDate(), dataTransfer.getBirthDate());
		tickets.getTickets().put(ticket.getTicketNum(),ticket);
		return 1;
	}

	@WebMethod
	public Ticket getTicketUsingNum(@WebParam(name = "ticketNum") int ticketNum) throws NotTicketFoundException {
		if (tickets.getTickets().get(ticketNum) == null) {
			throw new NotTicketFoundException();
		}
		return tickets.getTickets().get(ticketNum);
	}

	@WebMethod
	public boolean returnTicket(@WebParam(name = "ticketNum") int ticketNum) {
		if (tickets.getTickets().get(ticketNum) != null) {
			tickets.getTickets().remove(ticketNum);
			return true;
		}
		return false;
	}

	@WebMethod
	public Ticket payTicket(@WebParam(name = "ticketNum") int ticketNum)
			throws IsPaidException, NotTicketFoundException {
		if (tickets.getTickets().get(ticketNum) != null) {
			if (tickets.getTickets().get(ticketNum).getTicketStatus() != TicketStatus.IS_PAID) {
				tickets.getTickets().get(ticketNum).setTicketStatus(TicketStatus.IS_PAID);
			} else {
				throw new IsPaidException();
			}
		} else {
			throw new NotTicketFoundException();
		}
		return tickets.getTickets().get(ticketNum);
	}
}
