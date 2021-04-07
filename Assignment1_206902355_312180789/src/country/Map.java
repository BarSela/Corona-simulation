package country;

import location.Point;

public class Map {
	/**
	 * this class represent the map of Settlements
	 */
	public String toString()
	{
		/**
		 * @return string representation
		 */
		return 
	}
	public boolean equals(Object o)
	{
		/**
		 * check if the maps is similar
		 */
		if (!(o instanceof Map))
			return false;
		Map m=(Map)o;
		return settlements==m.settlements;

	}
	private Settlement settlements[];
	
	

}
