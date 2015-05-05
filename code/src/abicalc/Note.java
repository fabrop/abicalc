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
	
	public Note(double g, String n, int p, JPanel jpnl){//Konstruktor
		gewichtung = g;
		name = n;
		punkte = p;
		
		uiLaden(jpnl);		//Erzeugt das UI
	}


	public void uiLaden(JPanel jpnl){
		JPanel panel = new JPanel();		//Gesamter Container
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel label_n = new JLabel(name+":   ");		//Label mit dem Namen der Note
		panel.add(label_n);
		
		JLabel label_g = new JLabel("("+gewichtung+"x)");		//Label mit der Gewichtung der Note
		panel.add(label_g);
		
		JLabel label_p = new JLabel(""+punkte+" Punkte");		//Label mit der Punktzahl
		panel.add(label_p);
		
		
		panel.validate();		//UI-Refresh
		panel.repaint();
		jpnl.add(panel);
		
	}
	
	
}
