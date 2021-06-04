/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;

import country.City;
import country.Kibbutz;
import country.Moshav;
import country.Settlement;
import location.Location;
/**
 * SettlementFactory class
 * the class responsible to create the settelment
 */
public class SettlementFactory 
{
	public Settlement getSettlementinstance(String settlType,String name,Location location,int population,int capacity )
	{
		/**
		 * create the settelment 
		 * @param settel type 
		 * @param name of settel
		 * @paramlocation of the settel 
		 * @param num of population 
		 * @param max population
		 */
		if (settlType.contentEquals("City"))
		{

			City c=new City(name,location,population,capacity);
			return c;
		}
		else if (settlType.contentEquals("Kibbutz"))
		{
			Kibbutz k= new Kibbutz(name,location,population,capacity); 
			return k;
		}
		else 
		{
			Moshav m= new Moshav(name,location,population,capacity);
			return m;
		}	
	}
}
