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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Fach extends Component implements java.io.Serializable{//Datenstruktur aus Noten, die ein UI-ELement ist
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	LinkedList<Note>  notenliste;
	
	
	public Fach(String s, JPanel jpnl){ //Konstruktor
		
		//Verkette Liste aus Noten und Name des Fachs mithilfe des �bergebenen Strings
		name = s;
		notenliste = new LinkedList<Note>();		
		
		//Panel mit Label und Button und Layout einer Tabelle mit 1 Zeile
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		//Label mit F�chernamen
		JLabel lbl = new JLabel("   "+name);
		panel.add(lbl);
		
		//Button zum Noten bearbeiten
		JButton btn = new JButton("Noten bearbeiten...");
		btn.setBounds(641, 10, 113, 23);
		panel.add(btn);
		
		btn.addActionListener(new java.awt.event.ActionListener() {		//Code f�rs Fach hinzuf�gen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	openDialog();
	        	
	        	//Stellt sicher, dass UI aktualisiert wird
	        	panel.validate();
	    		panel.repaint();
	        }
	    });
			
		
		//Panel wird hinzugef�gt
		jpnl.add(panel);
		
		
	}
	
	
	
	public void openDialog() {

   	 	JDialog fachJDialog = new JDialog();		//Pop-up Fenster
        fachJDialog.setTitle(name+" bearbeiten");
        fachJDialog.setSize(500,400);
        fachJDialog.setModal(true);
        
        JPanel main = new JPanel();		//Enth�lt alles
        main.setLayout(new BorderLayout(5, 5));
        fachJDialog.add(main);
        main.setBackground(Color.LIGHT_GRAY);
        
        JPanel panel_title = new JPanel();		//Panel mit Titel-Label
        JLabel title = new JLabel(name+"                  ");//Titel-Label
        panel_title.add(title);
        
 
        JPanel panel_add = new JPanel();
        
        JTextField txt_Name = new JTextField();			//HJ Input f�r Notenname
        txt_Name.setText("Note...");
		panel_add.add(txt_Name);
		txt_Name.setColumns(10);
		JTextField txt_Note = new JTextField();			//HJ Input f�r Notenname
        txt_Note.setText("Punktzahl...");
		panel_add.add(txt_Note);
		txt_Note.setColumns(7);
		JTextField txt_Gewichtung = new JTextField();			//HJ Input f�r Notenname
        txt_Gewichtung.setText("Gewichtung...");
		panel_add.add(txt_Gewichtung);
		txt_Gewichtung.setColumns(7);
		JButton button_plus = new JButton("+");		//HJ Note hinzuf�gen Button
		panel_add.add(button_plus);
		JPanel panel_top = new JPanel();
		panel_top.add(panel_title);
		panel_top.add(panel_add);
		
        main.add(panel_top, BorderLayout.PAGE_START);		//Label, Textfelder und Button werden zum oberen Teil des Dialogs hinzugef�gt
        
        
        
        JPanel panel_noten = new JPanel();		//Container f�r einzelne Noten
        //panel_noten.setBackground(Color.WHITE);
        panel_noten.setLayout(null);
        
        JPanel panel_scroll = new JPanel();
        
    	JScrollPane scrollPane = new JScrollPane();		//Scrollbarer Bereich
    	scrollPane.setSize(getWidth(), 310);
    	panel_noten.add(scrollPane);
    	
		scrollPane.setViewportView(panel_scroll);
		GridLayout gl = new GridLayout(0, 1, 0, 10);
		panel_scroll.setLayout(gl);
		
		scrollPane.add(panel_scroll);
		
        main.add(panel_noten, BorderLayout.CENTER);	//Scrollbarer Bereich f�r einzelne Noten wird hinzugef�gt
        
        
        
        button_plus.addActionListener(new java.awt.event.ActionListener() {		//Code f�rs Noten hinzuf�gen
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	          
	        	Note n = new Note(Double.parseDouble(txt_Gewichtung.getText()), txt_Name.getText(), Integer.parseInt(txt_Note.getText()), panel_scroll);
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
