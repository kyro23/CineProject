package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.UserModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.awt.Toolkit;

public class ViewLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin frame = new ViewLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewLogin.class.getResource("/images/cine ico.png")));
		setResizable(false);
		setTitle("Cine Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(89, 95, 274, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de Usu\u00E1rio");
		lblNomeDeUsurio.setBounds(89, 70, 99, 14);
		contentPane.add(lblNomeDeUsurio);
		
		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtPass.setBounds(89, 167, 274, 20);
		contentPane.add(txtPass);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(89, 142, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblPorFavorFaa = new JLabel("Por favor fa\u00E7a Login");
		lblPorFavorFaa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPorFavorFaa.setBounds(143, 13, 205, 20);
		contentPane.add(lblPorFavorFaa);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			
			}
		});
		btnEntrar.setBounds(89, 206, 89, 23);
		contentPane.add(btnEntrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewLogin.this.dispose();
			}
		});
		btnCancelar.setBounds(274, 206, 89, 23);
		contentPane.add(btnCancelar);
	}
	
	public boolean chechlogin(String username, String pass) {
		boolean check = false;
		
		UserModel model = new UserModel();
		UserController controller = new UserController();
		
		model.setUsername(username);
		model.setPass(pass);
		
		if(controller.login(model)) {
			check = true;
		}else {
			check = false;
		}
		
		return check;
	}
	
	public UserModel findUserByName(UserModel user) {
		UserModel userFinded = new UserModel();
		UserController controller = new UserController();
			
		userFinded = controller.findUser(user);
		
		return userFinded;	
	}
	
	public void login() {
		
		String username = txtUsername.getText();
		String pass = String.valueOf(txtPass.getPassword());

		if(chechlogin(username, pass)) {
			UserModel user = new UserModel();
			user.setUsername(username);
			UserModel finded = findUserByName(user);
			
			if(finded.getPrivilegeLevel() == 3) {
				ViewAdminBoard board = new ViewAdminBoard(finded);
				board.setVisible(true);
				ViewLogin.this.dispose();
			}else if(finded.getPrivilegeLevel() == 2) {
				ViewEmployerBoard board = new ViewEmployerBoard(finded);
				board.setVisible(true);
				ViewLogin.this.dispose();
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Nome de usuário ou senha incorretos!");
		}
	}
	

}
