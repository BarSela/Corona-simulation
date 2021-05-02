/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import country.Map;

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
		int center = 0;

		super.paintComponent(g);
		if (world == null) {
			return;
		}
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int dimension_x = this.getWidth() / max_x;
		int dimension_y = this.getHeight() / max_y;

		for (int i = 0; i < world.getSettlement().length; i++) {
			g.setColor(Color.BLACK);
			for (int j = 0; j < world.getSettlement()[i].getneighbors().size(); j++) {
				int x1 = findCenterX(i);
				int y1 = findCenterY(i);
				int x2 = world.getSettlement()[i].getneighbors().get(j).getLocation().getPosition().getPoint_x()
						+ world.getSettlement()[i].getneighbors().get(j).getLocation().getsize().getHeight() / 2;
				int y2 = world.getSettlement()[i].getneighbors().get(j).getLocation().getPosition().getPoint_y()
						+ world.getSettlement()[i].getneighbors().get(j).getLocation().getsize().getHeight() / 2;
				g.drawLine(x1, y1, x2, y2);
				
			}
			center = world.getSettlement()[i].getLocation().getsize().getWidth() / 2
					+ world.getSettlement()[i].getLocation().getsize().getHeight() / 2;
			g.drawRect(world.getSettlement()[i].getLocation().getPosition().getPoint_x(),
					world.getSettlement()[i].getLocation().getPosition().getPoint_y(),
					world.getSettlement()[i].getLocation().getsize().getWidth(),
					world.getSettlement()[i].getLocation().getsize().getHeight());
			g.setColor(world.getSettlement()[i].getRamzorColor().getColor());
			g.fillRect(world.getSettlement()[i].getLocation().getPosition().getPoint_x(),
					world.getSettlement()[i].getLocation().getPosition().getPoint_y(),
					world.getSettlement()[i].getLocation().getsize().getWidth(),
					world.getSettlement()[i].getLocation().getsize().getHeight());
			g.setColor(Color.BLACK);
			g.drawString(world.getSettlement()[i].getName(),
					world.getSettlement()[i].getLocation().getPosition().getPoint_x(),
					world.getSettlement()[i].getLocation().getPosition().getPoint_y() + 15);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		// selected according max x and y points
		return new Dimension(1200, 400);
	}

	public int findCenterX(int i) {
		/**
		 * calc the x cordinate center of the settlement
		 * 
		 * @param i index of the settlement
		 * @return the x center cordinate of the settlement
		 */
		return world.getSettlement()[i].getLocation().getPosition().getPoint_x()
				+ world.getSettlement()[i].getLocation().getsize().getHeight() / 2;
	}

	public int findCenterY(int i) {
		/**
		 * calc the y cordinate center of the settlement
		 * 
		 * @param i index of the settlement
		 * @return the y center cordinate of the settlement
		 */
		return world.getSettlement()[i].getLocation().getPosition().getPoint_y()
				+ world.getSettlement()[i].getLocation().getsize().getHeight() / 2;
	}

	public void set_map(Map world) {
		this.world = world;
		// find max x and max y
		max_x = max_y = 0;
		if (world != null)
			for (int i = 0; i < world.getSettlement().length; i++) {
				if (world.getSettlement()[i].getLocation().getPosition().getPoint_x() > max_x) {
					max_x = world.getSettlement()[i].getLocation().getPosition().getPoint_x();
				}
				if (world.getSettlement()[i].getLocation().getPosition().getPoint_y() > max_y) {
					max_y = world.getSettlement()[i].getLocation().getPosition().getPoint_y();
				}
			}
		this.repaint();
	}

}