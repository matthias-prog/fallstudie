package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
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
import daten.Benutzer;
import daten.CIRecord;
import daten.CITyp;

public class BearbeiteBenutzer extends JDialog {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	String passwort;
	String benutzername;
	boolean sollAdmin;

	/**
	 * Create the frame.
	 */

	public BearbeiteBenutzer(int BenutzerID) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("ItemPro - Benutzer bearbeiten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 350, 350);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setMaximumSize(new Dimension(1080, 720));
		contentPane_1.setBounds(new Rectangle(172, 19, 1, 1));
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.WHITE);
		contentPane.add(contentPane_1);
		
		JTextField txtFieldName = new JTextField(Benutzer.getBenutzerName(BenutzerID));
		txtFieldName.setBounds(10, 90, 300, 20);
		contentPane.add(txtFieldName);
		txtFieldName.setColumns(10);

		JTextField txtFieldPasswort = new JTextField(Benutzer.getPasswort(BenutzerID));
		txtFieldPasswort.setBounds(10, 140, 300, 20);
		contentPane.add(txtFieldPasswort);
		txtFieldPasswort.setColumns(10);

		JCheckBox chckbxAdmin = new JCheckBox("Ja");
		chckbxAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxAdmin.setBackground(Color.WHITE);
		chckbxAdmin.setBounds(10, 187, 93, 21);
		chckbxAdmin.setSelected(Benutzer.getIstAdminb(BenutzerID));
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
		btnAbbrechen.setBounds(178,250, 123, 30);
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
				
				daten.Message message = hauptprogramm.benutzerNameVeraendern(BenutzerID, benutzername);
				daten.Message message2 = hauptprogramm.benutzerPasswortVeraendern(BenutzerID, passwort);
				daten.Message message3 = hauptprogramm.benutzerRechtVeraendern(BenutzerID, sollAdmin);

				dispose();
			}
			
		});

		lblNewLabel = new JLabel("Benutzer bearbeiten:");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 20, 200, 20);
		contentPane.add(lblNewLabel);	
		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Ja");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(10, 187, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JLabel lblNutzername = new JLabel("Benutzername");
		lblNutzername.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNutzername.setBounds(10, 70, 120, 20);
		contentPane.add(lblNutzername);
		
		JLabel lblPasswort1 = new JLabel("Passwort");
		lblPasswort1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPasswort1.setBounds(10, 120, 120, 20);
		contentPane.add(lblPasswort1);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAdmin.setBounds(10, 170, 120, 20);
		contentPane.add(lblAdmin);
	}
}
