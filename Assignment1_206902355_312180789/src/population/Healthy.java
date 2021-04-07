package population;

import country.Settlement;
import location.Point;

public class Healthy extends Person 
{

	public Healthy(int age, Point p, Settlement s) {
		super(age, p, s);
	}
	@Override
	public double contagionProbability() {
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
	public Person replicate() {
		// TODO Auto-generated method stub
		return new Healthy(this.getAge(),this.getLocation(),this.getSettlement());
	}
}