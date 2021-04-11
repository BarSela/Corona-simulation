package population;

import country.Settlement;
import location.Point;
import virus.IVirus;

public class Sick extends Person
{
	/*
	 * Sick class
	 */
	public Sick(Point p, Settlement s, long contTime, IVirus iv)
	{
		/**
		 * constractor 
		 */
		super(p,s);
		this.contagiousTime=contTime;
		this.virus=iv;
	}
	@Override
	public Person contagion(IVirus iv) 
	{
		/**
		 * sick person cant sick again
		 */
		return null;
	}

	@Override
	public String toString()
	{
		return super.toString()+"contagious Time:"+this.getContagiousTime()+"virus: "+this.getVirus();
	}
	private Person recover() 
	{
		/**
		 * return a recover person
		 */
		Healthy h=new Healthy(this.getLocation(),this.getSettlement());
		return h;
	}
	public boolean tryTODie() 
	{
		/**
		 * chek if the instance will die
		 */
		
		if(this.virus.tryToKill(this))
			return true;
		return false;
	}
	@Override
	public boolean equals(Object o) {
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
		 */
		return 0.0;
	}
	public IVirus getVirus() 
	{
		/**
		 * return virus
		 */
		return this.virus;
	}
	public long getContagiousTime()
	{
		/**
		 * return contagious time
		 */
		return this.contagiousTime;
	}
	@Override
	public Person replicate() {
		// replicate the instance
		return new Sick(this.getLocation(),this.getSettlement(),this.getContagiousTime(),this.getVirus());
	}
	private long contagiousTime;
	private IVirus virus;
}