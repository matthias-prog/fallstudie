package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Login {

	private JFrame frmItemproLogin;
	private JPasswordField passwordField;
	private JTextField txtBenutzername;
	private JLabel lblAnmelden1;
	private JTextField txtAnmeldungFehlgeschlagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmItemproLogin.setVisible(true);
					backend.hauptprogramm.dbVerbindungAufbauen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmItemproLogin = new JFrame();
		frmItemproLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		frmItemproLogin.setPreferredSize(new Dimension(400, 500));
		frmItemproLogin.setBounds(new Rectangle(0, 0, 400, 500));
		frmItemproLogin.setTitle("ItemPro - Login");
		frmItemproLogin.getContentPane().setBackground(Color.WHITE);
		frmItemproLogin.setBounds(100, 100, 400, 500);
		frmItemproLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemproLogin.getContentPane().setLayout(null);
		frmItemproLogin.setLocationRelativeTo(null);

    		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 14));
		passwordField.setName("Passwort");
		passwordField.setToolTipText("Passwort");
		passwordField.setBounds(100, 275, 200, 30);
		frmItemproLogin.getContentPane().add(passwordField);
		
		txtBenutzername = new JTextField();
		txtBenutzername.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtBenutzername.setHorizontalAlignment(SwingConstants.LEFT);
		txtBenutzername.setToolTipText("Benutzername");
		txtBenutzername.setBounds(100, 210, 200, 30);
		frmItemproLogin.getContentPane().add(txtBenutzername);
		txtBenutzername.setColumns(10);
		
		
		Calendar stunde = Calendar.getInstance();
		stunde.get(Calendar.HOUR_OF_DAY);
		String gruss;
		
		//Zeitlich angepasste Begrüßung
		int aktStunde = stunde.get(Calendar.HOUR_OF_DAY);
		if (aktStunde < 11) {
			gruss = "Guten Morgen!";}
		else if(aktStunde < 18) {
			gruss = "Guten Tag!";}
		else {
			gruss = "Guten Abend!";}
		
		JLabel lblWillkommen = new JLabel(gruss);
		lblWillkommen.setHorizontalAlignment(SwingConstants.CENTER);
		lblWillkommen.setBackground(Color.WHITE);
		lblWillkommen.setFont(new Font("Calibri", Font.BOLD, 24));
		lblWillkommen.setBounds(115, 75, 170, 30);
		frmItemproLogin.getContentPane().add(lblWillkommen);
		
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String username = txtBenutzername.getText();
					char [] passwort = passwordField.getPassword();
					String password = String.valueOf(passwort);
				
					daten.Message message = backend.hauptprogramm.versucheLogin (username, password);
					
					if (message.isErfolg()) {
					frmItemproLogin.dispose();
					
					if (backend.hauptprogramm.pruefeAdmin(username)) {
					MainAdmin mainAdmin = new MainAdmin();
					mainAdmin.setVisible(true);
					} else {
					Main main = new Main();
					main.setVisible(true);}}
					else {
						txtAnmeldungFehlgeschlagen.setText(message.getNachricht());
						txtAnmeldungFehlgeschlagen.setEnabled(true);
						txtAnmeldungFehlgeschlagen.setVisible(true);}
				
				}
				catch(Exception a){
					a.printStackTrace();
					System.out.println(a);
					System.out.println("null");
				}
				
			}
		});
		
		btnAnmelden.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAnmelden.setBackground(Color.WHITE);
		btnAnmelden.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAnmelden.setBounds(150, 355, 100, 30);
		frmItemproLogin.getContentPane().add(btnAnmelden);
		
		passwordField.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String username = txtBenutzername.getText();
					char [] passwort = passwordField.getPassword();
					String password = String.valueOf(passwort);
				
					daten.Message message = backend.hauptprogramm.versucheLogin (username, password);
					
					if (message.isErfolg()) {
					frmItemproLogin.dispose();
					
					if (backend.hauptprogramm.pruefeAdmin(username)) {
					MainAdmin mainAdmin = new MainAdmin();
					mainAdmin.setVisible(true);
					} else {
					Main main = new Main();
					main.setVisible(true);}}
					else {
						txtAnmeldungFehlgeschlagen.setEnabled(true);
						txtAnmeldungFehlgeschlagen.setVisible(true);}
				
				}
				catch(Exception a){
					a.printStackTrace();
					System.out.println(a);
					System.out.println("null");
				}
			}
		});
		
		lblAnmelden1 = new JLabel("Melden Sie sich an,");
		lblAnmelden1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAnmelden1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnmelden1.setBackground(Color.WHITE);
		lblAnmelden1.setBounds(125, 115, 150, 20);
		frmItemproLogin.getContentPane().add(lblAnmelden1);
		
		JLabel lblAnmelden2 = new JLabel("um fortzufahren.");
		lblAnmelden2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAnmelden2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnmelden2.setBackground(Color.WHITE);
		lblAnmelden2.setBounds(125, 132, 150, 20);
		frmItemproLogin.getContentPane().add(lblAnmelden2);
		
		JLabel lblBenutzername = new JLabel("Benutzername");
		lblBenutzername.setBackground(Color.WHITE);
		lblBenutzername.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblBenutzername.setBounds(100, 190, 100, 20);
		frmItemproLogin.getContentPane().add(lblBenutzername);
		
		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPasswort.setBackground(Color.WHITE);
		lblPasswort.setBounds(100, 255, 88, 20);
		frmItemproLogin.getContentPane().add(lblPasswort);
		
		txtAnmeldungFehlgeschlagen = new JTextField();
		txtAnmeldungFehlgeschlagen.setEnabled(false);
		txtAnmeldungFehlgeschlagen.setVisible(false);
		txtAnmeldungFehlgeschlagen.setDisabledTextColor(Color.WHITE);
		txtAnmeldungFehlgeschlagen.setBorder(null);
		txtAnmeldungFehlgeschlagen.setCaretColor(Color.WHITE);
		txtAnmeldungFehlgeschlagen.setBackground(Color.WHITE);
		txtAnmeldungFehlgeschlagen.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnmeldungFehlgeschlagen.setText("Anmeldung fehlgeschlagen!");
		txtAnmeldungFehlgeschlagen.setForeground(Color.RED);
		txtAnmeldungFehlgeschlagen.setEditable(false);
		txtAnmeldungFehlgeschlagen.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtAnmeldungFehlgeschlagen.setBounds(113, 320, 175, 15);
		frmItemproLogin.getContentPane().add(txtAnmeldungFehlgeschlagen);
		txtAnmeldungFehlgeschlagen.setColumns(10);
		
	}
	
	public JFrame getFrmItemproLogin() { //Methode wird fï¿½r den Logout benï¿½tigt!
		return frmItemproLogin;
	}
	

}
