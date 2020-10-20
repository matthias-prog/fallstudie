package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import daten.Message;

public class Benutzerverwaltung extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Create the frame.
	 */
	public Benutzerverwaltung() {
		setTitle("ItemPro - Benutzerverwaltung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);

		JLabel lblTitel = new JLabel("Benutzerverwaltung");
		lblTitel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitel.setBounds(10, 11, 286, 30);
		contentPane.add(lblTitel);
		
		JButton btnZurueck = new JButton("Zurück");
		btnZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		btnZurueck.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnZurueck.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnZurueck.setBackground(Color.WHITE);
		btnZurueck.setBounds(880, 70, 180, 36);
		contentPane.add(btnZurueck);

		JButton btnAnlegen = new JButton("Benutzer anlegen");
		btnAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					NeuerBenutzer neuerBenutzer = new NeuerBenutzer();
					neuerBenutzer.setVisible(true);
					ladeTabelle();
				} catch (Exception ert) {
					ert.printStackTrace();
				}
				ladeTabelle();
			}
		});
		btnAnlegen.setBackground(Color.WHITE);
		btnAnlegen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAnlegen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAnlegen.setBounds(880, 120, 180, 50);
		contentPane.add(btnAnlegen);
		
		JButton btnBearbeiten = new JButton("Benutzer bearbeiten");
		btnBearbeiten.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnBearbeiten.setBackground(Color.WHITE);
		btnBearbeiten.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBearbeiten.setBounds(880, 180, 180, 50);
		btnBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int selectedRow = table.getSelectedRow();
					
					if (selectedRow != -1) {
					Object benutzerID = table.getModel().getValueAt(selectedRow, 0);
					int userID = Integer.valueOf((String) benutzerID);
					BearbeiteBenutzer bearbeiteFenster = new BearbeiteBenutzer(userID);
					bearbeiteFenster.setVisible(true);
					ladeTabelle();
				
				}
					else {
							JOptionPane.showMessageDialog(null, "Kein Benutzer ausgewaehlt", "Fehler",
									JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		contentPane.add(btnBearbeiten);
		
		JButton btnLoeschen = new JButton("Benutzer loeschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
				String benutzerID = table.getValueAt(table.getSelectedRow(), 0).toString();
				int id = Integer.parseInt(benutzerID);
				Message result = backend.hauptprogramm.benutzerLoeschen(id);
				System.out.println(result.getNachricht());
				ladeTabelle();
				} else {
						JOptionPane.showMessageDialog(null, "Kein Benutzer ausgewaehlt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLoeschen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLoeschen.setBackground(Color.WHITE);
		btnLoeschen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnLoeschen.setBounds(880, 240, 180, 50);
		contentPane.add(btnLoeschen);

		ladeTabelle();


		JLabel lblErklaerung = new JLabel("Hier sehen Sie eine Uebersicht ueber alle Benutzer.");
		lblErklaerung.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErklaerung.setBackground(Color.WHITE);
		lblErklaerung.setBounds(10, 70, 350, 20);
		contentPane.add(lblErklaerung);

		JLabel lblErklaerung2 = new JLabel("Waehlen Sie einen Benutzer aus um ihn zu bearbeiten oder zu loeschen.");
		lblErklaerung2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErklaerung2.setBackground(Color.WHITE);
		lblErklaerung2.setBounds(10, 90, 500, 20);
		contentPane.add(lblErklaerung2);

		
		
		table_1 = new JTable();
		table_1.setBounds(126, 580, 86, 54);
		contentPane.add(table_1);
	
	
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
		 * for (int j = 2; j < 3; j++) { datenArray[i][j] =
		 * benutzer.getAttributnamen().get(j - 2);
		 * System.out.println(benutzer.getAttributnamen().get(j)); }
		 * 
		 * }
		 */

		DefaultTableModel tabelle = new DefaultTableModel(datenArray, spaltenNamen);

		table = new JTable(tabelle);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(30);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

	}

}
