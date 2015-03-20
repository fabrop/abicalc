package abicalc;

public class Noten {
	
	Note Klausur1 = new Note(2, "Klausur1", 0);
	Note Klausur2 = new Note(2, "Klausur1", 0);
	Note Muendlich1 = new Note(2, "Muendlich1", 0);
	Note Muendlich2 = new Note(2, "Muendlich2", 0);
	
	public Noten(){
		Klausur1.naechstes = Klausur2;
		Klausur2.naechstes = Muendlich1;
		Muendlich1.naechstes = Muendlich2;
	}
}
