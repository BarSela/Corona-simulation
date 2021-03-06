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
	 * The variable time is the Current time in simulation Over tick units
	 * The variable tick_per_day is the ratio of tick to day, ie how many ticks there are in one day.
	 */
	private static long time=0;
	private static int tick_per_day=1;
	
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
	 public static void set_tick_per_day(int new_tic)
	 {
		 /**
		  * claculate How many tics are considered one day
		  * @param new_tic new value for some worth one day in tics 
		  */
		 tick_per_day = new_tic;
	 }
	 public static long CalcDays(long start_time)
	 {
		 /**
		  * @param start_time the tick to calc how many days passed
		  * @return the number of days passed
		  */
		 long tick=Clock.now()-start_time;
		 long days= (long)Math.ceil(tick/tick_per_day);
		 return days;
	 }
}
