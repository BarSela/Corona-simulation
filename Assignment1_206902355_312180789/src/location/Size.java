package location;

import simulation.Replicable;

public class Size implements Replicable
{ 	/**
 	* this represent a size
 	* @param height 
 	* 
 	* @param width
 	*/
	
	public Size(int height,int width)
	{		/**
		   * class constructor
		   * Creates a new Size with the given height and width 
		   */
		this.height=height;
		this.width=width;
	}
	@Override
	public String toString()
	{	/**
		*@return string representation
		*/
		return "height: "+this.getHeight()+" width: "+this.getWidth();
	}
	public boolean equals(Object o)
	{
		/**
		 * @param s  Size to compare the value of height and width
		 * @return true if the values of height and width is equals, else false
		 */
		if(!(o instanceof Size))
			return false;
			
		Size s = (Size) o;
		 return (this.getHeight() == s.getHeight()&&this.getWidth()==s.getWidth());

	}
	public void setSize(Size s) 
	{	/**
		*@param s Size to set the values of height and width
		*/
		this.height=s.getHeight();
		this.width=s.getWidth();
	}
	public int getHeight() 
	{	/**
		*@return height of Size
		*/
		return this.height;
	}
	public int getWidth() 
	{	/**
		*@return width of Size
		*/
		return this.width;
	}
	@Override
	public Object replicate() {
		// replicate size
		return new Size(this.getHeight(),this.getWidth());
	}
	//data members
	private int height;
	private int width;

}

