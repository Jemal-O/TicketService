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
	private Storage tickets = new TicketStorage();
	private TicketCreation ticketCreation = new TicketCreation();

	// Эту кучу параметров можно запихнуть в один transfer object и передвавать
	// его
	// так в случае если один из параметров изменится не придется менять
	// интерфейс сервисов
	@WebMethod
	public int reserveTicket(@WebParam(name = "dataTransfer") DataTransfer dataTransfer) {
//		это должен быть полем метода, чтобы не создавать объект каждый раз
		
		Person person = new Person(dataTransfer.getName(), dataTransfer.getLastName(), dataTransfer.getPatronymicName(),
				dataTransfer.getBirthDate());
		Ticket ticket = ticketCreation.initTicket(person, dataTransfer.getDepartCity(), dataTransfer.getArrivalCity(),
				dataTransfer.getDepartDate(), dataTransfer.getArrivalDate(), dataTransfer.getBirthDate());
		tickets.setTicket(ticket);
//		судя по коду клиента, этот метод должен возвращать номер билета. А тут 1
		return ticket.getTicketNum();
	}

	// грамотнее было бы венести такую инициализацю тикета в отдельный сервис
	@WebMethod
	public Ticket getTicketUsingNum(@WebParam(name = "ticketNum") int ticketNum) throws NotTicketFoundException {
		// тут нет смысла создавать объект Ticket. Мы же планируем его получить
		// из хранилища
		// это должно выглядеть так: ticketsServie.get(ticketNum) и он либо
		// возвращает билет, либо ошибку
		if (tickets.getTicket(ticketNum) == null) {
			throw new NotTicketFoundException();
		}
		// нет проверки, что билета с таким номером может не быть
		return tickets.getTicket(ticketNum);
	}

	@WebMethod
	// аналогично предыдцщему замечанию. Не стоит раскрывать реализацию твоего
	// TicketStorage
	public boolean returnTicket(@WebParam(name = "ticketNum") int ticketNum) {
		if (tickets.getTicket(ticketNum) != null) {
			tickets.removeTicket(ticketNum);
			return true;
		}
		return false;
	}

	@WebMethod
	public Ticket payTicket(@WebParam(name = "ticketNum") int ticketNum)
			throws IsPaidException, NotTicketFoundException {
		// тут все те же самые замечания
		if (tickets.getTicket(ticketNum) != null) {
			// enum сравнивается без equals
			// опять же нет никаких проверок есть ли такой билет, оплачен ли он
			// уже

			if (tickets.getTicket(ticketNum).getTicketStatus() != TicketStatus.IS_PAID) {
				tickets.getTicket(ticketNum).setTicketStatus(TicketStatus.IS_PAID);
			} else {
				throw new IsPaidException();
			}
		} else {
			throw new NotTicketFoundException();
		}
		return tickets.getTicket(ticketNum);
	}
}
