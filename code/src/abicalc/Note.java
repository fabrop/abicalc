package abicalc;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Note extends Component implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	//Unterste Datenstruktur
	
	String name;
	int punkte;
	double gewichtung;
	
	//Label f�r Name, Gewichtung und Punktzahl
	JLabel labelName = new JLabel();
	JLabel labelGewichtung = new JLabel();
	JLabel labelPunkte = new JLabel();
	
	public Note(double g, String n, int p, JPanel jpnl){
		gewichtung = g;
		name = n;
		punkte = p;
		
		//Erzeugt das UI der einzelnen Note
		uiLaden(jpnl);		
	}


	public void uiLaden(JPanel jpnl){
		//Gesamter Container
		JPanel panel = new JPanel();		
		panel.setBackground(Color.LIGHT_GRAY);
		
		//Label mit dem Namen der Note	
		labelName.setText(""+name);
		panel.add(labelName);
		
		//Label mit der Gewichtung der Note
		labelGewichtung.setText("("+Abicalc.runden(gewichtung)+"x)");
		panel.add(labelGewichtung);
		
		//Label mit der Punktzahl
		labelPunkte.setText(punkte+" Punkte");
		panel.add(labelPunkte);

		//Button zum Bearbeiten
		JButton btnBearbeiten = new JButton("...");		
		panel.add(btnBearbeiten);
		
		btnBearbeiten.addActionListener(new java.awt.event.ActionListener() {		
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	        	//ruft den JDialog zum Bearbeiten der Noten auf
	        	dialogNote();		
	        	
	        	//Stellt sicher, dass UI aktualisiert wird
	        	panel.validate();
	    		panel.repaint();
	        }
	    });
		
		//UI-Aktualisierung
		panel.validate();		
		panel.repaint();
		
		//Note wird zum Fach hinzugef�gt
		jpnl.add(panel);
		
	}
	
	public void dialogNote(){
		//Pop-up Fenster
   	 	JDialog noteJDialog = new JDialog();		
   	 	noteJDialog.setTitle(name+": Note bearbeiten");
   	 	noteJDialog.setSize(500,75);
   		noteJDialog.setModal(true);
        
        //Panel, das den kompletten Inhalt enth�lt
        JPanel panelNameDialog = new JPanel();		
        
        
        //Input f�r Notenname
        JTextField txtNotenName = new JTextField();			
        txtNotenName.setText(""+this.name);
        txtNotenName.setToolTipText("Notenname...");
        panelNameDialog.add(txtNotenName);
        txtNotenName.setColumns(10);
        //Input f�r Notenname
        JTextField txtNotenPunkte = new JTextField();			
        txtNotenPunkte.setText(""+this.punkte);
        txtNotenPunkte.setToolTipText("Notenpunkte...");
        panelNameDialog.add(txtNotenPunkte);
        txtNotenPunkte.setColumns(10);
        //Input f�r Notenname
        JTextField txtNotenGewichtung = new JTextField();			
        txtNotenGewichtung.setText(""+this.gewichtung);
        txtNotenGewichtung.setToolTipText("Gewichtung...");
        panelNameDialog.add(txtNotenGewichtung);
        txtNotenGewichtung.setColumns(10);
        
        //Button zum Speichern
      		JButton btnSpeichern = new JButton("Speichern");		
      		panelNameDialog.add(btnSpeichern);
      		
      		btnSpeichern.addActionListener(new java.awt.event.ActionListener() {		
      	        public void actionPerformed(java.awt.event.ActionEvent e) {
      	        	noteSpeichern(txtNotenName.getText(), Integer.parseInt(txtNotenPunkte.getText()), Double.parseDouble(txtNotenGewichtung.getText()));		
      	        	
      	        }
      	    });
        
        //Sorgt f�r Aktualisierung des Fachnamens nach Schlie�en des JDialogs
		noteJDialog.addWindowListener(new WindowAdapter() {
        	@Override
    	    public void windowClosed(WindowEvent e) {
        		noteSpeichern(txtNotenName.getText(), Integer.parseInt(txtNotenPunkte.getText()), Double.parseDouble(txtNotenGewichtung.getText()));
    	    }
    	});
        //Sorgt f�r Aktualisierung des fachnames beim Schlie�en des JDialogs
		noteJDialog.addWindowListener(new WindowAdapter() {
        	@Override
    	    public void windowClosing(WindowEvent e) {
        		noteSpeichern(txtNotenName.getText(), Integer.parseInt(txtNotenPunkte.getText()), Double.parseDouble(txtNotenGewichtung.getText()));
    	    }
    	});
        
		//Inhalt wird zum Dialog hinzugef�gt
		noteJDialog.add(panelNameDialog);
		
        //UI wird geupdated
		noteJDialog.validate();	
		noteJDialog.repaint();
        
        //Dialog wird sichtbar gemacht
		noteJDialog.setVisible(true);
	}
	
	//Noten werden in den Daten und in der UI gespeichert und diese aktualisiert
	public void noteSpeichern(String s, int i, double d){
		name = s;
		punkte = i;
		gewichtung = d;

		uiAktualiseren();
		
		validate();	
		repaint();
	}
	
	//Labels werden neue Werte zuegewiesen
	public void uiAktualiseren(){
		labelName.setText(""+name);
		labelGewichtung.setText("("+Abicalc.runden(gewichtung)+"x)");
		labelPunkte.setText(punkte+" Punkte");
		
		validate();
		repaint();
		Abicalc.setzeGesamtSchnitt(Abicalc.getGesamtSchnitt(), Abicalc.punkteZuNote(Abicalc.getGesamtSchnitt()));
		for(int i = 0; i < 5; i++){
			for(int o = 0; o < Abicalc.halbjahre[i].faecherliste.size(); i++){
				Abicalc.halbjahre[i].faecherliste.get(o).fachSchnittAktualisieren();
			}
		}
	}
}
