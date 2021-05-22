/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import java.util.concurrent.CyclicBarrier;

import simulation.Main;

/*
 * Map class
 */
public class Map {
	/**
	 * this class represent the map of Settlements
	 */
	public Map(Settlement[] settlement)
	{
		/**
		 * load map-constructor
		 * @param data array of strings. evry string is a settlement
		 */
		this.settlements=new Settlement[size];
		this.settlements=settlement;
	}
	public String toString()
	{
		/**
		 * @return string representation
		 */
		return null;
	}
	public boolean equals(Object o)
	{
		/**
		 * check if the maps is similar
		 * @param o the object to compare with
		 */
		if (!(o instanceof Map))
			return false;
		Map m=(Map)o;
		return settlements==m.settlements;

	}
	public static int getSize() {
		/**
		 *@return size of the map
		 */
		return size;
		}
	public static void setSize() 
	{
		/**
		 * set size to size+1
		 */
		size++;
	}
	public static void resetSize() 
	{
		/**
		 * set size to size+1
		 */
		size=0;
	}
	public Settlement[] getSettlement() 
	{
		/**
		 * @return array of settlement
		 */
		return this.settlements;
	} 
	public boolean isPause()
	{
		/**
		 * @return if simulation is paused or not
		 */
		return Main.getPause();
		
	}
	public boolean isStop()
	{
		/**
		 * @return if simulation is stoped or not
		 */
		return Main.getStop();
		
	}
	public void start_thread()
	{
		/**
		 * start all threades
		 */
		for(int i=0;i<settlements.length;i++)
		{
			new Thread(getSettlement()[i]).start();
		}
	}
	

	//data members
	private Settlement settlements[];
	public CyclicBarrier cycle;
	private static int size=0;
}
