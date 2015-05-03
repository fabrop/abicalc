package abicalc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JScrollPane;					

public class Abicalc extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	static Halbjahr halbjahre[] = new Halbjahr[5];
	JPanel contentPane;		//Enthält alle anderen Elemente

	static JLabel lbl_Punkte;		//aktueller Gesamtschnitt
	/*
	JTextField txt_FachnameHJ1;		//Textfeld zum Hinzufügen/Benennen von Fächern	
	JTextField txt_FachnameHJ2;
	JTextField txt_FachnameHJ3;
	JTextField txt_FachnameHJ4;
	JTextField txt_FachnamePruefungen;

	static JPanel panel_FaecherHJ1 = new JPanel();		//Enthält scrollbaren Bereich
	static JPanel panel_FaecherHJ2 = new JPanel();
	static JPanel panel_FaecherHJ3 = new JPanel();
	static JPanel panel_FaecherHJ4 = new JPanel();
	static JPanel panel_FaecherPruefungen = new JPanel();	
	
	static JPanel panel_scrollContentHJ1 = new JPanel();		//Scrollbarer Bereich mit Fächern
	static JPanel panel_scrollContentHJ2 = new JPanel();
	static JPanel panel_scrollContentHJ3 = new JPanel();
	static JPanel panel_scrollContentHJ4 = new JPanel();
	static JPanel panel_scrollContentPruefungen = new JPanel();
	
	JTabbedPane tabbedPane_Halbjahre = new JTabbedPane(JTabbedPane.TOP);
	
	*/
	
	public Abicalc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Hauptfenster
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_title = new JPanel();					//Dunkelgrauer Container für Buttons/Titel
		panel_title.setBackground(Color.DARK_GRAY);
		panel_title.setBounds(10, 10, 764, 44);
		contentPane.add(panel_title);
		panel_title.setLayout(null);
		
		JLabel lblAbicalc = new JLabel("abicalc");			//Titel
		lblAbicalc.setBounds(25, -3, 92, 47);
		lblAbicalc.setForeground(Color.WHITE);
		lblAbicalc.setHorizontalAlignment(SwingConstants.LEFT);
		lblAbicalc.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel_title.add(lblAbicalc);
		
		JButton btnEinstellungen = new JButton("Einstellungen");		//Button Einstellungen
		btnEinstellungen.setForeground(Color.LIGHT_GRAY);
		btnEinstellungen.setBounds(518, 10, 113, 23);
		panel_title.add(btnEinstellungen);
		
		JButton btnZuruecksetzen = new JButton("Zur\u00FCcksetzen");		//Button Zurücksetzen
		btnZuruecksetzen.setForeground(Color.LIGHT_GRAY);
		btnZuruecksetzen.setBounds(641, 10, 113, 23);
		panel_title.add(btnZuruecksetzen);
		
		JButton btnSpeichern = new JButton("Speichern");		//Button Speichern und Aktualisieren
		btnSpeichern.setForeground(Color.LIGHT_GRAY);
		btnSpeichern.setBounds(395, 11, 113, 22);
		panel_title.add(btnSpeichern);
		
		JPanel panel_main = new JPanel();		//Container für Tabs und Übersicht
		panel_main.setBounds(10, 65, 764, 431);
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		
		
		
		
		
				//Tabs für Halbjahre eines Fachs
	
		
		
		Halbjahr hj1 = new Halbjahr("11.1");
		Halbjahr hj2 = new Halbjahr("11.2");
		Halbjahr hj3 = new Halbjahr("12.1");
		Halbjahr hj4 = new Halbjahr("12.2");
		Halbjahr hjP = new Halbjahr("Prüfungen");
		
		
		
		JPanel panel_unten = new JPanel();				//Dunkelgrauer Container für Gesamtschnitt
		panel_unten.setBackground(Color.DARK_GRAY);
		panel_unten.setBounds(10, 507, 764, 44);
		contentPane.add(panel_unten);
		panel_unten.setLayout(null);
		
		JLabel lblNotenschnitt = new JLabel("Notenschnitt:");					//Beschriftung Gesamtschnitt
		lblNotenschnitt.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNotenschnitt.setForeground(Color.WHITE);
		lblNotenschnitt.setBounds(29, 11, 166, 22);
		panel_unten.add(lblNotenschnitt);
		
		lbl_Punkte = new JLabel("00");						//Label mit Gesamtschnitt als Inhalt
		lbl_Punkte.setForeground(Color.WHITE);
		lbl_Punkte.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lbl_Punkte.setBounds(198, 11, 149, 22);
		panel_unten.add(lbl_Punkte);
		
		
	}
	
	
	public static double getGesamtSchnitt(){
		double gesamtSchnitt = (halbjahre[1].getHJSchnitt() + halbjahre[2].getHJSchnitt()
				+ halbjahre[3].getHJSchnitt() + halbjahre[4].getHJSchnitt()
				+ halbjahre[5].getHJSchnitt() + halbjahre[5].getHJSchnitt()) / 6;
		return gesamtSchnitt;
	}
	
	public static void setGesamtSchnitt(double d){				//Double-Variable als Input für JLabel mit Gesamtschnitt
		lbl_Punkte.setText(""+d);
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Abicalc frame = new Abicalc();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				//setGesamtSchnitt(getGesamtSchnitt());
				
				
			}
		});
		
	}
}
