package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.FilmModel;
import model.FilmSessionModel;
import model.RoomModel;
import model.TicketsOnSaleModel;
import model.UserModel;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FilmController;
import controller.FilmSessionController;
import controller.RoomController;
import controller.TicketsOnSaleController;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

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
				{"", null, null, null, null, null, null, null},
			},
			new String[] {
				"Filme", "Dia", "Hor\u00E1rio", "Sala", "Tipo", "Dimens\u00E3o", "Status", "Quantidade"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true
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
		cbFilm.setBounds(135, 59, 139, 20);
		panel_2.add(cbFilm);
		fillCbFilm();
		
		JLabel label_2 = new JLabel("Filme");
		label_2.setBounds(79, 62, 46, 14);
		panel_2.add(label_2);
		
		JComboBox<Object> cbType = new JComboBox<Object>();
		cbType.setBounds(135, 118, 139, 20);
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
		

		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(145, 149, 46, 14);
		panel_2.add(lblValor);
		
		JLabel lblValue = new JLabel("R$ 0,00");
		lblValue.setBounds(201, 149, 46, 14);
		panel_2.add(lblValue);
		
		JButton btnAdicionarIngresso = new JButton("Adicionar Ingresso");
		btnAdicionarIngresso.setBounds(135, 283, 139, 23);
		panel_2.add(btnAdicionarIngresso);
		
		JButton btnAddTicket = new JButton("Adicionar Ingresso");
		btnAddTicket.setBounds(104, 206, 170, 23);
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
				{null, null, null, null},
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
		
		JButton btlFinalize = new JButton("Finalizar Compra");
		btlFinalize.setBounds(225, 309, 129, 23);
		panel_1.add(btlFinalize);
		
		JLabel lblValorTotal = new JLabel("Valor total:");
		lblValorTotal.setBounds(282, 267, 72, 14);
		panel_1.add(lblValorTotal);
		
		JLabel lblTotal = new JLabel("R$ 0,00");
		lblTotal.setBounds(374, 267, 46, 14);
		panel_1.add(lblTotal);
		
		JButton btnCleanAll = new JButton("Limpar tudo");
		btnCleanAll.setBounds(364, 309, 110, 23);
		panel_1.add(btnCleanAll);
		
		JButton btnRemoverSelecionado = new JButton("Remover Selecionado");
		btnRemoverSelecionado.setBounds(10, 263, 178, 23);
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
			
			FilmModel film = filmController.findById(filmId);
			RoomModel room = roomController.findById(roomId);
			
			int quantity = ticket.getQuantity();
			
			model.addRow(new Object[] {
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
	
	private void fillCbFilm() {
		TicketsOnSaleController ticketController = new TicketsOnSaleController();
		FilmSessionController sessionController = new FilmSessionController();
		FilmController filmController = new FilmController();
		cbFilm.removeAllItems();
		cbFilm.addItem("Escolha o filme");
		for(TicketsOnSaleModel ticketModel : ticketController.read()) {
		
			FilmSessionModel sessionModel = sessionController.findById(ticketModel.getSessionId());
			FilmModel filmModel = filmController.findById(sessionModel.getFilm());
			
			cbFilm.addItem(filmModel);	
		}
	}
	
	private void fillCbSession() {
		
	}
}
