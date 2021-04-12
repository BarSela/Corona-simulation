/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package simulation;
/**
 *Clock class
 */

public class Clock 
{
	/**
	 * time is the Current time in simulation
	 */
	private static long time;
	
	public Clock()
	{
		/**
		 * defult constructor
		 */
		time=0;
	}
	public Clock(long time) 
	{
		/**
		 * constructor
		 * @param time 
		 */
		
		Clock.time=time;
	}
	
	public static long now() 
	 {
		/**
		 * @return current time
		 */
		return time;
	 }
	 public static void nextTick() 
	 {
		 /**
		  * extend time in 1
		  */
		 time +=1;
	 }
}
