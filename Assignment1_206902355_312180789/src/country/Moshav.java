package country;

import location.Location;

public class Moshav extends Settlement {
	/**
	 * this class represent the sttlement kibbutz
	 * @param name
	 * @param location
	 */
	public Moshav(String name, Location location) {
		super(name, location);
	}

	@Override
	public RamzorColor calculateramzorgrade() {
		// calculate ramzor color by the growth of  in the city
		double p =this.contagiousPercent();
		double c=0;
		switch(super.getRamzorColor())
		{
		case GREEN:
			c=0.4;
			break;
		case YELLOW:
			c=0.6;
			break;
		case ORANGE:
			c=0.8;
			break;
		case RED:
			c=1.0;
			break;
		}
		double new_color= 0.45+Math.pow(Math.pow(1.2, c)*(p-0.35),5);
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
	public boolean equals(Object o) 
	{
		// comapre between two kibbutzs
		if(!(o instanceof Kibbutz))
			return false;
		Kibbutz k=(Kibbutz)o;
		return super.getName().equals(k.getName())&& super.getLocation().equals(k.getLocation())&&super.getRamzorColor().equals(k.getRamzorColor());

	}

}
