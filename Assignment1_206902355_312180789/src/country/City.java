/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import location.Location;

/*
 * City class
 */
public class City extends Settlement {

	/**
	 * this class represent the sttlement city
	 * extend settlement class
	 */
	public City(String name, Location location,int population) {
		/**
		 * consturctor
		 * @param name          the name of the settlement
		 * @param location      position and size
		 * @param population    amount of peole in the city
		 */
		super(name, location,population);
	}
	@Override
	public RamzorColor calculateramzorgrade() {
		 /**
		  * calculate ramzor color by the growth of  in the city
		  * @return the new ramzor color
		  */
		double p =super.contagiousPercent();
		double new_color= 0.2*Math.pow(4,1.25*p);
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
	public boolean equals(Object o) {

		/**
		 * comapre between two citys
		 * @param o the object we want to compare with
		 * @return true if equals,else false
		 */
		if(!(o instanceof City))
			return false;
		City city=(City)o;
		return super.getName().equals(city.getName())&& super.getLocation().equals(city.getLocation())&&super.getRamzorColor().equals(city.getRamzorColor());
		
	}
	
}
