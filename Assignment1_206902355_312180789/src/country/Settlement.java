/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import java.util.Arrays;
import java.util.Random;
import location.Location;
import location.Point;
import population.Healthy;
import population.Person;
import population.Sick;

/*
 * Settlement class
 */

public abstract class Settlement {
	/**
	 * this class represent a settlement on the map
	 */
	
	public Settlement(String name, Location location,int population){
		/**
		 * consturctor
		 * @param name          the name of the settlement
		 * @param location      position and size
		 * @param population    amount of peole in the settlement
		 */
		this.name=name;
		this.location=new Location(location.getPosition(),location.getsize());
		this.ramzorColor=RamzorColor.GREEN;
		this.people=new Healthy[population];
	}
	// abstract 
	protected abstract RamzorColor calculateramzorgrade();
	public abstract boolean equals(Object o);
	
	
	protected double contagiousPercent()
	{
		/**
		 * @return the contagious percent in the settlement
		 */
		int sick_people=0;
		for(int i=0;i<people.length;i++)
		{
			if(people[i] instanceof Sick)
				sick_people++;
		}
		return sick_people/people.length;
	}
	public Point randomLocation()
	{
		/**
		 * @return random location
		 */
		Random rand = new Random();
		// Generate random integers in range x to 999
        int rand_x = rand.nextInt(this.getLocation().getsize().getWidth())+this.getLocation().getPosition().getPoint_x();
        int rand_y = rand.nextInt(this.getLocation().getsize().getHeight())+this.getLocation().getPosition().getPoint_y();
        Point p=new Point(rand_x,rand_y);
        return p;
	}
	public boolean AddPerson(Person p)
	{
		/**
		 * add person to the settlements
		 * @param p the person that added
		 * @return true if the addition succeeded
		 */
		if(people.length == 0)
		{
			people=new Person[1];
			people[0]=p.replicate();
			return true;
		}
		people= Arrays.copyOf(people, people.length + 1);
		people[people.length]=p.replicate();
		
		return true;
		
	}
	public boolean transferPerson(Person p, Settlement settl)
	{
		/**
		 * transfer person from settlement to another
		 * @param p the person that transferd
		 * @param settl the settlement the person is transferd to
		 * @return true if the transfer succeeded
		 */
		settl.AddPerson(p);
		Person[] temp_arr=new Person[people.length-1];
		for(int i=0,j=0;i<people.length;i++)
		{
			if (!(people[i].equals(p)))
			{
				temp_arr[j]=people[i];
				j++;
			}
		}
		people= Arrays.copyOf(temp_arr, temp_arr.length);
		return true;
		
	}
	@Override
	public String toString()
	{
		/**
		 * @return string representation
		 */
		return this.getName()+": location- "+this.getLocation().toString()+" population- "+this.people.length+" ramzor color- "+this.getRamzorColor();
	}
	public RamzorColor getRamzorColor()
	{
		/**
		 * @return the ramzor color of the settlement
		 */
		return this.ramzorColor;
	}
	private void setRamzorColor(RamzorColor new_color)
	{
		/**
		 * set ramzor color of the city
		 */
		this.ramzorColor=new_color;
	}
	public String getName()
	{
		/**
		 * @return the name of the settlement
		 */
		return name;
	}
	public Location getLocation()
	{
		/**
		 * @return the location of the settlement
		 */
		return (Location)location.replicate();
	}
	public int getPopulation() {
		/**
		 * @return amount of people in the settlemnet
		 */
		return people.length;
		} 
	
	//data members
	private String name;
	private Location location;
	private Person people[];
	private RamzorColor ramzorColor;
	
	
	

}
