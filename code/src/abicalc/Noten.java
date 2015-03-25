package abicalc;

public class Noten {
	
	double schnittHJ;
	Note Klausur1 = new Note(2, "Klausur1", 10);
	Note Klausur2 = new Note(2, "Klausur1", 12);
	Note Muendlich1 = new Note(1, "Muendlich1", 14);
	Note Muendlich2 = new Note(1, "Muendlich2", 3);
	//Hardcodierung der ersten Noten
	public Noten(){//Konstruktor
		Klausur1.naechstes = Klausur2;
		Klausur2.naechstes = Muendlich1;
		Muendlich1.naechstes = Muendlich2;
	}
	public double halbjahresschnitt(){ //Berechnung des Schnitts (Hardcodiert)
		double i;
		i = ((Klausur1.gewichtung * Klausur1.punkte) 
		+ (Klausur2.gewichtung * Klausur2.punkte) 
		+ (Muendlich1.gewichtung * Muendlich1.punkte) 
		+ (Muendlich2.gewichtung * Muendlich2.punkte))
		/ (Klausur1.gewichtung + Klausur2.gewichtung + Muendlich1.gewichtung + Muendlich2.gewichtung);
		return i;
	}
}
