package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.TicketSaleModel;

public class TicketSaleController {
	/*
	 * PAY ATTENTION
	 * this class is the registry for ONE ticket
	 * for this will be generated one pdf to impress the ticket
	 */
	
	public void create(TicketSaleModel sale) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO ticketSale (type, value, ticketId) VALUES (?, ?, ?)");
			stmt.setString(1, sale.getType());
			stmt.setDouble(2, sale.getValue());
			stmt.setInt(3, sale.getTicketId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<TicketSaleModel> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<TicketSaleModel> tickets = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM ticketSale");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TicketSaleModel ticket = new TicketSaleModel();
				ticket.setId(rs.getInt("id"));
				ticket.setType(rs.getString("type"));
				ticket.setValue(rs.getDouble("value"));
				ticket.setTicketId(rs.getInt("ticketId"));
				
				tickets.add(ticket);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return tickets;
	}
	
	public void update(TicketSaleModel sale) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE ticketSale set type = ?, value = ?, ticketId = ? WHERE id = ?");
			stmt.setString(1, sale.getType());
			stmt.setDouble(2, sale.getValue());
			stmt.setInt(3, sale.getTicketId());
			stmt.setInt(4, sale.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(TicketSaleModel sale) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM ticketSale WHERE id = ?");
			stmt.setInt(1, sale.getId());
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
