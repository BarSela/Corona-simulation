/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import location.Location;
import location.Point;
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
		this.people=new ArrayList<Person>(population);
	}

	protected double contagiousPercent()
	{
		/**
		 * @return the contagious percent in the settlement
		 */
		int sick_people=0;
		for(int i=0;i<people.size();i++)
		{
			if(people.get(i) instanceof Sick)
				sick_people++;
		}
		return sick_people/people.size();
	}
	public void setRamzorColor(RamzorColor new_color)
	{
		/**
		 * set the ramzor color of the settlement
		 * @param new_color the new color of the settlement
		 */
		this.ramzorColor=new_color;
	}
	public Point randomLocation()
	{
		/**
		 * @return random location (point) in the boundry of the settlement
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
		people.add(p);
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
		people.remove(p);
		return true;
		
	}
	@Override
	public String toString()
	{
		/**
		 * @return string representation
		 */
		return this.getName()+" "+this.getLocation().toString()+" population- "+this.getPopulation()+" ramzor color- "+this.getRamzorColor();
	}
	public RamzorColor getRamzorColor()
	{
		/**
		 * @return the ramzor color of the settlement
		 */
		return this.ramzorColor;
	}
	public String getName()
	{
		/**
		 * @return the name of the settlement
		 */
		return this.name;
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
		return this.people.size();
		} 
	public List<Person> getPeople()
	{
		/**
		 * #return the list of the people in the settlement
		 */
		return this.people;
	}
	
	
	// abstract 
	public abstract RamzorColor calculateramzorgrade();
	public abstract boolean equals(Object o);
	
	//data members
	private String name;
	private Location location;
	private List<Person> people;
	private RamzorColor ramzorColor;

	
	
	

}
