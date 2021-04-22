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
	private static long tick_per_day=1;
	
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
	 public static void set_tick_per_day(long new_tic)
	 {
		 tick_per_day = new_tic;
	 }
	 public static long CalcDays(long start_time)
	 {
		 long tick=time-start_time;
		 long days= (long)Math.ceil(tick/tick_per_day);
		 return days;
	 }
}
