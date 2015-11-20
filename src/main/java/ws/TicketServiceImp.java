package ws;

import java.util.Calendar;

import javax.jws.WebMethod;
import javax.jws.WebService;

import objects.*;

import javax.jws.WebParam;

@WebService
public class TicketServiceImp {
//    к данному можудю не отьносится, но на будущее. WebService классы не потокобезопасны. При текущей реализации TicketStorage будет
//    использоваться всеми потоками одновременно. Так что придется озаботиться о его синхронизации.
	private TicketStorage tickets = new TicketStorage();


//    Эту кучу параметров можно запихнуть в один transfer object и передвавать его
//    так в случае если один из параметров изменится не придется менять интерфейс сервисов
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

//	грамотнее было бы венести такую инициализацю тикета в отдельный сервис
	private Ticket initTicket(Person person, String departCity, String arrivalCity, Calendar departDate,
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

	@WebMethod
	public Ticket getTicketUsingNum(@WebParam(name = "ticketNum") int ticketNum) {
//		тут нет смысла создавать объект Ticket. Мы же планируем его получить из хранилища
		Ticket ticket = new Ticket();
		int ticketsSize = tickets.getTickets().size();
//		это должно выглядеть так: ticketsServie.get(ticketNum) и он либо возвращает билет, либо ошибку
		for (int i = 0; i < ticketsSize; i++) {
			if (ticketNum == tickets.getTickets().get(i).getTicketNum()) {
				ticket = tickets.getTickets().get(i);
			}
		}
//		нет проверки, что билета с таким номером может не быть
		return ticket;
	}

	@WebMethod
	public boolean returnTicket(@WebParam(name = "ticketNum") int ticketNum) {
		int ticketsSize = tickets.getTickets().size();
		for (int i = 0; i < ticketsSize; i++) {
//			аналогично предыдцщему замечанию. Не стоит раскрывать реализацию твоего TicketStorage
			if (ticketNum == tickets.getTickets().get(i).getTicketNum()) {
				tickets.getTickets().remove(i);
				return true;
			}
		}
		return false;
	}

	@WebMethod
	public Ticket payTicket(@WebParam(name = "ticketNum") int ticketNum) {
//		тут все те же самые замечания
		Ticket ticket = new Ticket();
		int ticketsSize = tickets.getTickets().size();
		for (int i = 0; i < ticketsSize; i++) {
			if (ticketNum == tickets.getTickets().get(i).getTicketNum()) {
				ticket = tickets.getTickets().get(i);
//				enum сравнивается без equals
				if (ticket.getTicketStatus().equals(TicketStatus.RESERVE)) {
					ticket.setTicketStatus(TicketStatus.IS_PAID);
				}
			}
		}
// опять же нет никаких проверок есть ли такой билет, оплачен ли он уже
		return ticket;
	}
}
