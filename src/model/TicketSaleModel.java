package model;

public class TicketSaleModel {
	/*
	 * PAY ATTENTION
	 * this class is the registry for ONE ticket
	 * for this will be generated one pdf to impress the ticket
	 */
	
	private int id;
	private String type;
	private double value;
	private int ticketId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

}
