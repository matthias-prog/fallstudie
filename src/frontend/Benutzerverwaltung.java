package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Benutzerverwaltung extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Benutzerverwaltung frame = new Benutzerverwaltung();
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
	public Benutzerverwaltung() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benutzerverwaltung");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 286, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Benutzer anlegen");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 52, 180, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Benutzer bearbeiten");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_1.setBounds(200, 52, 180, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Benutzer l\u00F6schen");
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_2.setBounds(390, 52, 180, 30);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 1048, 498);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Benutzername", "Passwort", "Rolle"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("Abmelden");
		btnNewButton_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_3.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(878, 52, 180, 30);
		contentPane.add(btnNewButton_3);
	}
}
