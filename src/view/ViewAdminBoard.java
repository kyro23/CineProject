package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.FilmModel;
import model.FilmSessionModel;
import model.RoomModel;
import model.TicketSaleModel;
import model.TicketsOnSaleModel;
import model.UserModel;
import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FilmController;
import controller.FilmSessionController;
import controller.RoomController;
import controller.TicketSaleController;
import controller.TicketsOnSaleController;
import controller.UserController;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.util.Date;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;

public class ViewAdminBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblUsers;
	private JTextField txtUsername;
	private JTextField txtPrivilegeLevel;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	
	private UserModel currentUser;
	private JTable tblRooms;
	private JTextField txtCapacity;
	private JTextField txtRoomNumber;
	private JTable tblFilm;
	private JTextField txtTitle;
	private JTextField txtActors;
	private JTextField txtGenere;
	private JTextField txtDuraction;
	private JTextField txtDirectors;
	private JTable tblSessions;
	private JComboBox<Object> cbFilm;
	private JComboBox<Object> cbRoom;
	private JTable tblTickets;
	
	private JComboBox<Object> cbSession;
	private JTextField txtPriece;
	private JTable tblTicketSold;
	private JTable table;
	
	private JComboBox<Object> cbFilmReport;
	private JComboBox<Object> cbPeriod;
	private JComboBox<Object> cbTicketType;
	private JPanel specificDays;
	private JSpinner spnDayInitialToReport;
	private JSpinner spnDayFinalToReport;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public ViewAdminBoard(UserModel user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAdminBoard.class.getResource("/images/cine ico.png")));
		setResizable(false);
		
		currentUser = user;
		
		setTitle("Painel administrativo - Cinema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Usu\u00E1rios", new ImageIcon(ViewAdminBoard.class.getResource("/images/user.png")), panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 746, 197);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tblUsers = new JTable();
		tblUsers.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "Nome de Usu\u00E1rio", "N\u00EDvel de Privil\u00E9gio"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblUsers.getColumnModel().getColumn(0).setResizable(false);
		tblUsers.getColumnModel().getColumn(1).setResizable(false);
		tblUsers.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(tblUsers);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 257, 746, 176);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(136, 36, 133, 20);
		panel_2.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPrivilegeLevel = new JTextField();
		txtPrivilegeLevel.setBounds(136, 97, 133, 20);
		panel_2.add(txtPrivilegeLevel);
		txtPrivilegeLevel.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(447, 34, 151, 20);
		panel_2.add(txtPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(447, 97, 151, 20);
		panel_2.add(txtConfirmPassword);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de usu\u00E1rio");
		lblNomeDeUsurio.setBounds(136, 11, 96, 14);
		panel_2.add(lblNomeDeUsurio);
		
		JLabel lblNvelDePrivilgio = new JLabel("N\u00EDvel de Privil\u00E9gio");
		lblNvelDePrivilgio.setBounds(136, 67, 133, 14);
		panel_2.add(lblNvelDePrivilgio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(447, 11, 46, 14);
		panel_2.add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setBounds(447, 65, 151, 14);
		panel_2.add(lblConfirmarSenha);
		
		JButton btnConcludo = new JButton("Salvar");
		btnConcludo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblUsers.getSelectedRow() != -1 && !txtUsername.getText().isEmpty() && !txtPrivilegeLevel.getText().isEmpty()) {
					updateUser(Integer.parseInt(tblUsers.getValueAt(tblUsers.getSelectedRow(), 0).toString()));
					btnConcludo.setEnabled(false);
				}
			}
		});
		
		btnConcludo.setEnabled(false);
		btnConcludo.setBounds(142, 142, 127, 23);
		panel_2.add(btnConcludo);
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUsername.setText("");
				txtConfirmPassword.setText("");
				txtPrivilegeLevel.setText("");
				txtPassword.setText("");
				btnConcludo.setEnabled(false);
			}
		});
		btnLimparCampos.setBounds(447, 142, 133, 23);
		panel_2.add(btnLimparCampos);
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		btnNewButton.setBounds(10, 221, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnDeletar = new JButton("Excluir");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblUsers.getSelectedRow() != -1){
					deleteUser(Integer.parseInt(tblUsers.getValueAt(tblUsers.getSelectedRow(), 0).toString()));
				}
			}
		});
		btnDeletar.setBounds(667, 221, 89, 23);
		panel.add(btnDeletar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblUsers.getSelectedRow() != -1) {
					txtUsername.setText(tblUsers.getValueAt(tblUsers.getSelectedRow(), 1).toString());
					txtPrivilegeLevel.setText(tblUsers.getValueAt(tblUsers.getSelectedRow(), 2).toString());
					btnConcludo.setEnabled(true);
				}
				
			}
		});
		btnEditar.setBounds(514, 219, 89, 23);
		panel.add(btnEditar);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Salas", new ImageIcon(ViewAdminBoard.class.getResource("/images/cog.png")), panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 11, 748, 210);
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);
		
		tblRooms = new JTable();
		tblRooms.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "N\u00FAmero da Sala", "Capacidade", "Status da sala"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblRooms.getColumnModel().getColumn(0).setResizable(false);
		tblRooms.getColumnModel().getColumn(1).setResizable(false);
		tblRooms.getColumnModel().getColumn(2).setResizable(false);
		tblRooms.getColumnModel().getColumn(3).setResizable(false);
		scrollPane_1.setViewportView(tblRooms);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Editar ou Adicionar sala", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 281, 737, 162);
		panel_3.add(panel_5);
		panel_5.setLayout(null);
		
		txtCapacity = new JTextField();
		txtCapacity.setBounds(319, 37, 92, 20);
		panel_5.add(txtCapacity);
		txtCapacity.setColumns(10);
		
		JLabel lblCapadicade = new JLabel("Capadicade");
		lblCapadicade.setBounds(241, 40, 68, 14);
		panel_5.add(lblCapadicade);
		
		JLabel lblSatus = new JLabel("Satus");
		lblSatus.setBounds(421, 37, 46, 14);
		panel_5.add(lblSatus);
		
		JComboBox<Object> cbStatus = new JComboBox<Object>();
		cbStatus.setModel(new DefaultComboBoxModel<Object>(new String[] {"Livre", "Limpesa", "Manuten\u00E7\u00E3o", "Em sess\u00E3o"}));
		cbStatus.setBounds(471, 37, 138, 20);
		panel_5.add(cbStatus);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblRooms.getSelectedRow() != -1) {
					int capacity = Integer.parseInt(txtCapacity.getText());
					String status = cbStatus.getSelectedItem().toString();
					int id = Integer.parseInt(tblRooms.getValueAt(tblRooms.getSelectedRow(), 0).toString());
					int roomNumber = Integer.parseInt(txtRoomNumber.getText());
					updateRoom(capacity, status, id, roomNumber);
					btnSalvar.setEnabled(false);
				}
			}
		});
		
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(241, 128, 89, 23);
		panel_5.add(btnSalvar);
		
		JButton btnLimparCampos_1 = new JButton("Limpar Campos");
		btnLimparCampos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCapacity.setText("");
				txtRoomNumber.setText("");
				btnSalvar.setEnabled(false);
			}
		});
		btnLimparCampos_1.setBounds(392, 128, 138, 23);
		panel_5.add(btnLimparCampos_1);
		
		txtRoomNumber = new JTextField();
		txtRoomNumber.setBounds(145, 37, 86, 20);
		panel_5.add(txtRoomNumber);
		txtRoomNumber.setColumns(10);
		
		JLabel lblNmeroDaSala = new JLabel("N\u00FAmero da Sala");
		lblNmeroDaSala.setBounds(32, 40, 103, 14);
		panel_5.add(lblNmeroDaSala);
		

		
		JButton btnNovaSala = new JButton("Nova Sala");
		btnNovaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCapacity.getText().isEmpty() && !txtRoomNumber.getText().isEmpty()) {
					int capacity = Integer.parseInt(txtCapacity.getText());
					String status = cbStatus.getSelectedItem().toString();
					int roomNumber = Integer.parseInt(txtRoomNumber.getText());

					createRoom(capacity, status, roomNumber);
				}else {
					JOptionPane.showMessageDialog(null, "Preencha os campos para adicionar uma sala.");
				}
			}
		});
		btnNovaSala.setBounds(10, 234, 115, 23);
		panel_3.add(btnNovaSala);
		
		JButton btnExcluirSala = new JButton("Excluir");
		btnExcluirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblRooms.getSelectedRow() != -1) {
					int id = Integer.parseInt(tblRooms.getValueAt(tblRooms.getSelectedRow(), 0).toString());
					deleteRoom(id);	
				}
				
			}
		});
		btnExcluirSala.setBounds(669, 234, 89, 23);
		panel_3.add(btnExcluirSala);
		
		JButton btnEditarSala = new JButton("Editar");
		btnEditarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblRooms.getSelectedRow() != -1) {
					int capacity = Integer.parseInt(tblRooms.getValueAt(tblRooms.getSelectedRow(), 2).toString());
					int roomNumber = Integer.parseInt(tblRooms.getValueAt(tblRooms.getSelectedRow(), 1).toString());
					
					txtCapacity.setText(Integer.toString(capacity));
					txtRoomNumber.setText(Integer.toString(roomNumber));
					cbStatus.setSelectedItem(tblRooms.getValueAt(tblRooms.getSelectedRow(), 2));
					btnSalvar.setEnabled(true);
				}
			}
		});
		btnEditarSala.setBounds(533, 234, 102, 23);
		panel_3.add(btnEditarSala);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Filmes", new ImageIcon(ViewAdminBoard.class.getResource("/images/film.png")), panel_6, null);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 0, 768, 205);
		panel_6.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_7.add(scrollPane_2, BorderLayout.CENTER);
		
		tblFilm = new JTable();
		tblFilm.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "T\u00EDtulo", "Atores", "Sinopse", "G\u00EAnero", "Dura\u00E7\u00E3o", "Diretores", "Class. Indicativa"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblFilm.getColumnModel().getColumn(0).setResizable(false);
		tblFilm.getColumnModel().getColumn(1).setResizable(false);
		tblFilm.getColumnModel().getColumn(2).setResizable(false);
		tblFilm.getColumnModel().getColumn(3).setResizable(false);
		tblFilm.getColumnModel().getColumn(4).setResizable(false);
		tblFilm.getColumnModel().getColumn(5).setResizable(false);
		tblFilm.getColumnModel().getColumn(6).setResizable(false);
		tblFilm.getColumnModel().getColumn(7).setResizable(false);
		scrollPane_2.setViewportView(tblFilm);
	
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Filme", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(0, 252, 768, 191);
		panel_6.add(panel_8);
		panel_8.setLayout(null);
				
		txtTitle = new JTextField();
		txtTitle.setBounds(82, 38, 142, 20);
		panel_8.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(26, 41, 46, 14);
		panel_8.add(lblTtulo);
		
		txtActors = new JTextField();
		txtActors.setBounds(82, 69, 142, 20);
		panel_8.add(txtActors);
		txtActors.setColumns(10);
		
		JLabel lblAtores = new JLabel("Atores");
		lblAtores.setBounds(26, 72, 46, 14);
		panel_8.add(lblAtores);
		
		JTextArea txtSinopse = new JTextArea();
		txtSinopse.setLineWrap(true);
		txtSinopse.setBounds(499, 36, 249, 129);
		panel_8.add(txtSinopse);
		
		JLabel lblSinopse = new JLabel("Sinopse");
		lblSinopse.setBounds(443, 41, 46, 14);
		panel_8.add(lblSinopse);
		
		txtGenere = new JTextField();
		txtGenere.setBounds(82, 100, 142, 20);
		panel_8.add(txtGenere);
		txtGenere.setColumns(10);
		
		JLabel lblGnero = new JLabel("G\u00EAnero");
		lblGnero.setBounds(26, 103, 46, 14);
		panel_8.add(lblGnero);
		
		txtDuraction = new JTextField();
		txtDuraction.setBounds(312, 38, 119, 20);
		panel_8.add(txtDuraction);
		txtDuraction.setColumns(10);
		
		JLabel lblDurao = new JLabel("Dura\u00E7\u00E3o");
		lblDurao.setBounds(246, 41, 56, 14);
		panel_8.add(lblDurao);
		
		txtDirectors = new JTextField();
		txtDirectors.setBounds(312, 69, 119, 20);
		panel_8.add(txtDirectors);
		txtDirectors.setColumns(10);
		
		JLabel lblDiretores = new JLabel("Diretores");
		lblDiretores.setBounds(246, 72, 56, 14);
		panel_8.add(lblDiretores);
		
		JComboBox<Object> cbClassIndic = new JComboBox<Object>();
		cbClassIndic.setModel(new DefaultComboBoxModel<Object>(new String[] {"Livre", "10 anos", "12 anos", "14 anos", "16 anos", "18 anos"}));
		cbClassIndic.setBounds(356, 100, 75, 20);
		panel_8.add(cbClassIndic);
		
		JLabel lblClassIndicativa = new JLabel("Class. Indicativa");
		lblClassIndicativa.setBounds(246, 106, 100, 14);
		panel_8.add(lblClassIndicativa);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblFilm.getSelectedRow() != -1) {
					int id = Integer.parseInt(tblFilm.getValueAt(tblFilm.getSelectedRow(), 0).toString());
					deleteFilm(id);
					JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
				}
			}
		});
		btnExcluir.setBounds(669, 216, 89, 23);
		panel_6.add(btnExcluir);
		
		JButton btnSalvar_1 = new JButton("Salvar");
		btnSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(tblFilm.getValueAt(tblFilm.getSelectedRow(), 0).toString());
				String title = txtTitle.getText();
				String actors = txtActors.getText();
				String sinopse = txtSinopse.getText();
				String genere = txtGenere.getText();
				int duraction = Integer.parseInt(txtDuraction.getText());
				String directors = txtDirectors.getText();
				String classIndicative = cbClassIndic.getSelectedItem().toString();
				
				updateFilm(id, title, actors, sinopse, genere, duraction, directors, classIndicative);
				
				txtSinopse.setText("");
				txtActors.setText("");
				txtDirectors.setText("");
				txtDuraction.setText("");
				txtGenere.setText("");
				txtTitle.setText("");
				cbClassIndic.setSelectedItem(cbClassIndic.getItemAt(0));
				
				btnSalvar_1.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Filme editado com sucesso!");
				
			}
		});
		
		btnSalvar_1.setBounds(246, 142, 89, 23);
		panel_8.add(btnSalvar_1);
		
		btnSalvar_1.setEnabled(false);
		
		JButton btnAdicionarFilme = new JButton("Adicionar FIlme");
		btnAdicionarFilme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				if(!txtTitle.getText().isEmpty() && !txtActors.getText().isEmpty() && !txtSinopse.getText().isEmpty() && !txtGenere.getText().isEmpty() && !txtDuraction.getText().isEmpty() && !txtDirectors.getText().isEmpty()) {
					String title = txtTitle.getText();
					String actors = txtActors.getText();
					String sinopse = txtSinopse.getText();
					String genere = txtGenere.getText();
					int duraction = Integer.parseInt(txtDuraction.getText());
					String directors = txtDirectors.getText();
					String classIndicative = cbClassIndic.getSelectedItem().toString();
					
					createFilm(title, actors, sinopse, genere, duraction, directors, classIndicative);
				
				
				}else {
					JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos!");
				}
			}
		});
		
		btnAdicionarFilme.setBounds(10, 218, 147, 23);
		panel_6.add(btnAdicionarFilme);
		
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblFilm.getSelectedRow() != -1) {
					txtTitle.setText(tblFilm.getValueAt(tblFilm.getSelectedRow(), 1).toString());
					txtActors.setText(tblFilm.getValueAt(tblFilm.getSelectedRow(), 2).toString());
					txtSinopse.setText(tblFilm.getValueAt(tblFilm.getSelectedRow(), 3).toString());
					txtGenere.setText(tblFilm.getValueAt(tblFilm.getSelectedRow(), 4).toString());
					txtDuraction.setText(tblFilm.getValueAt(tblFilm.getSelectedRow(), 5).toString());
					txtDirectors.setText(tblFilm.getValueAt(tblFilm.getSelectedRow(), 6).toString());
					cbClassIndic.setSelectedItem(tblFilm.getValueAt(tblFilm.getSelectedRow(), 7));
					btnSalvar_1.setEnabled(true);
				}
			}
			
		});
		
		btnEditar_1.setBounds(559, 216, 89, 23);
		panel_6.add(btnEditar_1);
		
		JButton btnLimparCampos_2 = new JButton("Limpar Campos");
		btnLimparCampos_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSinopse.setText("");
				txtActors.setText("");
				txtDirectors.setText("");
				txtDuraction.setText("");
				txtGenere.setText("");
				txtTitle.setText("");
				cbClassIndic.setSelectedItem(cbClassIndic.getItemAt(0));
				
				btnSalvar_1.setEnabled(false);
			}
		});
		btnLimparCampos_2.setBounds(356, 142, 133, 23);
		panel_8.add(btnLimparCampos_2);
		
		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("Sess\u00F5es", new ImageIcon(ViewAdminBoard.class.getResource("/images/film_add.png")), panel_9, null);
		panel_9.setLayout(null);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(0, 0, 768, 234);
		panel_9.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_10.add(scrollPane_3, BorderLayout.CENTER);
		
		tblSessions = new JTable();
		tblSessions.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Filme", "Dia", "Hora", "Sala", "Tipo", "Dimens\u00E3o", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
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
		scrollPane_3.setViewportView(tblSessions);
	
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Sess\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(0, 279, 768, 175);
		panel_9.add(panel_11);
		panel_11.setLayout(null);
		
		cbFilm = new JComboBox<Object>();
		fillCbFilm();
		cbFilm.setBounds(92, 25, 155, 20);
		panel_11.add(cbFilm);
		
		JLabel lblFilme = new JLabel("Filme");
		lblFilme.setBounds(36, 28, 46, 14);
		panel_11.add(lblFilme);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(36, 59, 46, 14);
		panel_11.add(lblData);
		
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(36, 90, 46, 14);
		panel_11.add(lblHora);
		
		cbRoom = new JComboBox<Object>();
		cbRoom.setBounds(418, 25, 64, 20);
		panel_11.add(cbRoom);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(362, 28, 46, 14);
		panel_11.add(lblSala);
		
		JComboBox<Object> cbType = new JComboBox<Object>();
		cbType.setModel(new DefaultComboBoxModel<Object>(new String[] {"Dublado", "Legendado"}));
		cbType.setBounds(418, 56, 92, 20);
		panel_11.add(cbType);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(362, 59, 46, 14);
		panel_11.add(lblTipo);
		
		JComboBox<Object> cbDimension = new JComboBox<Object>();
		cbDimension.setModel(new DefaultComboBoxModel<Object>(new String[] {"2D", "3D"}));
		cbDimension.setBounds(642, 25, 71, 20);
		panel_11.add(cbDimension);
		
		JLabel lblDimenso = new JLabel("Dimens\u00E3o");
		lblDimenso.setBounds(561, 28, 71, 14);
		panel_11.add(lblDimenso);
		
		JComboBox<Object> cbSessionStatus = new JComboBox<Object>();
		cbSessionStatus.setModel(new DefaultComboBoxModel<Object>(new String[] {"Aberta", "Encerrada"}));
		cbSessionStatus.setBounds(644, 59, 92, 20);
		panel_11.add(cbSessionStatus);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(561, 59, 61, 14);
		panel_11.add(lblStatus);

				
		JSpinner spnDay = new JSpinner();
		spnDay.setModel(new SpinnerDateModel());
		spnDay.setEditor(new JSpinner.DateEditor(spnDay, "dd/MM/yyyy"));
		
		spnDay.setBounds(92, 56, 155, 20);
		panel_11.add(spnDay);
		
		JSpinner spnHour = new JSpinner();
		spnHour.setModel(new SpinnerDateModel());
		spnHour.setEditor(new JSpinner.DateEditor(spnHour, "HH:mm"));
		
		spnHour.setBounds(92, 87, 71, 20);
		panel_11.add(spnHour);
		
		JButton btnSalvar_2 = new JButton("Salvar");
		btnSalvar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblSessions.getSelectedRow() != -1) {
					int id = Integer.parseInt(tblSessions.getValueAt(tblSessions.getSelectedRow(), 0).toString());
					Date dayValue = (Date) spnDay.getValue();
					Date hourValue = (Date) spnHour.getValue();
					
					FilmModel film = (FilmModel) cbFilm.getSelectedItem();
					RoomModel room = (RoomModel) cbRoom.getSelectedItem();
					
					
					String type = (String) cbType.getSelectedItem();
					String dimension = (String) cbDimension.getSelectedItem();
					String sessionStatus = (String) cbSessionStatus.getSelectedItem();
					
					java.sql.Date day = new java.sql.Date(dayValue.getTime());
					java.sql.Time hour = new java.sql.Time(hourValue.getTime());
					
					updateSession(id, film, day, hour, room, type, dimension, sessionStatus);
					
					
					btnSalvar_2.setEnabled(false);
					fillCbSession();
				}
			}
		});
		btnSalvar_2.setBounds(241, 128, 89, 23);
		panel_11.add(btnSalvar_2);
		
		btnSalvar_2.setEnabled(false);
		
		JButton btnAdicionarSesso = new JButton("Adicionar Sess\u00E3o");
		btnAdicionarSesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				Date dayValue = (Date) spnDay.getValue();
				Date hourValue = (Date) spnHour.getValue();
				
				FilmModel film = (FilmModel) cbFilm.getSelectedItem();
				RoomModel room = (RoomModel) cbRoom.getSelectedItem();
				
				
				String type = (String) cbType.getSelectedItem();
				String dimension = (String) cbDimension.getSelectedItem();
				String sessionStatus = (String) cbSessionStatus.getSelectedItem();
				
				java.sql.Date day = new java.sql.Date(dayValue.getTime());
				java.sql.Time hour = new java.sql.Time(hourValue.getTime());
				
				createSession(film, day, hour, room, type, dimension, sessionStatus);	
				fillCbSession();
			}
		});
		
		JButton btnLimparCampos_3 = new JButton("Zerar Campos");
		btnLimparCampos_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date today = new Date();
				
				
				spnDay.setValue(today);
				spnHour.setValue(today);
				cbFilm.setSelectedIndex(0);
				cbRoom.setSelectedIndex(0);
				cbDimension.setSelectedIndex(0);
				cbType.setSelectedIndex(0);
				cbSessionStatus.setSelectedIndex(0);
				
				btnSalvar_2.setEnabled(false);
			}
		});
		btnLimparCampos_3.setBounds(340, 128, 148, 23);
		panel_11.add(btnLimparCampos_3);
		
		btnAdicionarSesso.setBounds(10, 245, 151, 23);
		panel_9.add(btnAdicionarSesso);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblSessions.getSelectedRow() != -1) {
					int id = Integer.parseInt(tblSessions.getValueAt(tblSessions.getSelectedRow(), 0).toString());
					
					deleteSession(id);
					JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
				}
			}
		});
		btnExcluir_1.setBounds(669, 245, 89, 23);
		panel_9.add(btnExcluir_1);
		
		JButton btnEditar_2 = new JButton("Editar");
		btnEditar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblSessions.getSelectedRow() != -1) {
					SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
					
					FilmModel film = (FilmModel) tblSessions.getValueAt(tblSessions.getSelectedRow(), 1);
					RoomModel room = (RoomModel) tblSessions.getValueAt(tblSessions.getSelectedRow(), 4);
					
					Date day = null;
					Date hour = null;
					
					String type = tblSessions.getValueAt(tblSessions.getSelectedRow(), 5).toString();
					String dimension = tblSessions.getValueAt(tblSessions.getSelectedRow(), 6).toString();
					String sessionStatus = tblSessions.getValueAt(tblSessions.getSelectedRow(), 7).toString();
					
					try {
						day = sdfD.parse(tblSessions.getValueAt(tblSessions.getSelectedRow(), 2).toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

					try {
						hour = sdfH.parse(tblSessions.getValueAt(tblSessions.getSelectedRow(), 3).toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					spnHour.setValue(hour);
					spnDay.setValue(day);
					cbSessionStatus.setSelectedItem(sessionStatus);
					cbDimension.setSelectedItem(dimension);
					cbType.setSelectedItem(type);
					
					for(int i = 0; i < cbFilm.getItemCount(); i++) {
						if(cbFilm.getItemAt(i).toString().equals(film.toString())) {
							cbFilm.setSelectedIndex(i);
						}
					}

					for(int i = 0; i < cbRoom.getItemCount(); i++) {
						if(cbRoom.getItemAt(i).toString().equals(room.toString())) {
							cbRoom.setSelectedIndex(i);
						}
					}
					
					btnSalvar_2.setEnabled(true);
				}
			}
		});
		btnEditar_2.setBounds(570, 245, 89, 23);
		panel_9.add(btnEditar_2);
		
		JPanel panel_12 = new JPanel();
		tabbedPane.addTab("Ingressos \u00E0 venda", new ImageIcon(ViewAdminBoard.class.getResource("/images/film_edit.png")), panel_12, null);
		panel_12.setLayout(null);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(0, 0, 768, 230);
		panel_12.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel_13.add(scrollPane_4, BorderLayout.CENTER);
		
		tblTickets = new JTable();
		tblTickets.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Id", "Sess\u00E3o", "Pre\u00E7o", "Quantidade"
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
		scrollPane_4.setViewportView(tblTickets);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Ingresso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(0, 286, 768, 168);
		panel_12.add(panel_14);
		panel_14.setLayout(null);
		
		cbSession = new JComboBox<Object>();
		cbSession.setBounds(235, 62, 98, 20);
		panel_14.add(cbSession);
		
		JLabel lblSesso = new JLabel("Sess\u00E3o");
		lblSesso.setBounds(172, 65, 53, 14);
		panel_14.add(lblSesso);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setBounds(502, 65, 46, 14);
		panel_14.add(lblPreo);
		
		JLabel lblAtencao = new JLabel("Aten\u00E7\u00E3o: A quantidade de ingressos ser\u00E1 equivalente a quantidade de cadeiras da sala");
		lblAtencao.setBounds(124, 11, 528, 14);
		panel_14.add(lblAtencao);
		
		JButton btnSalvarEdicao = new JButton("Salvar edi\u00E7\u00E3o");
		btnSalvarEdicao.setBounds(235, 122, 118, 23);
		btnSalvarEdicao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblTickets.getSelectedRow() != -1) {
					int id = Integer.parseInt(tblTickets.getValueAt(tblTickets.getSelectedRow(), 0).toString());
					FilmSessionModel session = (FilmSessionModel) cbSession.getSelectedItem();
					double priece = Double.parseDouble(txtPriece.getText().replace(',', '.'));
					int quantity = Integer.parseInt(tblTickets.getValueAt(tblTickets.getSelectedRow(), 3).toString());
					
					updateTickets(id, session, priece, quantity);
					
					btnSalvarEdicao.setEnabled(false);
				}
			}
		});
		panel_14.add(btnSalvarEdicao);
		
		btnSalvarEdicao.setEnabled(false);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPriece.setText("");
				btnSalvarEdicao.setEnabled(false);
			}
		});
		btnLimpar.setBounds(401, 122, 89, 23);
		panel_14.add(btnLimpar);
		
		txtPriece = new JTextField();
		txtPriece.setBounds(558, 62, 89, 20);
		panel_14.add(txtPriece);
		txtPriece.setColumns(10);
		
		JButton btnAdicionarIngresso = new JButton("Adicionar Ingresso");
		btnAdicionarIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtPriece.getText().isEmpty()) {
				boolean haveSession = false;
					
				FilmSessionModel session = (FilmSessionModel) cbSession.getSelectedItem();
				double priece = Double.parseDouble(txtPriece.getText().replace(',', '.'));
				
				for(int i = 0; i < tblTickets.getRowCount(); i++) {
					if(tblTickets.getValueAt(i, 1).toString().equals(session.toString())) {
						haveSession = true;
					}
				}
				if(!haveSession) {
					createTicketsOnSale(session, priece);
					txtPriece.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Já existe um ingresso para essa sessão!");
				}

			
				}
			}
		});
		btnAdicionarIngresso.setBounds(24, 241, 158, 23);
		panel_12.add(btnAdicionarIngresso);
		
		JButton btnEditarIngresso = new JButton("Editar");
		btnEditarIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblTickets.getSelectedRow() != -1) {
					txtPriece.setText(tblTickets.getValueAt(tblTickets.getSelectedRow(), 2).toString().replace('.', ','));
					
					for(int i = 0; i < cbSession.getItemCount(); i++) {
						if(cbSession.getItemAt(i).toString().equals(tblTickets.getValueAt(tblTickets.getSelectedRow(), 1).toString())) {
							cbSession.setSelectedIndex(i);
						}
					}
					
					btnSalvarEdicao.setEnabled(true);
				}
			}
		});
		btnEditarIngresso.setBounds(570, 241, 89, 23);
		panel_12.add(btnEditarIngresso);
		
		JButton btnExcluiringresso = new JButton("Excluir");
		btnExcluiringresso.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblTickets.getSelectedRow() != -1) {
					int id = Integer.parseInt(tblTickets.getValueAt(tblTickets.getSelectedRow(), 0).toString());
					deleteTicket(id);
					
				}
			}
		});
		btnExcluiringresso.setBounds(669, 241, 89, 23);
		panel_12.add(btnExcluiringresso);
		
		JPanel panel_15 = new JPanel();
		tabbedPane.addTab("Ingressos vendidos", new ImageIcon(ViewAdminBoard.class.getResource("/images/tickectIcon.png")), panel_15, null);
		panel_15.setLayout(null);
	
		JPanel panel_16 = new JPanel();
		panel_16.setBounds(0, 0, 768, 443);
		panel_15.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panel_16.add(scrollPane_5, BorderLayout.CENTER);
		
		tblTicketSold = new JTable();
		tblTicketSold.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Id", "Tipo", "Valor", "Ingresso"
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
		tblTicketSold.getColumnModel().getColumn(0).setResizable(false);
		tblTicketSold.getColumnModel().getColumn(1).setResizable(false);
		tblTicketSold.getColumnModel().getColumn(2).setResizable(false);
		tblTicketSold.getColumnModel().getColumn(3).setResizable(false);
		scrollPane_5.setViewportView(tblTicketSold);
		
		JPanel panel_17 = new JPanel();
		tabbedPane.addTab("Relatr\u00F3rio", new ImageIcon(ViewAdminBoard.class.getResource("/images/chart_bar.png")), panel_17, null);
		panel_17.setLayout(null);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ingressos vendidos para filmes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_18.setBounds(0, 0, 376, 234);
		panel_17.add(panel_18);
		panel_18.setLayout(null);
		
		cbFilmReport = new JComboBox<Object>();
		cbFilmReport.setBounds(176, 39, 149, 20);
		panel_18.add(cbFilmReport);
		
		JLabel lblNewLabel = new JLabel("Selecione um filme:");
		lblNewLabel.setBounds(71, 42, 95, 14);
		panel_18.add(lblNewLabel);
		
		JLabel lblPerodo = new JLabel("Per\u00EDodo:");
		lblPerodo.setBounds(71, 77, 46, 14);
		panel_18.add(lblPerodo);
		
		cbPeriod = new JComboBox<Object>();
		cbPeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbPeriod.getSelectedItem().toString().equals("Dias específicos")) {
					specificDays.setVisible(true);
				}else {
					specificDays.setVisible(false);
				}
			}
		});
		cbPeriod.setModel(new DefaultComboBoxModel<Object>(new String[] {"Hoje", "Essa semana", "Esse m\u00EAs", "Esse ano", "Dias espec\u00EDficos"}));
		cbPeriod.setBounds(176, 74, 149, 20);
		panel_18.add(cbPeriod);
		
		JLabel lblTipoDeIngresso = new JLabel("Tipo de ingresso:");
		lblTipoDeIngresso.setBounds(71, 113, 95, 14);
		panel_18.add(lblTipoDeIngresso);
		
		cbTicketType = new JComboBox<Object>();
		cbTicketType.setModel(new DefaultComboBoxModel<Object>(new String[] {"Meia", "Inteira", "Todos"}));
		cbTicketType.setBounds(176, 110, 149, 20);
		panel_18.add(cbTicketType);
		
		JButton btnGenerateTSReport = new JButton("Gerar Relat\u00F3rio");
		btnGenerateTSReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//banana
				FilmModel film = (FilmModel) cbFilmReport.getSelectedItem();
				String period = cbPeriod.getSelectedItem().toString();
				String ticketType = cbTicketType.getSelectedItem().toString();
				
				Date dayInitialValue = null, dayFinalValue = null;
				
				if(period.equals("Dias específicos")) {
					dayInitialValue = (Date) spnDayInitialToReport.getValue();
					dayFinalValue = (Date) spnDayFinalToReport.getValue();
				}
				
				generateFilmReport(film, period, ticketType, dayInitialValue, dayFinalValue);
			}
		});
		btnGenerateTSReport.setBounds(130, 186, 129, 23);
		panel_18.add(btnGenerateTSReport);
		
		specificDays = new JPanel();
		specificDays.setBounds(10, 132, 315, 44);
		panel_18.add(specificDays);
		specificDays.setLayout(null);
		specificDays.setVisible(false);
		
		spnDayInitialToReport = new JSpinner();
		spnDayInitialToReport.setBounds(75, 11, 85, 20);
		specificDays.add(spnDayInitialToReport);
		spnDayInitialToReport.setModel(new SpinnerDateModel());
		spnDayInitialToReport.setEditor(new JSpinner.DateEditor(spnDayInitialToReport, "dd/MM/yyyy"));
		
		JLabel lblDiaInicial = new JLabel("Dia Inicial");
		lblDiaInicial.setBounds(10, 14, 55, 14);
		specificDays.add(lblDiaInicial);
		
		spnDayFinalToReport = new JSpinner();
		spnDayFinalToReport.setBounds(230, 11, 85, 20);
		specificDays.add(spnDayFinalToReport);
		spnDayFinalToReport.setModel(new SpinnerDateModel());
		spnDayFinalToReport.setEditor(new JSpinner.DateEditor(spnDayFinalToReport, "dd/MM/yyyy"));
		
		JLabel lblDiaFinal = new JLabel("Dia Final");
		lblDiaFinal.setBounds(176, 14, 46, 14);
		specificDays.add(lblDiaFinal);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Salas do cinema", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_19.setBounds(382, 0, 376, 234);
		panel_17.add(panel_19);
		panel_19.setLayout(null);
		
		JLabel lblEstadoDaSala = new JLabel("Estado da sala");
		lblEstadoDaSala.setBounds(35, 32, 83, 14);
		panel_19.add(lblEstadoDaSala);
		
		JComboBox<Object> comboBox_3 = new JComboBox<Object>();
		comboBox_3.setModel(new DefaultComboBoxModel<Object>(new String[] {"Livre", "Limpesa", "Manuten\u00E7\u00E3o", "Em sess\u00E3o"}));
		comboBox_3.setBounds(128, 29, 116, 20);
		panel_19.add(comboBox_3);
		
		JLabel lblCapacidade = new JLabel("Capacidade:");
		lblCapacidade.setBounds(35, 71, 83, 14);
		panel_19.add(lblCapacidade);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(128, 71, 27, 14);
		panel_19.add(lblDe);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(148, 68, 46, 20);
		panel_19.add(spinner);
		
		JLabel lblAt = new JLabel("At\u00E9:");
		lblAt.setBounds(204, 71, 27, 14);
		panel_19.add(lblAt);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(224, 68, 53, 20);
		panel_19.add(spinner_1);
		
		JLabel lblLugares = new JLabel("Lugares");
		lblLugares.setBounds(288, 71, 46, 14);
		panel_19.add(lblLugares);
		
		JCheckBox ckbAllRoom = new JCheckBox("Todas as salas");
		ckbAllRoom.setBounds(128, 119, 97, 23);
		panel_19.add(ckbAllRoom);
		
		JButton btnGenerateRoomR = new JButton("Gerar Relat\u00F3rio");
		btnGenerateRoomR.setBounds(128, 183, 122, 23);
		panel_19.add(btnGenerateRoomR);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new TitledBorder(null, "Histr\u00F3rico de Relatr\u00F3rios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_20.setBounds(0, 245, 758, 209);
		panel_17.add(panel_20);
		panel_20.setLayout(null);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBounds(0, 23, 541, 175);
		panel_20.add(panel_21);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		panel_21.add(scrollPane_6, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Nome do Relatr\u00F3rio", "Data de Cria\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane_6.setViewportView(table);
		
		JButton btnVisualizarRelatrrio = new JButton("Visualizar Relatr\u00F3rio");
		btnVisualizarRelatrrio.setBounds(585, 33, 143, 23);
		panel_20.add(btnVisualizarRelatrrio);
		
		JButton btnNewButton_1 = new JButton("Apagar Relatr\u00F3rio");
		btnNewButton_1.setBounds(585, 82, 143, 23);
		panel_20.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pesquisar Relatr\u00F3rio");
		btnNewButton_2.setBounds(585, 136, 143, 23);
		panel_20.add(btnNewButton_2);
		
		updateUserTable();
		
		updateRoomTable();
		
		updateTableFilm();
		
		fillCbRoom();
		fillCbFilm();
		updateTableSession();
		
		fillCbSession();
		updateTicketsTable();
		fillTblTicketSold();
		
		fillCbFilmReport();
	}
	
	
	//User
	private void updateUserTable() {
		DefaultTableModel model = (DefaultTableModel) tblUsers.getModel();
		model.setNumRows(0);
		
		UserController controll = new UserController();
		
		for(UserModel user : controll.read()) {
			model.addRow(new Object[] {
					user.getId(),
					user.getUsername(),
					user.getPrivilegeLevel()
			});
		}
	}
	private void addUser() {
		String username = txtUsername.getText();
		String privilegeLevel = txtPrivilegeLevel.getText();
		String password = String.valueOf(txtPassword.getPassword());
		String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
	
		
		if(!username.isEmpty() && !privilegeLevel.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {

			boolean haveUser = false;
			
			
			UserController controll = new UserController();
			for(UserModel user : controll.read()) {
				if(user.getUsername().equals(username)) {
					haveUser = true;
				}else {
					haveUser = false;
				}
			}
			
			if(!haveUser) {
				if(password.equals(confirmPassword)) {
					UserModel model = new UserModel();
					model.setUsername(username);
					model.setPrivilegeLevel(Integer.parseInt(privilegeLevel));
					model.setPass(password);
					
					controll.create(model);
					updateUserTable();
					
					txtUsername.setText("");
					txtPrivilegeLevel.setText("");
					txtPassword.setText("");
					txtConfirmPassword.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "As senhas não são iguais!");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Esse usuário já existe!");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Preencha os campos antes de adicionar um usuário.");
		}
	}
	
	private void updateUser(int id) {
		String username = txtUsername.getText();
		int privilegeLevel = Integer.parseInt(txtPrivilegeLevel.getText());
		String password = String.valueOf(txtPassword.getPassword());
		String confimPassword = String.valueOf(txtConfirmPassword.getPassword());

		if(password.equals(confimPassword)) {
			UserController controller = new UserController();
			UserModel model = new UserModel();
			
			model.setId(id);
			model.setUsername(username);
			model.setPrivilegeLevel(privilegeLevel);
			model.setPass(password);
			
			controller.update(model);
			if(currentUser.getId() == model.getId()) {
				JOptionPane.showMessageDialog(null, "Usuário atualizado, por favor faça login novamente.");
				ViewAdminBoard.this.dispose();
				ViewLogin viewLogin = new ViewLogin();
				viewLogin.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
				updateUserTable();
			}
			
			txtUsername.setText("");
			txtPrivilegeLevel.setText("");
			txtPassword.setText("");
			txtConfirmPassword.setText("");
		}
		
	}
	
	private void deleteUser(int id) {
		UserModel userToDelete = new UserModel();
		UserController controll = new UserController();
		
		userToDelete.setId(id);
		
		if(currentUser.getId() != id) {
			controll.delete(userToDelete);
			JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!");
			updateUserTable();
		}else {
			JOptionPane.showMessageDialog(null, "Você não pode deletar o usuário que está logado!");
		}
	}
	
	
	//Room
	private void createRoom(int capacity, String status, int RoomNumber) {
		RoomModel room = new RoomModel();
		RoomController controller = new RoomController();
		
		room.setCapacity(capacity);
		room.setStatus(status);
		room.setRoomNumer(RoomNumber);
		
		controller.create(room);
		updateRoomTable();
		txtRoomNumber.setText("");
		txtCapacity.setText("");
		fillCbRoom();
	}
	
	private void updateRoomTable() {
		RoomController controller = new RoomController();
		DefaultTableModel model = (DefaultTableModel) tblRooms.getModel();
		model.setNumRows(0);
		
		for(RoomModel room : controller.read()) {
			model.addRow(new Object[] {
					room.getId(),
					room.getRoomNumer(),
					room.getCapacity(),
					room.getStatus()
			});
		}
	}
	
	private void updateRoom(int capacity, String status, int id, int roomNumber) {
		RoomModel room = new RoomModel();
		RoomController controller = new RoomController();
		
		room.setId(id);
		room.setCapacity(capacity);
		room.setStatus(status);
		room.setRoomNumer(roomNumber);
		
		controller.update(room);
		updateRoomTable();
		
		txtCapacity.setText("");
		txtRoomNumber.setText("");
		
		JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
		fillCbRoom();
	}
	
	private void deleteRoom(int id) {
		RoomModel room = new RoomModel();
		RoomController controller = new RoomController();
		FilmSessionController sessionController = new FilmSessionController();
		
		for(FilmSessionModel session : sessionController.findSessionByRoom(id)) {
			deleteSession(session.getId());
		}
		
		room.setId(id);
		controller.delete(room);
		JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
		updateRoomTable();
		fillCbRoom();
	}
	
	
	//Film
	private void createFilm(String title,  String actors, String sinopse, String genere, int duraction, String directors, String classIndicative) {
	
		FilmModel model = new FilmModel();
		FilmController controller = new FilmController();
		
		model.setTitle(title);
		model.setActors(actors);
		model.setSinopse(sinopse);
		model.setGenere(genere);
		model.setDuraction(duraction);
		model.setDirectors(directors);
		model.setClassindicative(classIndicative);
		
		controller.Create(model);
		JOptionPane.showMessageDialog(null, "Filme adicionado com sucesso!");
		updateTableFilm();
		fillCbFilm();
		
	}
	
	private void updateTableFilm() {
		DefaultTableModel model = (DefaultTableModel) tblFilm.getModel();
		FilmController controller = new FilmController();
		
		model.setNumRows(0);
		
		for(FilmModel film : controller.read()) {
			model.addRow(new Object[] {
					film.getId(),
					film.getTitle(),
					film.getActors(),
					film.getSinopse(),
					film.getGenere(),
					film.getDuraction(),
					film.getDirectors(),
					film.getClassindicative()
			});
		}
	}
	
	private void updateFilm(int id, String title,  String actors, String sinopse, String genere, int duraction, String directors, String classIndicative) {
		FilmModel model = new FilmModel();
		FilmController controller = new FilmController();
		
		model.setId(id);
		model.setTitle(title);
		model.setActors(actors);
		model.setSinopse(sinopse);
		model.setGenere(genere);
		model.setDuraction(duraction);
		model.setDirectors(directors);
		model.setClassindicative(classIndicative);
		
		controller.update(model);
		updateTableFilm();
		fillCbFilm();
	}
	
	private void deleteFilm(int id) {
		FilmModel model = new FilmModel();
		FilmController controller = new FilmController();
		
		FilmSessionController sessionController = new FilmSessionController();
		
		for(FilmSessionModel session : sessionController.findFilms(id)) {
			deleteSession(session.getId());
		}
		
		model.setId(id);
		controller.delete(model);
		updateTableFilm();
		fillCbFilm();
	}
	
	//Session
	private void fillCbFilm() {
		FilmController controller = new FilmController();
		
		cbFilm.removeAllItems();
	
		for(FilmModel film : controller.read()) {
			cbFilm.addItem(film);
		}
	}
	
	private void fillCbRoom() {
		RoomController controller = new RoomController();
		cbRoom.removeAllItems();
		
		for(RoomModel room : controller.read()) {
			
			cbRoom.addItem(room);
		}
	}
	
	private void createSession(FilmModel film, java.sql.Date day, java.sql.Time hour, RoomModel room, String type, String dimension, String sessionStatus) {
		FilmSessionModel model = new FilmSessionModel();
		FilmSessionController controller = new FilmSessionController();
		
		model.setFilm(film.getId());
		model.setDay(day);
		model.setHour(hour);
		model.setRoom(room.getId());
		model.setType(type);
		model.setDimension(dimension);
		model.setSessionStatus(sessionStatus);
		
		controller.create(model);
		JOptionPane.showMessageDialog(null, "Sessão criada com sucesso!");	
		updateTableSession();
		fillCbSession();
	}
	
	private void updateTableSession() {
		SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
		
		FilmController filmController = new FilmController();
		RoomController roomController = new RoomController();
		
		DefaultTableModel model = (DefaultTableModel) tblSessions.getModel();
		FilmSessionController controller = new FilmSessionController();
		
		model.setNumRows(0);
		
		for(FilmSessionModel session : controller.read()) {
			int id = session.getId();
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
			
			model.addRow(new Object[] {
					id,
					film,
					day,
					hour,
					room,
					type,
					dimension,
					sessionStatus
			});
		}
	}
	
	private void updateSession(int id, FilmModel film, java.sql.Date day, java.sql.Time hour, RoomModel room, String type, String dimension, String sessionStatus) {
		FilmSessionModel model = new FilmSessionModel();
		FilmSessionController controller = new FilmSessionController();
		
		model.setId(id);
		model.setDay(day);
		model.setHour(hour);
		model.setRoom(room.getId());
		model.setFilm(film.getId());
		model.setType(type);
		model.setDimension(dimension);
		model.setSessionStatus(sessionStatus);
		
		controller.update(model);
		updateTableSession();
		JOptionPane.showMessageDialog(null, "atualizado com sucesso!");
		fillCbSession();
	}
	
	private void deleteSession(int id) {
		FilmSessionModel model = new FilmSessionModel();
		FilmSessionController controller = new FilmSessionController();
		
		model.setId(id);
		controller.delete(model);
		updateTableSession();
		fillCbSession();
		updateTicketsTable();
	}
	
	//Tickets on Sale
	private void fillCbSession() {
		FilmSessionController sessionController = new FilmSessionController();
		
		cbSession.removeAllItems();
		
		for(FilmSessionModel sessionModel : sessionController.read()) {
			cbSession.addItem(sessionModel);
		}
	}
	
	private void createTicketsOnSale(FilmSessionModel session, double priece) {
		TicketsOnSaleModel model = new TicketsOnSaleModel();
		TicketsOnSaleController controller = new TicketsOnSaleController();
		
		RoomController roomController = new RoomController();
		RoomModel roomModel = roomController.findById(session.getRoom());
		
		model.setSessionId(session.getId());
		model.setPriece(priece);
		model.setQuantity(roomModel.getCapacity());
		
		controller.create(model);
		JOptionPane.showMessageDialog(null, "Ingresso adicionado com sucesso!");
		updateTicketsTable();
	}
	
	private void updateTicketsTable(){
		DefaultTableModel tModel = (DefaultTableModel) tblTickets.getModel();
		TicketsOnSaleController controller = new TicketsOnSaleController();
		
		
		tModel.setNumRows(0);
		for(TicketsOnSaleModel tickets : controller.read()) {
			
			
			tModel.addRow(new Object[] {
				tickets.getId(),
				tickets.getSessionId(),
				tickets.getPriece(),
				tickets.getQuantity()
			});
		}
	}
	
	private void updateTickets(int id, FilmSessionModel session, double priece, int quantity) {
		TicketsOnSaleModel model = new TicketsOnSaleModel();
		TicketsOnSaleController controller = new TicketsOnSaleController();
		
		model.setId(id);
		model.setSessionId(session.getId());
		model.setPriece(priece);
		model.setQuantity(quantity);
		
		controller.update(model);
		JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
		updateTicketsTable();
		txtPriece.setText("");
	}
	
	private void deleteTicket(int id) {
		TicketsOnSaleModel model = new TicketsOnSaleModel();
		TicketsOnSaleController controller = new TicketsOnSaleController();
		
		model.setId(id);
		controller.delete(model);
		JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
		updateTicketsTable();
	}
	
	private void fillTblTicketSold() {
		TicketSaleController saleController = new TicketSaleController();
		
		DefaultTableModel tModel = (DefaultTableModel) tblTicketSold.getModel();
		tModel.setNumRows(0);
		
		for(TicketSaleModel saleModel : saleController.read()) {
			tModel.addRow(new Object[] {
					saleModel.getId(),
					saleModel.getType(),
					saleModel.getValue(),
					saleModel.getTicketId()
			});
		}
	}
	
	//reports
	private void fillCbFilmReport() {
		cbFilmReport.removeAll();
		FilmController filmController = new FilmController();
		
		for(FilmModel film : filmController.read()) {
			cbFilmReport.addItem(film);
		}
	}
	
	private void generateFilmReport(FilmModel film, String period, String ticketType, Date initialDay, Date finalDay) {
		if(!period.equals("Dias específicos")) {
			
		}else {
			java.sql.Date dayInitial = new java.sql.Date(initialDay.getTime());
			java.sql.Date dayFinal = new java.sql.Date(finalDay.getTime());
			
		}
	}
}