package view;

import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import factory.SystemFactory;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class Setup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JPanel step2;
	private JPanel step1;
	private JPanel step3;
	
	private String directory;
	private String host;
	private String port;
	private String login;
	private String password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		String hostProp;
		String loginProp;
		String passwordProp;
		String ticketOutProp;
		String portProp;
	
		try {
			Properties prop = SystemFactory.getProp();
			
			ticketOutProp = prop.getProperty("prop.system.ticketout");
			hostProp = prop.getProperty("prop.server.host");
			loginProp = prop.getProperty("prop.server.login");
			passwordProp = prop.getProperty("prop.server.password");
			portProp = prop.getProperty("prop.server.port");
			
			
			if(ticketOutProp.equals("default") && hostProp.equals("default") &&loginProp.equals("default") && passwordProp.equals("default") && portProp.equals("default")) {				
				
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Setup frame = new Setup();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}else {
				
				ViewLogin loginScreen = new ViewLogin();
				loginScreen.setVisible(true);
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Setup() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Setup.class.getResource("/images/cine ico.png")));
		setTitle("Setup - Cinema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		step2 = new JPanel();
		step2.setVisible(false);
		
		step3 = new JPanel();
		step3.setBounds(0, 0, 601, 266);
		contentPane.add(step3);
		step3.setLayout(null);
		
		JLabel lblLocalDeSada = new JLabel("Local de sa\u00EDda padr\u00E3o");
		lblLocalDeSada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocalDeSada.setBounds(208, 11, 190, 14);
		step3.add(lblLocalDeSada);
		
		JLabel lblNewLabel_3 = new JLabel("Este sistema gera arquivos em PDF, como ingressos e relat\u00F3rios");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(111, 46, 369, 14);
		step3.setVisible(false);
		step3.add(lblNewLabel_3);
		
		JLabel lblParaIssoPrecisamos = new JLabel("Para isso, precisamos que escolha o diret\u00F3rio onde os arquivos ser\u00E3o salvos.");
		lblParaIssoPrecisamos.setBounds(111, 71, 369, 14);
		step3.add(lblParaIssoPrecisamos);
		
		JLabel lblDirectory = new JLabel("");
		lblDirectory.setBounds(59, 130, 327, 14);
		step3.add(lblDirectory);
		
		JButton btnEscolherDiretrio = new JButton("Escolher diret\u00F3rio");
		btnEscolherDiretrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Escolha o diretório para salvar os aquivos");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(fc.showOpenDialog(btnEscolherDiretrio) == JFileChooser.APPROVE_OPTION) {
					
					lblDirectory.setText(fc.getSelectedFile().getAbsolutePath());
					directory = lblDirectory.getText();
				}
			}
		});
		btnEscolherDiretrio.setBounds(396, 130, 135, 23);
		step3.add(btnEscolherDiretrio);
		
		JButton btnFinalize = new JButton("Finalizar");
		btnFinalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeValues();
			}
		});
		btnFinalize.setBounds(309, 203, 89, 23);
		step3.add(btnFinalize);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step3.setVisible(false);
				step2.setVisible(true);
			}
		});
		btnVoltar.setBounds(181, 203, 89, 23);
		step3.add(btnVoltar);
		

		
		step1 = new JPanel();
		
		step1.setBounds(0, 0, 601, 266);
		contentPane.add(step1);
		step1.setLayout(null);
		
		JLabel lblText1 = new JLabel("Parece que \u00E9 a primeira vez que est\u00E1 executando o sistema!");
		lblText1.setBounds(85, 5, 420, 20);
		step1.add(lblText1);
		lblText1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblText2 = new JLabel("Precisaremos definir algumas coisas antes de come\u00E7armos.");
		lblText2.setBounds(48, 30, 417, 20);
		step1.add(lblText2);
		lblText2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel("Vamos l\u00E1?");
		lblNewLabel.setBounds(257, 72, 73, 20);
		step1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnNext = new JButton("Pr\u00F3ximo");
		btnNext.setBounds(314, 161, 123, 23);
		step1.add(btnNext);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Setup.this.dispose();
			}
		});
		btnCancelar.setBounds(164, 161, 110, 23);
		step1.add(btnCancelar);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				step1.setVisible(false);
				step2.setVisible(true);
			}
		});
		step2.setBounds(0, 0, 601, 266);
		contentPane.add(step2);
		step2.setLayout(null);
		
		JLabel lblBancoDeDados = new JLabel("Banco de dados");
		lblBancoDeDados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBancoDeDados.setBounds(229, 21, 128, 20);
		step2.add(lblBancoDeDados);
		
		JLabel lblParaQueO = new JLabel("Para que o sistema funcione corretamente, certifique-se de baixar e instalar o banco de dados mysql");
		lblParaQueO.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblParaQueO.setBounds(21, 47, 570, 14);
		step2.add(lblParaQueO);
		
		JLabel lblNewLabel_1 = new JLabel("que est\u00E1 descrito no projeto do github.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(21, 72, 247, 14);
		step2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Feito isso, digite o host, a porta, o usu\u00E1rio e a senha criados no Banco de dados.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(21, 97, 455, 14);
		step2.add(lblNewLabel_2);
		
		txtHost = new JTextField();
		txtHost.setBounds(81, 136, 128, 20);
		step2.add(txtHost);
		txtHost.setColumns(10);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setBounds(25, 139, 46, 14);
		step2.add(lblHost);
		
		txtPort = new JTextField();
		txtPort.setBounds(81, 167, 128, 20);
		step2.add(txtPort);
		txtPort.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(21, 170, 46, 14);
		step2.add(lblPorta);
		
		txtUser = new JTextField();
		txtUser.setBounds(356, 136, 139, 20);
		step2.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(300, 139, 46, 14);
		step2.add(lblUsurio);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(356, 167, 139, 20);
		step2.add(txtPassword);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(300, 170, 46, 14);
		step2.add(lblSenha);
		
		JButton btnCancelar_1 = new JButton("Anterior");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step2.setVisible(false);
				step1.setVisible(true);
			}
		});
		btnCancelar_1.setBounds(179, 217, 119, 23);
		step2.add(btnCancelar_1);
		
		JButton btnPrximo = new JButton("Pr\u00F3ximo");
		btnPrximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				host = txtHost.getText();
				port = txtPort.getText();
				login = txtUser.getText();
				password = String.valueOf(txtPassword.getPassword());
				step2.setVisible(false);
				step3.setVisible(true);
			}
		});
		btnPrximo.setBounds(335, 217, 89, 23);
		step2.add(btnPrximo);
	}

	public void storeValues() {
		SystemFactory system = new SystemFactory();
		try {
			Properties propDefault = SystemFactory.getDefaultProperties();
			FileOutputStream arquivoOut = new FileOutputStream("./properties/config.properties");
			

			
			String url = "jdbc:mysql://"+host+":"+port+"/cinema";
			
			boolean testConnection = system.verifyConnection(url, login, password);
			
			if(!testConnection) {
				
				propDefault.store(arquivoOut, null);
				JOptionPane.showMessageDialog(null, "Há um erro na conexão!");				
			}else {
				
				propDefault.setProperty("prop.server.password", password);
				propDefault.setProperty("prop.server.host", host);
				propDefault.setProperty("prop.server.login", login);
				propDefault.setProperty("prop.server.port", port);
				propDefault.setProperty("prop.system.ticketout", directory);
				
				JOptionPane.showMessageDialog(null, "Instalação finalizada!");
				propDefault.store(arquivoOut, null);
				
				ViewLogin loginScreen = new ViewLogin();
				loginScreen.setVisible(true);
				this.dispose();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
