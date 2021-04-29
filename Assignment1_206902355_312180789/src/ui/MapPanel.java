package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;

import country.Map;

public class MapPanel extends JPanel
{
	private Map world=null;
	public MapPanel(Map world) 
	{
		this.world=world;
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	
	}
	@Override
	public void paintComponent(Graphics g) 
	{
		int center=0;
		super.paintComponent(g);
		Graphics2D gr=(Graphics2D)g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int i=0;i<world.getSettlement().length;i++)
		{
			for (int j=0;j<world.getSettlement()[i].getneighbors().size();j++)
			{
				int x1=findCenterX(i);
				int y1=findCenterY(i);
				int x2=world.getSettlement()[i].getneighbors().get(j).getLocation().getPosition().getPoint_x()+world.getSettlement()[i].getneighbors().get(j).getLocation().getsize().getHeight()/2;
				int y2=world.getSettlement()[i].getneighbors().get(j).getLocation().getPosition().getPoint_y()+world.getSettlement()[i].getneighbors().get(j).getLocation().getsize().getHeight()/2;
				g.drawLine(x1,y1,x2,y2);
				g.setColor(Color.BLACK);
			}
			center= world.getSettlement()[i].getLocation().getsize().getWidth()/2 + world.getSettlement()[i].getLocation().getsize().getHeight()/2;
			g.drawRect(world.getSettlement()[i].getLocation().getPosition().getPoint_x(), world.getSettlement()[i].getLocation().getPosition().getPoint_y(), world.getSettlement()[i].getLocation().getsize().getWidth(), world.getSettlement()[i].getLocation().getsize().getHeight());
			g.setColor(world.getSettlement()[i].getRamzorColor().getColor());
			g.fillRect(world.getSettlement()[i].getLocation().getPosition().getPoint_x(), world.getSettlement()[i].getLocation().getPosition().getPoint_y(), world.getSettlement()[i].getLocation().getsize().getWidth(), world.getSettlement()[i].getLocation().getsize().getHeight());
			g.setColor(Color.BLACK);
			g.drawString(world.getSettlement()[i].getName(), world.getSettlement()[i].getLocation().getPosition().getPoint_x(), world.getSettlement()[i].getLocation().getPosition().getPoint_y()+15);
		}
	}
	@Override
	public Dimension getPreferredSize() {
		//selected according max x and y points 
	return new Dimension(1200,400);
	}
	public int findCenterX(int i)
	{
		return world.getSettlement()[i].getLocation().getPosition().getPoint_x()+world.getSettlement()[i].getLocation().getsize().getHeight()/2;
	}
	public int findCenterY(int i)
	{
		return world.getSettlement()[i].getLocation().getPosition().getPoint_y()+world.getSettlement()[i].getLocation().getsize().getHeight()/2;
	}
	
}