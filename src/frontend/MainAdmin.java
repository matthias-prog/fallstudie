package frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import com.sun.tools.javac.comp.Todo;

public class MainAdmin extends JFrame {

	private JFrame mainAdminFrame;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableCITypen;
	private JButton btnAbmelden;
	private JScrollPane scrollPane;
	private JButton btnCiTypHinzufügen;
	private JButton btnCiTypAendern;
	private JButton btnCiTypLoeschen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAdmin frame = new MainAdmin();
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
	public MainAdmin() {
		mainAdminFrame = new JFrame();
		
		setTitle("ItemPro - Startseite (Administrator)");
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(1080, 720));
		setBounds(new Rectangle(0, 0, 1080, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(1080, 720));
		contentPane.setBounds(new Rectangle(0, 0, 1080, 720));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAbmelden = new JButton("Abmelden");
		btnAbmelden.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbmelden.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAbmelden.setBackground(Color.WHITE);
		btnAbmelden.setBounds(873, 623, 175, 50);
		contentPane.add(btnAbmelden);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBounds(17, 60, 840, 613);
		contentPane.add(scrollPane);
		
		tableCITypen = new JTable();
		scrollPane.setViewportView(tableCITypen);
		tableCITypen.setRowHeight(30);
		tableCITypen.setName("");
		tableCITypen.setOpaque(false);
		tableCITypen.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableCITypen.setModel(new DefaultTableModel(
				//TODO: Das zu befüllen wird ein totales Gef**ke...
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Attribut 1", "Attribut 2", "Attribut 3", "Attribut 4", "Attribut 5", "Attribut 6"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		
		//Button "Benutzerverwaltung"
		JButton btnBenutzerverwaltung = new JButton("Benutzerverwaltung");
		btnBenutzerverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: ActionEvent: Funktionalität hinzufügen
			}
		});
		btnBenutzerverwaltung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBenutzerverwaltung.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBenutzerverwaltung.setBackground(Color.WHITE);
		btnBenutzerverwaltung.setBounds(873, 563, 175, 50);
		contentPane.add(btnBenutzerverwaltung);
		
		//Button "CI-Typ Hinzufügen"
		btnCiTypHinzufügen = new JButton("CI-Typ Hinzufügen");
		btnCiTypHinzufügen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: ActionEvent: Funktionalität hinzufügen
			}
		});
		btnCiTypHinzufügen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypHinzufügen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypHinzufügen.setBackground(Color.WHITE);
		btnCiTypHinzufügen.setBounds(873, 60, 175, 50);
		contentPane.add(btnCiTypHinzufügen);
		
		//Button "CI-Typ Ändern"
		btnCiTypAendern = new JButton("CI-Typ Bearbeiten");
		btnCiTypAendern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypAendern.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypAendern.setBackground(Color.WHITE);
		btnCiTypAendern.setBounds(873, 120, 175, 50);
		contentPane.add(btnCiTypAendern);
		
		//Button "CI-Typ Löschen"
		btnCiTypLoeschen = new JButton("CI-Typ Löschen");
		btnCiTypLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypLoeschen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypLoeschen.setBackground(Color.WHITE);
		btnCiTypLoeschen.setBounds(873, 180, 175, 50);
		contentPane.add(btnCiTypLoeschen);
		
		
	}
}
