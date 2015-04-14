package abicalc;

public class Faecher {
	Fach anfang = new Fach("1337");		
	Fach ende = new Fach("13337");
	
	public Faecher(){			//Konstruktor = Erstellen der verketteten Liste
		anfang.naechstes = ende;
		ende.naechstes = null;
	}
	
	public void neuesFachEinfuegen (String name){		//Fügt nach dem letzten aktuellen Fach ein Neues ein, übergibt einen String als Namen
		Fach f = new Fach(name);
		Fach n = new Fach("");
		while (n.naechstes!=ende){
			n.naechstes = n.naechstes.naechstes;
		}
		n.naechstes = f;
		f.naechstes = ende;
	}
}
