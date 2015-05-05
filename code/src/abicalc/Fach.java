package abicalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Fach extends Component implements java.io.Serializable{//Datenstruktur aus Noten, die ein UI-ELement ist
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	LinkedList<Note>  notenliste;
	double schnitt;
	public JPanel panel_content = new JPanel();
	
	public Fach(String s, JPanel jpnl){ //Konstruktor
		
		//Verkette Liste aus Noten und Name des Fachs mithilfe des übergebenen Strings
		name = s;
		notenliste = new LinkedList<Note>();		
		
		uiLaden(jpnl);
		
	}
	
	public void fachSchnittBerechnen(){
		double summe = 0;
		double sGewichtung = 0;
		for(int i = 0; i < notenliste.size(); i++){
			summe = summe + (notenliste.get(i).gewichtung * notenliste.get(i).punkte);
			sGewichtung = sGewichtung + notenliste.get(i).gewichtung;
		}
		schnitt = summe / sGewichtung;
	}
	
	public void uiLaden(JPanel jpnl){
		//Panel mit Label und Button und Layout einer Tabelle mit 1 Zeile
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		//Label mit Fächernamen
		JLabel lbl = new JLabel("   "+name);
		panel.add(lbl);
		
		//Button zum Noten bearbeiten
		JButton btn = new JButton("Noten bearbeiten...");
		btn.setBounds(641, 10, 113, 23);
		panel.add(btn);
		
		btn.addActionListener(new java.awt.event.ActionListener() {		//Code fürs Fach hinzufügen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	openDialog();
	        	
	        	//Stellt sicher, dass UI aktualisiert wird
	        	panel.validate();
	    		panel.repaint();
	        }
	    });
			
		
		//Panel wird hinzugefügt
		jpnl.add(panel);
				
	}
	
	public void openDialog() {

   	 	JDialog fachJDialog = new JDialog();		//Pop-up Fenster
        fachJDialog.setTitle(name+" bearbeiten");
        fachJDialog.setSize(500,400);
        fachJDialog.setModal(true);
        
        JPanel main = new JPanel();		//Enthält alles
        main.setLayout(new BorderLayout(5, 5));
        fachJDialog.add(main);
        main.setBackground(Color.LIGHT_GRAY);
        
        JPanel panel_title = new JPanel();		//Panel mit Titel-Label
        JLabel title = new JLabel(name+"                  ");//Titel-Label
        panel_title.add(title);
        
 
        JPanel panel_add = new JPanel();
        
        JTextField txt_Name = new JTextField();			//HJ Input für Notenname
        txt_Name.setText("Note");
		panel_add.add(txt_Name);
		txt_Name.setColumns(10);
		JTextField txt_Note = new JTextField();			//HJ Input für Notenname
        txt_Note.setText("Punktzahl");
		panel_add.add(txt_Note);
		txt_Note.setColumns(7);
		JTextField txt_Gewichtung = new JTextField();			//HJ Input für Notenname
        txt_Gewichtung.setText("Gewichtung");
		panel_add.add(txt_Gewichtung);
		txt_Gewichtung.setColumns(7);
		JButton button_plus = new JButton("+");		//HJ Note hinzufügen Button
		panel_add.add(button_plus);
		JPanel panel_top = new JPanel();
		panel_top.add(panel_title);
		panel_top.add(panel_add);
		
        main.add(panel_top, BorderLayout.PAGE_START);		//Label, Textfelder und Button werden zum oberen Teil des Dialogs hinzugefügt
        
        
        
        JPanel panel_noten = new JPanel();		//Übercontainer für einzelne Noten
        
        
        //Container der scrollbar wird und Noten enthält
        JScrollPane scrollPane = new JScrollPane (panel_content); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(460, 300));
        
        panel_noten.add(scrollPane);
        
        GridLayout gl = new GridLayout(0, 1, 0, 10);	//sorgt für vertikale Anordnung der Elemente
        panel_content.setLayout(gl);
		
        
        /*
        //Alte Noten werden geladen
        for (int i = 0; i<notenliste.size(); i++){
        	Note n = new Note(notenliste.get(i).gewichtung, notenliste.get(i).name, notenliste.get(i).punkte, panel_content);
        	//es werden neue Noten generiert (die UI-Elemente sind) aber deren Daten nicht zur notenliste hinzugefügt
        }
        */
        
        main.validate();
		main.repaint();
        
        
        
        
        main.add(panel_noten, BorderLayout.CENTER);	//Scrollbarer Bereich für einzelne Noten wird hinzugefügt
        
        
        button_plus.addActionListener(new java.awt.event.ActionListener() {		//Code fürs Noten hinzufügen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	Note n = new Note(Double.parseDouble(txt_Gewichtung.getText()), txt_Name.getText(), Integer.parseInt(txt_Note.getText()), panel_content);
	        	notenliste.add(n);
	        	main.validate();
	    		main.repaint();
	        }
	    });
        
        
        
        main.validate();	//UI wird geupdated
        main.repaint();
        fachJDialog.setVisible(true);		//Dialog wird sichtbar gemacht
	}

	public double getFachSchnitt() {
		double schnitt=0;
		for (int i=0; i<notenliste.size(); i++){
			schnitt=schnitt+notenliste.get(i).punkte;
		}
		schnitt=schnitt/notenliste.size();
		return schnitt;
	}

}
