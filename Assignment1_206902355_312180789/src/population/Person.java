package population;

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
	public Person(Point p, Settlement s,int age)
	{
		/*
		 * Person constructor
		 */
		this.age=age;
		this.location=p;
		if (s instanceof City)
			this.settlement=s;
		else if (s instanceof Kibbutz)
			this.settlement=s;
		else if (s instanceof Moshav)
			this.settlement=s;
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
		Sick s=new Sick(this.getLocation(),this.getSettlement(),this.getAge(),Clock.now(),iv);
		return s;
	}
	public String toString() {
		return "age: "+this.getAge()+" location:"+this.getLocation().toString()+" Settlment:"+this.getSettlement().toString();
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

	//data members
	private int age;
	private Point location;
	private Settlement settlement;
	

}