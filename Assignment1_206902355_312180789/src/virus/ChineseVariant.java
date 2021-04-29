/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package virus;

import java.util.HashSet;
import java.util.Set;
import population.Person;
import population.Sick;
import simulation.Clock;
/*
 * Chinese variant virus class
 */

public class ChineseVariant implements IVirus 
{
	/**
	 * this class represent the Chinese virus variant
	 */
	public ChineseVariant()
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
		return result>=Math.random();
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
		 * @param o the object we want to compare with
		 * @return true if equals,else false
		 */
		if(!(o instanceof ChineseVariant))
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
	
	public static boolean get_british_m()
	{
		return british_m;
	}
	public static boolean get_chinese_m()
	{
		return chinese_m;
	}
	public static boolean get_southafrican_m()
	{
		return southafrican_m;
	}
	public static void set_british_m(boolean b)
	{
		british_m=b;
	}
	public static void set_chinese_m(boolean b)
	{
		chinese_m=b;
	}
	public static void set_southafrican_m(boolean b)
	{
		southafrican_m=b;
	}
	

	
	private static Set<IVirus> variants=new HashSet<IVirus>();
	static {
		variants.add(new ChineseVariant());
	}
	
	
	private static boolean british_m=false;
	private static boolean chinese_m=true;
	private static boolean southafrican_m=false;
	
	//conation propabillity
	private final static double con_18=0.2;
	private final static double con_18_55=0.5;
	private final static double con_up55=0.7;

	//death propabillity
	private final static double death_18=0.001;
	private final static double death_18_55=0.05;
	private final static double death_up55=0.1;
	
	private final static int Min_contage_time=5;


}
