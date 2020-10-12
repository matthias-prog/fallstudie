package frontend;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import daten.CITyp;

//import com.sun.tools.javac.comp.Todo;

public class MainAdmin extends JFrame {

	private JFrame mainAdminFrame;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableCITypen;
	private JScrollPane scrollPane;
	private JButton btnCiTypHinzufügen;
	private JButton btnCiTypAendern;
	private JButton btnCiTypLoeschen;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAdmin frame = new MainAdmin();
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
			spaltenNamen[i + 1] = "Attribut" + i;
		}

		for (int i = 0; i < listeCITypen.size(); i++) {
			CITyp cityp = listeCITypen.get(i);
			datenArray[i][0] = String.valueOf(cityp.getCItypID());
			datenArray[i][1] = cityp.getCItypName();

			for (int j = 2; j < maxlength + 2; j++) {
				datenArray[i][j] = cityp.getAttributnamen().get(j - 2);
				System.out.println(cityp.getAttributnamen().get(j));
			}

		}

		DefaultTableModel tabelle = new DefaultTableModel(datenArray, spaltenNamen);

		
		tableCITypen = new JTable(tabelle);
		scrollPane.setViewportView(tableCITypen);
		tableCITypen.setRowHeight(30);
		tableCITypen.setName("");
		tableCITypen.setOpaque(false);
		tableCITypen.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		
		
		//Button "Benutzerverwaltung"
		JButton btnBenutzerverwaltung = new JButton("Benutzerverwaltung");
		btnBenutzerverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: ActionEvent: Funktionalität hinzufügen
			}
		});
		btnBenutzerverwaltung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBenutzerverwaltung.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBenutzerverwaltung.setBackground(Color.WHITE);
		btnBenutzerverwaltung.setBounds(685, 70, 180, 30);
		contentPane.add(btnBenutzerverwaltung);
		
		//Button "CI-Typ Hinzufügen"
		btnCiTypHinzufügen = new JButton("CI-Typ Hinzufügen");
		btnCiTypHinzufügen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: ActionEvent: Funktionalität hinzufügen
			}
		});
		btnCiTypHinzufügen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypHinzufügen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypHinzufügen.setBackground(Color.WHITE);
		btnCiTypHinzufügen.setBounds(880, 120, 180, 50);
		contentPane.add(btnCiTypHinzufügen);
		
		//Button "CI-Typ Ändern"
		btnCiTypAendern = new JButton("CI-Typ Bearbeiten");
		btnCiTypAendern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: ActionEvent: Funktionalität hinzufügen
			}
		});
		btnCiTypAendern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypAendern.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypAendern.setBackground(Color.WHITE);
		btnCiTypAendern.setBounds(880, 180, 180, 50);
		contentPane.add(btnCiTypAendern);
		
		//Button "CI-Typ Löschen"
		btnCiTypLoeschen = new JButton("CI-Typ Löschen");
		btnCiTypLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: ActionEvent: Funktionalität hinzufügen
			}
		});
		btnCiTypLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypLoeschen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypLoeschen.setBackground(Color.WHITE);
		btnCiTypLoeschen.setBounds(880, 240, 180, 50);
		contentPane.add(btnCiTypLoeschen);
		
		lblNewLabel = new JLabel("CI-Typ-\u00DCbersicht (Admin)");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(15, 15, 250, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnAbmelden_1 = new JButton("Abmelden");
		btnAbmelden_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbmelden_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAbmelden_1.setBackground(Color.WHITE);
		btnAbmelden_1.setBounds(880, 70, 180, 30);
		contentPane.add(btnAbmelden_1);
		
		
	}
}
