package virus;

import population.Person;
import population.Sick;
import simulation.Clock;


public class ChineseVariant implements IVirus 
{
	/**
	 * this class represent the Chinese virus variant
	 */
	
	public double contagionProbability(Person p)
	{
		if(p.getAge()<=18)
		{
			return con_18*p.contagionProbability();
		}
		if(p.getAge()>18 && p.getAge()<=55)
		{
			return con_18_55*p.contagionProbability();
		}
		return con_up55*p.contagionProbability();
	}
	public boolean tryToContagion(Person p_sick, Person p_check) throws Exception
	{
		if(p_check instanceof Sick )
		{
			throw new Exception("Both person's are sick! A sick person cannot get sick again");
		}
		
		double distance=Math.sqrt(Math.pow(p_sick.getLocation().getPoint_y()-p_check.getLocation().getPoint_y(),2)+Math.pow(p_sick.getLocation().getPoint_x()-p_check.getLocation().getPoint_x(),2));
		double result = contagionProbability(p_check)*Math.min(0,0.14*Math.exp(2-0.25*distance));
		return result <=Math.random();
		
	}
	public boolean tryToKill(Sick s)
	{
		long time=s.getContagiousTime()-Clock.now();
		//if the age of the person is 55 and up ,if not it will change properly
		double result=Math.max(0,death_up55-0.01*death_up55*Math.pow(time-15,2));
		
		if(s.getAge()<=18)
		{
			result=Math.max(0,death_18-0.01*death_18*Math.pow(time-15,2));
		}
		else if(s.getAge()>18 && s.getAge()<=55)
		{
			result=Math.max(0,death_18_55-0.01*death_18_55*Math.pow(time-15,2));
		}
		return result<=Math.random();
	}
	public String toString()
	{
		/**
	 	*@return string representation
	 	*/
		return "Chinese virus variant";
	}
	public boolean equals(Object o)
	{
		/**
		 * check if it is the same virus variant
		 * there is no difference between two viruses from the same class
		 */
		if(!(o instanceof ChineseVariant))
			return false;
		return true;
		
	}
	
	
	private final static double con_18=0.2;
	private final static double con_18_55=0.5;
	private final static double con_up55=0.7;

	private final static double death_18=0.001;
	private final static double death_18_55=0.05;
	private final static double death_up55=10;


}
