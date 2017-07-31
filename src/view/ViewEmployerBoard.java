package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.UserModel;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewEmployerBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblSessions;


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
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Filme", "Dia", "Hor\u00E1rio", "Sala", "Tipo", "Dimens\u00E3o", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, true
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
		scrollPane.setViewportView(tblSessions);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ingressos", new ImageIcon(ViewEmployerBoard.class.getResource("/images/tickectIcon.png")), panel_1, null);
		panel_1.setLayout(null);
	}

}
