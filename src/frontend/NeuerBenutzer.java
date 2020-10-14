package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class NeuerBenutzer extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtFieldName;
	private JTextField txtFieldPasswort;

	static NeuerBenutzer frame = new NeuerBenutzer();
	private JLabel lblNewLabel;
	private JLabel lblUsername;
	private JButton btnAbmelden_1;
	private JLabel lblPassword;
	private JLabel lblAdmin;

	/**
	 * Create the frame.
	 */

	public NeuerBenutzer() {
		setBackground(Color.WHITE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("ItemPro - Benutzer hinzufügen");
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

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBorder(new LineBorder(new Color(171, 173, 179)));
		textField.setBounds(17, 60, 600, 50);
		contentPane_1.add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBounds(17, 160, 1029, 513);
		contentPane_1.add(scrollPane);

		txtFieldName = new JTextField();
		txtFieldName.setBounds(10, 90, 300, 20);
		contentPane.add(txtFieldName);
		txtFieldName.setColumns(10);

		txtFieldPasswort = new JTextField();
		txtFieldPasswort.setBounds(10, 140, 300, 20);
		contentPane.add(txtFieldPasswort);
		txtFieldPasswort.setColumns(10);

		

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
					String benutzername = txtFieldName.getText();
					attribute.add(benutzername);
				};
				
				if (txtFieldPasswort.getText().isEmpty()) {
					{
						JOptionPane.showMessageDialog(null, "Passwort fehlt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					String passwort = txtFieldPasswort.getText();
					attribute.add(passwort);
				};
				
				JCheckBox chckbxIsAdmin = new JCheckBox("Ja");
				chckbxIsAdmin.setBackground(Color.WHITE);
				chckbxIsAdmin.setBounds(10, 190, 120, 20);
				contentPane.add(chckbxIsAdmin);
				
				boolean sollAdmin = false;
				if(chckbxIsAdmin.isSelected() == true){sollAdmin = true;}
				else {sollAdmin=false;}
				

				
				

				

				// daten.Message message = hauptprogramm.erstelleCIRecord(CITyp, attribute);

				dispose();

				// MainAdmin.frame.dispose();

				// new MainAdmin().setVisible(true);

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
		
		JCheckBox chckbxIsAdmin = new JCheckBox("Ja");
		chckbxIsAdmin.setBackground(Color.WHITE);
		chckbxIsAdmin.setBounds(10, 190, 120, 20);
		contentPane.add(chckbxIsAdmin);
	}
}