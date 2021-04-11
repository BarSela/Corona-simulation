package population;

import country.Settlement;
import location.Point;

public class Vaccinated extends Person
{
	/*
	 * vaccinated class
	 */
	public Vaccinated(Point p, Settlement s,int age,long vaccinationTime) 
	{
		/**
		 * constractor
		 */
		super(p, s,age);
		this.vaccinationTime=vaccinationTime;
	}
	public Vaccinated(Vaccinated v_p) 
	{
		/**
		 * constractor
		 */
		super(v_p);
		this.vaccinationTime=v_p.getVaccinationTime();
	}
	public String toString() 
	{
		return  super.toString()+"vaccination time"+this.getVaccinationTime();
	}
	@Override
	public double contagionProbability() {
		/**
		 * @return contagion probability
		 */
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
		/**
		 * return the vaccinated time
		 */
		return this.vaccinationTime;
	}
	@Override
	public Person replicate() {
		// replicate the instance
		return new Vaccinated(this);
	}
	private long vaccinationTime;
}