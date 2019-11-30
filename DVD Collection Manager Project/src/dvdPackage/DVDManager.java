package dvdPackage;

import java.util.*;

import javax.swing.JFrame;

/**
 * 	Program to display and modify a simple DVD collection
 */

public class DVDManager {

	public static void main(String[] args) {
		
	 DVDUserInterface dlInterface;
	 DVDCollection dl = new DVDCollection();
	 dlInterface = new DVDGUI(dl);
	 dlInterface.processCommands();
	//	Scanner scan = new Scanner(System.in);
		//JFrame loadFile = new LoadFileFrame();
		//loadFile.setVisible(true);
	
	
		
		//dl.loadData(filename);

	
		
		
	}

}
