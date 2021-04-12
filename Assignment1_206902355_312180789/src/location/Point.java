/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package location;

import simulation.Replicable;

/*
 * Point class
 */
public class Point implements Replicable
{
	  /**
	   * the class represent a point 
	   * implement Replicable
	   * @param x  the x-coordinate 
	   *                 
	   * @param y  the y-coordinate 
	   */

	public Point(int x,int y)
	{		/**
		   * class constructor
		   * Creates a new Point with the given x-coordinate and y-coordinate  .
		   */
		this.x=x;
		this.y=y;
	}
	@Override
	public String toString() 
	{	/**
	 	*@return string representation
	 	*/
		return "("+this.getPoint_x()+","+this.getPoint_y()+")";
	}
	@Override
	public boolean equals(Object o)
	{	/**
		*@param o  object to compare the values of x-coordinate and y-coordinate
		*@return true if the values of x-coordinate and y-coordinate is equals, else false
		*/
		if (!(o instanceof Point))
			return false;
		Point p=(Point)o;
		return (p.getPoint_x()==this.getPoint_x() && p.getPoint_y()==this.getPoint_y());
		
	}
	public int getPoint_x()
	{		/**
		   * @return point's x coordinate.  
		   */
		return this.x;
	}
	public int getPoint_y()
	{		/**
		   * @return point's y coordinate.  
		   */
		return this.y;
	}
	public void setPoint(Point p)
	{		/**
		   * Changes x and y coordinates of this Point.
		   * @param p Point with  new x and  y coordinates.  
		   */
		this.x=p.getPoint_x();
		this.y=p.getPoint_y();
	}
	public Point getPoint()
	{
		/**
		 * @return point object
		 */
		Point p=(Point) this.replicate();
		return p;
	}

	@Override
	public Object replicate() {
		/**
		 * replicate the Point
		 * @return Point object
		 */
		return new Point(this.getPoint_x(),this.getPoint_y());
	}
	
	//data members
	private int x;
	private int y;
}