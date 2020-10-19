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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import backend.hauptprogramm;

public class NeuerBenutzer extends JDialog {

	private JPanel contentPane;
	private JTextField txtFieldName;
	private JTextField txtFieldPasswort;

	private JLabel lblNewLabel;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblAdmin;
	String passwort;
	String benutzername;
	boolean sollAdmin;

	/**
	 * Create the frame.
	 */

	public NeuerBenutzer() {
		setBackground(Color.WHITE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("ItemPro - Benutzer hinzufuegen");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 350, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtFieldName = new JTextField();
		txtFieldName.setBounds(10, 90, 300, 20);
		contentPane.add(txtFieldName);
		txtFieldName.setColumns(10);

		txtFieldPasswort = new JTextField();
		txtFieldPasswort.setBounds(10, 140, 300, 20);
		contentPane.add(txtFieldPasswort);
		txtFieldPasswort.setColumns(10);

		JCheckBox chckbxAdmin = new JCheckBox("Ja");
		chckbxAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxAdmin.setBackground(Color.WHITE);
		chckbxAdmin.setBounds(10, 187, 93, 21);
		contentPane.add(chckbxAdmin);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAbbrechen.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAbbrechen.setBackground(Color.WHITE);
		btnAbbrechen.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAbbrechen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnAbbrechen.setBounds(178, 250, 123, 30);
		contentPane.add(btnAbbrechen);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSpeichern.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnSpeichern.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSpeichern.setBackground(Color.WHITE);
		btnSpeichern.setBounds(10, 250, 123, 30);
		contentPane.add(btnSpeichern);

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> attribute = new ArrayList<>();

				if (txtFieldName.getText().isEmpty()) {
					{
						JOptionPane.showMessageDialog(null, "Benutzername fehlt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					benutzername = txtFieldName.getText();
					
				};
				
				if (txtFieldPasswort.getText().isEmpty()) {
					{
						JOptionPane.showMessageDialog(null, "Passwort fehlt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					passwort = txtFieldPasswort.getText();
					
				};
				
				
				sollAdmin = chckbxAdmin.isSelected();
				

				daten.Message message = hauptprogramm.benutzerAnlegen(benutzername, passwort, sollAdmin);

				dispose();

			}
		});

		lblNewLabel = new JLabel("Neuen Benutzer anlegen:");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 20, 200, 20);
		contentPane.add(lblNewLabel);

		lblUsername = new JLabel("Benutzername");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUsername.setBounds(10, 70, 120, 20);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Passwort");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPassword.setBounds(10, 120, 120, 20);
		contentPane.add(lblPassword);
		
		lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAdmin.setBounds(10, 170, 120, 20);
		contentPane.add(lblAdmin);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Ja");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(10, 187, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		
	}
}