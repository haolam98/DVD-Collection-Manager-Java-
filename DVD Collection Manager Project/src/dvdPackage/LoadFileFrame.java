package dvdPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

public class LoadFileFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtFile;
	private String fileName="";
	private DVDCollection dl ;
	private boolean isLoaded; // Checking whether file is loaded or not
	public String getFileName()
	{
		return fileName;
	}
	/**
	 * Create the frame.
	 */
	
	public LoadFileFrame() 
	{
		isLoaded = false;
		dl = new DVDCollection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		txtFile = new JTextField();
		txtFile.setBounds(70, 97, 319, 40);
		contentPane.add(txtFile);
		txtFile.setColumns(10);
		
		JButton loadFile = new JButton("Load File");
		loadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 fileName = txtFile.getText();
				 isLoaded = dl.loadData(fileName);
				 
			}
		});
		loadFile.setBounds(70, 161, 117, 29);
		contentPane.add(loadFile);
		
		
		JLabel lblEnterYourData = new JLabel("Enter Your Data File Bellow:");
		lblEnterYourData.setBounds(57, 69, 198, 16);
		contentPane.add(lblEnterYourData);
		
		JButton btnOpenMenu = new JButton("Open Menu");
		btnOpenMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	// In the case, user did not load any file => pop up notification
				if (isLoaded==false)
				{
					int answ = JOptionPane.showConfirmDialog(null,"You haven't load file. Continue? ", "Swing Tester",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
					if (answ == JOptionPane.YES_OPTION)
					{
						JFrame menuFrame = new viewEditFrame(dl);
						menuFrame.setVisible(true);
					}
				}
				else
				{	
					JFrame menuFrame = new viewEditFrame(dl);
					menuFrame.setVisible(true);
				}
				
			}
		});
		btnOpenMenu.setBounds(272, 161, 117, 29);
		contentPane.add(btnOpenMenu);
	}
}
