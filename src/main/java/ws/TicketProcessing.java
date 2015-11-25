package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import exception.IsPaidException;
import exception.NotTicketFoundException;
import objects.*;

import javax.jws.WebParam;

@WebService
public class TicketProcessing {
	// к данному можудю не отьносится, но на будущее. WebService классы не
	// потокобезопасны. При текущей реализации TicketStorage будет
	// использоваться всеми потоками одновременно. Так что придется озаботиться
	// о его синхронизации.
	private TicketStorage tickets = new TicketStorage();

	// Эту кучу параметров можно запихнуть в один transfer object и передвавать
	// его
	// так в случае если один из параметров изменится не придется менять
	// интерфейс сервисов
	@WebMethod
	public int reserveTicket(@WebParam(name = "dataTransfer") DataTransfer dataTransfer) {
		TicketCreation ticketCreation = new TicketCreation();
		Person person = new Person(dataTransfer.getName(), dataTransfer.getLastName(), dataTransfer.getPatronymicName(),
				dataTransfer.getBirthDate());
		Ticket ticket = ticketCreation.initTicket(person, dataTransfer.getDepartCity(), dataTransfer.getArrivalCity(),
				dataTransfer.getDepartDate(), dataTransfer.getArrivalDate(), dataTransfer.getBirthDate());
		tickets.getTickets().put(ticket.getTicketNum(), ticket);
		return 1;
	}

	// грамотнее было бы венести такую инициализацю тикета в отдельный сервис
	@WebMethod
	public Ticket getTicketUsingNum(@WebParam(name = "ticketNum") int ticketNum) throws NotTicketFoundException {
		// тут нет смысла создавать объект Ticket. Мы же планируем его получить
		// из хранилища
		// это должно выглядеть так: ticketsServie.get(ticketNum) и он либо
		// возвращает билет, либо ошибку
		if (tickets.getTickets().get(ticketNum) == null) {
			throw new NotTicketFoundException();
		}
		// нет проверки, что билета с таким номером может не быть
		return tickets.getTickets().get(ticketNum);
	}

	@WebMethod
	// аналогично предыдцщему замечанию. Не стоит раскрывать реализацию твоего
	// TicketStorage
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
		// тут все те же самые замечания
		if (tickets.getTickets().get(ticketNum) != null) {
			// enum сравнивается без equals
			// опять же нет никаких проверок есть ли такой билет, оплачен ли он
			// уже

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
