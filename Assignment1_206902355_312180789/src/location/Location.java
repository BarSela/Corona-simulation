/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package location;

import simulation.Replicable;

/*
 * Location Class
 */
public class Location implements Replicable
{
	  /**
	   * the class represent the location of the person
	   * implement Replicable
	   * @param positon  =>left top point
	   *                 
	   * @param size  the size => height and width
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
		return "location: "+this.getPosition().toString()+" "+this.getsize().toString();
	}
	@Override
	public boolean equals(Object o)
	{	/**
		*@param o  object to compare the values of position and size
		*@return true if the values of position and size of the two locations is equals, else false
		*/
		if(!(o instanceof Location))
			return false;
		Location loc=(Location)o;
		return (this.getPosition().equals(loc.getPosition())&&this.getsize().equals(loc.getsize()));

	}
	public Location getLocation()
	{
		/**
		 * @return location 
		 */
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
		/**
		 * @return the position (Point) of the Location
		 */
		Point p=(Point) this.position.replicate();
		return p;
	}
	public Size getsize()
	{
		/**
		 * @return the size of the Location
		 */
		Size s=(Size) this.size.replicate();
		return s;
	}
	@Override
	public Object replicate() {
		/**
		 * replicate location
		 * @return object 
		 */
		
		return new Location(this.getPosition(),this.getsize());
	}
	
	//data members
	private Point position;
	private Size size;
}