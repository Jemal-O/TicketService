package objects;

import java.util.Calendar;

public class Ticket {

	private static int lastTicketNum = 1;
	private float defaultPrice = 1000;
	private int ticketNum;
	private String departCity;
	private String arrivalCity;
	private Calendar departDate;
	private Calendar arrivalDate;
	private float ticketPrice;
//	Если уж делать поле с валютой, то делать это правильно через Enum
	private String currency = "RUR";
	private TicketStatus ticketStatus;
	private Person person;

//    как портатип ок. Но в идеале было бы, чтобы тикет не сам себя инициализировал, а былы бы некая фабрика тикетов.
//    она бы как раз и знала, с каким id, статусом и т.п. создавать объект
	public int getNewNum() {
		return ticketNum = lastTicketNum++;
	}

	public String getCurrency() {
		return currency;
	}

	public Calendar getArrivalDate() {
		return arrivalDate;
	}


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getDepartCity() {
		return departCity;
	}

	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Calendar getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Calendar departDate) {
		this.departDate = departDate;
	}

	public Calendar getArrivalDateTime() {
		return arrivalDate;
	}

	public void setArrivalDate(Calendar arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public float getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(float defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

}
