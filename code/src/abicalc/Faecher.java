package abicalc;

public class Faecher {
	Fach Mathematik = new Fach("Mathematik");
	Fach Deutsch = new Fach("Deutsch");
	Fach NeuesFach = new Fach("neues Fach");
	
	public Faecher(){//KOnstruktor = Erstelen der verketteten Liste
		Mathematik.naechstes = Deutsch;
		Deutsch.naechstes = NeuesFach;
	}
}
