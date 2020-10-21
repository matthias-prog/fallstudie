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

import daten.CIRecord;
import daten.CITyp;

public class CIRecordAnsicht extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private CITyp cityp;

	/**
	 * Create the frame.
	 */
	public CIRecordAnsicht(CITyp cityp) {
		this.cityp=cityp;
		setTitle("ItemPro - CI-Record");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JButton btnLoeschen = new JButton("CI-Record loeschen");
		
		btnLoeschen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLoeschen.setBackground(Color.WHITE);
		btnLoeschen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				
				if (selectedRow !=-1) {
				Object ciRecordID = table.getModel().getValueAt(selectedRow, 0);
				int recordID = Integer.valueOf((String) ciRecordID);
				
				String citypname = cityp.getCItypName();
				
				backend.hauptprogramm.loescheCIRecord(citypname, recordID);
				ladeTabelle();
				} else {
						JOptionPane.showMessageDialog(null, "Kein CI-Record ausgewählt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLoeschen.setBounds(880, 240, 180, 50);
		contentPane.add(btnLoeschen);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);
		
		ladeTabelle();
		
		JButton btnBearbeiten = new JButton("CI-Record bearbeiten");
		btnBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int selectedRow = table.getSelectedRow();
					
					if (selectedRow != -1) {
					Object ciRecordID = table.getModel().getValueAt(selectedRow, 0);
					int recordID = Integer.valueOf((String) ciRecordID);
					BearbeiteCIRecord bearbeiteFenster = new BearbeiteCIRecord(cityp, recordID);
					bearbeiteFenster.setVisible(true);
					ladeTabelle();
				
				}
					else {
						JOptionPane.showMessageDialog(null, "Kein CI-Record ausgewählt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		btnBearbeiten.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBearbeiten.setBackground(Color.WHITE);
		btnBearbeiten.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnBearbeiten.setBounds(880, 180, 180, 50);
		contentPane.add(btnBearbeiten);
		
		JButton btnAnlegen = new JButton("CI-Record anlegen");
		btnAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					NeuerCIRecord neuerCIRecord = new NeuerCIRecord(cityp);
					neuerCIRecord.setVisible(true);
					ladeTabelle();
					
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		btnAnlegen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAnlegen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAnlegen.setBackground(Color.WHITE);
		btnAnlegen.setBounds(880, 120, 180, 50);
		contentPane.add(btnAnlegen);
		
		JLabel lblTitel = new JLabel("CI-Records von CI Typ "+cityp.getCItypName());
		lblTitel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitel.setBounds(15, 15, 345, 30);
		contentPane.add(lblTitel);
		
		JLabel lblErklaerung = new JLabel("Hier sehen Sie eine Uebersicht ueber alle Records eines CI Typen.");
		lblErklaerung.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErklaerung.setBackground(Color.WHITE);
		lblErklaerung.setBounds(10, 70, 500, 20);
		contentPane.add(lblErklaerung);
		
		JLabel lblErklaerung2 = new JLabel("Waehlen Sie einen CI-Record aus, um ihn zu bearbeiten oder zu loeschen.");
		lblErklaerung2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblErklaerung2.setBackground(Color.WHITE);
		lblErklaerung2.setBounds(10, 90, 500, 20);
		contentPane.add(lblErklaerung2);
		
		
	}
	
	private void ladeTabelle() {
		ArrayList<CIRecord> listeCIRecords = backend.hauptprogramm.holeAlleRecordsVonCITyp(cityp.getCItypName());

		String[][] datenArray = new String[listeCIRecords.size()][cityp.getAttributnamen().size() +1];
		String[] spaltenNamen = new String[cityp.getAttributnamen().size()+1];
		spaltenNamen[0] = "ID";
		for (int i = 1; i < cityp.getAttributnamen().size()+1; i++) {
			spaltenNamen[i] = cityp.getAttributnamen().get(i-1);
		}

		for (int i = 0; i < listeCIRecords.size(); i++) {
			CIRecord cirecord = listeCIRecords.get(i);
			datenArray[i][0] = String.valueOf(cirecord.getCIRecordID());

			for (int j = 1; j < cityp.getAttributnamen().size()+1; j++) {
				datenArray[i][j] = cirecord.getAttribute().get(j - 1);
			}

		}

		DefaultTableModel tabelle = new DefaultTableModel(datenArray, spaltenNamen);
		
		table = new JTable(tabelle);
		table.setRowHeight(30);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
	}
}
