package population;

import country.Settlement;
import location.Point;
import virus.IVirus;

public class Convalescent extends Person 
{
	public Convalescent(Point p, Settlement s,IVirus virus) {
		super(p, s);
		this.virus=virus;
	}
	@Override
	public double contagionProbability() {
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
		return this.virus;
	}
	@Override
	public Person replicate() {
		// TODO Auto-generated method stub
		return new Convalescent(this.getLocation(),this.getSettlement(),this.getVirus());
	}
	private IVirus virus;
}
