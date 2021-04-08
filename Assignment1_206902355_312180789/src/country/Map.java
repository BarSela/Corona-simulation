package country;

import java.io.File;

import location.Point;

public class Map {
	/**
	 * this class represent the map of Settlements
	 */
	public Map(File file) 
	{
		for(על כל שורה בקובץ)
			לקחת את השורה ולפרק אותה לרשימה
			1. את הבנאי שצריך
			..]1[ 2]
					City(1,2,3)
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
		 */
		if (!(o instanceof Map))
			return false;
		Map m=(Map)o;
		return settlements==m.settlements;

	}
	private Settlement settlements[];
	
	

}
