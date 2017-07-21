package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import model.RoomModel;

public class RoomController {
	public void create(RoomModel room) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO cineroom (capacity, status, roomNumber) VALUES (?, ?, ?)");
			stmt.setInt(1, room.getCapacity());
			stmt.setString(2, room.getStatus());
			stmt.setInt(3, room.getRoomNumer());
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao inserir sala.");
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<RoomModel> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<RoomModel> rooms = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cineroom");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				RoomModel room = new RoomModel();
				room.setId(rs.getInt("id"));
				room.setCapacity(rs.getInt("capacity"));
				room.setStatus(rs.getString("status"));
				room.setRoomNumer(rs.getInt("roomNumber"));
				
				rooms.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return rooms;
	}
	
	public void update(RoomModel room) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE cineroom SET capacity = ?, status = ?, roomNumber = ? WHERE id = ?");
			stmt.setInt(1, room.getCapacity());
			stmt.setString(2, room.getStatus());
			stmt.setInt(3, room.getRoomNumer());
			stmt.setInt(4, room.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(RoomModel room) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM cineroom WHERE id = ?");
			stmt.setInt(1, room.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public RoomModel findById(int id){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		RoomModel room = new RoomModel();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cineroom WHERE id = ?");
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				room.setId(rs.getInt("id"));
				room.setCapacity(rs.getInt("capacity"));
				room.setStatus(rs.getString("status"));
				room.setRoomNumer(rs.getInt("roomNumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return room;
	}
}
