/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulation.Main;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import IO.SimulationFile;
import country.Map;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class Main_window extends JFrame {
	public Main_window(Map world) {
		super("Corona-simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu Menu= new JMenu("Menu");
		menuBar.add(Menu);
		
		JMenu file = new JMenu("File");
		JMenuItem load = new JMenuItem("Load");
		load.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				/**
				 * Upload Step: Get the location of the upload file and load the entire map.
				 */
				File file=Main.loadFileFunc();
				SimulationFile simulationFile=new SimulationFile();
				try {
					world=simulationFile.loadMap(file);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		Menu.add(file);
		
		Menu.addSeparator();
		
		JMenu submenu_simulation = new JMenu("Simulation");
		JMenuItem play = new JMenuItem("Play");
		play.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
			}
		});
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem stop = new JMenuItem("Stop");
		JMenuItem set_tick = new JMenuItem("Set tick per day");
		
		submenu_simulation.add(play);
		submenu_simulation.addSeparator();
		submenu_simulation.add(pause);
		submenu_simulation.addSeparator();
		submenu_simulation.add(stop);
		submenu_simulation.addSeparator();
		submenu_simulation.add(set_tick);
		Menu.add(submenu_simulation);
		
		Menu.addSeparator();
		
		JMenu submenu_help = new JMenu("Help");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem about = new JMenuItem("About");
		submenu_help.add(help);
		submenu_help.addSeparator();
		submenu_help.add(about);
		Menu.add(submenu_help);
		
		
	}
	
}
