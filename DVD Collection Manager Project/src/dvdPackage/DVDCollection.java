package dvdPackage;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class DVDCollection {

	// Data fields
	
	/** The current number of DVDs in the array */
	private int numdvds;
	
	/** The array to contain the DVDs */
	private DVD[] dvdArray;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	public int getLength ()
	{
		//Return the current length of the dvd array
		return dvdArray.length;
	}
	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		// See homework instructions for proper format.
		String allDVD;
		allDVD= "Number of DVDs:"+(numdvds +1)+"\n";
		for (int i=0; i< numdvds; i++)
		{
			allDVD+= dvdArray[i].toString()+ "\n";
		}
		
		return allDVD;	
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
		
		// Set title to all upper-case letters
		title = title.toUpperCase();
		
		//1. Checking running time is valid value; else return 
		int time;
		try
		{
			time= Integer.parseInt(runningTime);
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
			return;
		}
		
		
		//2. Use for loop to check whether "title" is on the list
		
		for (int i=0; i< numdvds; i++)
		{
			String name =dvdArray[i].getTitle() ;
			if (name.compareTo(title) ==0)
			{
				//2a. Title is in the collection =>  Modify DVD
				dvdArray[i].setRating(rating);
				dvdArray[i].setRunningTime(time);
				return;
			}
			
		}
		//2b. Title is not in the collection => Add new DVD
		
		if (numdvds == dvdArray.length)
		{
			//if array is full
			dvdArray = Arrays.copyOf(dvdArray, dvdArray.length*2);
		
		}
			//add new DVD
		
		DVD newDVD = new DVD(title,rating,time);
			//Case 1: newDVD will be pushed in front or middle of the list
		if (numdvds==0) //Empty collection
		{
			dvdArray[numdvds]=newDVD;
			numdvds+=1;
			return;
		}
		
			//Case 2: newDVD will be pushed in the middle of the list
		for (int i=0; i<numdvds; i++)
		{
			String name = dvdArray[i].getTitle();
			if (name.compareTo(title)>0)
			{
				// Push elements from i to last forward 1 position ahead
				for (int x=numdvds; x>i; x--)
				{
					dvdArray[x]= dvdArray[x-1];
				}
				// Add new DVD to i position
				dvdArray[i]= newDVD;
				numdvds+=1;
			
				return;
			}
		}
			//Case 3: newDVD is added as the last element of the list
		
		dvdArray[numdvds] = newDVD;
		numdvds+=1;
	
		return;
	}
	
	public void removeDVD(String title) 
	{
		// Set title to all upper-case letters
		title = title.toUpperCase();
		
		// Find and remove DVD
		for (int i=0; i< numdvds; i++)
		{
			
			String dvdTitle = dvdArray[i].getTitle();
			if (dvdTitle.compareTo(title)==0)
			{
		
				//Case 1: Title is the last DVD in collection
				if (i == (numdvds-1))
				{
					numdvds-=1;
					return;
				}
				//Case 2: Title is up front or in the middle
					// remove dvd by moving backward 1 position
				for (int x=i; x<numdvds-1; x++)
				{
					dvdArray[x]= dvdArray[x+1];
				
				}
				// reduce numdvds by 1
				numdvds-=1;
			
				return;
			}
		}
		//If nothing match => return 
		return;

	}
	
	public String getDVDsByRating(String rating) 
	{
		
		String sameRate= "";
		for (int i=0; i< numdvds; i++)
		{
			
			String dvdRate = dvdArray[i].getRating();
			if (dvdRate.compareTo(rating)==0 )
			{
				
				sameRate+= dvdArray[i].getTitle()+ "\n";
			}
		}
	
		return sameRate;


	}

	public int getTotalRunningTime() 
	{
		int total=0;

		for (int i=0; i< numdvds; i++)
		{
			total+= dvdArray[i].getRunningTime();
		}


		return total;

	}
	public String getCurrentDVDs ()
	{
		String names="";
		for (int i=0; i< numdvds; i++)
		{
			names+= (i+1)+". "+ dvdArray[i].getTitle()+"\n";
		}
		return names;
	}
	
	public String titles()
	{
		String names="";
		for (int i=0; i< numdvds; i++)
		{
			names+= dvdArray[i].getTitle()+",";
		}
		return names;
	}
	
	public boolean loadData(String filename) 
	{
			try
			{
			InputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader( fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			String line;
				try {
					while ((line= br.readLine())!=null)
					{
						// Split phrases by "," into an array
					
						String[] phrases = new String[4];
						try
						{ 
							if (line.split(",")== null || line.split(",").length!=3)
								throw new OverUnderRangePhraseException();
							
						}
						catch (OverUnderRangePhraseException ex) 
						{
							System.out.println("File obtain lines that have no value or more values than expected! ");
							break;
						}
						phrases= line.split(",");
						addOrModifyDVD(phrases[0], phrases[1], phrases[2]);
						
					}
					fis.close();
					JOptionPane.showMessageDialog(null, "Load File Success" , "File Loaded", JOptionPane.INFORMATION_MESSAGE);
					return true;
				} 
				catch (IOException e) {
				    JOptionPane.showMessageDialog(null, "File Not Found!" , "File Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			
			}
			catch (FileNotFoundException e)
			{
				 JOptionPane.showMessageDialog(null, "File Not Found!" , "File Error", JOptionPane.ERROR_MESSAGE);
			}
			return false;
			
	}
	public void editRateRuntime (String dvd,String rate, int runningTime)
	{

		//edit running time
		for (int i=0; i<numdvds-1; i++)
			{
				if (dvd == dvdArray[i].getTitle())
				{
					
					//edit either rating or running time of dvd
					if (rate=="")
						dvdArray[i].setRunningTime(runningTime);
					else dvdArray[i].setRating(rate);
					break;
				}
			}
	}
	public String getDVDInfo (String dvd)
	{
		//Return DVD info
		String info="";
		for (int i=0; i<numdvds; i++)
			{
				
				if (dvd.compareTo(dvdArray[i].getTitle()) == 0)
				{
	
					info= dvdArray[i].getTitle()+ ": \n->Rating: "+ dvdArray[i].getRating() +"\n->Running Time: "+ dvdArray[i].getRunningTime()+" min";
					break;
				}
			}
		return info;
	}
	public void save() 
	{
		String textArea="";
		
		for (int i=0; i<numdvds-1; i++)
		{
			textArea+= dvdArray[i].toString()+"\n";

		}
		textArea+= dvdArray[numdvds-1].toString();// to prevent adding new line on the text file at the end.
		FileWriter fw;
		try {
			fw = new FileWriter("dvddata.txt",false);
		    BufferedWriter out = new BufferedWriter(fw);
		    out.write(textArea);
		    out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    


	}

	// Additional private helper methods go here:
class OverUnderRangePhraseException extends Exception
	{
	      public OverUnderRangePhraseException() { }

	      public OverUnderRangePhraseException(String message)
	      {
	         super(message);
	      }
	 }
	
}
