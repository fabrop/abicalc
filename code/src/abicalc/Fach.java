package abicalc;

import java.util.LinkedList;

public class Fach implements java.io.Serializable{//Datenstruktur aus Noten
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	LinkedList<Note>  notenliste;
	Fach naechstes;
	
	public Fach(String s){//Konstruktor
		name = s;
		notenliste = new LinkedList<Note>();
	}

}
