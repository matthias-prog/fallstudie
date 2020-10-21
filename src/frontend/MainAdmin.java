package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import daten.CITyp;
import daten.Message;

//import com.sun.tools.javac.comp.Todo;

public class MainAdmin extends JFrame {

	public JFrame mainAdminFrame;
	private JPanel contentPane;
	private JTable tableCITypen;
	private JScrollPane scrollPane;
	private JButton btnCiTypHinzufuegen;
	private JButton btnCiTypLoeschen;
	private JLabel lblTitel;

	static MainAdmin frame = new MainAdmin();
	private JLabel lblErklaerung1;
	private JLabel lblErklaerung2;
	private JButton btnAuswertungAnzeigen;

	/**
	 * Create the frame.
	 */
	public MainAdmin() {
		mainAdminFrame = new JFrame();

		setTitle("ItemPro - Startseite (Administrator)");
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(1080, 720));
		setBounds(new Rectangle(0, 0, 1080, 720));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(1080, 720));
		contentPane.setBounds(new Rectangle(0, 0, 1080, 720));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);

		ladeTabelle();

		// Button "Benutzerverwaltung"
		JButton btnBenutzerverwaltung = new JButton("Benutzerverwaltung");
		btnBenutzerverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Benutzerverwaltung benutzerverwaltung = new Benutzerverwaltung();
				benutzerverwaltung.setVisible(true);
				// TODO: ActionEvent: Funktionalitï¿½t hinzufï¿½gen
			}
		});
		btnBenutzerverwaltung.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnBenutzerverwaltung.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBenutzerverwaltung.setBackground(Color.WHITE);
		btnBenutzerverwaltung.setBounds(685, 70, 180, 36);
		contentPane.add(btnBenutzerverwaltung);

		// Button "CI-Typ Hinzufï¿½gen"
		btnCiTypHinzufuegen = new JButton("CI-Typ hinzufuegen");
		btnCiTypHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				NeuerCITyp neuerCITyp = new NeuerCITyp();
				neuerCITyp.setVisible(true);
				}
				catch (Exception ert) {
				ert.printStackTrace();
				}
				ladeTabelle();
			}
		});
		
		btnCiTypHinzufuegen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCiTypHinzufuegen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypHinzufuegen.setBackground(Color.WHITE);
		btnCiTypHinzufuegen.setBounds(880, 120, 180, 36);
		contentPane.add(btnCiTypHinzufuegen);

		// Button "CI-Typ Lï¿½schen"
		btnCiTypLoeschen = new JButton("CI-Typ loeschen");
		btnCiTypLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Methode löscheCITyp
			
				if (tableCITypen.getSelectedRow() != -1) {
				String typ = tableCITypen.getValueAt(tableCITypen.getSelectedRow(), 1).toString();
				Message result = backend.hauptprogramm.loescheCITyp(typ);
				System.out.println(result.getNachricht());;
				if (result.isErfolg() == false) {
				JOptionPane.showMessageDialog(null, result.getNachricht(), "Fehler",
						JOptionPane.ERROR_MESSAGE);}
				ladeTabelle();
				} else {
						JOptionPane.showMessageDialog(null, "Kein CI-Typ ausgewaehlt", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCiTypLoeschen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCiTypLoeschen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypLoeschen.setBackground(Color.WHITE);
		btnCiTypLoeschen.setBounds(880, 171, 180, 36);
		contentPane.add(btnCiTypLoeschen);

		lblTitel = new JLabel("CI-Typ-Uebersicht (Admin)");
		lblTitel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitel.setBounds(15, 15, 354, 30);
		contentPane.add(lblTitel);

		JButton btnAbmelden = new JButton("Abmelden");
		btnAbmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.getFrmItemproLogin().setVisible(true); // öffnet das Login-Fenster
				backend.hauptprogramm.logout(); // aktueller Benutzer wird im Backend abgemeldet bzw. "vergessen"
			}
		});
		
		btnAbmelden.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAbmelden.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAbmelden.setBackground(Color.WHITE);
		btnAbmelden.setBounds(880, 70, 180, 36);
		contentPane.add(btnAbmelden);

		lblErklaerung1 = new JLabel("Hier sehen Sie eine Uebersicht ueber alle CI-Typen.");
		lblErklaerung1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErklaerung1.setBackground(Color.WHITE);
		lblErklaerung1.setBounds(10, 70, 400, 20);
		contentPane.add(lblErklaerung1);

		lblErklaerung2 = new JLabel("Waehlen Sie einen CI-Typ aus, um die zugehoerigen Records anzuzeigen.");
		lblErklaerung2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErklaerung2.setBackground(Color.WHITE);
		lblErklaerung2.setBounds(10, 90, 500, 20);
		contentPane.add(lblErklaerung2);

		JButton btnCiRecordsAnzeigen = new JButton("CI Records anzeigen");
		btnCiRecordsAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = tableCITypen.getSelectedRow();
				if (row != -1) {
					int anzahlSpalten = tableCITypen.getColumnCount();
					String id1 = (String) tableCITypen.getValueAt(row, 0);
					int id = Integer.parseInt(id1);
					String ciTypName = (String) tableCITypen.getValueAt(row, 1);
					ArrayList<String> attributNamen = new ArrayList<String>();

					for (int i = 2; i < anzahlSpalten; i++) {
						String attribut = (String) tableCITypen.getValueAt(row, i);
						if (attribut != null) {
							attributNamen.add(attribut);
						}
					}

					CITyp selectedCITyp = new CITyp(id, ciTypName, attributNamen);
					CIRecordAnsicht ciRecordAnsicht = new CIRecordAnsicht(selectedCITyp);
					ciRecordAnsicht.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Kein CI-Typ ausgewaehlt", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnCiRecordsAnzeigen.setBounds(880, 221, 180, 36);
		btnCiRecordsAnzeigen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCiRecordsAnzeigen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiRecordsAnzeigen.setBackground(Color.WHITE);
		contentPane.add(btnCiRecordsAnzeigen);

		
		btnAuswertungAnzeigen = new JButton("Auswertung anzeigen");
		btnAuswertungAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Auswertung auswertung = new Auswertung();
				auswertung.setVisible(true);
			}
		});
		btnAuswertungAnzeigen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAuswertungAnzeigen.setBackground(Color.WHITE);
		btnAuswertungAnzeigen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAuswertungAnzeigen.setBounds(495, 70, 180, 36);
		contentPane.add(btnAuswertungAnzeigen);
		
	}
	
	private void ladeTabelle() {
		ArrayList<CITyp> listeCITypen = backend.hauptprogramm.holeAlleCITypen();

		// es wird berechnet wie lang der lÃ¤ngste CITyp ist
		int maxlength = 0;
		for (CITyp c : listeCITypen) {
			int i = 0;
			for (String s : c.getAttributnamen()) {
				if (s != null) {
					i++;
				}
			}
			if (i > maxlength) {
				maxlength = i;
			}
		}

		String[][] datenArray = new String[listeCITypen.size()][maxlength + 2];
		String[] spaltenNamen = new String[maxlength + 2];
		spaltenNamen[0] = "ID";
		spaltenNamen[1] = "Name";
		for (int i = 1; i <= maxlength; i++) {
			spaltenNamen[i + 1] = "Attribut " + i;
		}

		for (int i = 0; i < listeCITypen.size(); i++) {
			CITyp cityp = listeCITypen.get(i);
			datenArray[i][0] = String.valueOf(cityp.getCItypID());
			datenArray[i][1] = cityp.getCItypName();

			for (int j = 2; j < maxlength + 2; j++) {
				datenArray[i][j] = cityp.getAttributnamen().get(j - 2);
			}

		}

		DefaultTableModel tabelle = new DefaultTableModel(datenArray, spaltenNamen);

		tableCITypen = new JTable(tabelle);
		tableCITypen.setRowHeight(30);
		tableCITypen.setName("");
		tableCITypen.setOpaque(false);
		tableCITypen.setDefaultEditor(Object.class, null);
		tableCITypen.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tableCITypen);
		
	}
}