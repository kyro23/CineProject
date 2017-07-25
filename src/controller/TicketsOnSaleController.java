package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.TicketsOnSaleModel;

public class TicketsOnSaleController {

	public void create(TicketsOnSaleModel ticketsSale) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO ticketsonsale (sessionId, priece, quantity) values (?, ?, ?)");
			stmt.setInt(1, ticketsSale.getSessionId());
			stmt.setDouble(2, ticketsSale.getPriece());
			stmt.setInt(3, ticketsSale.getQuantity());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<TicketsOnSaleModel> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<TicketsOnSaleModel> tickets = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM ticketsonsale");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TicketsOnSaleModel ticket = new TicketsOnSaleModel();
				
				ticket.setId(rs.getInt("id"));
				ticket.setSessionId(rs.getInt("sessionId"));
				ticket.setPriece(rs.getDouble("priece"));
				ticket.setQuantity(rs.getInt("quantity"));
				
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
	
	public void update(TicketsOnSaleModel ticket) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE ticketsonsale SET sessionId = ?, priece = ?, quantity = ? WHERE id = ?");
			stmt.setInt(1, ticket.getSessionId());
			stmt.setDouble(2, ticket.getPriece());
			stmt.setInt(3, ticket.getQuantity());
			stmt.setInt(4, ticket.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(TicketsOnSaleModel ticket) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM ticketsonsale WHERE id = ?");
			stmt.setInt(1, ticket.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
