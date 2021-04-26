/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;


public class Main_window extends JFrame {
	public Main_window() {
		super("Corona-simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu Menu= new JMenu("Menu");
		menuBar.add(Menu);
		
		JMenu file = new JMenu("File");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem statistics = new JMenuItem("Statistics");
		JMenuItem edit_mutations = new JMenuItem("edit Mutations");
		JMenuItem exits = new JMenuItem("Exits");
		file.add(load);
		file.add(statistics);
		file.add(edit_mutations);
		file.add(exits);
		Menu.add(file);
		
		Menu.addSeparator();
		
		JMenu submenu_simulation = new JMenu("Simulation");
		JMenuItem play = new JMenuItem("Play");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem stop = new JMenuItem("Stop");
		JMenuItem set_tick = new JMenuItem("Set tick per day");
		
		submenu_simulation.add(play);
		submenu_simulation.add(pause);
		submenu_simulation.add(stop);
		submenu_simulation.add(set_tick);
		Menu.add(submenu_simulation);
		
		Menu.addSeparator();
		
		JMenu submenu_help = new JMenu("Help");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem about = new JMenuItem("About");
		submenu_help.add(help);
		submenu_help.add(about);
		Menu.add(submenu_help);
		
		
	}
}
