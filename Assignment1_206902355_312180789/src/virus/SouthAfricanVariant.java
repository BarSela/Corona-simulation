package virus;

import population.Person;
import population.Sick;
import simulation.Clock;

public class SouthAfricanVariant implements IVirus {
	/**
	 * this class represent the South African virus variant
	 */
	
	public double contagionProbability(Person p)
	{
		if(p.getAge()<=18)
		{
			return con_0_18*p.contagionProbability();
		}
		return con_up18*p.contagionProbability();
	}
	public boolean tryToContagion(Person p_sick, Person p_check) throws Exception
	{
		if(p_check instanceof Sick )
		{
			throw new Exception("Both person's are sick! A sick person cannot get sick again");
		}
		double distance=Math.sqrt(Math.pow(p_sick.getLocation().getPoint_y()-p_check.getLocation().getPoint_y(),2)+Math.pow(p_sick.getLocation().getPoint_x()-p_check.getLocation().getPoint_x(),2));
		double result = contagionProbability(p_check)*Math.min(0,0.14*Math.exp(2-0.25*distance));
		return result <= Math.random();
		
	}
	public boolean tryToKill(Sick s)
	{
		long time=s.getContagiousTime()-Clock.now();
		//if the age of the person is under 18, else will change properly
		double result=Math.max(0,death_0_18-0.01*death_0_18*Math.pow(time-15,2));

		if(s.getAge()>18)
		{
			result=Math.max(0,death_up18-0.01*death_up18*Math.pow(time-15,2));
		}
		return result <=Math.random();
	}
	public String toString()
	{
		/**
	 	*@return string representation
	 	*/
		return "South African virus variant";
	}
	public boolean equals(Object o)
	{
		/**
		 * check if it is the same virus variant
		 * there is no difference between two viruses from the same class
		 */
		if(!(o instanceof SouthAfricanVariant))
			return false;
		return true;
		
	}
	
	
	private final static double death_0_18=0.05;
	private final static double death_up18=0.08;

	private final static double con_0_18=0.6;
	private final static double con_up18=0.5;

}
