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
import daten.CIRecord;
import daten.CITyp;

public class BearbeiteCIRecord extends JDialog {

	private JPanel contentPane;
	private JTextField[] textfelder = new JTextField[15];
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

		ArrayList<CIRecord> cirecords = backend.hauptprogramm.holeAlleRecordsVonCITyp(cityp.getCItypName());
		CIRecord cirecord = null;
		for (CIRecord cir : cirecords) {
			if (cir.getCIRecordID() == RecordID) {
				cirecord = cir;
			}
		}

		for (int i = 0; i < cityp.getAttributnamen().size(); i++) {
			textfelder[i] = new JTextField(cirecord.getAttribute().get(i));
			textfelder[i].setBounds(10, 95 + i * 25, 291, 20);
			contentPane.add(textfelder[i]);
			textfelder[i].setColumns(10);
		}

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

				for (int i = 0; i < cityp.getAttributnamen().size(); i++) {
					if (!textfelder[i].getText().isEmpty()) {
						attribute.add(textfelder[i].getText());
					}
				}
				daten.Message message = hauptprogramm.aktualisiereCIRecord(cityp.getCItypName(), RecordID, attribute);

				dispose();
			}
		});

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
