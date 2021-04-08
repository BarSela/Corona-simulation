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
			output.close();
		}
		catch (IOException ex) 
		{
			System.out.printf("Error %s\n",ex);
		}
	}
	public static void readFromfile()
	{
		try 
		{
			Scanner in= new Scanner(file);
			String name = in.nextLine();
			String[] temp=name.split("; ");
			for (int i=0;i<temp.length;i++)
				System.out.printf("%s\n",temp[i]);
		}
		catch (FileNotFoundException ex) 
		{
			System.out.printf("Error %s\n",ex);
		}
	}
}
