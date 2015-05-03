package abicalc;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fach extends Component implements java.io.Serializable{//Datenstruktur aus Noten, die ein UI-ELement ist
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	LinkedList<Note>  notenliste;
	Fach naechstes;
	
	public Fach(String s){ //Konstruktor
		
		//Verkette Liste aus Noten und Name des Fachs mithilfe des übergebenen Strings
		name = s;
		notenliste = new LinkedList<Note>();		
		
		//Panel mit Label und Button und Layout einer Tabelle mit 1 Zeile
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//Label mit Fächernamen
		JLabel lbl = new JLabel(name);
		panel.add(lbl);
		
		//Button zum Noten bearbeiten
		JButton btn = new JButton("Noten bearbeiten...");
		btn.setBounds(641, 10, 113, 23);
		panel.add(btn);
		
		//Stellt sicher, dass UI aktualisiert wird
		Abicalc.panel_scrollContentHJ1.add(panel);
		Abicalc.panel_FaecherHJ1.validate();
		Abicalc.panel_FaecherHJ1.repaint();
		
	}

}
