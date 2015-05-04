package abicalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        main.setBackground(Color.LIGHT_GRAY);
        
        JPanel panel_title = new JPanel();		//Panel mit Titel-Label
        //panel_title.setSize(500, getHeight());
        panel_title.setBackground(Color.WHITE);
        JLabel title = new JLabel(name);		//Titel-Label
        panel_title.add(title);
        main.add(panel_title, BorderLayout.PAGE_START);
        
        
        
        
        JPanel panel_add = new JPanel();
        //panel_add.setSize(500, getHeight());
        
        JTextField txt_Note = new JTextField();			//HJ Input für Fachname
        txt_Note.setText("Note");
		panel_add.add(txt_Note);
		txt_Note.setColumns(10);
		JButton button_plus = new JButton("+");		//HJ Fach hinzufügen Button
		
		panel_add.add(button_plus);
        main.add(panel_add, BorderLayout.CENTER);
        
        
        JPanel panel_noten = new JPanel();
        main.add(panel_noten, BorderLayout.PAGE_END);
        
        
        
        main.validate();
        main.repaint();
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
