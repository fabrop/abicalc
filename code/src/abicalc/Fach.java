package abicalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Fach extends Component implements java.io.Serializable{		//Datenstruktur aus Noten, die ein UI-ELement ist
	
	private static final long serialVersionUID = 1L;
	
	String name;
	LinkedList<Note>  notenliste;
	double fachSchnitt;
	
	public JPanel panelHaupt = new JPanel();		//Panel mit Noten
	
	public JLabel lbl_fs = new JLabel();		//Label mit Fachschnitt
	
	public Fach(String s, JPanel jpnl){ //Konstruktor
		
		//Verkette Liste aus Noten und Name des Fachs mithilfe des übergebenen Strings
		name = s;
		notenliste = new LinkedList<Note>();		
		
		uiLaden(jpnl);
		
	}
	
	public double fachSchnittBerechnen(){		//Berechnet den Schnitt eines einzelnen Faches
		double schnitt;
		double summe = 0;		//Summe sind alle Noten mit deren Gewichtungen eingerechnet
		double sGewichtung = 0;		//Summe der Gewichtungen
		for(int i = 0; i < notenliste.size(); i++){		//für jede Note wird deren Wert mit ihrer Gewichtung multipliziert
			summe = summe + (notenliste.get(i).gewichtung * notenliste.get(i).punkte);
			sGewichtung = sGewichtung + notenliste.get(i).gewichtung;
		}
		schnitt = summe / sGewichtung;	
		return schnitt;
	}
	
	public void uiLaden(JPanel jpnl){
		//Panel mit Fachname und Button und Layout einer Tabelle mit 1 Zeile
		JPanel panel = new JPanel();	
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		//Label mit Fächernamen
		JLabel lbl_n = new JLabel("   "+name);
		panel.add(lbl_n);
		
		//Fächerdurchschnitt
		fachSchnittAktualisieren();
		panel.add(lbl_fs);
		
		//Button zum Noten bearbeiten
		JButton btn = new JButton("Noten bearbeiten...");
		btn.setBounds(641, 10, 113, 23);
		panel.add(btn);
		
		btn.addActionListener(new java.awt.event.ActionListener() {		//Code fürs Fach hinzufügen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	dialogOeffnen();		//ruft den JDialog zum Bearbeiten der Noten auf
	        	
	        	//Stellt sicher, dass UI aktualisiert wird
	        	panel.validate();
	    		panel.repaint();
	        }
	    });
			
		
		//Panel wird hinzugefügt
		jpnl.add(panel);
				
	}
	
	public void fachSchnittAktualisieren(){
		this.fachSchnitt = Abicalc.runden(this.fachSchnittBerechnen());
		this.lbl_fs.setText(String.valueOf(fachSchnitt));
	}
	
	
	
	public void dialogOeffnen() {
		
		//Pop-up Fenster
   	 	JDialog fachJDialog = new JDialog();		
        fachJDialog.setTitle(name+" bearbeiten");
        fachJDialog.setSize(500,400);
        fachJDialog.setModal(true);
        
        //Panel, das den kompletten Inhalt enthält
        JPanel panelDialog = new JPanel();		
        panelDialog.setLayout(new BorderLayout(5, 5));
        fachJDialog.add(panelDialog);
        panelDialog.setBackground(Color.LIGHT_GRAY);
        
        //Panel mit Titel-Label
        JPanel panelTitel = new JPanel();		
        JLabel titel = new JLabel(name+"                  ");
        panelTitel.add(titel);
        JPanel panelHinzufuegen = new JPanel();
        
        //Input für Notenname
        JTextField txtName = new JTextField();			
        txtName.setText("Note");
        panelHinzufuegen.add(txtName);
		txtName.setColumns(10);
		//Input für Punktzahl
		JTextField txtNote = new JTextField();			
        txtNote.setText("Punktzahl");
        panelHinzufuegen.add(txtNote);
		txtNote.setColumns(7);
		JTextField txtGewichtung = new JTextField();			
		//Input für Gewichtung
        txtGewichtung.setText("Gewichtung");
        panelHinzufuegen.add(txtGewichtung);
		txtGewichtung.setColumns(7);
		JButton buttonPlus = new JButton("+");		
		//Note hinzufügen Button
		panelHinzufuegen.add(buttonPlus);
		JPanel panelOben = new JPanel();
		panelOben.add(panelTitel);
		panelOben.add(panelHinzufuegen);
		
		//Label, Textfelder und Button werden zum oberen Teil des Dialogs hinzugefügt
		panelDialog.add(panelOben, BorderLayout.PAGE_START);		
        
		//Übercontainer für einzelne Noten
        JPanel panelNoten = new JPanel();		
        
        //Container der scrollbar wird und Noten enthält
        JScrollPane scrollPane = new JScrollPane (panelHaupt); 
        //bekommt nur Scrollbar, wenn genug Elemente vorhanden sind
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);		
        scrollPane.setPreferredSize(new Dimension(460, 300));
        
        panelNoten.add(scrollPane);
        
        //sorgt für vertikale Anordnung der Elemente
        GridLayout gl = new GridLayout(0, 1, 0, 10);	
        panelHaupt.setLayout(gl);
		
        //UI-Aktualisierung
        panelDialog.validate();		
        panelDialog.repaint();
        
        //Scrollbarer Bereich für einzelne Noten wird hinzugefügt
        panelDialog.add(panelNoten, BorderLayout.CENTER);	
        
        //Code fürs Noten hinzufügen
        buttonPlus.addActionListener(new java.awt.event.ActionListener() {		
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	//Note wird mithilfe der Daten aus den Textfeldern erzeugt
	        	Note n = new Note(Double.parseDouble(txtGewichtung.getText()), txtName.getText(), Integer.parseInt(txtNote.getText()), panelHaupt);	
	        	//Note wird zur LinkedList hinzugefügt
	        	notenliste.add(n);
	        	//UI wird aktualisiert
	        	panelDialog.validate();
	        	panelDialog.repaint();
	    		Abicalc.setzeGesamtSchnitt(Abicalc.getGesamtSchnitt(), Abicalc.punkteZuNote(Abicalc.getGesamtSchnitt()));		//Aktualisieren des Gesamtschnitts
	        }
	    });
        
        fachJDialog.addWindowListener(new WindowAdapter() {
        	@Override
    	    public void windowClosed(WindowEvent e) {
    	    	fachSchnittAktualisieren();
    	    }
    	});
        
        //UI wird geupdated
        panelDialog.validate();	
        panelDialog.repaint();
        
        //Dialog wird sichtbar gemacht
        fachJDialog.setVisible(true);		
	}

	
	
	
}
