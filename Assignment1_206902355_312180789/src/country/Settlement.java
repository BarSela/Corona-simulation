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
	
	public Settlement(String name, Location location,int population,int capacity){
		/**
		 * consturctor
		 * @param name          the name of the settlement
		 * @param location      position and size
		 * @param population    amount of peole in the settlement
		 */
		this.name=name;
		this.location=new Location(location.getPosition(),location.getsize());
		this.ramzorColor=RamzorColor.GREEN;
		this.healthy_people=new ArrayList<Person>(population);
		this.sick_people=new ArrayList<Person>(population);
		this.capacity=capacity;
	}

	protected double contagiousPercent()
	{
		/**
		 * @return the contagious percent in the settlement
		 */
		int sick_people=this.getsick_people().size();
		int population=this.getPopulation();
		double percent=(double)sick_people/population;
		return percent;
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
		
		if(this.getPopulation() < this.getCapacity())
		{
			if(p instanceof Healthy)
			{
				this.healthy_people.add(p);
			}
			else
			{
				this.sick_people.add(p);
			}
			return true;
		}
		return false;

		
		
	}
	public boolean transferPerson(Person p, Settlement settl)
	{
		/**
		 * transfer person from settlement to another
		 * @param p the person that transferd
		 * @param settl the settlement the person is transferd to
		 * @return true if the transfer succeeded
		 */
		double p1=this.getRamzorColor().getP();
		double p2=settl.getRamzorColor().getP();
		
		if(transferPropabillity(p1,p2))
		{
			if(settl.AddPerson(p))
			{
				if(p instanceof Sick)
				{
					this.healthy_people.remove(p);
				}
				else
				{
					this.sick_people.remove(p);
				}
				return true;
			}
		}
		return false;

		
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
		int population =this.healthy_people.size()+this.sick_people.size();
		return population;
		} 
	public List<Person> gethealthy_people()
	{
		/**
		 * @return the list of the healthy people in the settlement
		 */
		return this.healthy_people;
	}
	public List<Person> getsick_people()
	{
		/**
		 * @return the list of the sick people in the settlement
		 */
		return this.sick_people;
	}
	public List<Settlement> getneighbors()
	{
		/**
		 * @return the list neighbor settlements
		 */
		return neighbors;
	}
	public int getCapacity()
	{
		/**
		 * @return the max capacity of the settlement
		 */
		return capacity;
	}
	public int getVaccine_doses()
	{
		/**
		 * @return the number of vaccine_doses
		 */
		return vaccine_doses;
	}
	private boolean transferPropabillity(double p1, double p2)
	{
		/**
		 * @param p1 propabillity of the origin settlement ramzorcolor
		 * @param p2 propabillity of the target settlement ramzorcolor
		 * @return true if the transfer successed else false
		 */
		
		 double chance =p1*p2;
		 return chance >=Math.random();
		 
	}
	
	// abstract 
	public abstract RamzorColor calculateramzorgrade();
	public abstract boolean equals(Object o);
	
	//data members
	private String name;
	private Location location;
	private List<Person> healthy_people;
	private List<Person> sick_people;
	private RamzorColor ramzorColor;
	private int capacity;
	private int vaccine_doses=0;
	private List<Settlement> neighbors;
}
