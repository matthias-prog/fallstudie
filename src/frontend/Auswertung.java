package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Auswertung extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Auswertung frame = new Auswertung();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Auswertung() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.setMinimumSize(new Dimension(1080, 720));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAuswertung = new JLabel("Auswertung");
		lblAuswertung.setFont(new Font("Calibri", Font.BOLD, 24));
		lblAuswertung.setBounds(10, 11, 180, 30);
		contentPane.add(lblAuswertung);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 14));
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(10, 52, 1048, 220);
		contentPane.add(textArea);
	}
}
