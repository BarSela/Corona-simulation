package population;

import java.util.Random;

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
	public Person(Point p, Settlement s)
	{
		this.age=calcAge();
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
		Sick s=new Sick(this.getLocation(),this.getSettlement(),Clock.now(),iv);
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
	private int calcAge()
	{
		Random rand = new Random();
		int y =rand.nextInt(5); //between 0 to 4
		double x= rand.nextGaussian();
		int age=(int) ((int) 5*x+y);
		return age;
	}
	//data members
	private int age;
	private Point location;
	private Settlement settlement;

}