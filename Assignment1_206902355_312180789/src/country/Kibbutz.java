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
	public Kibbutz(String name, Location location,int population) {
		/**
		 * consturctor
		 * @param name          the name of the settlement
		 * @param location      position and size
		 * @param population    amount of peole in the kibbutz
		 */
		super(name, location, population);
	}

	@Override
	protected RamzorColor calculateramzorgrade() {
		 /**
		  * calculate ramzor color by the growth of  in the kibbutz
		  * @return the new ramzor color
		  */
		double p =super.contagiousPercent();
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
		double new_color= 0.45+Math.pow(Math.pow(1.5, c)*(p-0.4),3);
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



