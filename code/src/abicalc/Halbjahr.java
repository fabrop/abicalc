package abicalc;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Halbjahr extends Component implements java.io.Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String verzeichnis = Halbjahr.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	JTextField txt_Fachname;
	static JPanel panel_Faecher = new JPanel();
	static JPanel panel_scrollContent = new JPanel();
	JPanel panel = new JPanel();
	
//oberste Datenstruktur unter dem Hauptprogramm
	String halbjahrName;
	LinkedList<Fach> faecherliste;
	
	//boolean ersterAufruf = ueberpruefen();
	public Halbjahr(String S){
		
		if(ueberpruefen() == true){ //falls es der erste Aufruf ist wird die Linked list generiert und eine File zum vermerken des Ersten Aufrufs angelegt
		faecherliste = new LinkedList<Fach>();
		vermerken();
		}
		else{
			laden();
		}
		
		
		
		halbjahrName = S;
		
		
		
		
		
		
		
		panel.setLayout(null);
		
			//Container für Fächer
		panel_Faecher.setBounds(10, 56, 719, 315);
		panel.add(panel_Faecher);
		panel_Faecher.setBackground(Color.WHITE);
		panel_Faecher.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 699, 293);
		panel_Faecher.add(scrollPane);
		
		scrollPane.setViewportView(panel_scrollContent);		
		GridLayout gl = new GridLayout(0, 1, 0, -10);
		panel_scrollContent.setLayout(gl);
		
		JPanel panel_Add = new JPanel();		//HJ1 Panel mit Textfeld und Button
		panel_Add.setBackground(Color.WHITE);
		panel_Add.setBounds(10, 11, 719, 45);
		panel.add(panel_Add);
		panel_Add.setLayout(null);
		
		txt_Fachname = new JTextField();			//HJ1 Input für Fachname
		txt_Fachname.setBounds(10, 11, 216, 23);
		txt_Fachname.setText("Fachname");
		panel_Add.add(txt_Fachname);
		txt_Fachname.setColumns(10);
		
		JButton button_plus = new JButton("+");		//HJ1 Fach hinzufügen Button
		button_plus.setBounds(236, 11, 41, 23);
		panel_Add.add(button_plus);
		
		
		button_plus.addActionListener(new java.awt.event.ActionListener() {		//Code fürs Fach hinzufügen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	panel_scrollContent.add(new Fach(txt_Fachname.getText(), panel_scrollContent, panel_Faecher));
	        }
	    });
		
		
		
		
		
		
	}

	public void save(){ // Methode, die die Linked List faecherliste in eine Datei abspeichert
		FileOutputStream speichern;
		try {
			speichern = new FileOutputStream(verzeichnis + "/data/" + halbjahrName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(speichern);
			out.writeObject(faecherliste);
			out.close();
			speichern.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void laden(){ // Methode, die vorhandene serialisierte Linked List einliest um damit zu arbeiten
		FileInputStream laden;
		try {
			laden = new FileInputStream(verzeichnis + "/data/" + halbjahrName + ".ser");
			ObjectInputStream in = new ObjectInputStream(laden);
			faecherliste = (LinkedList<Fach>) in.readObject();
			in.close();
			laden.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public boolean ueberpruefen(){ //Es wird Überprüft ob eine Datei vorhanden ist, welche bei dem ersten Start vorhanden ist
		File test = new File(verzeichnis + "/data/aufgerufen.txt");
		if(test.exists()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void vermerken(){ //Eine Datei wird angelegt, um den ersten Start des Programms zu vermerken
		File vermerk = new File("/data/aufgerufen.txt");
		try {
			FileWriter writer = new FileWriter(vermerk);
			writer.write("true");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void fachHinzufügen(String fachName){ // nicht vollständig
	//	faecherliste.add(new Fach(fachName));
	}
	
	public double getHJSchnitt(){
		double schnitt=0;
		for (int i =0; i<faecherliste.size();i++){
			schnitt=schnitt+faecherliste.get(i).getFachSchnitt();
		}
		return schnitt;
	}
}
