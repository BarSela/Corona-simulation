/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package virus;
import population.Person;
import population.Sick;
/*
 * IVirus interface. there are different variants of viruses
 */
public interface IVirus {
	/**
	 * Calculates the probability that the transmitted person will be infected with the virus
	 */
	public double contagionProbability(Person p);
	
	/**
	 * Check if the person will be infected
	 * @throws Exception 
	 */
	public boolean tryToContagion(Person p_sick, Person p_check) throws Exception;
	
	/**
	 * check if the sick person will be killed by the virus
	 */
	public boolean tryToKill(Sick s);
	

}
