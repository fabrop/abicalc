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

public class Halbjahr extends Component implements java.io.Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String verzeichnis = Halbjahr.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	JTextField txt_Fachname;
	JPanel panel_Faecher = new JPanel();
	
	JPanel panel = new JPanel();
	
	//oberste Datenstruktur unter dem Hauptprogramm
	String halbjahrName;
	LinkedList<Fach> faecherliste;
	
	//boolean ersterAufruf = ueberpruefen();
	public Halbjahr(String S, String title, JTabbedPane jtbp, JPanel jpnl){
		
		halbjahrName = S;
		if(!ueberpruefen() == true){ //falls es der erste Aufruf ist wird die Linked list generiert und eine File zum vermerken des Ersten Aufrufs angelegt
		ordnerAnlegen("data");
		faecherliste = new LinkedList<Fach>();
		save();
		}
		else{
			laden();
		}
		
		panel.setLayout(null);
		 
			//Container f�r F�cher
		panel_Faecher.setBounds(10, 56, 719, 315);
		panel.add(panel_Faecher);
		panel_Faecher.setBackground(Color.WHITE);
		panel_Faecher.setLayout(null);
		
		
		
		JPanel panel_scrollContent = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();		//Scrollbarer Bereich
		scrollPane.setBounds(10, 11, 699, 293);
		panel_Faecher.add(scrollPane);
		
		scrollPane.setViewportView(panel_scrollContent);		
		GridLayout gl = new GridLayout(0, 1, 0, 10);
		panel_scrollContent.setLayout(gl);
		
		JPanel panel_Add = new JPanel();		//HJ Panel mit Textfeld und Button
		panel_Add.setBackground(Color.WHITE);
		panel_Add.setBounds(10, 11, 719, 45);
		panel.add(panel_Add);
		panel_Add.setLayout(null);
		
		txt_Fachname = new JTextField();			//HJ Input f�r Fachname
		txt_Fachname.setBounds(10, 11, 216, 23);
		txt_Fachname.setText("Fachname...");
		panel_Add.add(txt_Fachname);
		txt_Fachname.setColumns(10);
		
		JButton button_plus = new JButton("+");		//HJ Fach hinzuf�gen Button
		button_plus.setBounds(236, 11, 41, 23);
		panel_Add.add(button_plus);
		
		
		button_plus.addActionListener(new java.awt.event.ActionListener() {		//Code f�rs Fach hinzuf�gen
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
		try {
			datei = new File(verzeichnis + "/data/" + halbjahrName + ".ser");
			speichern = new FileOutputStream(datei);
			ObjectOutputStream out = new ObjectOutputStream(speichern);
			if(!datei.exists()){
				datei.createNewFile();
			}
			out.writeObject(faecherliste);
			out.close();
			speichern.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ordnerAnlegen(String name){
		String ordnerName = name;
		File ordner = new File(verzeichnis + "/" + ordnerName);
		ordner.mkdirs();
	}
	
	@SuppressWarnings("unchecked")
	public void laden(){ // Methode, die vorhandene serialisierte Linked List einliest um damit zu arbeiten
		FileInputStream laden;
		File datei;
		try {
			datei = new File(verzeichnis + "/data/" + halbjahrName + ".ser");
			laden = new FileInputStream(datei);
			ObjectInputStream in = new ObjectInputStream(laden);
			faecherliste = (LinkedList<Fach>) in.readObject();
			in.close();
			laden.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		painteLinkedList();
	}
	
	public boolean ueberpruefen(){ //Es wird �berpr�ft ob eine Datei vorhanden ist, welche bei dem ersten Start vorhanden ist
		File test = new File(verzeichnis + "/data/aufgerufen.txt");
		if(test.exists()){
			return true;
		}
		else{
			return false;
		}
	}
	

	public void painteLinkedList(){
		for(int i = 0; i < faecherliste.size(); i++){
			faecherliste.get(i).fachPanelHinzufuegen(panel);
		}
	}
	
	
	public void fachHinzuf�gen(String fachName){ // nicht vollst�ndig
	//	faecherliste.add(new Fach(fachName));
	}
	
	public double getHJSchnitt(){
		double schnitt=0;
		for (int i =0; i<faecherliste.size();i++){
			schnitt=schnitt+faecherliste.get(i).getFachSchnitt();
		}
		schnitt=schnitt/faecherliste.size();
		return schnitt;
	}
}
