package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JPanel;

import country.Map;

public class MapPanel extends JPanel
{
	private Map world=null;
	public MapPanel(Map world) 
	{
		this.world=world;
	}
	@Override
	public void paintComponent(Graphics g) 
	{
		int center=0;
		super.paintComponent(g);
		for(int i=0;i<world.getSettlement().length;i++)
		{
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
	return new Dimension(800,800);
	}
}