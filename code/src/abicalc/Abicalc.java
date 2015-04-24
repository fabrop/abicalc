package abicalc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;

import java.io.*;

import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Component;						

public class Abicalc extends JFrame {

	private JPanel contentPane;

	static JLabel lbl_Punkte;
	private JTextField txt_FachnameHJ1;

	public Abicalc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Hauptfenster
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_title = new JPanel();					//Dunkelgrauer Contaier für Buttons/Titel
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
		btnEinstellungen.setBounds(518, 10, 113, 23);
		panel_title.add(btnEinstellungen);
		
		JButton btnZuruecksetzen = new JButton("Zur\u00FCcksetzen");		//Button Zurücksetzen
		btnZuruecksetzen.setBounds(641, 10, 113, 23);
		panel_title.add(btnZuruecksetzen);
		
		JButton btnSpeichern = new JButton("Speichern");		//Button Speichern und Aktualisieren
		btnSpeichern.setBounds(395, 11, 113, 22);
		panel_title.add(btnSpeichern);
		
		JPanel panel_main = new JPanel();		//Container für Tabs und Übersicht
		panel_main.setBounds(10, 65, 764, 431);
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		
		JTabbedPane tabbedPane_Halbjahre = new JTabbedPane(JTabbedPane.TOP);		//Tabs für Halbjahre eines Fachs
		tabbedPane_Halbjahre.setBounds(10, 10, 744, 410);
		panel_main.add(tabbedPane_Halbjahre);
		
		JPanel panel_HJ1 = new JPanel();		//Tab für erstes HJ
		tabbedPane_Halbjahre.addTab("Halbjahr 11.1", null, panel_HJ1, null);
		panel_HJ1.setLayout(null);
		
		JPanel panel_FaecherHJ1 = new JPanel();		//Container für Fächer
		panel_FaecherHJ1.setBounds(10, 67, 719, 304);
		panel_HJ1.add(panel_FaecherHJ1);
		panel_FaecherHJ1.setBackground(Color.WHITE);
		panel_FaecherHJ1.setLayout(new GridLayout(1, 0, 0, 0));
		
		Box verticalBox_HJ1 = Box.createVerticalBox();		//vertikal angeordnete Fächer
		panel_FaecherHJ1.add(verticalBox_HJ1);
		
		JPanel panel_AddHJ1 = new JPanel();		//HJ1 Panel mit Textfeld und Button
		panel_AddHJ1.setBackground(Color.WHITE);
		panel_AddHJ1.setBounds(10, 11, 719, 45);
		panel_HJ1.add(panel_AddHJ1);
		panel_AddHJ1.setLayout(null);
		
		txt_FachnameHJ1 = new JTextField();			//HJ1 Input für Fachname
		txt_FachnameHJ1.setBounds(10, 11, 216, 23);
		txt_FachnameHJ1.setText("Fachname");
		panel_AddHJ1.add(txt_FachnameHJ1);
		txt_FachnameHJ1.setColumns(10);
		
		JButton button_plusHJ1 = new JButton("+");		//HJ1 Fach hinzufügen Button
		button_plusHJ1.setBounds(236, 11, 41, 23);
		panel_AddHJ1.add(button_plusHJ1);
		
		
		button_plusHJ1.addActionListener(new java.awt.event.ActionListener() {		//Code fürs Fach hinzufügen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	verticalBox_HJ1.add(new FachUI(txt_FachnameHJ1.getText()));
	        }
	    });
			
		
		JPanel panel_HJ2 = new JPanel();
		tabbedPane_Halbjahre.addTab("Halbjahr 11.2", null, panel_HJ2, null);
		
		JPanel panel_HJ3 = new JPanel();
		tabbedPane_Halbjahre.addTab("Halbjahr 12.1", null, panel_HJ3, null);
		
		JPanel panel_HJ4 = new JPanel();
		tabbedPane_Halbjahre.addTab("Halbjahr 12.2", null, panel_HJ4, null);
		
		JPanel panel_Pruefungen = new JPanel();
		tabbedPane_Halbjahre.addTab("Pr\u00FCfungen", null, panel_Pruefungen, null);
		
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
		
		
		/*
		Faecher hj1 = new Faecher();			//erstellt Fächerlisten für die einzelnen Halbjahre 
		Faecher hj2 = new Faecher();
		Faecher hj3 = new Faecher();
		Faecher hj4 = new Faecher();
		Faecher pruefungen = new Faecher();
		*/
	}
	
	public static void setGesamtSchnitt(double d){				//Double-Variable als Input für JLabel mit Gesamtschnitt
		lbl_Punkte.setText(""+d);
	}
	
	public static void neuesFach(String name, Faecher faecherliste){		//Ruft den jeweiligen Konstrukor der Fächerliste auf
		faecherliste.neuesFachEinfuegen(name);
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
				
				
				
				Noten notenliste = new Noten();								//temp tests
				setGesamtSchnitt(notenliste.halbjahresschnitt());
				
				
				
				
				
				
			}
		});
		
	}
}
