/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package virus;

import population.Person;
import population.Sick;
import simulation.Clock;
/*
 * Sout hAfrican variant virus class
 */
public class SouthAfricanVariant implements IVirus {
	/**
	 * this class represent the South African virus variant
	 */
	public SouthAfricanVariant()
	{
		/**
		 * No initialization information for constructor
		 */
	}
	public double contagionProbability(Person p)
	{
		/**
		 * Calculates the probability that the transmitted person will be infected with the virus
		 * @param p the person we want to check his propabillity to get contage
		 * @return the propabillity of the person to get contage
		 */
		if(p.getAge()<=18)
		{
			return con_0_18*p.contagionProbability();
		}
		return con_up18*p.contagionProbability();
	}
	public boolean tryToContagion(Person p_sick, Person p_check) throws Exception
	{
		/**
		 * Check if the  second person will be infected by the sick person.
		 * the function check the health condition of the second person. if he is allready sick he cant get infected again.
		 * @param p_sick the sick person
		 * @param p_check the person that was next to the sick person.
		 * @return truth if the second person got infected by the sick person,else false
		 * @throws Exception 
		 */
		if(p_check instanceof Sick )
		{
			throw new Exception("Both person's are sick! A sick person cannot get sick again");
		}
		double distance=Math.sqrt(Math.pow(p_sick.getLocation().getPoint_y()-p_check.getLocation().getPoint_y(),2)+Math.pow(p_sick.getLocation().getPoint_x()-p_check.getLocation().getPoint_x(),2));
		double result = contagionProbability(p_check)*Math.min(1,0.14*Math.exp(2-0.25*distance));
		return result >= Math.random();
		
	}
	public boolean tryToKill(Sick s)
	{
		/**
		 * check if the sick person will be killed by the virus
		 * @param s  sick person
		 * @return true if the person will be killed by the virus,else false
		 */
		long time=s.getContagiousTime()-Clock.now();
		//if the age of the person is under 18, else will change properly
		double result=Math.max(0,death_0_18-0.01*death_0_18*Math.pow(time-15,2));

		if(s.getAge()>18)
		{
			result=Math.max(0,death_up18-0.01*death_up18*Math.pow(time-15,2));
		}
		return result >=Math.random();
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
		 * @param o the object we want to compare with
		 * @return true if equals,else false
		 */
		if(!(o instanceof SouthAfricanVariant))
			return false;
		return true;
		
	}
	
	

	//conation propabillity
	private final static double con_0_18=0.6;
	private final static double con_up18=0.5;
	
	//death propabillity
	private final static double death_0_18=0.05;
	private final static double death_up18=0.08;
}
