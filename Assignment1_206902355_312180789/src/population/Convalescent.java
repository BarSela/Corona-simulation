/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package population;

import country.Settlement;
import location.Point;
import virus.IVirus;
/*
 * Convalescent class
 */
public class Convalescent extends Person 
{
	/*
	 * Convalescent class represent a person who recovered from a virus
	 */
	public Convalescent(Point p, Settlement s,int age,IVirus virus) {
		/**
		 * Constractor
		 * @param p the point
		 * @param s Settlement info
		 * @param age 
		 * @param virus the virus he got infected
		 */
		super(p, s,age);
		this.virus=virus;
	}
	public Convalescent(Convalescent c_p)
	{
		/**
		 * copy Constractor
		 * @param c_p the instance to copy
		 */
		super(c_p);
		this.virus=c_p.getVirus();

	}
	@Override
	public double contagionProbability() {
		/**
		 * return the probabilitye
		 * @return the propabillity of the person to get contage
		 */
		return 0.2;
	}
	@Override
	public boolean equals(Object o) {
		/**
		 * check if the instance is equals
		 * @param o the instance
		 * @return true if the instance is equal to another else false
		 */
		if(!(o instanceof Convalescent))
			return false;
		Convalescent c=(Convalescent)o;
		return (this.getAge()==c.getAge()&&this.getLocation().equals(c.getLocation())&&this.getSettlement().equals(c.getSettlement())&&this.getVirus()==c.getVirus());
	}
	public IVirus getVirus()
	{
		/**
		 * @return the virus that the person is sick
		 */
		return this.virus;
	}
	@Override
	public Person replicate() {
		/**
		 * @return new instance with the copy constructor
		 */
		return new Convalescent(this);
	}
	
	//data member
	private IVirus virus;
}
