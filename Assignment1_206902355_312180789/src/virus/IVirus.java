package virus;
import population.Person;
import population.Sick;

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
	 * check if the sick person will e killed by the virus
	 */
	public boolean tryToKill(Sick s);
	

}
