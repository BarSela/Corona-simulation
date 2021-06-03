/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import country.Map;
import country.Settlement;

public class MapPanel extends JPanel {
	/**
	 * this class represent the map panel drow and paint all the settlements and the
	 * line conector between two neighbors
	 */

	// data member
	private Map world = null;
	private int max_x = 0;
	private int max_y = 0;

	public MapPanel() {
		/**
		 * Constructor
		 */
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

	}

	@Override
	public void paintComponent(Graphics g) {
		/**
		 * Calls the UI delegate's paint method
		 * 
		 * @param g the Graphics object
		 */
		

		super.paintComponent(g);
		if (world == null) {
			return;
		}
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double dimension_x = (double)this.getWidth() / max_x;
		double dimension_y = (double)this.getHeight() / max_y;

		Iterator<Settlement> iter = world.iterator();
		while(iter.hasNext()) {
			Settlement s=iter.next();
			
			for (int j = 0; j < s.getneighbors().size(); j++) {
				
				int center_x1 = findCenterX(s);
				int center_y1 = findCenterY(s);
				int center_x2 = s.getneighbors().get(j).getLocation().getPosition().getPoint_x()
						+ s.getneighbors().get(j).getLocation().getsize().getWidth() / 2;
				int center_y2 = s.getneighbors().get(j).getLocation().getPosition().getPoint_y()
						+ s.getneighbors().get(j).getLocation().getsize().getHeight() / 2;
				NeighborLineDecorator n1= new NeighborLineDecorator(s,s.getneighbors().get(j));
				n1.SetColor(g);
				g.drawLine((int)(center_x1*dimension_x), (int)(center_y1*dimension_y), (int)(center_x2*dimension_x),(int) (center_y2*dimension_y));			
			}
		}
		iter=world.iterator();
		
		
		
		while(iter.hasNext()) {
			Settlement s=iter.next();
			g.setColor(Color.BLACK);
			int x=s.getLocation().getPosition().getPoint_x();
			int y=s.getLocation().getPosition().getPoint_y();
			int height=s.getLocation().getsize().getHeight();
			int width=s.getLocation().getsize().getWidth();
			g.drawRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(s.getRamzorColor().getColor());
			g.fillRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(Color.BLACK);
			g.drawString(s.getName(),(int)(x*dimension_x),(int)((y+15)*dimension_y));
			g.setFont(new Font("TimesRoman", Font.PLAIN,(int) (14*dimension_x)));
		}
	}

	public int findCenterX(Settlement s) {
		/**
		 * calc the x cordinate center of the settlement
		 * 
		 * @param i index of the settlement
		 * @return the x center cordinate of the settlement
		 */
		return s.getLocation().getPosition().getPoint_x()
				+ s.getLocation().getsize().getWidth() / 2;
	}

	public int findCenterY(Settlement s) {
		/**
		 * calc the y cordinate center of the settlement
		 * 
		 * @param i index of the settlement
		 * @return the y center cordinate of the settlement
		 */
		return s.getLocation().getPosition().getPoint_y()
				+ s.getLocation().getsize().getHeight() / 2;
	}

	public void set_map(Map world) {
		/**
		 * after load map set the map by the loaded file
		 * @param world the map
		 */
		this.world = world;
		// find max x and max y
		max_x = max_y = 0;
		if (world != null)
			for (int i = 0; i < world.getSettlement().length; i++) {
				if (world.getSettlement()[i].getLocation().getPosition().getPoint_x()+world.getSettlement()[i].getLocation().getsize().getWidth() > max_x) {
					max_x = world.getSettlement()[i].getLocation().getPosition().getPoint_x()+world.getSettlement()[i].getLocation().getsize().getWidth();
				}
				if (world.getSettlement()[i].getLocation().getPosition().getPoint_y()+world.getSettlement()[i].getLocation().getsize().getHeight() > max_y) {
					max_y = world.getSettlement()[i].getLocation().getPosition().getPoint_y()+world.getSettlement()[i].getLocation().getsize().getHeight();
				}
			}
		max_x += 10;
		max_y +=10;
		this.repaint();
	}
	public double getDimentionX()
	{
		/**
		 * resolution for point x
		 */
		return (double)this.getWidth() / max_x;
	}
	public double getDimentionY()
	{
		/**
		 * resolution for point y
		 */
		return (double)this.getHeight() / max_y;
	}

}