package model;

import java.sql.Date;
import java.sql.Time;


public class FilmSessionModel {
	private int id;
	private int film;
	private Date day;;
	private Time hour;
	private int room;
	private String type;
	private String dimension;
	private String sessionStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFilm() {
		return film;
	}
	public void setFilm(int film) {
		this.film = film;
	}
	
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	
	public Time getHour() {
		return hour;
	}
	public void setHour(Time hour) {
		this.hour = hour;
	}
	
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSessionStatus() {
		return sessionStatus;
	}
	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
}
