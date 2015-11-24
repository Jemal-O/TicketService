package objects;

import java.util.Calendar;

public class Ticket {
	private int lastTicketNum = 1;
	private float defaultPrice = 1000;
	private int ticketNum;
	private String departCity;
	private String arrivalCity;
	private Calendar departDate;
	private Calendar arrivalDate;
	private float ticketPrice;
	private String currency = "RUR";
	private TicketStatus ticketStatus;
	private Person person;
	
	
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
