package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.FilmModel;

public class FilmController {

	public void Create(FilmModel film) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO film (title, actors, sinopse, genere, duraction, directors, classIndicative) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getActors());
			stmt.setString(3, film.getSinopse());
			stmt.setString(4, film.getGenere());
			stmt.setInt(5, film.getDuraction());
			stmt.setString(6, film.getDirectors());
			stmt.setString(7, film.getClassindicative());
			
			stmt.executeUpdate();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}	
	}
	
	
	public List<FilmModel> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<FilmModel> films = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM film");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FilmModel film = new FilmModel();
				
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setActors(rs.getString("actors"));
				film.setSinopse(rs.getString("sinopse"));
				film.setGenere(rs.getString("genere"));
				film.setDuraction(rs.getInt("duraction"));
				film.setDirectors(rs.getString("directors"));
				film.setClassindicative(rs.getString("classIndicative"));
				
				films.add(film);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return films;
	}
	
	public void update(FilmModel film) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE film SET title = ?, actors = ?, sinopse = ?, genere = ?, duraction = ?, directors = ?, classIndicative = ? WHERE id = ?");
			
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getActors());
			stmt.setString(3, film.getSinopse());
			stmt.setString(4, film.getGenere());
			stmt.setInt(5, film.getDuraction());
			stmt.setString(6, film.getDirectors());
			stmt.setString(7, film.getClassindicative());
			stmt.setInt(8, film.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(FilmModel film) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM film WHERE id = ?");
			stmt.setInt(1, film.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public FilmModel findById(int id) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		FilmModel film = new FilmModel();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM film WHERE id = ?");
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setActors(rs.getString("actors"));
				film.setSinopse(rs.getString("sinopse"));
				film.setGenere(rs.getString("genere"));
				film.setDuraction(rs.getInt("duraction"));
				film.setDirectors(rs.getString("directors"));
				film.setClassindicative(rs.getString("classIndicative"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return film;
	}
}
