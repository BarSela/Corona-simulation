package population;

import country.Settlement;
import location.Point;
import simulation.Clock;
/**
* Helthy class
*/
public class Healthy extends Person 
{
	
	public Healthy(Point p, Settlement s,int age) {
		/**
		 * Constractor
		 * @param p the point, s Settlement info
		 */		super(p,s,age);
	}
	public Healthy(Healthy h_p)
	{
		/**
		 *  Copy Constractor
		 * @param h_p the person 
		 */
		super(h_p);
	}
	@Override
	public double contagionProbability() {
		/**
		 * contaion probabilty 
		 * @return the probabilty to get sick
		 */
		return 1;
	}
	@Override
	public boolean equals(Object o) {
		/**
		 * chek if the instance is equals
		 * @param o the instance
		 * @return true or false
		 */
		if(!(o instanceof Healthy))
			return false;
		Healthy h=(Healthy)o;
		return (this.getAge()==h.getAge()&&this.getLocation().equals(h.getLocation())&&this.getSettlement().equals(h.getSettlement()));	
	}
	@Override
	public Object replicate() {
		/**
		 * @return replicate instance
		 */
		return new Healthy(this);
	}
	public Person vaccinate()
	{
		/**
		 * make a healthy person vaccinate
		 * @return new instance
		 */
		Vaccinated v=new Vaccinated(this.getLocation(),this.getSettlement(),this.getAge(),Clock.now());
		return v;
	}

}