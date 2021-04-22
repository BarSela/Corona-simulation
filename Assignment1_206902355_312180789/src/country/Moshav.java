/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import location.Location;
/*
 * Moshav class
 */
public class Moshav extends Settlement {
	/**
	 * this class represent the sttlement moshav
	 * extend settlement class
	 */
	public Moshav(String name, Location location,int population) {
		/**
		 * consturctor
		 * @param name          the name of the settlement
		 * @param location      position and size
		 * @param population    amount of peole in the moshav
		 */
		super(name, location,population);
	}

	@Override
	public RamzorColor calculateramzorgrade() {
		 /**
		  * calculate ramzor color by the growth of  in the moshav
		  * @return the new ramzor color
		  */
		double p =this.contagiousPercent();
		double c=super.getRamzorColor().getVal();
		double new_color= 0.3+3*Math.pow(Math.pow(1.2, c)*(p-0.35),5);
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
		 * comapre between two moshav
		 * @param o the object we want to compare with
		 * @return true if equals,else false
		 */
		if(!(o instanceof Kibbutz))
			return false;
		Kibbutz k=(Kibbutz)o;
		return super.getName().equals(k.getName())&& super.getLocation().equals(k.getLocation())&&super.getRamzorColor().equals(k.getRamzorColor());

	}

}
