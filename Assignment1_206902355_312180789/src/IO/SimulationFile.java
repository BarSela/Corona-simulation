/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
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
import population.Healthy;

/**
 * simulation file class
 * the class responsible for loading instances of the simulation from a file.
 */

public class SimulationFile
{
	public Map loadMap(File file) throws Exception
	{
		/**
		 * the function responsible for load the map
		 * @param file the file that contain all the settlement
		 * @return the map object
		 */
		
		return new Map(SimulationFile.parse(file));
	}
	public static Settlement[] parse(File file) throws Exception
	{
		/**
		 * the function read the file and split the settlement and parse them to the array of settlement
		 * @param file to read the settlements 
		 * @return the function return array of settlements
		 */
		Settlement[] settlement=null;
		String name = null;
		String[] line=null;
		int[] numPeopole=null;
		try 
		{
			FileReader fr= new FileReader(file);
			BufferedReader br= new BufferedReader(fr);
			Scanner in=new Scanner(file);
			while (br.readLine() != null)
				Map.setSize();
			settlement=new Settlement[Map.getSize()];
			numPeopole=new int[Map.getSize()];
			name=in.nextLine();
			for (int i=0;i<Map.getSize();i++)
			{
				line=name.split(";");
				if (parseSettlement(line)!=null)
					{
						settlement[i]=parseSettlement(line);
						numPeopole[i]=Integer.parseInt(line[6].replace(" ", ""));
					}
				else
					throw new Exception("Error with the Settlement type");
				if (!(i==Map.getSize()-1))
					name=in.nextLine();
			}
			Healthy new_person;
			for(int i=0;i<Map.getSize();i++)
			{
				for(int j=0;j<numPeopole[i];j++) 
				{
					new_person=new Healthy(settlement[i].randomLocation(),settlement[i],randomAge());
					settlement[i].AddPerson(new_person);
				}
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
	private static Settlement parseSettlement(String[] line)
	{
		/**
		 * the function create a settlemnt from string
		 * @param line string array 
		 * @return Settlement 
		 */
		
		//point-position of the settlement
		int x=Integer.parseInt(line[2].replace(" ", ""));
		int y=Integer.parseInt(line[3].replace(" ", ""));
		Point p=new Point(x,y);
		
		//size-size of the settlement
		int height=Integer.parseInt(line[4].replace(" ", ""));
		int width=Integer.parseInt(line[5].replace(" ", ""));
		Size s1=new Size(height,width);
		
		//population-the number of people in the settlement
		int numpeople=Integer.parseInt(line[6].replace(" ", ""));
		Location location=new Location(p,s1);
		
		
		//Settlement type
		if (line[0].contentEquals("City"))
		{

			City c=new City(line[1],location,numpeople);
			return c;
		}
		else if (line[0].contentEquals("Kibbutz"))
		{
			Kibbutz k= new Kibbutz(line[1],location,numpeople); 
			return k;
		}
		else if (line[0].contentEquals("Moshav"))
		{
			Moshav m= new Moshav(line[1],location,numpeople);
			return m;
		}
		return null;
	}
	private static int randomAge()
	{
		/**
		 * calc random age
		 * @return age
		 */
		Random rand = new Random();
		int y =rand.nextInt(5); //between 0 to 4
		int g=(int) rand.nextGaussian();
		
		while(g>1 || g<-1)
		{
			g=(int) rand.nextGaussian();
		}
		double x= (int)g*standardDeviation+expectation;
		int age= (int) Math.abs(5*x+y);
		return Math.abs(age);
		
	}
	
	//data members
	private Map map;
	private static int Size;
	
	private final static int standardDeviation=6;
	private final static int expectation=9;
}
