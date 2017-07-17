package model;

import factory.SystemFactory;

public class UserModel {
	private int id;
	private String username;
	private String pass;
	private int privilegeLevel;
	private SystemFactory sys = new SystemFactory();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getPrivilegeLevel() {
		return privilegeLevel;
	}
	public void setPrivilegeLevel(int privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String password) {
		this.pass = sys.hashPass(password);
	}
	
	
}
