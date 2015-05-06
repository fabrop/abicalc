package abicalc;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Halbjahr extends Component implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	String verzeichnis = Halbjahr.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	JTextField txt_Fachname;
	JPanel panel_Faecher = new JPanel();
	
	JPanel panel = new JPanel();
	
	JPanel panel_scrollContent = new JPanel();
	
	//oberste Datenstruktur unter dem Hauptprogramm
	String halbjahrName;
	LinkedList<Fach> faecherliste;
	
	//boolean ersterAufruf = ueberpruefen();
	public Halbjahr(String S, String title, JTabbedPane jtbp, JPanel jpnl){
		
		halbjahrName = S;
		if(!ueberpruefen() == true){ //falls es der erste Aufruf ist, wird die Linked List generiert und eine Datei zum Vermerken des ersten Aufrufs angelegt
		ordnerAnlegen("data"); //Ein Ordner für die verschiedenen Daten des Programms wird erstellt
		faecherliste = new LinkedList<Fach>(); // die Liste für die einzelnen Fächer des Halbjahrs
		save(); //Die Liste wird gespeichert
		}
		else{
			laden();
		}
		
		panel.setLayout(null);
		 
			//Container für Fächer
		panel_Faecher.setBounds(10, 56, 719, 315);
		panel.add(panel_Faecher);
		panel_Faecher.setBackground(Color.WHITE);
		panel_Faecher.setLayout(null);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();		//Scrollbarer Bereich
		scrollPane.setBounds(10, 11, 699, 293);
		panel_Faecher.add(scrollPane);
		
		scrollPane.setViewportView(panel_scrollContent);		
		GridLayout gl = new GridLayout(0, 1, 0, 10);
		panel_scrollContent.setLayout(gl);
		
		/*
		for (int i = 0; i>faecherliste.size(); i++){
			
			
			Fach f = new Fach(faecherliste.get(i).name, panel_scrollContent);
        	//es werden neue Fächer generiert (die UI-Elemente sind) aber deren Daten nicht zur faecherliste hinzugefügt
			
			for (int j = 0; j<f.notenliste.size(); j++){
	        	f.notenliste.add(new Note(f.notenliste.get(j).gewichtung, f.notenliste.get(j).name, f.notenliste.get(j).punkte, f.panel_content));
	        	//die Noten des Daten-Faches werden in das UI-Fach eingetragen
	        }
			
		}
		*/
		
		
		JPanel panel_Add = new JPanel();		//HJ Panel mit Textfeld und Button
		panel_Add.setBackground(Color.WHITE);
		panel_Add.setBounds(10, 11, 719, 45);
		panel.add(panel_Add);
		panel_Add.setLayout(null);
		
		txt_Fachname = new JTextField();			//HJ Input für Fachname
		txt_Fachname.setBounds(10, 11, 216, 23);
		txt_Fachname.setText("Fachname...");
		panel_Add.add(txt_Fachname);
		txt_Fachname.setColumns(10);
		
		JButton button_plus = new JButton("+");		//HJ Fach hinzufügen Button
		button_plus.setBounds(236, 11, 41, 23);
		panel_Add.add(button_plus);
		
		
		button_plus.addActionListener(new java.awt.event.ActionListener() {		//Code fürs Fach hinzufügen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	Fach f = new Fach(txt_Fachname.getText(), panel_scrollContent);
	        	faecherliste.add(f);
	        	jpnl.validate();
	    		jpnl.repaint();
	        }
	    });		
		
		jtbp.add(panel, title);
		
	}

	public void save(){ // Methode, die die Linked List faecherliste in eine Datei abspeichert
		FileOutputStream speichern;
		File datei;
		File ordner  = new File(verzeichnis + "/data/");
		try {
			if(!ordner.exists()) ordnerAnlegen("data");
			datei = new File(verzeichnis + "/data/" + halbjahrName + ".ser"); //Datei mit angegen´benem Verzeichnis wird erstellt
			speichern = new FileOutputStream(datei); //FileOutputStream ist nötig, um auf die Festplatte zu schreiben
			ObjectOutputStream out = new ObjectOutputStream(speichern); //ObjectOutputStream serialisiert die Linked List
			if(!datei.exists()){ // Fall die Datei noch nicht existiert wrd sie erstellt
				datei.createNewFile();
			}
			out.writeObject(faecherliste); //der ObjectOutputStream schreibt die Linked List serialisiert in die Datei
			out.flush(); // Der Cache des ObjectOutputStreams wird entleert und somit auf die Festplatte geschrieben
			out.close(); 
			speichern.close(); // Die OutStreams werden geschlossen
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ordnerAnlegen(String name){ //MEthode, die gebraucht wird, um Ordner zum Speichern der daten zu erzeugen
		String ordnerName = name;
		File ordner = new File(verzeichnis + "/" + ordnerName);
		ordner.mkdirs(); 
	}
	

	@SuppressWarnings("unchecked")
	public void laden(){ // Methode, die vorhandene serialisierte Linked List einliest um damit zu arbeiten
		FileInputStream laden;
		File datei;
		try {
			datei = new File(verzeichnis + "/data/" + halbjahrName + ".ser"); //Pfad der einzulesenden Datei
			laden = new FileInputStream(datei);
			ObjectInputStream in = new ObjectInputStream(laden);
			faecherliste = (LinkedList<Fach>) in.readObject(); // Gespeicherte Linked List wird deserialisiert und als Linked List festgelegt
			in.close();
			laden.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		painteLinkedList();
	}
	
	public boolean ueberpruefen(){ //Es wird Überprüft ob eine Datei vorhanden ist, welche bei dem ersten Speichern generiert wird
		File test = new File(verzeichnis + "/data/aufgerufen.txt");
		if(test.exists()){
			return true;
		}
		else{
			return false;
		}
	}
	

	public void painteLinkedList(){ //geladenen Linked Lists werden grafisch abgebildet
		for(int i = 0; i < faecherliste.size(); i++){
			faecherliste.get(i).uiLaden(panel);
		}
		panel.validate();
		panel.repaint();
	}
	
	
	public double getHJSchnitt(){ //Der schnitt des Halbjahres wird berechnet
		double schnitt=0;
		for (int i =0; i<faecherliste.size();i++){
			schnitt=schnitt+faecherliste.get(i).fachSchnittBerechnen();
		}
		schnitt=schnitt/faecherliste.size();
		return schnitt;
	}
}
