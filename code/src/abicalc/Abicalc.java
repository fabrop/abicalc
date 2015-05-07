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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JTabbedPane;				

public class Abicalc extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	static Halbjahr halbjahre[] = new Halbjahr[5];
	JPanel contentPane;		//Enthält alle anderen Elemente

	static JLabel lbl_Punkte;		//aktueller Gesamtschnitt

	
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
		btnZuruecksetzen.setForeground(Color.DARK_GRAY);
		btnZuruecksetzen.setBounds(641, 10, 113, 23);
		btnZuruecksetzen.addActionListener(new ActionListener() {		
	        public void actionPerformed(ActionEvent e) {
	        	allesZuruecksetzen(halbjahre[1].verzeichnis);
	        }
	    });
		panel_title.add(btnZuruecksetzen);
		
		JButton btnSpeichern = new JButton("Speichern");		//Button Speichern und Aktualisieren
		btnSpeichern.setForeground(Color.DARK_GRAY);
		btnSpeichern.setBounds(395, 11, 113, 22);
		panel_title.add(btnSpeichern);
		btnSpeichern.addActionListener(new ActionListener() {		
	        public void actionPerformed(ActionEvent e) {
	        	allesSpeichern();
	        }
	    });
		
		JPanel panel_main = new JPanel();		//Container für Tabs und Übersicht
		panel_main.setBounds(10, 65, 764, 431);
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		
		
		
		
				//Tabs für Halbjahre eines Fachs
	
		JTabbedPane tabbedPane_Halbjahre = new JTabbedPane(JTabbedPane.TOP);
		panel_main.add(tabbedPane_Halbjahre);
		
		//Halbjahr[] halbjahre = new Halbjahr[5];
		halbjahre[0] = new Halbjahr("hj1", "11.1", tabbedPane_Halbjahre, panel_main);
		halbjahre[1] = new Halbjahr("hj2", "11.2", tabbedPane_Halbjahre, panel_main);
		halbjahre[2] = new Halbjahr("hj3", "12.1", tabbedPane_Halbjahre, panel_main);
		halbjahre[3] = new Halbjahr("hj4", "12.2", tabbedPane_Halbjahre, panel_main);
		halbjahre[4] = new Halbjahr("hjP", "Prüfungen", tabbedPane_Halbjahre, panel_main);


		tabbedPane_Halbjahre.setBounds(10, 10, 744, 410);
		
		
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
		lbl_Punkte.setBounds(198, 11, 350, 22);
		panel_unten.add(lbl_Punkte);
			
		
	}
	
	

	public static double getGesamtSchnitt(){ //Berechnet den Schnitt aller Halbjahre, welche man bisher benutzt hat
		double gesamtSchnitt;
		double hjSumme = 0;		//Summe aller Halbjahrespunkte
		double aktiveHJs = 0;		//Anzahl der Halbjahre, in denen bereits Fächer eingetragen sind
		for(int i = 0; i < halbjahre.length; i++){
			if(!halbjahre[i].faecherliste.isEmpty()){
				
				aktiveHJs++;
				hjSumme = hjSumme + halbjahre[i].getHJSchnitt();
			}
		}
		gesamtSchnitt = hjSumme / aktiveHJs;
		
		return gesamtSchnitt;
	}
	
	public static double punkteZuNote(double d){		//rechnet Punkte in Noten um
		d = (17-d)/3;
		return d;
	}
	
	public static double runden(double d){		//rundet doubles auf 2 Nachkommastellen
		d = d*100;
		d = (double) Math.round(d);
		d = d /100;
		return d;
	}
	
	public static void setzeGesamtSchnitt(double d, double e){				//Double-Variable als Input für JLabel mit Gesamtschnitt
		lbl_Punkte.setText(runden(e)+" / "+runden(d)+" Punkte");
	}
	
	public static void vermerken(){ //Eine Datei wird angelegt, um den ersten Start des Programms zu vermerken
		String verzeichnis = Halbjahr.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File vermerk = new File(verzeichnis + "/data/aufgerufen.txt");
		try {
			if(!vermerk.exists()){
				vermerk.createNewFile();
			}
			FileWriter writer = new FileWriter(vermerk);
			writer.write("true");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void allesZuruecksetzen(String verzeichnis){		//löscht alle Dateien, welche von dem Programm generiert wurden
		File zuLoeschen = new File(verzeichnis + "/data/");
		String[] dateienZuLoeschen = zuLoeschen.list();
		if(zuLoeschen.exists()){
			for(int i = 0; i < dateienZuLoeschen.length; i++){
			File dieseDatei = new File(zuLoeschen,dateienZuLoeschen[i]);
			dieseDatei.delete();
			}	
			zuLoeschen.delete();
		}
		for(int i = 0; i < halbjahre.length; i++){ 
    		halbjahre[i].faecherliste.clear();	//Linked Lists werden gelöscht
    		halbjahre[i].panel_scrollContent.removeAll();	//setzt UI zurück
    		
    		setzeGesamtSchnitt(0, 0);	//setzt Schnitt zurück
    		
    	}
    	contentPane.validate();
    	contentPane.repaint();
	}
	
	public void allesSpeichern(){		//Ruft die Speichern-Methode für jedes Halbjahr auf
		for(int i = 0; i < halbjahre.length; i++){
			halbjahre[i].save();
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Abicalc frame = new Abicalc();
					frame.setVisible(true);
					vermerken();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				setzeGesamtSchnitt(getGesamtSchnitt(), punkteZuNote(getGesamtSchnitt()));		//setzt bei Programmstart den Schnitt
				
				
				
			}
		});
		
	}
}
