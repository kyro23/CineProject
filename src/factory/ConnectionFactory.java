package factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
 
public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static String url;
	private static String username;
	private static String password;
	
	public static Connection getConnection() {
		url = "jdbc:mysql://"+getHost()+":"+getPort()+"/cinema";
		username = getUsername();
		password = getPassword();
		
		if(password == null) {
			password = "";
		}
		
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(url, username, password);
		}catch(ClassNotFoundException | SQLException ex) {
			JOptionPane.showMessageDialog(null, "Não é possivel estabelecer conexão com o banco de dados, favor contate o administrador");
			throw new RuntimeException("DEU MERDA:", ex);
		}
	}
	
	public static void closeConnection (Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		closeConnection(con, stmt);
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public boolean testConnection(String url, String login, String password ) {
		
		boolean test = false;
		Connection testCon = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			testCon = DriverManager.getConnection(url, login, password);
			if(testCon != null) {
				test = true;
				testCon.close();
			}else {
				test = false;
			}
			
		}catch(ClassNotFoundException | SQLException ex) {
			test = false;
		}

		return test;
	}
	
	private static String getHost() {
		String host = null;
		try {
			Properties prop = SystemFactory.getProp();
			host = prop.getProperty("prop.server.host");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return host;
	}
	
	private static String getPort() {
		String port = null;
		try {
			Properties prop = SystemFactory.getProp();
			port = prop.getProperty("prop.server.port");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return port;
	}
	
	private static String getUsername() {
		String usernameProp = null;
		try {
			Properties prop = SystemFactory.getProp();
			usernameProp = prop.getProperty("prop.server.login");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usernameProp;
	}
	
	private static String getPassword() {
		String passwordProp = null;
		try {
			Properties prop = SystemFactory.getProp();
			passwordProp = prop.getProperty("prop.server.password");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return passwordProp;
	}
}
