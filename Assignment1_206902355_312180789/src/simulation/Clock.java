package simulation;


public class Clock 
{
	private static long time;
	public Clock()
	{
		time=0;
	}
	public Clock(long time) 
	{
		
		Clock.time=time;
	}
	
	public static long now() 
	 {
		return time;
	 }
	 public static void nextTick() 
	 {
		 time +=1;
	 }
}
