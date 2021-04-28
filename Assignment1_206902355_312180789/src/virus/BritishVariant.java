/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package virus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import population.Person;
import population.Sick;
import simulation.Clock;
/*
 * British variant virus class
 */
public class BritishVariant implements IVirus {
	/**
	 * this class represent the British virus variant
	 * implement IVirus
	 */
	public BritishVariant()
	{
		/**
		 * variants list contain the variant type that this variant can contaige
		 */
	}
	public double contagionProbability(Person p)
	{
		/**
		 * Calculates the probability that the transmitted person will be infected with the virus
		 * @param p the person we want to check his propabillity to get contage
		 * @return the propabillity of the person to get contage
		 */
		return contagion*p.contagionProbability();
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
		if(p_sick instanceof Sick )
		{
			Sick p_s =(Sick)p_sick;
			long contagiousTime=p_s.getContagiousTime();
			long sickDays=Clock.CalcDays(contagiousTime);
			
			if(sickDays < Min_contage_time)
			{
				return false;
			}
		}
		if(p_check instanceof Sick )
		{
			throw new Exception("Both person's are sick! A sick person cannot get sick again");
		}
		double distance=Math.sqrt(Math.pow(p_sick.getLocation().getPoint_y()-p_check.getLocation().getPoint_y(),2)+Math.pow(p_sick.getLocation().getPoint_x()-p_check.getLocation().getPoint_x(),2));
		double result = contagionProbability(p_check)*Math.min(1,0.14*Math.exp(2-0.25*distance));
		return result >=Math.random();
		
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
		return "British virus variant";
	}
	public boolean equals(Object o)
	{
		/**
		 * check if it is the same virus variant
		 * there is no difference between two viruses from the same class
		 * @param o the object we want to compare with
		 * @return true if equals,else false
		 */
		if(!(o instanceof BritishVariant))
			return false;
		return true;
		
	}
	public static void addMutation(IVirus v) 
	{
		variants.add(v);
	}
	public static void removeMutation(IVirus v) 
	{
		variants.remove(v);
	}
	public static Set<IVirus> getSetMutation() 
	{
		return variants;
	}
	

	
	private static Set<IVirus> variants=new HashSet<IVirus>();
	static {
		variants.add(new BritishVariant());
	}
	
	//conation propabillity
	private final static double contagion=0.7;
	
	//death propabillity
	private final static double death_0_18=0.01;
	private final static double death_up18=0.1;

	private final static int Min_contage_time=5;
}
