package country;

import location.Point;
import location.Size;
import location.Location;

public class Map {
	/**
	 * this class represent the map of Settlements
	 */
	public Map(String[] data) 
	{
		String[] settlement=null; 
		for(int i=0;i<data.length;i++)
		{
			settlement=data[i].split(";");
			if (settlement[0].contentEquals("City"))
			{
				int x=Integer.parseInt(settlement[2]);
				int y=Integer.parseInt(settlement[3]);
				Point p=new Point(x,y);
				x=Integer.parseInt(settlement[4]);
				y=Integer.parseInt(settlement[5]);
				Size s1=new Size(x,y);
				x=Integer.parseInt(settlement[6]);
				Location location=new Location(p,s1);
				City c=new City(data[1],location,x);
				this.settlements[size]=c;
				size++;
			}
			else if (settlement[0].contentEquals("Kibbutz"))
			{
				int x=Integer.parseInt(settlement[2]);
				int y=Integer.parseInt(settlement[3]);
				Point p=new Point(x,y);
				x=Integer.parseInt(settlement[4]);
				y=Integer.parseInt(settlement[5]);
				Size s1=new Size(x,y);
				x=Integer.parseInt(settlement[6]);
				Location location=new Location(p,s1);
				Kibbutz c= new Kibbutz(settlement[1],location,x); 
				this.settlements[size]=c;
				size++;
			}
			else if (settlement[0].contentEquals("Moshav"))
			{
				int x=Integer.parseInt(settlement[2]);
				int y=Integer.parseInt(settlement[3]);
				Point p=new Point(x,y);
				x=Integer.parseInt(settlement[4]);
				y=Integer.parseInt(settlement[5]);
				Size s1=new Size(x,y);
				x=Integer.parseInt(settlement[6]);
				Location location=new Location(p,s1);
				Moshav c= new Moshav(settlement[1],location,x); 
				this.settlements[size]=c;
				size++;
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
		 */
		if (!(o instanceof Map))
			return false;
		Map m=(Map)o;
		return settlements==m.settlements;

	}
	private Settlement settlements[];
	private static int size=1;
}
