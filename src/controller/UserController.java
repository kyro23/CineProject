package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.UserModel;

public class UserController {
	
	public void create(UserModel user) {

		Connection con = ConnectionFactory.getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO users (username, pass, privilegeLevel) VALUES (?, ?, ?)");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPass());
			stmt.setInt(3, user.getPrivilegeLevel());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void update(UserModel user) {
		Connection con = ConnectionFactory.getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE users SET username = ?, pass = ?, privilegeLevel = ? WHERE id = ?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPass());
			stmt.setInt(3, user.getPrivilegeLevel());
			stmt.setInt(4, user.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<UserModel> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<UserModel> users = new ArrayList<>(); 
	
		try {
			stmt = con.prepareStatement("SELECT * FROM users");
			rs = stmt.executeQuery();
			while(rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPass(rs.getString("pass"));
				user.setPrivilegeLevel(rs.getInt("privilegeLevel"));
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return users;
	}
	
	public void delete(UserModel user) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM users WHERE id = ?");
			stmt.setInt(1, user.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public boolean login(UserModel user) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		boolean check = false;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND pass = ?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPass());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return check;
	}

	public UserModel findUser(UserModel user) {
		UserModel userFinded = new UserModel();
		Connection con = ConnectionFactory.getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM users WHERE username = ?");
			stmt.setString(1, user.getUsername());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				userFinded.setId(rs.getInt("id"));
				userFinded.setUsername(rs.getString("username"));
				userFinded.setPrivilegeLevel(rs.getInt("privilegeLevel"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return userFinded;
		
	}
}
