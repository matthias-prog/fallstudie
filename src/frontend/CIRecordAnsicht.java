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
		
		JButton loeschenButton = new JButton("CI-Record l\u00F6schen");
		
		loeschenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		loeschenButton.setBackground(Color.WHITE);
		loeschenButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		loeschenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				Object ciRecordID = table.getModel().getValueAt(selectedRow, 0);
				int recordID = Integer.valueOf((String) ciRecordID);
				
				String citypname = cityp.getCItypName();
				
				backend.hauptprogramm.loescheCIRecord(citypname, recordID);
				ladeTabelle();
			}
		});
		loeschenButton.setBounds(880, 240, 180, 50);
		contentPane.add(loeschenButton);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);
		
		ladeTabelle();
		
		JButton bearbeitenButton = new JButton("CI-Record bearbeiten");
		bearbeitenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int selectedRow = table.getSelectedRow();
					Object ciRecordID = table.getModel().getValueAt(selectedRow, 0);
					int recordID = Integer.valueOf((String) ciRecordID);
					BearbeiteCIRecord bearbeiteFenster = new BearbeiteCIRecord(cityp, recordID);
					bearbeiteFenster.setVisible(true);
					ladeTabelle();
				
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		bearbeitenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		bearbeitenButton.setBackground(Color.WHITE);
		bearbeitenButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		bearbeitenButton.setBounds(880, 180, 180, 50);
		contentPane.add(bearbeitenButton);
		
		JButton anlegenButton = new JButton("CI-Record anlegen");
		anlegenButton.addActionListener(new ActionListener() {
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
		anlegenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		anlegenButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		anlegenButton.setBackground(Color.WHITE);
		anlegenButton.setBounds(880, 120, 180, 50);
		contentPane.add(anlegenButton);
		
		JLabel lblNewLabel = new JLabel("CI-Records von CI Typ "+cityp.getCItypName());
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(15, 15, 345, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblHeader = new JLabel("Hier sehen Sie eine Uebersicht ueber alle Records eines CI Typen.");
		lblHeader.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblHeader.setBackground(Color.WHITE);
		lblHeader.setBounds(10, 70, 371, 20);
		contentPane.add(lblHeader);
		
		
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
		scrollPane.setViewportView(table);
	}
}
