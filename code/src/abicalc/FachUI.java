/*package abicalc;

import java.awt.Color;
import java.awt.Component;
//import java.awt.Dimension;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FachUI extends Component {
	private static final long serialVersionUID = -854793322105068780L;
	
	
	FachUI(String Name){
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		
		//panel.setMaximumSize(new Dimension(600, 100));
		//panel.setPreferredSize(new Dimension(600, 100));
		//panel.setMinimumSize(new Dimension(600, 100));
		
		
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl = new JLabel(Name);
		panel.add(lbl);
		
		JButton btn = new JButton("Noten");
		btn.setBounds(641, 10, 113, 23);
		panel.add(btn);
		
		
		//panel.setName("fachPanel_"+Name);
		
		Abicalc.panel_scrollContentHJ1.add(panel);
		Abicalc.panel_FaecherHJ1.validate();
		Abicalc.panel_FaecherHJ1.repaint();
	}
}
*/