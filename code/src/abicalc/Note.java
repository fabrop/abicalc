package abicalc;

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
	

}
