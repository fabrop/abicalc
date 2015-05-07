package abicalc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Note extends Component implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	//Unterste Datenstruktur
	
	String name;
	int punkte;
	double gewichtung;
	
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
		JLabel label_n = new JLabel(name+":   ");		
		panel.add(label_n);
		
		//Label mit der Gewichtung der Note
		JLabel label_g = new JLabel("("+gewichtung+"x)");		
		panel.add(label_g);
		
		//Label mit der Punktzahl
		JLabel label_p = new JLabel(""+punkte+" Punkte");		
		panel.add(label_p);
		
		//UI-Aktualisierung
		panel.validate();		
		panel.repaint();
		
		//Note wird zum Fach hinzugefügt
		jpnl.add(panel);
		
	}
	
	
}
