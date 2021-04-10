/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import location.Point;
import location.Size;
import location.Location;
/*
 * Map class
 */
public class Map {
	/**
	 * this class represent the map of Settlements
	 */
	public Map(String[] data) 
	{
		/**
		 * load map-constructor
		 * @param data array of strings. evry string is a settlement
		 */
		this.settlements=new Settlement[size];
		String[] settlement=null; 
		for(int i=0;i<size-1;i++)
		{
			settlement=data[i].split(";");
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
				City c=new City(data[1],location,numpeople);
				
				this.settlements[i]=c;
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
				Kibbutz c= new Kibbutz(settlement[1],location,numpeople); 
				this.settlements[i]=c;
				size++;
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
				Moshav c= new Moshav(settlement[1],location,numpeople); 
				this.settlements[i]=c;
			}
		}
	}
	public String toString()
	{
		/**
		 * @return string representation
		 */
		return null;
	}
	public boolean equals(Object o)
	{
		/**
		 * check if the maps is similar
		 * @param o the object to compare with
		 */
		if (!(o instanceof Map))
			return false;
		Map m=(Map)o;
		return settlements==m.settlements;

	}
	//data members
	private Settlement settlements[];
	public static int size=0;
}
