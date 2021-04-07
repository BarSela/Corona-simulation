package country;

import location.Location;

public class City extends Settlement {

	/**
	 * this class represent the sttlement city
	 * @param name
	 * @param location
	 */
	public City(String name, Location location) {
		super(name, location);
	}

	@Override
	public RamzorColor calculateramzorgrade() {
		// calculate ramzor color by the growth of  in the city
		double p =this.contagiousPercent();
		double new_color= 0.2*Math.pow(4,1.25*p);
		if(new_color<=0.4)
			return RamzorColor.GREEN;
		else if (new_color<=0.6)
			return  RamzorColor.YELLOW;
		else if (new_color<=0.8)
			return  RamzorColor.ORANGE;
		else
			return RamzorColor.RED;
		
	}
	@Override
	public boolean equals(Object o) {
		// comapre between two citys
		if(!(o instanceof City))
			return false;
		City city=(City)o;
		return super.getName().equals(city.getName())&& super.getLocation().equals(city.getLocation())&&super.getRamzorColor().equals(city.getRamzorColor());
		
	}
	

}
