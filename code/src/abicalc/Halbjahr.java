package abicalc;

import java.util.LinkedList;

public class Halbjahr implements java.io.Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//oberste Datenstruktur unter dem Hauptprogramm
	
	LinkedList<Fach> faecherliste;
	
	public Halbjahr(){
		faecherliste = new LinkedList<Fach>();
	}
}
