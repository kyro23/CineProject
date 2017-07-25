package model;

public class TicketsOnSaleModel {
	private int id;
	private int sessionId;
	private double priece;
	private int quantity;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public double getPriece() {
		return priece;
	}
	public void setPriece(double priece) {
		this.priece = priece;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
