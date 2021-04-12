/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package population;

import country.Settlement;
import location.Point;
import virus.IVirus;

public class Sick extends Person
{
	/*
	 * Sick class
	 */
	public Sick(Point p, Settlement s, int age,long contTime, IVirus iv)
	{
		/**
		 * Constractor
		 * @param p the point
		 * @param s Settlement info
		 * @param age 
		 * @param virus the virus he got infected
		 */
		super(p,s,age);
		this.contagiousTime=contTime;
		this.virus=iv;
	}
	public Sick(Sick s_p)
	{
		/**
		 * copy constractor 
		 * @param s_p the person to copy from his details
		 */
		super(s_p);
		this.contagiousTime=s_p.getContagiousTime();
		this.virus=s_p.getVirus();
	}
	@Override
	public Person contagion(IVirus iv) 
	{
		/**
		 * note: sick person cant sick again
		 * @param iv the virus
		 * @return null!!!
		 */
		return null;
	}

	@Override
	public String toString()
	{
		/**
		 * @return String representation
		 */
		return super.toString()+" contagious Time:"+this.getContagiousTime()+" virus: "+this.getVirus();
	}
	private Person recover() 
	{
		/**
		 * return a recover person
		 * @return instance of Healthy Person
		 */
		Healthy h=new Healthy(this.getLocation(),this.getSettlement(),this.getAge());
		return h;
	}
	public boolean tryTODie() 
	{
		/**
		 * check if the instance will die
		 * @return true if the person will die,else false
		 */
		
		if(this.virus.tryToKill(this))
			return true;
		return false;
	}
	@Override
	public boolean equals(Object o) {
		/**
		 * check if the instance is equals
		 * @param o the instance
		 * @return true if the instance is equal to another else false
		 */
		
		if(!(o instanceof Sick))
			return false;
		Sick s=(Sick)o;
		return (this.getAge()==s.getAge()&&this.getLocation().equals(s.getLocation())&&this.getSettlement().equals(s.getSettlement())&&this.getContagiousTime()==s.getContagiousTime()&&this.getVirus().equals(s.getVirus()));
	}
	@Override
	public double contagionProbability()
	{
		/**
		 * sick person cant sick again
		 * @return 0
		 */
		return 0.0;
	}
	public IVirus getVirus() 
	{
		/**
		 * @return the virus that contage the person
		 */
		return this.virus;
	}
	public long getContagiousTime()
	{
		/**
		 * @return contagious time
		 */
		return this.contagiousTime;
	}
	@Override
	public Person replicate() {
		/**
		 *replicate the instance 
		 *@return replicate of Sick person
		 */
		return new Sick(this);
	}
	
	//data members
	private long contagiousTime;
	private IVirus virus;
}