package objects;

public enum TicketStatus {
	reserve("reserve"), isPaid("is paid");

	private String description;

	TicketStatus(String description) {
		this.description=description;
	}

	public String getDescription() {
		return description;
	}


}
