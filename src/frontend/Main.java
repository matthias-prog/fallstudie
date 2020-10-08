package frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import daten.CITyp;

public class Main extends JFrame {

	private JFrame mainFrame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtSuchfeld;
	private JTable tableCITypen;
	private JButton btnAbmelden;
	private JButton btnSuche;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		mainFrame = new JFrame();
		
		setTitle("ItemPro - Startseite");
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(1080, 720));
		setBounds(new Rectangle(0, 0, 1080, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(1080, 720));
		contentPane.setBounds(new Rectangle(0, 0, 1080, 720));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Image img = new ImageIcon(this.getClass().getResource("/Favicon.png")).getImage();
		//mainFrame.setIconImage(img);

		
		txtSuchfeld = new JTextField();
		txtSuchfeld.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSuchfeld.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtSuchfeld.setBounds(17, 60, 600, 50);
		contentPane.add(txtSuchfeld);
		txtSuchfeld.setColumns(10);
		
		btnAbmelden = new JButton("Abmelden");
		btnAbmelden.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbmelden.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAbmelden.setBackground(Color.WHITE);
		btnAbmelden.setBounds(861, 60, 185, 50);
		contentPane.add(btnAbmelden);
		
		btnSuche = new JButton("\uD83D\uDD0D");
		btnSuche.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		btnSuche.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSuche.setBackground(Color.WHITE);
		btnSuche.setBounds(622, 60, 50, 50);
		contentPane.add(btnSuche);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBounds(17, 160, 1029, 513);
		contentPane.add(scrollPane);
		
		
		ArrayList<CITyp> listeCITypen = backend.hauptprogramm.holeAlleCITypen();
		
		String [][] array = new String [listeCITypen.size()+1][17];
		Vector row = new Vector(); 
		
		for (int i=0; i < listeCITypen.size(); i ++) {
			CITyp cityp = listeCITypen.get(i);
			array[i][0] = String.valueOf(cityp.getCItypID());
			array[i][1] = cityp.getCItypName();
			row.add(String.valueOf(cityp.getCItypID()));
			row.add(cityp.getCItypName());
			
		            for (int j = 0; j < cityp.getAttributnamen().size(); j++) {
		               array[i][j] = cityp.getAttributnamen().get(j);
		               System.out.println(cityp.getAttributnamen().get(j));
		               row.add(cityp.getAttributnamen().get(j));
		            }
			
		}
		
		//TABelle ist nicht sichtbar... wieso?????
		tableCITypen = new JTable();
		scrollPane.setViewportView(tableCITypen);
		tableCITypen.setRowHeight(30);
		tableCITypen.setName("");
		tableCITypen.setOpaque(false);
		tableCITypen.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		 DefaultTableModel tableCITypen = new DefaultTableModel(); 
		 tableCITypen.addColumn("ID"); 
		 tableCITypen.addColumn("Name"); 
		 tableCITypen.addColumn("Attribut1"); 
		 tableCITypen.addColumn("Attribut2"); 
		 tableCITypen.addColumn("Attribut3"); 
		 tableCITypen.addColumn("Attribut4"); 
		 tableCITypen.addColumn("Attribut5"); 
		 tableCITypen.addColumn("Attribut6"); 
		 tableCITypen.addColumn("Attribut7"); 
		 tableCITypen.addColumn("Attribut8"); 
		 tableCITypen.addColumn("Attribut9"); 
		 tableCITypen.addColumn("Attribut10"); 
		 tableCITypen.addColumn("Attribut11"); 
		 tableCITypen.addColumn("Attribut12"); 
		 tableCITypen.addColumn("Attribut13"); 
		 tableCITypen.addColumn("Attribut14"); 
		 tableCITypen.addColumn("Attribut15"); 
	     JTable table = new JTable(tableCITypen); 	
	     
	     tableCITypen.addRow(row); 		
		
		
	}
}
