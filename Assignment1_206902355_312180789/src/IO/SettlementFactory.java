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

public class SettlementFactory 
{
	public Settlement getSettlementinstance(String settlType,String name,Location location,int population,int capacity )
	{
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
