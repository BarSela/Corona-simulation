package IO;

import java.io.*;
import java.util.*;

public class SimulationFile
{
	public static File file= new File("data.txt");
	public static void writeTofile()
	{
		try 
		{
			PrintWriter output=new PrintWriter(file);
			output.println("City; Ashdod; 0;0; 90;50; 1000");
			output.println("City; Beer-Sheva; 30;0; 80;80; 1200\r\n"
					+ "Moshav; Gevim; 15;15 10;20; 100\r\n"
					+ "");
			output.close();
		}
		catch (IOException ex) 
		{
			System.out.printf("Error %s\n",ex);
		}
	}
	public static String[] readFromfile() throws IOException
	{
		String[] temp=null;
		String name = null;
		try 
		{
			FileReader fr= new FileReader(file);
			BufferedReader br= new BufferedReader(fr);
			int counter=0;
			Scanner in=new Scanner(file);
			while (br.readLine() != null)
				counter++;
			System.out.println(counter);
			temp=new String[counter];
			name=in.nextLine();
			for (int i=0;i<counter;i++)
			{
				
				temp[i]=name;
				if (!(i==counter-1))
					name=in.nextLine();
			}
		}
		catch (FileNotFoundException ex) 
		{
			System.out.printf("Error %s\n",ex);
		}
		return temp;
	}
}
