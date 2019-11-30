package dvdPackage;

import java.awt.EventQueue;

import javax.swing.*;

/**
 *  This class is an implementation of DVDUserInterface
 *  that uses JOptionPane to display the menu of command choices. 
 */

public class DVDGUI implements DVDUserInterface  {
	 
	 private DVDCollection dvdlist;
	
	 public DVDGUI(DVDCollection dl)
	 {
		 dvdlist = dl;
	 }
	 
	 public void processCommands()
	 {
		 JFrame loadFile = new LoadFileFrame();
		 loadFile.setVisible(true);
	 }
}
