package IO;

import java.io.*;
import java.util.*;

import country.City;
import country.Kibbutz;
import country.Map;
import country.Moshav;
import country.Settlement;
import location.Location;
import location.Point;
import location.Size;

public class SimulationFile
{
	public static File file= new File("data.txt");
	public static void writeTofile()
	{
		try 
		{
			PrintWriter output=new PrintWriter(file);
			output.println("City; Ashdod; 0;0; 90;50; 100");
			output.println("City; Beer-Sheva; 30;0; 80;80; 1200");
			output.println("Moshav; Gevim; 15;9; 15;20; 700");
			output.println("City; Rehovot; 7;18; 40;30; 9000");
			output.println("Kibbutz; ruhama; 10;10; 20;10; 500");
			output.println("City; Eilat; 70;30; 80;80; 10000");
			output.println("Moshav; Shtulim; 47;23; 90;25; 600");
			output.println("City; Tel-Aviv; 50;50; 80;80; 9000");
			output.println("Kibbutz; Beeri; 12;15; 60;60; 400");
			output.println("City; Jerusalem; 9;6; 80;80; 10000");
			output.close();
		}
		catch (IOException ex) 
		{
			System.out.printf("Error %s\n",ex);
		}
	}
	public static Settlement[] parse() throws Exception
	{
		Settlement[] settlement=null;
		String name = null;
		try 
		{
			FileReader fr= new FileReader(file);
			BufferedReader br= new BufferedReader(fr);
			Scanner in=new Scanner(file);
			while (br.readLine() != null)
				Map.setSize();
			settlement=new Settlement[Map.getSize()];
			name=in.nextLine();
			for (int i=0;i<Map.getSize();i++)
			{
				
				if (parseSettlement(name)!=null)
					settlement[i]=parseSettlement(name);
				else
					throw new Exception("Error with the Settlement type");
				if (!(i==Map.getSize()-1))
					name=in.nextLine();
			}
			in.close();
			br.close();
			fr.close();
		}
		catch (FileNotFoundException ex) 
		{
			System.out.printf("Error %s\n",ex);
		}
		return settlement;
	}
	private static Settlement parseSettlement(String line)
	{
		String[] settlement=line.split(";");
			if (settlement[0].contentEquals("City"))
			{
				//point-position of the settlement
				int x=Integer.parseInt(settlement[2].replace(" ", ""));
				int y=Integer.parseInt(settlement[3].replace(" ", ""));
				Point p=new Point(x,y);
				
				//size-size of the settlement
				int height=Integer.parseInt(settlement[4].replace(" ", ""));
				int width=Integer.parseInt(settlement[5].replace(" ", ""));
				Size s1=new Size(height,width);
				
				//population-the number of people in the settlement
				int numpeople=Integer.parseInt(settlement[6].replace(" ", ""));
				Location location=new Location(p,s1);
				City c=new City(settlement[1],location,numpeople);
				return c;
			}
			else if (settlement[0].contentEquals("Kibbutz"))
			{
				//point-position of the settlement
				int x=Integer.parseInt(settlement[2].replace(" ", ""));
				int y=Integer.parseInt(settlement[3].replace(" ", ""));
				Point p=new Point(x,y);
				
				//size-size of the settlement
				int height=Integer.parseInt(settlement[4].replace(" ", ""));
				int width=Integer.parseInt(settlement[5].replace(" ", ""));
				Size s1=new Size(height,width);
				
				//population-the number of people in the settlement
				int numpeople=Integer.parseInt(settlement[6].replace(" ", ""));
				Location location=new Location(p,s1);
				Kibbutz k= new Kibbutz(settlement[1],location,numpeople); 
				return k;
			}
			else if (settlement[0].contentEquals("Moshav"))
			{
				int x=Integer.parseInt(settlement[2].replace(" ", ""));
				int y=Integer.parseInt(settlement[3].replace(" ", ""));
				Point p=new Point(x,y);
				
				//size-size of the settlement
				int height=Integer.parseInt(settlement[4].replace(" ", ""));
				int width=Integer.parseInt(settlement[5].replace(" ", ""));
				Size s1=new Size(height,width);
				
				//population-the number of people in the settlement
				int numpeople=Integer.parseInt(settlement[6].replace(" ", ""));
				Location location=new Location(p,s1);
				Moshav m= new Moshav(settlement[1],location,numpeople);
				return m;
		
			}
			return null;
		}
}
