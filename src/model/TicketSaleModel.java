package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controller.FilmController;
import controller.FilmSessionController;
import controller.RoomController;
import controller.TicketsOnSaleController;
import factory.SystemFactory;

public class TicketSaleModel {
	/*
	 * PAY ATTENTION
	 * this class is the registry for ONE ticket
	 * for this will be generated one pdf to impress the ticket
	 */
	
	private int id;
	private String type;
	private double value;
	private int ticketId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public void generateTicket(List<TicketSaleModel> ticketsList, double total) {
		Document ticketPdf = new Document();
		SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfH = new SimpleDateFormat("hh:mm");
		
		SimpleDateFormat sdfDay = new SimpleDateFormat("ddMMyyyyhhmmss");
		
		try {
			Properties prop = SystemFactory.getProp();
			String folderToGenerate = prop.getProperty("prop.system.ticketout");
			
			java.util.Date soldDay = new java.util.Date();
			String soldDayStr = sdfDay.format(soldDay);
			
			PdfWriter.getInstance(ticketPdf, new FileOutputStream(folderToGenerate+"/ingresso"+soldDayStr+".pdf"));
			
			
			ticketPdf.open();
			
			ticketPdf.add(new Paragraph("CineProject ltda"));
			ticketPdf.add(new Paragraph("Total da compra: "+total));
			for(int k = 0; k < ticketsList.size(); k++) {
				int ticketId = ticketsList.get(k).getTicketId();
				
				TicketsOnSaleController ticketOnSaleCtrl = new TicketsOnSaleController();
				FilmSessionController sessionController = new FilmSessionController();
				FilmController filmController = new FilmController();
				RoomController roomController = new RoomController();
				
				TicketsOnSaleModel ticketOnSaleModel = ticketOnSaleCtrl.findById(ticketId);
				FilmSessionModel sessionModel = sessionController.findById(ticketOnSaleModel.getSessionId());
				FilmModel filmModel = filmController.findById(sessionModel.getFilm());
				RoomModel roomModel = roomController.findById(sessionModel.getRoom());
				
				
				String priece = Double.toString(ticketsList.get(k).getValue()).replace('.', ',');
				String ticketType = ticketsList.get(k).getType();
				String filmTitle = filmModel.getTitle();
				String room = Integer.toString(roomModel.getRoomNumer());
				String typeFilm = sessionModel.getType();
				String dimension = sessionModel.getDimension();
				
				Date date = new Date(sessionModel.getDay().getTime());
				Date hour = new Date(sessionModel.getHour().getTime());
				
				String day = sdfD.format(date);
				String hourTicket = sdfH.format(hour);				

				ticketPdf.add(new Paragraph("==============================="));
				ticketPdf.add(new Paragraph("Filme: "+filmTitle+" "+typeFilm+" "+dimension));
				ticketPdf.add(new Paragraph("Tipo de entrada: "+ticketType));
				ticketPdf.add(new Paragraph("Preço: "+priece));
				ticketPdf.add(new Paragraph("Sala: "+room));
				ticketPdf.add(new Paragraph("Dia: "+day+" ás "+hourTicket));
				
				
				
			}
			
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ticketPdf.close();
		}
	}
}
