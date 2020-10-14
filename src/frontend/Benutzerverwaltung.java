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

import daten.CITyp;
import daten.Message;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

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
		setTitle("ItemPro - Benutzerverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NeuerBenutzer neuerBenutzer = new NeuerBenutzer();
					neuerBenutzer.setVisible(true);
					}
					catch (Exception ert) {
					ert.printStackTrace();
					}
					ladeTabelle();
				}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton.setBounds(880, 120, 180, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Benutzer bearbeiten");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_1.setBounds(880, 180, 180, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Benutzer loeschen");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String benutzerID = table.getValueAt(table.getSelectedRow(), 0).toString();
				int id = Integer.parseInt(benutzerID);
				Message result = backend.hauptprogramm.benutzerLoeschen(id);
				System.out.println(result.getNachricht());;
				ladeTabelle();
				
			}
		});
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_2.setBounds(880, 240, 180, 50);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);
		
		ladeTabelle();
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Hier sehen Sie eine Uebersicht ueber alle Benutzer.");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 70, 300, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblWaehlenSieEinen = new JLabel("Waehlen Sie einen Benutzer aus um ihn zu bearbeiten oder zu loeschen.");
		lblWaehlenSieEinen.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblWaehlenSieEinen.setBackground(Color.WHITE);
		lblWaehlenSieEinen.setBounds(10, 90, 450, 20);
		contentPane.add(lblWaehlenSieEinen);
				
		
		JButton btnNewButton_3 = new JButton("Abmelden");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnNewButton_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_3.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(880, 70, 180, 30);
		contentPane.add(btnNewButton_3);
	
	
	}
	
	private void ladeTabelle() {
		ArrayList<daten.Benutzer> listeBenutzer = backend.hauptprogramm.holeAlleBenutzer();


		String[][] datenArray = new String[listeBenutzer.size()][4];
		String[] spaltenNamen = new String[4];
		spaltenNamen[0] = "ID";
		spaltenNamen[1] = "Benutzername";
		spaltenNamen[2] = "Passwort";
		spaltenNamen[3] = "Admin";
		

		for (int i = 0; i < listeBenutzer.size(); i++) {
			daten.Benutzer benutzer = listeBenutzer.get(i);
			datenArray[i][0] = String.valueOf(benutzer.getBenutzerID());
			datenArray[i][1] = benutzer.getBenutzername();
			datenArray[i][2] = benutzer.getPasswort();
			datenArray[i][3] = benutzer.getIstAdmin();
		}

			/*
			for (int j = 2; j < 3; j++) {
				datenArray[i][j] = benutzer.getAttributnamen().get(j - 2);
				System.out.println(benutzer.getAttributnamen().get(j));
			}

		} */

		DefaultTableModel tabelle = new DefaultTableModel(datenArray, spaltenNamen);		
		
		table = new JTable(tabelle);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
	}
	
	
	
}
