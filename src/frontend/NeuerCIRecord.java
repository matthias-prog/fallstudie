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

public class NeuerCIRecord extends JDialog {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField[] textfelder = new JTextField[15];
	private JLabel[] attributNamen = new JLabel[15];

	/**
	 * Create the frame.
	 */

	public NeuerCIRecord(CITyp cityp) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("ItemPro - CI-Record hinzufügen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		int hoehe = 84 + cityp.getAttributnamen().size() * 45+70;
		setBounds(100, 100, 323, hoehe);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (int i = 0; i < cityp.getAttributnamen().size(); i++) {
			textfelder[i] = new JTextField();
			int abstand = 84 + i * 45;
			textfelder[i].setBounds(10, abstand, 291, 20);
			contentPane.add(textfelder[i]);
			textfelder[i].setColumns(10);

			attributNamen[i] = new JLabel(cityp.getAttributnamen().get(i) + ":");
			attributNamen[i].setFont(new Font("Calibri", Font.PLAIN, 14));
			attributNamen[i].setBounds(10, abstand - 14, 290, 14);
			contentPane.add(attributNamen[i]);
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
		btnAbbrechen.setBounds(178,hoehe-80, 123, 30);
		contentPane.add(btnAbbrechen);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSpeichern.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnSpeichern.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSpeichern.setBackground(Color.WHITE);
		btnSpeichern.setBounds(10, hoehe-80, 123, 30);
		contentPane.add(btnSpeichern);

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> attribute = new ArrayList<>();
				
				
				for (int i = 0; i < cityp.getAttributnamen().size(); i++) {
					if (!textfelder[i].getText().isEmpty()) {
						attribute.add(textfelder[i].getText());
					}
				}

				daten.Message message = hauptprogramm.erstelleCIRecord(cityp.getCItypName(), attribute);

				dispose();

			}
		});


		lblNewLabel = new JLabel("Neuer CI-Record");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 19, 105, 21);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Attribute:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 44, 82, 14);
		contentPane.add(lblNewLabel_1);
	}
}
