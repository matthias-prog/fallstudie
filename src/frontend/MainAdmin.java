package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import daten.CITyp;
import javax.swing.ImageIcon;

//import com.sun.tools.javac.comp.Todo;

public class MainAdmin extends JFrame {

	private JFrame mainAdminFrame;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableCITypen;
	private JScrollPane scrollPane;
	private JButton btnCiTypHinzufuegen;
	private JButton btnCiTypAendern;
	private JButton btnCiTypLoeschen;
	private JLabel lblNewLabel;
	
	static MainAdmin frame = new MainAdmin();
	private JLabel lblNewLabel_1;
	private JLabel lblWhlenSieEinen;

	
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
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				CIRecordAnsicht records = new CIRecordAnsicht();
				records.setVisible(true);
			}
		});
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
		
		/* funktioniert nicht
		tableCITypen.getModel().addTableModelListener(new TableModelListener() {

			  public void tableChanged(TableModelEvent e) {
				  tableCITypen.repaint();
			  }
			});
		*/
		
		//Button "Benutzerverwaltung"
		JButton btnBenutzerverwaltung = new JButton("Benutzerverwaltung");
		btnBenutzerverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Benutzerverwaltung benutzerverwaltung = new Benutzerverwaltung();
				benutzerverwaltung.setVisible(true);
				//TODO: ActionEvent: Funktionalitï¿½t hinzufï¿½gen
			}
		});
		btnBenutzerverwaltung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBenutzerverwaltung.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBenutzerverwaltung.setBackground(Color.WHITE);
		btnBenutzerverwaltung.setBounds(685, 70, 180, 30);
		contentPane.add(btnBenutzerverwaltung);
		
		//Button "CI-Typ Hinzufï¿½gen"
		btnCiTypHinzufuegen = new JButton("CI-Typ Hinzufuegen");
		btnCiTypHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NeuerCITyp neuerCITyp = new NeuerCITyp();
				neuerCITyp.setVisible(true);
				
			}
		});
		btnCiTypHinzufuegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypHinzufuegen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypHinzufuegen.setBackground(Color.WHITE);
		btnCiTypHinzufuegen.setBounds(880, 120, 180, 50);
		contentPane.add(btnCiTypHinzufuegen);
		
		//Button "CI-Typ ï¿½ndern"
		btnCiTypAendern = new JButton("CI-Typ Bearbeiten");
		btnCiTypAendern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NeuerCITyp neuerCITyp = new NeuerCITyp();
				neuerCITyp.setVisible(true);
			}
		});
		btnCiTypAendern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypAendern.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypAendern.setBackground(Color.WHITE);
		btnCiTypAendern.setBounds(880, 180, 180, 50);
		contentPane.add(btnCiTypAendern);
		
		//Button "CI-Typ Lï¿½schen"
		btnCiTypLoeschen = new JButton("CI-Typ Loeschen");
		btnCiTypLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Methode löscheCITyp

			}
		});
		btnCiTypLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCiTypLoeschen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCiTypLoeschen.setBackground(Color.WHITE);
		btnCiTypLoeschen.setBounds(880, 240, 180, 50);
		contentPane.add(btnCiTypLoeschen);
		
		lblNewLabel = new JLabel("CI-Typ-Uebersicht (Admin)");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(15, 15, 354, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnAbmelden_1 = new JButton("Abmelden");
		btnAbmelden_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.getFrmItemproLogin().setVisible(true); // öffnet das Login-Fenster
				backend.hauptprogramm.logout(); // aktueller Benutzer wird im Backend abgemeldet bzw. "vergessen"
			}
		});
		
		btnAbmelden_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbmelden_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAbmelden_1.setBackground(Color.WHITE);
		btnAbmelden_1.setBounds(880, 70, 180, 30);
		contentPane.add(btnAbmelden_1);
		
		lblNewLabel_1 = new JLabel("Hier sehen Sie eine Uebersicht ueber alle CI-Typen.");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 70, 300, 20);
		contentPane.add(lblNewLabel_1);
		
		lblWhlenSieEinen = new JLabel("Waehlen Sie einen CI-Typ aus um die zugehoerigen Records anzuzeigen.");
		lblWhlenSieEinen.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblWhlenSieEinen.setBackground(Color.WHITE);
		lblWhlenSieEinen.setBounds(10, 90, 450, 20);
		contentPane.add(lblWhlenSieEinen);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MainAdmin.class.getResource("/img/refresh.png")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose(); //this will close frame i.e. NewJFrame

				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(645, 70, 30, 30);
		contentPane.add(btnNewButton);
		
		
	}
}