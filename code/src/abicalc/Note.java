package abicalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Note {
	
	//UntersteDatenstruktur
	
	String name;
	int punkte;
	double gewichtung;
	Note naechstes;
	
	public Note(double g, String n, int p){//Konstruktor
		gewichtung = g;
		name = n;
		punkte = p;
		}
	
	public Note(String n){
		File datei = new File(n + "txt");
		Scanner reader;
		try {
			reader = new Scanner(datei);
			name = reader.nextLine();
			punkte = Integer.parseInt(reader.nextLine());
			gewichtung = Double.parseDouble(reader.nextLine());
			if(reader.hasNextLine()){
				naechstes.name = reader.nextLine();
			}
			else{
				naechstes = null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
