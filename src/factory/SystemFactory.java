package factory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
