package frontend;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import daten.CITyp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JFrame mainFrame;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableCITypen;
	private JButton btnAbmelden;
	private JScrollPane scrollPane;
	private JLabel lblErklaerung1;
	private JLabel lblErklaerung2;
	private JButton btnAuswertungAnzeigen;


	/**
	 * Create the frame.
	 */
	public Main() {
		mainFrame = new JFrame();

		setTitle("ItemPro - Startseite");
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(1080, 720));
		setBounds(new Rectangle(0, 0, 1080, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.setMaximumSize(new Dimension(1080, 720));
		contentPane.setBounds(new Rectangle(0, 0, 1080, 720));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAbmelden = new JButton("Abmelden");
		btnAbmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.getFrmItemproLogin().setVisible(true); // �ffnet das Login-Fenster
				backend.hauptprogramm.logout(); // aktueller Benutzer wird im Backend abgemeldet bzw. "vergessen"
			}
		});
		btnAbmelden.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAbmelden.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAbmelden.setBackground(Color.WHITE);
		btnAbmelden.setBounds(880, 70, 180, 36);
		contentPane.add(btnAbmelden);

		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setBounds(10, 122, 1050, 450);
		contentPane.add(scrollPane);
		
		ArrayList<CITyp> listeCITypen = backend.hauptprogramm.holeAlleCITypen();

		// es wird berechnet wie lang der längste CITyp ist
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
		
		JLabel lblTitel = new JLabel("CI-Typ-Uebersicht");
		lblTitel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitel.setBounds(15, 15, 230, 30);
		contentPane.add(lblTitel);
		
		lblErklaerung1 = new JLabel("Hier sehen Sie eine Uebersicht ueber alle CI-Typen.");
		lblErklaerung1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErklaerung1.setBackground(Color.WHITE);
		lblErklaerung1.setBounds(10, 70, 400, 20);
		contentPane.add(lblErklaerung1);
		
		lblErklaerung2 = new JLabel("Waehlen Sie einen CI-Typ aus um die zugehoerigen Records anzuzeigen.");
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
		btnCiRecordsAnzeigen.setBounds(497, 70, 183, 36);
		btnCiRecordsAnzeigen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCiRecordsAnzeigen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiRecordsAnzeigen.setBackground(Color.WHITE);
		contentPane.add(btnCiRecordsAnzeigen);
		
		btnAuswertungAnzeigen = new JButton("Auswertung anzeigen");
		btnAuswertungAnzeigen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAuswertungAnzeigen.setBackground(Color.WHITE);
		btnAuswertungAnzeigen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAuswertungAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Auswertung auswertung = new Auswertung();
				auswertung.setVisible(true);
			}
		});
		btnAuswertungAnzeigen.setBounds(690, 70, 180, 36);
		contentPane.add(btnAuswertungAnzeigen);

	}
}
