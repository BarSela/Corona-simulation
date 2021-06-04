/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;

import country.Settlement;
/**
 * NeighborLineDecorator class
 * the class responsible to drow the line neighbor
 */
public class NeighborLineDecorator 
{
	private Settlement settl1;
	private Settlement settl2;
	
	public NeighborLineDecorator(Settlement s1,Settlement s2)
	{
		/**
		 * constructor
		 * @param Settlement s1-the first settel	 
		 * @param Settlement s2-the secound settel
		 */
		this.settl1=s1;
		this.settl2=s2;
	}
	public void SetColor(Graphics g)
	{
		/**
		 * set color
		 * @param Graphics g-the component	 
		 */
		Color c1= settl1.getRamzorColor().getColor();
		Color c2= settl2.getRamzorColor().getColor();
		int red_avg=(c1.getRed()+c2.getRed())/2;
		int blue_avg=(c1.getBlue()+c2.getBlue())/2;
		int green_avg=(c1.getGreen()+c2.getGreen())/2;
		Color avg_color=new Color(red_avg, green_avg, blue_avg);
		g.setColor(avg_color);
	}
}	
