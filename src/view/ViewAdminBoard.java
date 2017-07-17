package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.RoomModel;
import model.UserModel;
import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.RoomController;
import controller.UserController;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

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

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
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
		));
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
		
		updateUserTable();
		updateRoomTable();
		
	}
	
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
	}
	
	private void deleteRoom(int id) {
		RoomModel room = new RoomModel();
		RoomController controller = new RoomController();
		
		room.setId(id);
		controller.delete(room);
		JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
		updateRoomTable();
	}
}
