package location;

import simulation.Replicable;

public class Location implements Replicable
{
	  /**
	   * the class represent the location of the person
	   * @param positon  the point
	   *                 
	   * @param size  the size
	   */
	public Location(Point p,Size s)
	{
		/**
		 * class constructor
		 * create a new position
		 * @param p point
		 * @param s size
		 */
		this.position=new Point(p. getPoint_x(),p. getPoint_y());
		this.size=new Size(s.getHeight(),s.getWidth());
	}
	public String toString() 
	{
		/**
		 * @return string representation
		 */
		return "location is:"+this.getPosition().toString()+this.getsize().toString();
	}
	@Override
	public boolean equals(Object o)
	{	/**
		*@param l  location to compare the values of position and size
		*@return true if the values of position and size of the two locations is equals, else false
		*/
		if(!(o instanceof Location))
			return false;
		Location loc=(Location)o;
		return (this.getPosition().equals(loc.getPosition())&&this.getsize().equals(loc.getsize()));

	}
	public Location getLocation()
	{
		Location l=(Location) this.replicate();
		return l;
	}
	public void setLocation(Location l)
	{
		/**
		 *set position and size to new values
		 * @param l location
		 */
		this.position.setPoint(l.position);
		this.size.setSize(l.size);
	}

	public Point getPosition()
	{
		Point p=(Point) this.position.replicate();
		return p;
	}
	public Size getsize()
	{
		Size s=(Size) this.size.replicate();
		return s;
	}
	@Override
	public Object replicate() {
		// replicate location
		return new Location(this.getPosition(),this.getsize());
	}
	
	//data members
	private Point position;
	private Size size;
}