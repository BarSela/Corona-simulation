package population;

import country.Settlement;
import location.Point;
import simulation.Clock;

public class Healthy extends Person 
{
	/**
	 * helthy class
	 */

	public Healthy(Point p, Settlement s) {
		/**
		 * constructor 
		 */
		super(p, s);
	}
	public Healthy(Healthy h_p)
	{
		super(h_p);
	}
	@Override
	public double contagionProbability() {
		/**
		 * return contagion probability
		 */
		return 1;
	}
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Healthy))
			return false;
		Healthy h=(Healthy)o;
		return (this.getAge()==h.getAge()&&this.getLocation().equals(h.getLocation())&&this.getSettlement().equals(h.getSettlement()));	
	}
	@Override
	public Object replicate() {
		// return replicate instance
		return new Healthy(this);
	}
	public Person vaccinate()
	{
		/**
		 * return a instence vaccinated
		 */
		Vaccinated v=new Vaccinated(this.getLocation(),this.getSettlement(),Clock.now());
		return v;
	}

}