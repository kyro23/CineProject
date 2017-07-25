package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.FilmSessionModel;

public class FilmSessionController {

	public void create(FilmSessionModel session) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO filmsession (film, day, hour, room, type, dimension, sessionStatus) VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, session.getFilm());
			stmt.setDate(2, session.getDay());
			stmt.setTime(3, session.getHour());
			stmt.setInt(4, session.getRoom());
			stmt.setString(5, session.getType());
			stmt.setString(6, session.getDimension());
			stmt.setString(7, session.getSessionStatus());
			
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<FilmSessionModel> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<FilmSessionModel> sessions = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM filmsession");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FilmSessionModel session = new FilmSessionModel();
				session.setId(rs.getInt("id"));
				session.setFilm(rs.getInt("film"));
				session.setDay(rs.getDate("day"));
				session.setHour(rs.getTime("hour"));
				session.setRoom(rs.getInt("room"));
				session.setType(rs.getString("type"));
				session.setDimension(rs.getString("dimension"));
				session.setSessionStatus(rs.getString("sessionStatus"));
				
				sessions.add(session);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return sessions;
	}
	
	public void update(FilmSessionModel session) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE filmsession SET film = ?, day = ?, hour = ?, room = ?, type = ?, dimension = ?, sessionStatus = ? WHERE id = ?");
			stmt.setInt(1, session.getFilm());
			stmt.setDate(2, session.getDay());
			stmt.setTime(3, session.getHour());
			stmt.setInt(4, session.getRoom());
			stmt.setString(5, session.getType());
			stmt.setString(6, session.getDimension());
			stmt.setString(7, session.getSessionStatus());
			stmt.setInt(8, session.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(FilmSessionModel session) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM filmsession WHERE id = ?");
			stmt.setInt(1, session.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	
	public List<FilmSessionModel> findFilms(int film){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<FilmSessionModel> sessions = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM filmsession WHERE film = ?");
			stmt.setInt(1, film);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FilmSessionModel session = new FilmSessionModel();
				session.setId(rs.getInt("id"));
				session.setFilm(rs.getInt("film"));
				session.setDay(rs.getDate("day"));
				session.setHour(rs.getTime("hour"));
				session.setRoom(rs.getInt("room"));
				session.setType(rs.getString("type"));
				session.setDimension(rs.getString("dimension"));
				session.setSessionStatus(rs.getString("sessionStatus"));
				
				sessions.add(session);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return sessions;
	}
	
	public List<FilmSessionModel> findSessionByRoom(int room){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<FilmSessionModel> sessions = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM filmsession WHERE room = ?");
			stmt.setInt(1, room);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmSessionModel session = new FilmSessionModel();
				
				session.setId(rs.getInt("id"));
				session.setFilm(rs.getInt("film"));
				session.setDay(rs.getDate("day"));
				session.setHour(rs.getTime("hour"));
				session.setRoom(rs.getInt("room"));
				session.setType(rs.getString("type"));
				session.setDimension(rs.getString("dimension"));
				session.setSessionStatus(rs.getString("sessionStatus"));
				
				sessions.add(session);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return sessions;
	}
	
	public FilmSessionModel findById(int id) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		FilmSessionModel session = new FilmSessionModel();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM filmsession WHERE id = ?");
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				session.setId(rs.getInt("id"));
				session.setFilm(rs.getInt("film"));
				session.setDay(rs.getDate("day"));
				session.setHour(rs.getTime("hour"));
				session.setRoom(rs.getInt("room"));
				session.setType(rs.getString("type"));
				session.setDimension(rs.getString("dimension"));
				session.setSessionStatus(rs.getString("sessionStatus"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session;
	}
}
