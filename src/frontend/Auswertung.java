package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import daten.CIRecord;
import daten.CITyp;

import java.awt.Color;

public class Auswertung extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Auswertung() {
		setBounds(100, 100, 397, 318);
		setTitle("ItemPro - Auswertung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.setMinimumSize(new Dimension(1080, 720));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitel = new JLabel("Auswertung");
		lblTitel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitel.setBounds(10, 11, 180, 30);
		contentPane.add(lblTitel);
		
		JTextArea textAreaAuswertung = new JTextArea();
		textAreaAuswertung.setFont(new Font("Calibri", Font.PLAIN, 14));
		textAreaAuswertung.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaAuswertung.setBounds(10, 52, 365, 220);
		textAreaAuswertung.setEditable(false);
		contentPane.add(textAreaAuswertung);
		
		int anzahlTypen = backend.hauptprogramm.zeigeAnzahlTypen();
		String anzahlCITypen = "Anzahl aller vorhanden CITypen: " + anzahlTypen;
		ArrayList <CITyp> alleCITypen = new ArrayList<CITyp>();
		alleCITypen = backend.hauptprogramm.holeAlleCITypen();
		//ArrayList <CIRecord> alleCIRecords = new ArrayList<CIRecord>();
		//alleCIRecords = backend.hauptprogramm.holeAlleRecordsVonCITyp(CITyp)
		
		textAreaAuswertung.setText(anzahlCITypen + "\n");
		for (int i =0; i< anzahlTypen; i++){
			String nameCITyp = alleCITypen.get(i).getCItypName();
			int anzahlRecords = backend.hauptprogramm.zeigeAnzahlRecords(nameCITyp);
			
			textAreaAuswertung.append("Anzahl der CIRecords von " + nameCITyp + " : " + anzahlRecords + "\n");
	}
		
		
}
}