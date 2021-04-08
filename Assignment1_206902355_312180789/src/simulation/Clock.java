package simulation;


public class Clock 
{
	public static long time;
	public Clock(long time) {Clock.time=time;}
	public static long now() 
	 {
		return time;
	 }
	 public static void nextTick() 
	 {
		 time +=1;
	 }
}
