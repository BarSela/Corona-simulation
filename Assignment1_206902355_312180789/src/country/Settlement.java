package country;

import java.util.Arrays;
import java.util.Random;
import location.Location;
import location.Point;
import population.Person;
import population.Sick;



public abstract class Settlement {
	
	public Settlement(String name, Location location){
		this.name=name;
		this.location=location;//not sure
		this.ramzorColor=RamzorColor.GREEN;
	}
	public abstract RamzorColor calculateramzorgrade();
	public abstract boolean equals(Object o);
	
	protected double contagiousPercent()
	{
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
		Random rand = new Random();
		// Generate random integers in range x to 999
        int rand_x = rand.nextInt(this.getLocation().getsize().getWidth())+this.getLocation().getPosition().getPoint_x();
        int rand_y = rand.nextInt(this.getLocation().getsize().getWidth())+this.getLocation().getPosition().getPoint_y();
        Point p=new Point(rand_x,rand_y);
        return p;
	}
	public boolean AddPerson(Person p)
	{
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
	public String toString()
	{
		/**
		 * @return string representation
		 */
		return this.getName()+": location- "+this.getLocation().toString()+" population- "+this.people.length+" ramzor color- "+this.getRamzorColor();
	}
	public RamzorColor getRamzorColor()
	{
		return this.ramzorColor;
	}
	public String getName()
	{
		return name;
	}
	public Location getLocation()
	{
		return (Location)location.replicate();
	}
	
	
	
	private String name;
	private Location location;
	private Person people[];
	private RamzorColor ramzorColor;
	
	
	

}
