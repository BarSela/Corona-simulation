/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;
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
	
	//data members
	private Settlement settlements[];
	private static int size=0;
}
