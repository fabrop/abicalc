package abicalc;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fach extends Component implements java.io.Serializable{//Datenstruktur aus Noten
	
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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		
		

		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl = new JLabel(name);
		panel.add(lbl);
		
		JButton btn = new JButton("Noten bearbeiten...");
		btn.setBounds(641, 10, 113, 23);
		panel.add(btn);
		
		

		Abicalc.panel_scrollContentHJ1.add(panel);
		Abicalc.panel_FaecherHJ1.validate();
		Abicalc.panel_FaecherHJ1.repaint();
		
	}

}
