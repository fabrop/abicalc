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
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.io.*;						

public class Abicalc extends JFrame {

	private JPanel contentPane;

	static JLabel lbl_Punkte;
	
	/*
	 * Anfang UI
	 */
	
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
		btnEinstellungen.setBounds(526, 10, 109, 23);
		panel_title.add(btnEinstellungen);
		
		JButton btnZuruecksetzen = new JButton("Zur\u00FCcksetzen");		//Button Zurücksetzen
		btnZuruecksetzen.setBounds(645, 10, 109, 23);
		panel_title.add(btnZuruecksetzen);
		
		JButton btnSpeichern = new JButton("Speichern");		//Button Speichern und Aktualisieren
		btnSpeichern.setBounds(412, 11, 109, 22);
		panel_title.add(btnSpeichern);
		
		JPanel panel_main = new JPanel();		//Container für Tabs und Übersicht
		panel_main.setBounds(10, 65, 764, 431);
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		
		JTabbedPane tabbedPane_Halbjahre = new JTabbedPane(JTabbedPane.TOP);		//Tabs für Halbjahre eines Fachs
		tabbedPane_Halbjahre.setBounds(10, 10, 744, 410);
		panel_main.add(tabbedPane_Halbjahre);
		
		JPanel panel_HJ1 = new JPanel();
		tabbedPane_Halbjahre.addTab("Halbjahr 11.1", null, panel_HJ1, null);
		
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
	}
	
	/*
	 * Ende UI
	 */
	
	public static void setGesamtSchnitt(double d){				//Double-Variable als Input für JLabel mit Gesamtschnitt
		lbl_Punkte.setText(""+d);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Abicalc frame = new Abicalc();
					frame.setVisible(true);
					
					//Hauptprogramm
					
					Noten notenliste = new Noten();
					setGesamtSchnitt(notenliste.halbjahresschnitt());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
