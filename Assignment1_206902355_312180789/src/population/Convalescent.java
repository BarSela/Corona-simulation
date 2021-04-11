package population;

import country.Settlement;
import location.Point;
import virus.IVirus;

public class Convalescent extends Person 
{
	/*
	 * Convalescent class
	 */
	public Convalescent(Point p, Settlement s,IVirus virus) {
		/**
		 * constructor to Convalescent
		 */
		super(p, s);
		this.virus=virus;
	}
	public Convalescent(Convalescent c_p)
	{
		super(c_p);
		this.virus=c_p.getVirus();

	}
	@Override
	public double contagionProbability() {
		/**
		 * return tha contagion probability
		 */
		return 0.2;
	}
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Convalescent))
			return false;
		Convalescent c=(Convalescent)o;
		return (this.getAge()==c.getAge()&&this.getLocation().equals(c.getLocation())&&this.getSettlement().equals(c.getSettlement())&&this.getVirus()==c.getVirus());
	}
	public IVirus getVirus()
	{
		/**
		 * return the virus
		 */
		return this.virus;
	}
	@Override
	public Person replicate() {
		/**
		 * return a replicate instence
		 */
		return new Convalescent(this);
	}
	private IVirus virus;
}
