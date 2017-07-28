package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://192.168.0.4:3306/cinema";
	private static final String USERNAME = "DBMananger";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
}
