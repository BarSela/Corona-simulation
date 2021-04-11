package population;

import java.util.Random;

import country.City;
import country.Kibbutz;
import country.Moshav;
import country.Settlement;
import location.Point;
import simulation.Clock;
import virus.IVirus;
public abstract class Person 
{
	/*
	 * Person abstract class
	 */
	public Person(Point p, Settlement s)
	{
		/*
		 * Person constructor
		 */
		this.age=calcAge();
		this.location=new Point(p.getPoint_x(),p.getPoint_y());
		if (s instanceof City)
			this.settlement=new City(s.getName(),s.getLocation(),s.getPopulation());
		else if (s instanceof Kibbutz)
			this.settlement=new Kibbutz(s.getName(),s.getLocation(),s.getPopulation());
		else if (s instanceof Moshav)
			this.settlement=new Moshav(s.getName(),s.getLocation(),s.getPopulation());
	}
	public Person(Person p)
	{
		this.age=p.getAge();
		this.location=p.getLocation();
		this.settlement=p.getSettlement();
	}
	public abstract double contagionProbability();
	public Person contagion(IVirus iv) 
	{
		/**
		 * contagion a healthy person
		 * @param iv the virus that the man is sick
		 * @return a sick person
		 */
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
		/**
		 * return the location of the person
		 */
		return this.location.getPoint();
	}
	public Settlement getSettlement() {return this.settlement;}
	public abstract Object replicate();
	private int calcAge()
	{
		/**
		 * calc the age in random
		 * @return age
		 */
		Random rand = new Random();
		int y =rand.nextInt(5); //between 0 to 4
		double x= rand.nextGaussian()*standardDeviation+expectation;
		int age=(int) ((int) 5*x+y);
		return age;
	}
	//data members
	private int age;
	private Point location;
	private Settlement settlement;
	private final int standardDeviation=6;
	private final int expectation=9;
	

}