package dvdPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

public class viewEditFrame extends JFrame {

	private JPanel contentPane;
	private String[] dvdTitles;
	private DVDCollection dvdlist;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public viewEditFrame(DVDCollection dvdlist) 
	{
		this.dvdlist = dvdlist;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 47, 190, 159);
		contentPane.add(scrollPane);
		
		JList myList = new JList();
		scrollPane.setViewportView(myList);
		
		//Automatically Show the current Dvds collection
				DefaultListModel DML = new DefaultListModel();
				dvdTitles = dvdlist.titles().split(",");
				for (int i=0; i<dvdTitles.length; i++)
				{
					DML.addElement(dvdTitles[i]);
				}
				myList.setModel(DML);
				
				JButton ReloadDVDs = new JButton("Refresh");
				ReloadDVDs.setForeground(Color.BLACK);
				ReloadDVDs.setIcon(new ImageIcon("refresh_icon.png"));

				ReloadDVDs.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						
						dvdTitles = dvdlist.titles().split(",");
						DefaultListModel DML = new DefaultListModel();
						
						
						for (int i=0; i<dvdTitles.length; i++)
						{
							DML.addElement(dvdTitles[i]);
						}
						myList.setModel(DML);
					}
				});
		ReloadDVDs.setBounds(275, 222, 112, 37);
		contentPane.add(ReloadDVDs);
		
		JButton btnNewButton = new JButton("View Selected DVD Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String selectedDVD =myList.getSelectedValue().toString();
					JFrame infoFrame = new showInfoFrame(dvdlist,selectedDVD);
					infoFrame.setVisible(true);
				}
				catch (NullPointerException ex)
				{
					JOptionPane.showMessageDialog(null, "You haven't selected any specific DVD. \nPlease select a DVD from the list to view its info","Undefined Selected DVD", JOptionPane.INFORMATION_MESSAGE);  
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(17, 47, 200, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblYourCurrentDvd = new JLabel("Your Current DVD Collection:");
		lblYourCurrentDvd.setBounds(226, 19, 224, 16);
		contentPane.add(lblYourCurrentDvd);
		
		JButton btnAddModifyDVD = new JButton("Add New or Modify DVD");
		btnAddModifyDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				doAddOrModifyDVD();
			}
		});
		btnAddModifyDVD.setBounds(17, 82, 200, 23);
		contentPane.add(btnAddModifyDVD);
		
		JButton btnRemoveDvd = new JButton("Remove DVD");
		btnRemoveDvd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				doRemoveDVD();
			}
		});
		btnRemoveDvd.setBounds(17, 117, 200, 23);
		contentPane.add(btnRemoveDvd);
		
		JButton btnGetDvdsByRating = new JButton("Get DVDs by Rating");
		btnGetDvdsByRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				doGetDVDsByRating();
			}
		});
		btnGetDvdsByRating.setBounds(17, 152, 200, 23);
		contentPane.add(btnGetDvdsByRating);
		
		JButton btnTotalRuntime = new JButton("Get Total Runtime");
		btnTotalRuntime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				doGetTotalRunningTime();
			}
		});
		btnTotalRuntime.setBounds(17, 187, 200, 23);
		contentPane.add(btnTotalRuntime);
		
		JButton btnSaveExit = new JButton("Save & Exit");
		btnSaveExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dvdlist.save();
				JOptionPane.showMessageDialog(null,"All data is saved to file dvddata.txt.\nClose Window to Exit ","Save File", JOptionPane.INFORMATION_MESSAGE); 
			}
		});
		btnSaveExit.setForeground(Color.RED);
		btnSaveExit.setIcon(new ImageIcon("save_icon.png"));
		btnSaveExit.setBounds(52, 222, 142, 37);
		contentPane.add(btnSaveExit);
		
		textField = new JTextField();
		textField.setBounds(431, 79, 149, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFindResult = new JLabel("");
		lblFindResult.setBounds(431, 149, 145, 73);
		contentPane.add(lblFindResult);
		
		JButton btnFind = new JButton("Look Up");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if (textField.getText().length()==0)
				{
					lblFindResult.setText("<html>Please enter the name of DVD you want to find</html>");
					lblFindResult.setForeground(Color.red);
				}
				else
				{
					String input = textField.getText().toUpperCase();
					boolean isFound = false;
					for (int i=0; i< dvdTitles.length; i++)
					{
						if (input.compareTo(dvdTitles[i])==0)
						{
							myList.setSelectedValue(input, true);
							lblFindResult.setText("Find match!");
							lblFindResult.setForeground(Color.blue);
							isFound= true;
							break;
						}
					}
					if (isFound==false)
					{
						lblFindResult.setText("<html>Sorry! DVD is not in the list.</html>"); // Add <html> tag => Help get print to second line if the text is too long
						lblFindResult.setForeground(Color.red);
					}
				}
			}
		});
		btnFind.setIcon(new ImageIcon("find_icon.png"));
		btnFind.setBounds(444, 114, 117, 29);
		contentPane.add(btnFind);
		
		
		
		
		
	}
	
	private void doAddOrModifyDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating for " + title);
		if (rating == null) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
		// Request the running time
		String time = JOptionPane.showInputDialog("Enter running time for " + title);
		if (time == null) {
			return;		// dialog was cancelled
		}
		
        // Add or modify the DVD (assuming the rating and time are valid
        dvdlist.addOrModifyDVD(title, rating, time);
                
        // Display current collection to window
        String showMess =  title + " is added/modified\n"+ "Click Refresh Button to see the Update Version."; 
        JOptionPane.showMessageDialog(null, showMess, "Add/Modify DVD", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	private void doRemoveDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
        // Remove the matching DVD if found
        dvdlist.removeDVD(title);
                
        // Display current collection to the console for debugging
        String showMess =  title + " is removed." + "\nClick Refresh Button to see the update version."; 
        JOptionPane.showMessageDialog(null, showMess, "Remove DVD", JOptionPane.INFORMATION_MESSAGE);

	}
	
	private void doGetDVDsByRating() {

		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating");
		if (rating == null) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
        String result = "DVDs with rate "+ rating+ ":\n" + dvdlist.getDVDsByRating(rating);
        
        JOptionPane.showMessageDialog(null, result, "Get DVDs by Rating", JOptionPane.INFORMATION_MESSAGE);

	}

    private void doGetTotalRunningTime() {
                 
        int total = dvdlist.getTotalRunningTime();
        JOptionPane.showMessageDialog(null, "Total running time is "+total+" min.", "Get Total Running Time", JOptionPane.INFORMATION_MESSAGE);  
                
   }
}
