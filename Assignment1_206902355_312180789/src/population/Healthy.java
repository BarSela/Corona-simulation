package population;

import country.Settlement;
import location.Point;
import simulation.Clock;

public class Healthy extends Person 
{

	public Healthy(Point p, Settlement s) {
		super(p, s);
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
		return new Healthy(this.getLocation(),this.getSettlement());
	}
	public Person vaccinate()
	{
		Vaccinated v=new Vaccinated(this.getLocation(),this.getSettlement(),Clock.now());
		return v;
	}

}