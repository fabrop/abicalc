package abicalc;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;				

public class Abicalc extends JFrame{

	private static final long serialVersionUID = 1L;
	
	//Array mit den 4 Halbjahren und den Prüfungen
	static Halbjahr halbjahre[] = new Halbjahr[5];
	//Enthält alle UI Elemente
	JPanel contentPane;		
	//Label für den aktuellen Gesamtschnitt
	static JLabel lbl_Punkte;		

	
	public Abicalc() {
		//Hauptfenster	
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				allesSpeichern();
				e.getWindow().dispose();
			}
		});
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Dunkelgrauer Container für Buttons/Titel
		JPanel panel_title = new JPanel();					
		panel_title.setBackground(Color.DARK_GRAY);
		panel_title.setBounds(10, 10, 764, 44);
		contentPane.add(panel_title);
		panel_title.setLayout(null);
		
		//Titel
		JLabel lblAbicalc = new JLabel("abicalc");			
		lblAbicalc.setBounds(25, -3, 92, 47);
		lblAbicalc.setForeground(Color.WHITE);
		lblAbicalc.setHorizontalAlignment(SwingConstants.LEFT);
		lblAbicalc.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel_title.add(lblAbicalc);
		
		//Button Einstellungen
		JButton btnEinstellungen = new JButton("Einstellungen");		
		btnEinstellungen.setForeground(Color.LIGHT_GRAY);
		btnEinstellungen.setBounds(518, 10, 113, 23);
		panel_title.add(btnEinstellungen);
		//Button Zurücksetzen
		JButton btnZuruecksetzen = new JButton("Zur\u00FCcksetzen");		
		btnZuruecksetzen.setForeground(Color.DARK_GRAY);
		btnZuruecksetzen.setBounds(641, 10, 113, 23);
		btnZuruecksetzen.addActionListener(new ActionListener() {		
	        public void actionPerformed(ActionEvent e) {
	        	allesZuruecksetzen(halbjahre[1].verzeichnis);
	        }
	    });
		panel_title.add(btnZuruecksetzen);
		
		//Button Speichern und Aktualisieren
		JButton btnSpeichern = new JButton("Speichern");		
		btnSpeichern.setForeground(Color.DARK_GRAY);
		btnSpeichern.setBounds(395, 11, 113, 22);
		panel_title.add(btnSpeichern);
		btnSpeichern.addActionListener(new ActionListener() {		
	        public void actionPerformed(ActionEvent e) {
	        	allesSpeichern();
	        }
	    });
		
		//Container für Tabs und Übersicht
		JPanel panel_main = new JPanel();		
		panel_main.setBounds(10, 65, 764, 431);
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		
		
		
		//Tabs für Halbjahre eines Fachs
		JTabbedPane tabbedPane_Halbjahre = new JTabbedPane(JTabbedPane.TOP);
		panel_main.add(tabbedPane_Halbjahre);
		
		halbjahre[0] = new Halbjahr("hj1", "11.1", tabbedPane_Halbjahre, panel_main);
		halbjahre[1] = new Halbjahr("hj2", "11.2", tabbedPane_Halbjahre, panel_main);
		halbjahre[2] = new Halbjahr("hj3", "12.1", tabbedPane_Halbjahre, panel_main);
		halbjahre[3] = new Halbjahr("hj4", "12.2", tabbedPane_Halbjahre, panel_main);
		halbjahre[4] = new Halbjahr("hjP", "Prüfungen", tabbedPane_Halbjahre, panel_main);

		tabbedPane_Halbjahre.setBounds(10, 10, 744, 410);
		
		
		//Dunkelgrauer Container für Gesamtschnitt
		JPanel panel_unten = new JPanel();				
		panel_unten.setBackground(Color.DARK_GRAY);
		panel_unten.setBounds(10, 507, 764, 44);
		contentPane.add(panel_unten);
		panel_unten.setLayout(null);
		
		//Beschriftung Gesamtschnitt
		JLabel lblNotenschnitt = new JLabel("Notenschnitt:");					
		lblNotenschnitt.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNotenschnitt.setForeground(Color.WHITE);
		lblNotenschnitt.setBounds(29, 11, 166, 22);
		panel_unten.add(lblNotenschnitt);
		
		//Label mit Gesamtschnitt als Inhalt
		lbl_Punkte = new JLabel("00");						
		lbl_Punkte.setForeground(Color.WHITE);
		lbl_Punkte.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lbl_Punkte.setBounds(198, 11, 350, 22);
		panel_unten.add(lbl_Punkte);
			
		
	}
	
	
	//Berechnet den Schnitt aller Halbjahre, welche man bisher benutzt hat
	public static double getGesamtSchnitt(){ 
		double gesamtSchnitt;
		//Summe aller Halbjahrespunkte
		double hjSumme = 0;		
		//Anzahl der Halbjahre, in denen bereits Fächer eingetragen sind
		double aktiveHJs = 0;		
		//solange das aktuelle Halbjahr nicht leer ist, werden Schnitte addiert und die Halbjahre mitgezählt
		for(int i = 0; i < halbjahre.length; i++){
			if(!halbjahre[i].faecherliste.isEmpty()){
				
				aktiveHJs++;
				hjSumme = hjSumme + halbjahre[i].getHJSchnitt();
			}
		}
		//Summe aller HJ-Punkte wird durch Anzahl der HJ mit Einträgen geteilt
		gesamtSchnitt = hjSumme / aktiveHJs;
		
		return gesamtSchnitt;
	}
	
	//rechnet Punkte in Noten um
	public static double punkteZuNote(double d){
		//17 entpricht Note 0
		d = (17-d)/3;
		return d;
	}
	
	//rundet doubles auf 2 Nachkommastellen
	public static double runden(double d){
		//mal 100 für gesuchte Stellenanzahl
		d = d*100;
		//auf ganze Zahl runden
		d = (double) Math.round(d);
		//Komma zurückverschieben
		d = d /100;
		return d;
	}
	
	//Double-Variable als Input für JLabel mit Gesamtschnitt
	public static void setzeGesamtSchnitt(double d, double e){				
		lbl_Punkte.setText(runden(e)+" / "+runden(d)+" Punkte");
	}
	
	//Eine Datei wird angelegt, um den ersten Start des Programms zu vermerken
	public static void vermerken(){
		File vermerk = new File(halbjahre[1].myTitle + "/data/aufgerufen.txt");
		//neue Datei wird erstellt, falls noch keine existiert
		try {
			if(!vermerk.exists()){
				vermerk.createNewFile();
			}
			FileWriter writer = new FileWriter(vermerk);
			//Text wird in Datei geschrieben
			writer.write("true");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//löscht alle Dateien, welche von dem Programm generiert wurden
	public void allesZuruecksetzen(String verzeichnis){		
		File zuLoeschen = new File(halbjahre[1].myTitle + "/data/");
		String[] dateienZuLoeschen = zuLoeschen.list();
		if(zuLoeschen.exists()){
			for(int i = 0; i < dateienZuLoeschen.length; i++){
			File dieseDatei = new File(zuLoeschen,dateienZuLoeschen[i]);
			dieseDatei.delete();
			}	
			zuLoeschen.delete();
		}
		for(int i = 0; i < halbjahre.length; i++){ 
			//Linked Lists werden gelöscht
    		halbjahre[i].faecherliste.clear();	
    		//setzt UI zurück
    		halbjahre[i].panel_scrollContent.removeAll();	
    		
    		//setzt Schnitt zurück
    		setzeGesamtSchnitt(0, 0);	
    		
    	}
		//UI wird aktualisiert
    	contentPane.validate();
    	contentPane.repaint();
	}
	
	//Ruft die Speichern-Methode für jedes einzelne Halbjahr auf
	public void allesSpeichern(){		
		for(int i = 0; i < halbjahre.length; i++){
			halbjahre[i].save();
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Abicalc myframe = new Abicalc();
					myframe.setVisible(true);
					myframe.setTitle("" + halbjahre[1].myTitle);
					vermerken();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//beim Programmstart wird der Gesamtschnitt berechnet, Fächerschnitte werden beim Erzeugen der UI generiert
				setzeGesamtSchnitt(getGesamtSchnitt(), punkteZuNote(getGesamtSchnitt()));
				
				
				
			}
		});
		
	}
}
