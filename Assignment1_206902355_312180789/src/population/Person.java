package population;

import country.City;
import country.Kibbutz;
import country.Moshav;
import country.Settlement;
import location.Location;
import location.Point;
import simulation.Clock;
import virus.IVirus;
public abstract class Person 
{
	public Person(int age, Point p, Settlement s)
	{
		this.age=age;
		this.location=new Point(p.getPoint_x(),p.getPoint_y());
		if (s instanceof City)
			this.settlement=new City(s.getName(),s.getLocation(),s.getPopulation());
		else if (s instanceof Kibbutz)
			this.settlement=new Kibbutz(s.getName(),s.getLocation(),s.getPopulation());
		else if (s instanceof Moshav)
			this.settlement=new Moshav(s.getName(),s.getLocation(),s.getPopulation());
	}
	public abstract double contagionProbability();
	public Person contagion(IVirus iv) 
	{
		Sick s=new Sick(this.getAge(),this.getLocation(),this.getSettlement(),Clock.now(),iv);
		return s;
	}
	public String toString() {
		return "age:"+this.getAge()+"location:"+location.toString()+"Settlment:"+settlement.toString();
	}
	public  abstract boolean equals(Object o);
	public int getAge()
	{
		/**
		 * @return the age of the person
		 */
		return this.age;
	}
	public Point getLocation()
	{
		return this.location.getPoint();
	}
	public Settlement getSettlement() {return this.settlement;}
	public abstract Person replicate();
	
	//data members
	private int age;
	private Point location;
	private Settlement settlement;

}