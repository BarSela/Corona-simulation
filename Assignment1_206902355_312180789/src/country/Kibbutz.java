/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import location.Location;
/*
 * Kibbutz class
 */
public class Kibbutz extends Settlement {
	/**
	 * this class represent the sttlement kibbutz
	 * extend settlement class

	 */
	public Kibbutz(String name, Location location,int population,int capacity) {
		/**
		 * consturctor
		 * @param name          the name of the settlement
		 * @param location      position and size
		 * @param population    amount of peole in the kibbutz
		 * @param capacity of the settlment
		 */
		super(name, location, population,capacity);
	}

	@Override
	public RamzorColor calculateramzorgrade() {
		 /**
		  * calculate ramzor color by the growth of  in the kibbutz
		  * @return the new ramzor color
		  */
		double p =super.contagiousPercent();
		double c=super.getRamzorColor().getVal();
		double new_color= 0.45+Math.pow(Math.pow(1.5, c)*(p-0.4),3);
		if(new_color<=0.4)
			return RamzorColor.GREEN;
		if (new_color> 0.4 && new_color<=0.6)
			return  RamzorColor.YELLOW;
		if (new_color>0.6 && new_color<=0.8)
			return  RamzorColor.ORANGE;
		else
			return RamzorColor.RED;
		
	}
	@Override
	public boolean equals(Object o) 
	{
		/**
		 * comapre between two kibbutz
		 * @param o the object we want to compare with
		 * @return true if equals,else false
		 */
		if(!(o instanceof Kibbutz))
			return false;
		Kibbutz k=(Kibbutz)o;
		return super.getName().equals(k.getName())&& super.getLocation().equals(k.getLocation())&&super.getRamzorColor().equals(k.getRamzorColor());

	}
}



