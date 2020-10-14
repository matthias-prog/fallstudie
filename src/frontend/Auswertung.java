package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.setMinimumSize(new Dimension(1080, 720));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAuswertung = new JLabel("Auswertung");
		lblAuswertung.setFont(new Font("Calibri", Font.BOLD, 24));
		lblAuswertung.setBounds(10, 11, 180, 30);
		contentPane.add(lblAuswertung);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 14));
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(10, 52, 1048, 220);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		int anzahlTypen = backend.hauptprogramm.zeigeAnzahlTypen();
		String anzahlCITypen = "Anzahl aller vorhanden CITypen: " + anzahlTypen;
		ArrayList <CITyp> alleCITypen = new ArrayList<CITyp>();
		alleCITypen = backend.hauptprogramm.holeAlleCITypen();
		//ArrayList <CIRecord> alleCIRecords = new ArrayList<CIRecord>();
		//alleCIRecords = backend.hauptprogramm.holeAlleRecordsVonCITyp(CITyp)
		
		textArea.setText(anzahlCITypen + "\n");
		for (int i =0; i< anzahlTypen; i++){
			String nameCITyp = alleCITypen.get(i).getCItypName();
			int anzahlRecords = backend.hauptprogramm.zeigeAnzahlRecords(nameCITyp);
			
			textArea.append("Anzahl der CIRecords von " + nameCITyp + " :" + anzahlRecords + "\n");
	}
		
		
}
}