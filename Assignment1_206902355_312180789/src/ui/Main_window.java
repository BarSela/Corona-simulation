/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulation.Main;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import IO.SimulationFile;
import country.Map;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import simulation.Clock;


public class Main_window extends JFrame {
	private Map world=null;
	private boolean run=false;
	private boolean loaded=false;
	public Main_window() {
		super("Corona-simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout myGridLayout = new GridLayout(3, 1);
		setLayout(myGridLayout);
		JPanel map_panel=new JPanel();
		JSlider simulation_speed=new JSlider();
		simulation_speed.setMajorTickSpacing(10);
		simulation_speed.setMinorTickSpacing(1);
		simulation_speed.setPaintLabels(true);
		simulation_speed.setPaintTicks(true);
		simulation_speed.getValue();
		
		

		add(map_panel);
		add(simulation_speed);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu file = new JMenu("File");
		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/**
				 * Upload Step: Get the location of the upload file and load the entire map.
				 */
				if(!loaded)
				{
					loaded=true;
					File file=Main.loadFileFunc();
					SimulationFile simulationFile=new SimulationFile();
					try {
						world=simulationFile.loadMap(file);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		
		JMenuItem statistics = new JMenuItem("Statistics");
		JMenuItem edit_mutations = new JMenuItem("edit Mutations");
		JMenuItem exits = new JMenuItem("Exits");
		file.add(load);
		file.addSeparator();
		file.add(statistics);
		file.addSeparator();
		file.add(edit_mutations);
		file.addSeparator();
		file.add(exits);
		menuBar.add(file);
		
		JMenu submenu_simulation = new JMenu("Simulation");
		JMenuItem play = new JMenuItem("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(loaded)
				{
					run = true;
					try {
						for (int i=0;i<world.getSettlement().length;i++)
							world.getSettlement()[i].InitialSimulation();
						for (int i=0;i<world.getSettlement().length;i++)
							world.getSettlement()[i].Simulation(world);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		});
		
		JMenuItem pause = new JMenuItem("Pause");
		
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if(run)
				{
					run =false;
					//*******************************************************
				}
			}
		});
		JMenuItem stop = new JMenuItem("Stop");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(loaded)
				{
					loaded = false;
					//*******************************************************
				}
				
			}
		});
		JMenuItem set_tick = new JMenuItem("Set tick per day");
		
		SpinnerModel tick_per_day=new SpinnerNumberModel();
		JSpinner spinner = new JSpinner(tick_per_day);
		JPanel p_tick=new JPanel();
		JButton b = new JButton("Set");
		p_tick.add(spinner);
		p_tick.add(b);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				long spinner_tick=(long) spinner.getValue();
				Clock.set_tick_per_day(spinner_tick);
			}
		});
		
		set_tick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				
				

			}
		});
		
		submenu_simulation.add(play);
		submenu_simulation.addSeparator();
		submenu_simulation.add(pause);
		submenu_simulation.addSeparator();
		submenu_simulation.add(stop);
		submenu_simulation.addSeparator();
		submenu_simulation.add(set_tick);
		menuBar.add(submenu_simulation);
		
		
		JMenu submenu_help = new JMenu("Help");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem about = new JMenuItem("About");
		submenu_help.add(help);
		submenu_help.addSeparator();
		submenu_help.add(about);
		menuBar.add(submenu_help);
		
		
	}
	
}

