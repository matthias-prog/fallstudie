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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);

		JLabel ueberschriftLabel = new JLabel("Benutzerverwaltung");
		ueberschriftLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		ueberschriftLabel.setBounds(10, 11, 286, 30);
		contentPane.add(ueberschriftLabel);

		JButton anlegenButton = new JButton("Benutzer anlegen");
		anlegenButton.addActionListener(new ActionListener() {
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
		anlegenButton.setBackground(Color.WHITE);
		anlegenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		anlegenButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		anlegenButton.setBounds(880, 120, 180, 50);
		contentPane.add(anlegenButton);

		JButton bearbeitenButton = new JButton("Benutzer bearbeiten");
		bearbeitenButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		bearbeitenButton.setBackground(Color.WHITE);
		bearbeitenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		bearbeitenButton.setBounds(880, 180, 180, 50);
		contentPane.add(bearbeitenButton);

		JButton loeschenButton = new JButton("Benutzer loeschen");
		loeschenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String benutzerID = table.getValueAt(table.getSelectedRow(), 0).toString();
				int id = Integer.parseInt(benutzerID);
				Message result = backend.hauptprogramm.benutzerLoeschen(id);
				System.out.println(result.getNachricht());
				ladeTabelle();

			}
		});
		loeschenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		loeschenButton.setBackground(Color.WHITE);
		loeschenButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		loeschenButton.setBounds(880, 240, 180, 50);
		contentPane.add(loeschenButton);

		ladeTabelle();


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

		JButton abmeldenButton = new JButton("Abmelden");
		abmeldenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		abmeldenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		abmeldenButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		abmeldenButton.setBackground(Color.WHITE);
		abmeldenButton.setBounds(880, 70, 180, 30);
		contentPane.add(abmeldenButton);

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
		table.setRowHeight(30);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

	}

}
