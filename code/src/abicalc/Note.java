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
	
	//Label für Name, Gewichtung und Punktzahl
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
		
		//Note wird zum Fach hinzugefügt
		jpnl.add(panel);
		
	}
	
	public void dialogNote(){
		//Pop-up Fenster
   	 	JDialog noteJDialog = new JDialog();		
   	 	noteJDialog.setTitle(name+": Note bearbeiten");
   	 	noteJDialog.setSize(500,75);
   		noteJDialog.setModal(true);
        
        //Panel, das den kompletten Inhalt enthält
        JPanel panelNameDialog = new JPanel();		
        
        
        //Input für Notenname
        JTextField txtNotenName = new JTextField();			
        txtNotenName.setText("Notenname...");
        panelNameDialog.add(txtNotenName);
        txtNotenName.setColumns(10);
        //Input für Notenname
        JTextField txtNotenPunkte = new JTextField();			
        txtNotenPunkte.setText("Notenpunkte...");
        panelNameDialog.add(txtNotenPunkte);
        txtNotenPunkte.setColumns(10);
        //Input für Notenname
        JTextField txtNotenGewichtung = new JTextField();			
        txtNotenGewichtung.setText("Notengewichtung...");
        panelNameDialog.add(txtNotenGewichtung);
        txtNotenGewichtung.setColumns(10);
        
        
        
      //Button zum Speichern
      		JButton btnSpeichern = new JButton("Speichern");		
      		panelNameDialog.add(btnSpeichern);
      		
      		btnSpeichern.addActionListener(new java.awt.event.ActionListener() {		
      	        public void actionPerformed(java.awt.event.ActionEvent e) {
      	        	Note.this.noteSpeichern(txtNotenName.getText(), Integer.parseInt(txtNotenPunkte.getText()), Double.parseDouble(txtNotenGewichtung.getText()));		
      	        	
      	        }
      	    });
        
        //Sorgt für Aktualisierung des Fachnamens nach Schließen des JDialogs
		noteJDialog.addWindowListener(new WindowAdapter() {
        	@Override
    	    public void windowClosed(WindowEvent e) {
        		Note.this.noteSpeichern(txtNotenName.getText(), Integer.parseInt(txtNotenPunkte.getText()), Double.parseDouble(txtNotenGewichtung.getText()));
    	    }
    	});
        //Sorgt für Aktualisierung des fachnames beim Schließen des JDialogs
		noteJDialog.addWindowListener(new WindowAdapter() {
        	@Override
    	    public void windowClosing(WindowEvent e) {
        		Note.this.noteSpeichern(txtNotenName.getText(), Integer.parseInt(txtNotenPunkte.getText()), Double.parseDouble(txtNotenGewichtung.getText()));
    	    }

			
    	});
        
		//Inhalt wird zum Dialog hinzugefügt
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
		
		Note.this.validate();	
		Note.this.repaint();
	}
	
	//Labels werden neue Werte zuegewiesen
	public void uiAktualiseren(){
		labelName.setText(""+name);
		labelGewichtung.setText("("+Abicalc.runden(gewichtung)+"x)");
		labelPunkte.setText(punkte+" Punkte");
		
		Note.this.validate();
		Note.this.repaint();
		Abicalc.setzeGesamtSchnitt(Abicalc.getGesamtSchnitt(), Abicalc.punkteZuNote(Abicalc.getGesamtSchnitt()));
	}
}
