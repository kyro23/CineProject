package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.FilmModel;
import model.FilmSessionModel;
import model.RoomModel;
import model.TicketSaleModel;
import model.TicketsOnSaleModel;
import model.UserModel;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FilmController;
import controller.FilmSessionController;
import controller.RoomController;
import controller.TicketSaleController;
import controller.TicketsOnSaleController;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ViewEmployerBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblSessions;
	private JTable tblTickets;
	private JComboBox<Object> cbFilm;
	private JComboBox<Object> cbSession;
	private JLabel lblValue;
	private JLabel lblTotal;
	private JComboBox<Object> cbType;
	
	private int m = 0;
	

	/**
	 * Create the frame.
	 */
	public ViewEmployerBoard(UserModel currentUser) {
		setTitle("Cinema - Vendas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewEmployerBoard.class.getResource("/images/cine ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Sess\u00F5es", new ImageIcon(ViewEmployerBoard.class.getResource("/images/film.png")), panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tblSessions = new JTable();
		tblSessions.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", null, null, null, null, null, null, null},
			},
			new String[] {
				"id", "Filme", "Dia", "Hor\u00E1rio", "Sala", "Tipo", "Dimens\u00E3o", "Status", "Quantidade"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSessions.getColumnModel().getColumn(0).setResizable(false);
		tblSessions.getColumnModel().getColumn(1).setResizable(false);
		tblSessions.getColumnModel().getColumn(2).setResizable(false);
		tblSessions.getColumnModel().getColumn(3).setResizable(false);
		tblSessions.getColumnModel().getColumn(4).setResizable(false);
		tblSessions.getColumnModel().getColumn(5).setResizable(false);
		tblSessions.getColumnModel().getColumn(6).setResizable(false);
		tblSessions.getColumnModel().getColumn(7).setResizable(false);
		tblSessions.getColumnModel().getColumn(8).setResizable(false);
		scrollPane.setViewportView(tblSessions);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ingressos", new ImageIcon(ViewEmployerBoard.class.getResource("/images/tickectIcon.png")), panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Adicionar Ingresso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(364, 0, 358, 256);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		cbFilm = new JComboBox<Object>();

		cbFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m++;
				if(m > 1) {
					fillCbSession();
				}
			}
		});
		cbFilm.setBounds(135, 59, 139, 20);
		panel_2.add(cbFilm);
		fillCbFilm();
		
		JLabel label_2 = new JLabel("Filme");
		label_2.setBounds(79, 62, 46, 14);
		panel_2.add(label_2);
		
		cbType = new JComboBox<Object>();
		cbType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbFilm.getSelectedItem().toString() != "Selecione um sessão se o filme estiver selecionado") {
					
					
				}
			}
		});
		cbType.setModel(new DefaultComboBoxModel<Object>(new String[] {"Inteira", "Meia"}));
		cbType.setBounds(135, 118, 139, 20);
		cbType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cbSession.getSelectedItem() != "Selecione a sessão") {
					updateLblValue(Integer.parseInt(cbSession.getSelectedItem().toString()), cbType.getSelectedItem().toString());
				}
			}
		});
		panel_2.add(cbType);
		
		JLabel label = new JLabel("Tipo");
		label.setBounds(79, 121, 46, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Sess\u00E3o");
		label_1.setBounds(79, 93, 46, 14);
		panel_2.add(label_1);
		
		cbSession = new JComboBox<Object>();
		cbSession.setBounds(135, 90, 139, 20);
		panel_2.add(cbSession);
		fillCbSession();		
		cbSession.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cbSession.getSelectedItem() != null) {
					if(cbSession.getSelectedItem() != "Selecione a sessão") {
						updateLblValue(Integer.parseInt(cbSession.getSelectedItem().toString()), cbType.getSelectedItem().toString());
					}
				}
			}
		});

		
		JLabel lblValor = new JLabel("Valor: R$ ");
		lblValor.setBounds(135, 149, 56, 14);
		panel_2.add(lblValor);
		
		lblValue = new JLabel("0,00");
		lblValue.setBounds(201, 149, 46, 14);
		panel_2.add(lblValue);
		
		JButton btnAddTicket = new JButton("Adicionar Ingresso");
		btnAddTicket.setBounds(104, 206, 170, 23);
		btnAddTicket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cbFilm.getSelectedItem().toString() != "Escolha o filme" && cbSession.getSelectedItem().toString() != "Selecione a sessão") {
					String film = cbFilm.getSelectedItem().toString();
					int session = Integer.parseInt(cbSession.getSelectedItem().toString());
					String priece = lblValue.getText();
					String type = cbType.getSelectedItem().toString();
					
					updateTableTickets(film, session, priece, type);
				}
			}
		});
		panel_2.add(btnAddTicket);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Ingresssos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 0, 354, 256);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		tblTickets = new JTable();
		tblTickets.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Filme", "Sess\u00E3o", "Tipo", "Valor"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblTickets.getColumnModel().getColumn(0).setResizable(false);
		tblTickets.getColumnModel().getColumn(1).setResizable(false);
		tblTickets.getColumnModel().getColumn(2).setResizable(false);
		tblTickets.getColumnModel().getColumn(3).setResizable(false);
		scrollPane_1.setViewportView(tblTickets);
		
		JButton btnFinalize = new JButton("Finalizar venda");
		btnFinalize.setBounds(225, 309, 129, 23);
		btnFinalize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblTickets.getRowCount() != 0) {
					int checkFinalize = JOptionPane.showConfirmDialog(null, "Finalizar Venda?");
					if(checkFinalize == 0) {
						finalizeSold();
					}
				}
			}
		});
		panel_1.add(btnFinalize);
		
		JLabel lblValorTotal = new JLabel("Valor total: R$");
		lblValorTotal.setBounds(274, 267, 80, 14);
		panel_1.add(lblValorTotal);
		
		lblTotal = new JLabel("0,00");
		lblTotal.setBounds(364, 267, 46, 14);
		panel_1.add(lblTotal);
		
		JButton btnCleanAll = new JButton("Limpar tudo");
		btnCleanAll.setBounds(364, 309, 110, 23);
		btnCleanAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cleanAll();
			}
		});
		panel_1.add(btnCleanAll);
		
		JButton btnRemoverSelecionado = new JButton("Remover Selecionado");
		btnRemoverSelecionado.setBounds(10, 263, 178, 23);
		btnRemoverSelecionado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblTickets.getSelectedRow() != -1) {
					DefaultTableModel tModel = (DefaultTableModel) tblTickets.getModel();
					tModel.removeRow(tblTickets.getSelectedRow());
					JOptionPane.showMessageDialog(null, "Ingresso removido com sucesso!");
					updateLblTotal();;
				}
			}
		});
		panel_1.add(btnRemoverSelecionado);
		
		updateSessionTable();
	}
	
	private void updateSessionTable() {
		SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
		
		FilmController filmController = new FilmController();
		RoomController roomController = new RoomController();
		TicketsOnSaleController ticketController = new TicketsOnSaleController();
		FilmSessionController sessionController = new FilmSessionController();
		
		DefaultTableModel model = (DefaultTableModel) tblSessions.getModel();
		model.setNumRows(0);
		

		for(TicketsOnSaleModel ticket : ticketController.read()) {
			FilmSessionModel session = sessionController.findById(ticket.getSessionId());
			int filmId = session.getFilm();
			
			Date getDay = session.getDay();
			String day = sdfD.format(getDay);
			
			Date getHour = session.getHour();
			String hour = sdfH.format(getHour);
			
			int roomId = session.getRoom();
			String type = session.getType();
			String dimension = session.getDimension();
			String sessionStatus = session.getSessionStatus();
			
			if(!sessionStatus.equals("Encerrada")) {
				FilmModel film = filmController.findById(filmId);
				RoomModel room = roomController.findById(roomId);
				
				int quantity = ticket.getQuantity();
				int id = ticket.getId();
				
				model.addRow(new Object[] {
						id,
						film,
						day,
						hour,
						room,
						type,
						dimension,
						sessionStatus,
						quantity
				});
			}
		}
	}
	
	private void fillCbFilm() {
		TicketsOnSaleController ticketController = new TicketsOnSaleController();
		FilmSessionController sessionController = new FilmSessionController();
		FilmController filmController = new FilmController();
		
		cbFilm.removeAllItems();
		cbFilm.addItem("Escolha o filme");
		
		List<FilmModel> films = new ArrayList<>();
		
		for(TicketsOnSaleModel ticketModel : ticketController.read()) {
		
			FilmSessionModel sessionModel = sessionController.findById(ticketModel.getSessionId());
			FilmModel filmModel = filmController.findById(sessionModel.getFilm());
		
			if(!sessionModel.getSessionStatus().equals("Encerrada")) {
				films.add(filmModel);	
			}
		}
		
		for(int i = 0; i < films.size(); i++) {
			cbFilm.addItem(films.get(i));
			if(i != 0) {
			
				if(films.get(i).toString().equals(films.get(i-1).toString())) {
					cbFilm.removeItem(films.get(i));
				}
			}
		}
	}
	
	private void fillCbSession() {
		cbSession.removeAllItems();
		cbSession.addItem("Selecione a sessão");
		if(cbFilm.getSelectedItem() != "Escolha o filme") {
			FilmSessionController sessionController = new FilmSessionController();
			TicketsOnSaleController ticketController = new TicketsOnSaleController();
			
			FilmModel film = (FilmModel) cbFilm.getSelectedItem();
			
			
			for(FilmSessionModel sessionModel : sessionController.findFilms(film.getId())) {
				TicketsOnSaleModel ticketModel = ticketController.findBySession(sessionModel);
				cbSession.addItem(ticketModel);
			}
		}
	}
	
	private void updateLblValue(int session, String type){
		TicketsOnSaleController ticketController = new TicketsOnSaleController();
		TicketsOnSaleModel ticketModel = ticketController.findById(session);
		Double value = ticketModel.getPriece();
		
		if(type.equals("Meia")) {
			value /= 2;
		}
		
		lblValue.setText(value.toString().replace('.', ','));
	}
	
	private void updateTableTickets(String film, int session, String priece, String type) {		
		TicketsOnSaleController ticketController = new TicketsOnSaleController();
		
		TicketsOnSaleModel ticketModel = ticketController.findById(session);
		DefaultTableModel tModel = (DefaultTableModel) tblTickets.getModel();
		
		int quantity = ticketModel.getQuantity();
		
		if(tblTickets.getRowCount() < quantity) {
			tModel.addRow(new Object[] {
					film,
					session,
					type,
					priece
			});
		}else {
			JOptionPane.showMessageDialog(null, "A quantidade de ingressos excede o limite da sala.");
		}
		updateLblTotal();
	}
	
	private void updateLblTotal() {
		Double currentPriece = 0.0;
		for(int i = 0; i < tblTickets.getRowCount(); i++) {
			currentPriece += Double.parseDouble(tblTickets.getValueAt(i, 3).toString().replace(',',  '.'));
		}
		
		lblTotal.setText(currentPriece.toString().replace('.', ','));
	}
	
	private void finalizeSold() {
		TicketSaleController saleController = new TicketSaleController();
		TicketSaleModel saleModel = new TicketSaleModel();
		FilmSessionController sessionController = new FilmSessionController();
		TicketsOnSaleController ticketController = new TicketsOnSaleController();
		
		List<TicketSaleModel> ticketsList = new ArrayList<>();
		
		for(int i = 0; i < tblTickets.getRowCount(); i++) {
			TicketsOnSaleModel ticket = ticketController.findById(Integer.parseInt(tblTickets.getValueAt(i, 1).toString()));
			FilmSessionModel sessionModel= sessionController.findById(ticket.getSessionId());
			String type = tblTickets.getValueAt(i, 2).toString();
			double value = Double.parseDouble(tblTickets.getValueAt(i, 3).toString().replace(',', '.'));
			
			int quantity = ticket.getQuantity() - 1;
			ticket.setQuantity(quantity);
			if(ticket.getQuantity() == 0) {
				sessionModel.setSessionStatus("Encerrada");
				sessionController.update(sessionModel);
			}
			
			saleModel.setTicketId(ticket.getId());
			saleModel.setType(type);
			saleModel.setValue(value);
			
			saleController.create(saleModel);
			ticketController.update(ticket);
			
			ticketsList.add(saleModel);
		}
		double totalValue = Double.parseDouble(lblTotal.getText().replace(',', '.'));
		saleModel.generateTicket(ticketsList, totalValue);
		
		JOptionPane.showMessageDialog(null, "Vendido!");
		cleanAll();
		updateSessionTable();
	}
	
	private void cleanAll() {
		cbFilm.setSelectedIndex(0);
		cbSession.setSelectedIndex(0);
		cbType.setSelectedIndex(0);
		lblValue.setText("0,0");
		lblTotal.setText("0,0");
		
		int cleanTable = JOptionPane.showConfirmDialog(null, "Deseja zerar tabela?");
		if(cleanTable == 0) {
			DefaultTableModel tModel =(DefaultTableModel) tblTickets.getModel();
			tModel.setNumRows(0);
		}
	}
}
