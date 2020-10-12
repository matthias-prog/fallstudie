package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import backend.hauptprogramm;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class übersicht extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					übersicht frame = new übersicht();
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
	public übersicht() {
		setTitle("ItemPro - CI-Record");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Favicon.png")));
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("CI-Record l\u00F6schen");
		
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				Object ciRecordID = table.getModel().getValueAt(selectedRow, 0);
				int recordID = Integer.valueOf((String) ciRecordID);
				
				String cityp = "";
				
				backend.hauptprogramm.loescheCIRecord(cityp, recordID);
			}
		});
		btnNewButton.setBounds(880, 240, 180, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Abmelden");
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(880, 70, 180, 30);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 855, 450);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Attribut 1", "Attribut 2", "Attribut 3", "Attribut 4", "Attribut 5", "Attribut 6"
			}
		));
		
		JButton btnNewButton_2 = new JButton("CI-Record bearbeiten");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int selectedRow = table.getSelectedRow();
					Object ciRecordID = table.getModel().getValueAt(selectedRow, 0);
					int recordID = Integer.valueOf((String) ciRecordID);
					// NeuerCIRecord neuerCIRecord = new NeuerCIRecord();
					//neuerCIRecord.setVisible(true);
				
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(880, 180, 180, 50);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("CI-Record anlegen");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// NeuerCIRecord neuerCIRecord = new NeuerCIRecord();
					//neuerCIRecord.setVisible(true);
				
				}
				catch(Exception a){
					System.out.println("null");
				}
				
			}
		});
		btnNewButton_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(880, 120, 180, 50);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("CI-Records");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(15, 15, 230, 30);
		contentPane.add(lblNewLabel);
	}
}
