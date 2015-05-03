package abicalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class Halbjahr implements java.io.Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//oberste Datenstruktur unter dem Hauptprogramm
	String halbjahrName;
	LinkedList<Fach> faecherliste;
	//boolean ersterAufruf = ueberpruefen();
	
	public Halbjahr(){
		
		if(ueberpruefen() == true){ //falls es der erste Aufruf ist wird die Linked list generiert und eine File zum vermerken des Ersten Aufrufs angelegt
		faecherliste = new LinkedList<Fach>();
		vermerken();
		}
		else{
			laden();
		}
	}
	
	public void save(){ // Methode, die die Linked List faecherliste in eine Datei abspeichert
		FileOutputStream speichern;
		try {
			speichern = new FileOutputStream("/data/" + halbjahrName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(speichern);
			out.writeObject(faecherliste);
			out.close();
			speichern.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void laden(){ // Methode, die vorhandene serialisierte Linked List einliest um damit zu arbeiten
		FileInputStream laden;
		try {
			laden = new FileInputStream("/data/" + halbjahrName + ".ser");
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
		File test = new File("/data/aufgerufen.txt");
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
			writer.write("Das Programm wurde schon einmal gestartet");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fachHinzufügen(String fachName){ // nicht vollständig
		faecherliste.add(new Fach(fachName));
	}
	
}
