package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class SystemFactory {
	public String hashPass(String pass) {
		String md5Hash = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte messageDigest[] = md.digest(pass.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			
			for(byte b:messageDigest) {
				sb.append(String.format("%02X", 0xFF & b));
			}
			String passHex = sb.toString();
			md5Hash = passHex;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return md5Hash;
	}
	
	public static Properties getProp() throws IOException{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("./properties/config.properties");
		
		prop.load(file);
		return prop;
	}
	
	public static Properties getDefaultProperties() throws IOException{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("./properties/default.properties");
		
		prop.load(file);
		return prop;
	}
	
	public boolean verifyConnection(String url, String login, String password) {
		ConnectionFactory conFact = new ConnectionFactory();
		return conFact.testConnection(url, login, password);
	}
}
