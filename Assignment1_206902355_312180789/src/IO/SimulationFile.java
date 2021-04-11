package IO;

import java.io.*;
import java.util.*;
import country.Map;

public class SimulationFile
{
	public static File file= new File("data.txt");
	public static void writeTofile()
	{
		try 
		{
			PrintWriter output=new PrintWriter(file);
			output.println("City; Ashdod; 0;0; 90;50; 10");
			output.println("City; Beer-Sheva; 30;0; 80;80; 12");
			output.println("Moshav; Gevim; 15;9 15;20; 7");
			output.println("City; Rehovot; 7;18; 40;30; 9");
			output.println("kibbutz; ruhama; 10;10; 20;10; 5");
			output.println("City; Eilat; 70;30; 80;80; 10");
			output.println("Moshav; Shtulim; 47;23; 90;25; 6");
			output.println("City; Tel-Aviv; 50;50; 80;80; 9");
			output.println("Kibbutz; Beeri; 12;15; 60;60; 4");
			output.println("City; Jerusalem; 9;6; 80;80; 10");
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
			Scanner in=new Scanner(file);
			while (br.readLine() != null)
				Map.size++;
			temp=new String[Map.size];
			name=in.nextLine();
			for (int i=0;i<Map.size;i++)
			{
				
				temp[i]=name;
				if (!(i==Map.size-1))
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
