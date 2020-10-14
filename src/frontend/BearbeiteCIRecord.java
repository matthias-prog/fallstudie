package frontend;

import java.awt.Color;
import java.awt.Dimension;
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
import daten.CITyp;

public class BearbeiteCIRecord extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_Attribut1;
	private JTextField textField_Attribut2;
	private JTextField textField_Attribut3;
	private JTextField textField_Attribut4;
	private JTextField textField_Attribut5;
	private JTextField textField_Attribut6;
	private JTextField textField_Attribut7;
	private JTextField textField_Attribut8;
	private JTextField textField_Attribut9;
	private JTextField textField_Attribut10;
	private JTextField textField_Attribut11;
	private JTextField textField_Attribut12;
	private JTextField textField_Attribut13;
	private JTextField textField_Attribut14;
	private JTextField textField_Attribut15;
	private JTextField textField_ciRecordName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */

	public BearbeiteCIRecord(CITyp cityp, int RecordID) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("ItemPro - CI-Record hinzuf�gen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 323, 560);
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

		JButton btnSuche = new JButton("\uD83D\uDD0D");
		btnSuche.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		btnSuche.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSuche.setBackground(Color.WHITE);
		btnSuche.setBounds(622, 60, 50, 50);
		contentPane_1.add(btnSuche);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBounds(17, 160, 1029, 513);
		contentPane_1.add(scrollPane);

		textField_Attribut1 = new JTextField();
		textField_Attribut1.setBounds(10, 95, 291, 20);
		contentPane.add(textField_Attribut1);
		textField_Attribut1.setColumns(10);

		textField_Attribut2 = new JTextField();
		textField_Attribut2.setBounds(10, 120, 291, 20);
		contentPane.add(textField_Attribut2);
		textField_Attribut2.setColumns(10);

		textField_Attribut3 = new JTextField();
		textField_Attribut3.setBounds(10, 145, 291, 20);
		contentPane.add(textField_Attribut3);
		textField_Attribut3.setColumns(10);

		textField_Attribut4 = new JTextField();
		textField_Attribut4.setColumns(10);
		textField_Attribut4.setBounds(10, 170, 291, 20);
		contentPane.add(textField_Attribut4);

		textField_Attribut5 = new JTextField();
		textField_Attribut5.setColumns(10);
		textField_Attribut5.setBounds(10, 195, 291, 20);
		contentPane.add(textField_Attribut5);

		textField_Attribut6 = new JTextField();
		textField_Attribut6.setColumns(10);
		textField_Attribut6.setBounds(10, 220, 291, 20);
		contentPane.add(textField_Attribut6);

		textField_Attribut7 = new JTextField();
		textField_Attribut7.setColumns(10);
		textField_Attribut7.setBounds(10, 245, 291, 20);
		contentPane.add(textField_Attribut7);

		textField_Attribut8 = new JTextField();
		textField_Attribut8.setColumns(10);
		textField_Attribut8.setBounds(10, 270, 291, 20);
		contentPane.add(textField_Attribut8);

		textField_Attribut9 = new JTextField();
		textField_Attribut9.setColumns(10);
		textField_Attribut9.setBounds(10, 295, 291, 20);
		contentPane.add(textField_Attribut9);

		textField_Attribut10 = new JTextField();
		textField_Attribut10.setColumns(10);
		textField_Attribut10.setBounds(10, 320, 291, 20);
		contentPane.add(textField_Attribut10);

		textField_Attribut11 = new JTextField();
		textField_Attribut11.setColumns(10);
		textField_Attribut11.setBounds(10, 345, 291, 20);
		contentPane.add(textField_Attribut11);

		textField_Attribut12 = new JTextField();
		textField_Attribut12.setColumns(10);
		textField_Attribut12.setBounds(10, 370, 291, 20);
		contentPane.add(textField_Attribut12);

		textField_Attribut13 = new JTextField();
		textField_Attribut13.setColumns(10);
		textField_Attribut13.setBounds(10, 395, 291, 20);
		contentPane.add(textField_Attribut13);

		textField_Attribut14 = new JTextField();
		textField_Attribut14.setColumns(10);
		textField_Attribut14.setBounds(10, 420, 291, 20);
		contentPane.add(textField_Attribut14);

		textField_Attribut15 = new JTextField();
		textField_Attribut15.setColumns(10);
		textField_Attribut15.setBounds(10, 445, 291, 20);
		contentPane.add(textField_Attribut15);

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
		btnAbbrechen.setBounds(178, 491, 123, 30);
		contentPane.add(btnAbbrechen);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSpeichern.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnSpeichern.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSpeichern.setBackground(Color.WHITE);
		btnSpeichern.setBounds(10, 491, 123, 30);
		contentPane.add(btnSpeichern);

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> attribute = new ArrayList<>();

				if (textField_Attribut1.getText().isEmpty()) {
					{
						JOptionPane.showMessageDialog(null, "Name des CIRecords fehlt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					String cirecordName = textField_ciRecordName.getText();
					attribute.add(cirecordName);
				}

				;

				if (textField_Attribut1.getText().isEmpty()) {
				} else {
					String attribut1 = textField_Attribut1.getText();
					attribute.add(attribut1);
				}

				if (textField_Attribut2.getText().isEmpty()) {
				} else {
					String attribut2 = textField_Attribut2.getText();
					attribute.add(attribut2);
				}

				if (textField_Attribut3.getText().isEmpty()) {
				} else {
					String attribut3 = textField_Attribut3.getText();
					attribute.add(attribut3);
				}

				if (textField_Attribut4.getText().isEmpty()) {
				} else {
					String attribut4 = textField_Attribut4.getText();
					attribute.add(attribut4);
				}

				if (textField_Attribut5.getText().isEmpty()) {
				} else {
					String attribut5 = textField_Attribut5.getText();
					attribute.add(attribut5);
				}

				if (textField_Attribut6.getText().isEmpty()) {
				} else {
					String attribut6 = textField_Attribut6.getText();
					attribute.add(attribut6);
				}

				if (textField_Attribut7.getText().isEmpty()) {
				} else {
					String attribut7 = textField_Attribut7.getText();
					attribute.add(attribut7);
				}

				if (textField_Attribut8.getText().isEmpty()) {
				} else {
					String attribut8 = textField_Attribut8.getText();
					attribute.add(attribut8);
				}

				if (textField_Attribut9.getText().isEmpty()) {
				} else {
					String attribut9 = textField_Attribut9.getText();
					attribute.add(attribut9);
				}

				if (textField_Attribut10.getText().isEmpty()) {
				} else {
					String attribut10 = textField_Attribut10.getText();
					attribute.add(attribut10);
				}

				if (textField_Attribut11.getText().isEmpty()) {
				} else {
					String attribut11 = textField_Attribut11.getText();
					attribute.add(attribut11);
				}

				if (textField_Attribut12.getText().isEmpty()) {
				} else {
					String attribut12 = textField_Attribut12.getText();
					attribute.add(attribut12);
				}

				if (textField_Attribut13.getText().isEmpty()) {
				} else {
					String attribut13 = textField_Attribut13.getText();
					attribute.add(attribut13);
				}

				if (textField_Attribut14.getText().isEmpty()) {
				} else {
					String attribut14 = textField_Attribut14.getText();
					attribute.add(attribut14);
				}

				if (textField_Attribut15.getText().isEmpty()) {
				} else {
					String attribut15 = textField_Attribut15.getText();
					attribute.add(attribut15);
				}

				daten.Message message = hauptprogramm.erstelleCIRecord(cityp.getCItypName(), attribute);

				dispose();

				// MainAdmin.frame.dispose();

				// new MainAdmin().setVisible(true);

			}
		});

		textField_ciRecordName = new JTextField();
		textField_ciRecordName.setBounds(10, 43, 105, 20);
		contentPane.add(textField_ciRecordName);
		textField_ciRecordName.setColumns(10);

		lblNewLabel = new JLabel("Neuer CI-Record");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 19, 105, 21);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Attribute");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 74, 82, 14);
		contentPane.add(lblNewLabel_1);
	}
}
