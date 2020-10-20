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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import backend.hauptprogramm;

import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NeuerCITyp extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldName;

	private JTextField[] textfelder = new JTextField[15];

	private JLabel lblTitel;
	private JLabel lblAttribute;

	/**
	 * Create the frame.
	 */

	public NeuerCITyp() {
		setTitle("ItemPro - CI-Typ hinzufuegen");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 323, 560);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (int i = 0; i < 15; i++) {
			textfelder[i] = new JTextField();
			int abstand = 95 + i * 25;
			textfelder[i].setBounds(10, abstand, 291, 20);
			contentPane.add(textfelder[i]);
			textfelder[i].setColumns(10);
		}

		textFieldName = new JTextField();
		textFieldName.setToolTipText("Name");
		textFieldName.setFont(new Font("Calibri", Font.PLAIN, 12));
		textFieldName.setBounds(10, 43, 105, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		lblTitel = new JLabel("Neuer CI-Typ");
		lblTitel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitel.setBounds(10, 19, 200, 27);
		contentPane.add(lblTitel);

		lblAttribute = new JLabel("Attribute:");
		lblAttribute.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAttribute.setBounds(10, 74, 82, 14);
		contentPane.add(lblAttribute);

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
		btnAbbrechen.setBounds(178, 485, 123, 30);
		contentPane.add(btnAbbrechen);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSpeichern.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnSpeichern.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSpeichern.setBackground(Color.WHITE);
		btnSpeichern.setBounds(10, 485, 123, 30);
		contentPane.add(btnSpeichern);

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String citypName = "";

				if (textFieldName.getText().isEmpty()) {
					{
						JOptionPane.showMessageDialog(null, "Name des CITypen fehlt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					citypName = textFieldName.getText();
				}

				ArrayList<String> attribute = new ArrayList<>();

				for (int i = 0; i < 15; i++) {
					if (!textfelder[i].getText().isEmpty()) {
						attribute.add(textfelder[i].getText());
					}
				}

				daten.Message message = hauptprogramm.erstelleCITyp(citypName, attribute);
				if (message.isErfolg() == false) {
					JOptionPane.showMessageDialog(null, message.getNachricht(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}

				dispose();

			}
		});

	}
}