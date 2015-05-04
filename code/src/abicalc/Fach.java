package abicalc;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fach extends Component implements java.io.Serializable{//Datenstruktur aus Noten, die ein UI-ELement ist
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	LinkedList<Note>  notenliste;
	
	public Fach(String s, JPanel jpnl1, JPanel jpnl2){ //Konstruktor
		
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
		
		btn.addActionListener(new java.awt.event.ActionListener() {		//Code fürs Fach hinzufügen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	openDialog();
	        }
	    });
			

		//Stellt sicher, dass UI aktualisiert wird
		jpnl1.add(panel);
		jpnl2.validate();
		jpnl2.repaint();
		
	}
	
	
	public void openDialog() {

   	 	JDialog fachJDialog = new JDialog();		//Pop-up Fenster
        fachJDialog.setTitle(name+" bearbeiten");
        fachJDialog.setSize(500,400);
        fachJDialog.setModal(true);
        
        JPanel main = new JPanel();		//Enthält alles
        fachJDialog.add(main);
        main.setLayout(null);
        main.setBackground(Color.LIGHT_GRAY);
        
        JPanel titlePanel = new JPanel();		//Panel mit Titel-Label
        main.add(titlePanel);
        titlePanel.setBackground(Color.WHITE);
        JLabel title = new JLabel(name);		//Titel-Label
        titlePanel.add(title);
        
        fachJDialog.setVisible(true);		//Dialog wird sichtbar gemacht
	}

	public double getFachSchnitt() {
		double schnitt=0;
		for (int i=0; i<notenliste.size(); i++){
			schnitt=schnitt+notenliste.get(i).punkte;
		}
		return schnitt;
	}

}
