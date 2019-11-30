package dvdPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class showInfoFrame extends JFrame {

	private JPanel contentPane;
	private DVDCollection dvdlist;
	private String dvd;
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public showInfoFrame(DVDCollection dvdlist, String selectedDVD) throws IOException 
	{
		this.dvdlist = dvdlist;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your DVD Information:");
		lblNewLabel.setBounds(30, 18, 323, 16);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 35, 174, 186);
		contentPane.add(textArea);
		
		JLabel lblPoster = new JLabel("");
	
		lblPoster.setBounds(216, 18, 200, 240);
		contentPane.add(lblPoster);
		
		JButton btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnBackToMenu.setBounds(58, 236, 117, 29);
		contentPane.add(btnBackToMenu);
		
		//Display DVD's info
		String info =dvdlist.getDVDInfo(selectedDVD);
		textArea.setText(info);
		
		//Display DVD's poster
			
			
			//Catching non-exist file 
			try
			{
				String posterFile = selectedDVD.toLowerCase()+".jpg"; // path
				lblPoster.setIcon(new ImageIcon(posterFile));
				InputStream fis = new FileInputStream(posterFile);
			}
			catch (Exception e2)
			{
			
				lblPoster.setIcon(new ImageIcon("new movie.jpg"));
			}
				
			
			

	}
}
