package abicalc;

import java.awt.Component;

import javax.swing.JPanel;


public class Note extends Component implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//UntersteDatenstruktur
	
	String name;
	int punkte;
	double gewichtung;
	
	public Note(double g, String n, int p){//Konstruktor
		gewichtung = g;
		name = n;
		punkte = p;
		
		
		JPanel panel = new JPanel();
		
		
		
		
		
		
		
		
	}
  /*	
	public Note(String n){
		File datei = new File(n + ".txt");
		Scanner reader;
		try {
			reader = new Scanner(datei);
			name = reader.nextLine();
			punkte = Integer.parseInt(reader.nextLine());
			gewichtung = Double.parseDouble(reader.nextLine());
			if(reader.hasNextLine()){
				naechstes.name = reader.nextLine();
			}
			else{
				naechstes = null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void noteSpeichern(){
		File datei = new File(name + ".txt");
		try {
			datei.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter writer;
		try {
			writer = new FileWriter(datei);
			writer.write(name);
			writer.write(punkte);
			writer.write("" + gewichtung);
			writer.write(naechstes.name);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
*/
}
