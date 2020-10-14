package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import backend.hauptprogramm;
import daten.CIRecord;
import daten.CITyp;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CIRecordAnsicht extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public CIRecordAnsicht(CITyp cityp) {
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
				
				String cityp = "";
				
				backend.hauptprogramm.loescheCIRecord(cityp, recordID);
			}
		});
		loeschenButton.setBounds(880, 240, 180, 50);
		contentPane.add(loeschenButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);
		
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
		
		JButton bearbeitenButton = new JButton("CI-Record bearbeiten");
		bearbeitenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int selectedRow = table.getSelectedRow();
					Object ciRecordID = table.getModel().getValueAt(selectedRow, 0);
					int recordID = Integer.valueOf((String) ciRecordID);
					// NeuerCIRecord neuerCIRecord = new NeuerCIRecord();
					//neuerCIRecord.setVisible(true);
				
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		bearbeitenButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		bearbeitenButton.setBackground(Color.WHITE);
		bearbeitenButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bearbeitenButton.setBounds(880, 180, 180, 50);
		contentPane.add(bearbeitenButton);
		
		JButton anlegenButton = new JButton("CI-Record anlegen");
		anlegenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// NeuerCIRecord neuerCIRecord = new NeuerCIRecord();
					//neuerCIRecord.setVisible(true);
				
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
}
