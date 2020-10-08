package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmItemproLogin;
	private JPasswordField passwordField;
	private JTextField txtBenutzername;
	private JLabel lblAnmelden1;
	private JTable table;
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
		frmItemproLogin.setPreferredSize(new Dimension(400, 500));
		frmItemproLogin.setBounds(new Rectangle(0, 0, 400, 500));
		frmItemproLogin.setTitle("ItemPro - Login");
		frmItemproLogin.getContentPane().setBackground(Color.WHITE);
		frmItemproLogin.setBounds(100, 100, 400, 500);
		frmItemproLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemproLogin.getContentPane().setLayout(null);
		//Image img = new ImageIcon(this.getClass().getResource("/Favicon.png")).getImage();
		//frmItemproLogin.setIconImage(img);
		
		passwordField = new JPasswordField();
		passwordField.setName("Passwort");
		passwordField.setToolTipText("Passwort");
		passwordField.setBounds(100, 250, 200, 30);
		frmItemproLogin.getContentPane().add(passwordField);
		
		txtBenutzername = new JTextField();
		txtBenutzername.setHorizontalAlignment(SwingConstants.LEFT);
		txtBenutzername.setToolTipText("Benutzername");
		txtBenutzername.setBounds(100, 185, 200, 30);
		frmItemproLogin.getContentPane().add(txtBenutzername);
		txtBenutzername.setColumns(10);
		
		JLabel lblWillkommen = new JLabel("Willkommen");
		lblWillkommen.setHorizontalAlignment(SwingConstants.CENTER);
		lblWillkommen.setBackground(Color.WHITE);
		lblWillkommen.setFont(new Font("Calibri", Font.BOLD, 24));
		lblWillkommen.setBounds(125, 75, 150, 30);
		frmItemproLogin.getContentPane().add(lblWillkommen);
		
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String username = txtBenutzername.getText();
					String password = passwordField.getText();
					System.out.println("Benutzername: " + username + " Passwort: " + password);
					frmItemproLogin.dispose();
					Main main = new Main();
					main.setVisible(true);
				
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		btnAnmelden.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAnmelden.setBackground(Color.WHITE);
		btnAnmelden.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnAnmelden.setBounds(150, 330, 100, 30);
		frmItemproLogin.getContentPane().add(btnAnmelden);
		
		lblAnmelden1 = new JLabel("Melden Sie sich an,");
		lblAnmelden1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAnmelden1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnmelden1.setBackground(Color.WHITE);
		lblAnmelden1.setBounds(125, 115, 150, 15);
		frmItemproLogin.getContentPane().add(lblAnmelden1);
		
		JLabel lblAnmelden2 = new JLabel("um fortzufahren.");
		lblAnmelden2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAnmelden2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnmelden2.setBackground(Color.WHITE);
		lblAnmelden2.setBounds(125, 130, 150, 15);
		frmItemproLogin.getContentPane().add(lblAnmelden2);
		
		JLabel lblBenutzername = new JLabel("Benutzername");
		lblBenutzername.setBackground(Color.WHITE);
		lblBenutzername.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblBenutzername.setBounds(100, 170, 88, 10);
		frmItemproLogin.getContentPane().add(lblBenutzername);
		
		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblPasswort.setBackground(Color.WHITE);
		lblPasswort.setBounds(100, 235, 88, 10);
		frmItemproLogin.getContentPane().add(lblPasswort);
		
		txtAnmeldungFehlgeschlagen = new JTextField();
		txtAnmeldungFehlgeschlagen.setDisabledTextColor(Color.WHITE);
		txtAnmeldungFehlgeschlagen.setBorder(null);
		txtAnmeldungFehlgeschlagen.setCaretColor(Color.WHITE);
		txtAnmeldungFehlgeschlagen.setBackground(Color.WHITE);
		txtAnmeldungFehlgeschlagen.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnmeldungFehlgeschlagen.setText("Anmeldung fehlgeschlagen!");
		txtAnmeldungFehlgeschlagen.setForeground(Color.RED);
		txtAnmeldungFehlgeschlagen.setEditable(false);
		txtAnmeldungFehlgeschlagen.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtAnmeldungFehlgeschlagen.setBounds(113, 290, 175, 15);
		frmItemproLogin.getContentPane().add(txtAnmeldungFehlgeschlagen);
		txtAnmeldungFehlgeschlagen.setColumns(10);
		
		
	}
}