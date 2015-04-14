package abicalc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Note {
	
	//UntersteDatenstruktur
	double gewichtung;
	String name;
	int punkte;
	Note naechstes;
	
	public Note(double g, String n, int p){//Konstruktor
		gewichtung = g;
		name = n;
		punkte = p;
		}
	
	public void noteSpeichern(){
		File datei = new File(name + ".txt");
		try {
			datei.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter writer;
		try {
			writer = new FileWriter(datei);
			writer.write(name);
			writer.write(punkte);
			writer.write("" + gewichtung);
			writer.write(naechstes.name);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
