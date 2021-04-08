package population;

import country.Settlement;
import location.Point;

public class Vaccinated extends Person
{
	public Vaccinated(Point p, Settlement s,long vaccinationTime) 
	{
		super(p, s);
		this.vaccinationTime=vaccinationTime;
	}
	public String toString() 
	{
		return  super.toString()+"vaccination time"+this.getVaccinationTime();
	}
	@Override
	public double contagionProbability() {
		int t=(int) Math.abs(21-vaccinationTime);
		if (vaccinationTime<21)
			return Math.min(1, (0.56 + 0.15*Math.sqrt(t)));
		else 
			return Math.max(0.05,1.05/t-14);
	}
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Vaccinated))
			return false;
		Vaccinated v=(Vaccinated)o;
		return (this.getAge()==v.getAge()&&this.getLocation().equals(v.getLocation())&&this.getSettlement().equals(v.getSettlement())&&this.getVaccinationTime()==v.getVaccinationTime());
	}
	public long getVaccinationTime()
	{
		return this.vaccinationTime;
	}
	@Override
	public Person replicate() {
		// TODO Auto-generated method stub
		return new Vaccinated(this.getLocation(),this.getSettlement(),this.getVaccinationTime());
	}
	private long vaccinationTime;
}