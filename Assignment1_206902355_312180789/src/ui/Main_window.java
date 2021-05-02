/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulation.Main;
import virus.BritishVariant;
import virus.ChineseVariant;
import virus.IVirus;
import virus.SouthAfricanVariant;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import IO.SimulationFile;
import country.Map;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import java.io.File;
import java.io.IOException;
import simulation.Clock;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main_window extends JFrame {
	/**
	 * this Class represent the main window contains the menuBar, map panel and the
	 * speed slider
	 * 
	 */

	// data members:
	private Map world = null; // the map
	private int sleep_time = 30000; // gap between ticks (simulation)
	private MapPanel map_panel;

	public Main_window() throws IOException {
		/**
		 * Constructor main window extends Jframe
		 */

		super("Corona-simulation Main Window");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		BorderLayout myBorderLayout = new BorderLayout();
		getContentPane().setLayout(myBorderLayout);
		setBounds(390, 170, 200, 300);
		setPreferredSize(new Dimension(1000, 700));

		// main window components
		menuBar();
		getContentPane().add(map_panel(), BorderLayout.CENTER);
		simulationSpeedSlider();

		this.pack();
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Map getmap() {
		/**
		 * map getter
		 * 
		 * @return the loaded map
		 */
		return world;
	}

	public int getsleeptime() {
		/**
		 * sleep time getter sleep time is the gap between simulations. control by speed
		 * simulation
		 * 
		 * @return sleep time
		 */
		return sleep_time;
	}

	public void simulationSpeedSlider() {
		/**
		 * this function create the panel of the speed slider on the main window
		 */
		// panel
		JPanel simulationspeed_p = new JPanel();
		simulationspeed_p.setLayout(new BoxLayout(simulationspeed_p, BoxLayout.LINE_AXIS));
		// slider
		JSlider simulation_speed = new JSlider();
		simulation_speed.setMajorTickSpacing(5);
		simulation_speed.setMinorTickSpacing(1);
		simulation_speed.setMaximum(50);
		simulation_speed.setValue(30);
		simulation_speed.setToolTipText("<= go faster || go slower =>");
		simulation_speed.setPaintLabels(true);
		simulation_speed.setPaintTicks(true);

		// save the selected speed
		JButton b_speed = new JButton("Set");
		b_speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sleep_time = 1000 * simulation_speed.getValue();
			}
		});

		// speed icon
		ImageIcon speedIcon = new ImageIcon(getClass().getResource("/Speed-icon.png"));
		JLabel sp_icon = new JLabel();
		sp_icon.setIcon(speedIcon);

		// add to panel
		simulationspeed_p.add(sp_icon);
		simulationspeed_p.add(simulation_speed);
		simulationspeed_p.add(b_speed);

		getContentPane().add(simulationspeed_p, BorderLayout.SOUTH);
	}

	public MapPanel map_panel() {
		/**
		 * this function create the map panel. called after load the file
		 */

		// panel
		map_panel = new MapPanel();
		map_panel.setVisible(true);
		map_panel.repaint();

		// identify click on spesific settlement. open statistic window with the row of
		// the settlement
		map_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(world==null)
					return;
				for (int i = 0; i < world.getSettlement().length; i++) {

					
					int x_settl =(int)( world.getSettlement()[i].getLocation().getPosition().getPoint_x()*map_panel.getDimentionX());
					int y_settl = (int)(world.getSettlement()[i].getLocation().getPosition().getPoint_y()*map_panel.getDimentionY());
					int h_settl = (int)(world.getSettlement()[i].getLocation().getsize().getHeight()*map_panel.getDimentionY());
					int w_settl = (int)(world.getSettlement()[i].getLocation().getsize().getWidth()*map_panel.getDimentionX());

					if (x_settl <= e.getPoint().getX() && e.getPoint().getX() <= x_settl + w_settl
							&& y_settl <= e.getPoint().getY() && e.getPoint().getY() <= y_settl + h_settl) {
						System.out.println(world.getSettlement()[i].getName());
						StatisticWindow statistic_d = statisticWindow(world, world.getSettlement()[i].getName());
						statistic_d.setVisible(true);
						break;
					}

				}
			}
		});

		return map_panel;

	}

	public StatisticWindow statisticWindow(Map world, String row_name) {
		/**
		 * this function create the statistic window and return it
		 * 
		 * @param world the map
		 * @param row   name the name of the settlement to represent on the statistic
		 *              table
		 * @return statistic window
		 */
		StatisticWindow statistic_d = new StatisticWindow(this, world, row_name);
		return statistic_d;

	}

	public void menuBar() throws IOException {
		/**
		 * the function create the menu bar on the main window
		 */

		// create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// ------------file submenu-----------------
		JMenu file = new JMenu("File");

		// ICONS for the menu items
		Image sIcon = new ImageIcon(getClass().getResource("/Statistic-icon.png")).getImage();
		Image loadIcon = new ImageIcon(getClass().getResource("/load-o-icon.png")).getImage();
		Image playIcon = new ImageIcon(getClass().getResource("/Play-icon.png")).getImage();
		Image pauseIcon = new ImageIcon(getClass().getResource("/Pause-icon.png")).getImage();
		Image stopIcon = new ImageIcon(getClass().getResource("/Stop-icon.png")).getImage();
		Image exitIcon = new ImageIcon(getClass().getResource("/Exit-icon.png")).getImage();
		Image virusIcon = new ImageIcon(getClass().getResource("/Virus-icon-s.png")).getImage();
		Image tickIcon = new ImageIcon(getClass().getResource("/Clock-icon.png")).getImage();

		// menu items
		JMenuItem statistics = new JMenuItem("Statistics");
		statistics.setIcon(new ImageIcon(sIcon));
		JMenuItem load = new JMenuItem("Load");
		load.setIcon(new ImageIcon(loadIcon));
		JMenuItem play = new JMenuItem("Play");
		play.setIcon(new ImageIcon(playIcon));
		JMenuItem pause = new JMenuItem("Pause");
		pause.setIcon(new ImageIcon(pauseIcon));
		JMenuItem stop = new JMenuItem("Stop");
		stop.setIcon(new ImageIcon(stopIcon));

		// load
		load.setEnabled(true);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Upload Step: Get the location of the upload file and load the entire map.
				 */

				load.setEnabled(false);
				play.setEnabled(true);
				statistics.setEnabled(true);
				File file = Main.loadFileFunc();
				SimulationFile simulationFile = new SimulationFile();
				try {
					world = simulationFile.loadMap(file);
					map_panel.set_map(world);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// statistic
		statistics.setSelected(true);
		statistics.setEnabled(false);
		statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticWindow statistic_d = statisticWindow(world, "");
				statistic_d.setVisible(true);

			}
		});

		// edit mutations
		JMenuItem edit_mutations = new JMenuItem("Edit Mutations");
		edit_mutations.setIcon(new ImageIcon(virusIcon));
		IVirus[] variants = { new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant() };
		MutationTable edit_mutations_d = new MutationTable(this, variants);

		// open edit mutation window
		edit_mutations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit_mutations_d.setVisible(true);
			}
		});

		// exit
		JMenuItem exit = new JMenuItem("Exit");
		exit.setIcon(new ImageIcon(exitIcon));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// add to submenu
		file.add(load);
		file.addSeparator();
		file.add(statistics);
		file.addSeparator();
		file.add(edit_mutations);
		file.addSeparator();
		file.add(exit);
		menuBar.add(file);

		// ---------simulation submenu---------
		JMenu submenu_simulation = new JMenu("Simulation");

		// play
		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play.setEnabled(false);
				pause.setEnabled(true);
				stop.setEnabled(true);
				Main.setPlay(true);
				Main.setPause(false);
				Main.setStop(false);

			}
		});

		// pause
		pause.setEnabled(false);
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause.setEnabled(false);
				play.setEnabled(true);
				stop.setEnabled(true);
				Main.setPlay(false);
				Main.setPause(true);
				Main.setStop(false);
			}
		});

		// stop
		stop.setEnabled(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play.setEnabled(false);
				pause.setEnabled(false);
				stop.setEnabled(false);
				load.setEnabled(true);
				statistics.setEnabled(false);
				Main.setPlay(false);
				Main.setStop(true);
				Main.setPause(false);
				Main.setInitialPlay(false);
				world = null;
				map_panel.set_map(null);

			}
		});

		// set tick
		JMenuItem set_tick = new JMenuItem("Set tick per day");
		set_tick.setIcon(new ImageIcon(tickIcon));
		SpinnerModel tick_per_day = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spinner = new JSpinner(tick_per_day);
		JPanel p_tick = new JPanel();
		JButton b = new JButton("Set");
		JLabel l_tick = new JLabel("ticks:");
		p_tick.add(l_tick);
		p_tick.add(spinner);
		p_tick.add(b);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinner_tick = (Integer) spinner.getValue();
				Clock.set_tick_per_day(spinner_tick);
			}
		});
		// open set ticks per day dialog
		JDialog set = new JDialog(this, "Set tick per day", true);
		set.setBounds(390, 170, 200, 300);
		set.getContentPane().add(p_tick);
		set.pack();
		set_tick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set.setVisible(true);
			}
		});

		// add to submenu
		submenu_simulation.add(play);
		submenu_simulation.addSeparator();
		submenu_simulation.add(pause);
		submenu_simulation.addSeparator();
		submenu_simulation.add(stop);
		submenu_simulation.addSeparator();
		submenu_simulation.add(set_tick);
		menuBar.add(submenu_simulation);

		// -----------help submenu-------------
		JMenu submenu_help = new JMenu("Help");

		// help
		JMenuItem help = new JMenuItem("Help");

		// ICONS
		Image helpIcon = new ImageIcon(getClass().getResource("/Help-icon.png")).getImage();
		help.setIcon(new ImageIcon(helpIcon));
		ImageIcon corona_pic = new ImageIcon(getClass().getResource("/Virus-a.png"));
		JLabel corona_icon = new JLabel();
		corona_icon.setIcon(corona_pic);

		JDialog help_dialog = new JDialog(this, "Help", true);
		help_dialog.setBounds(390, 170, 200, 300);
		JPanel help_p = new JPanel();
		help_p.setLayout(new BoxLayout(help_p, BoxLayout.PAGE_AXIS));

		// lable
		JLabel l = new JLabel("<html>Hello<br/>" + "This is a corona simulation<br/> "
				+ "There is a land where the corona began to develop<br/>"
				+ "Each simulation will allow you to control the development of the disease in the country<br/>"
				+ "The map appears on the main window. There are all the settlements according to their location<br/>"
				+ "Each settlement is painted in color according to the number of patients in the locality in relation to the population<br/>"
				+ "In the main window you can control the speed of the simulation using the slider<br/>"
				+ "Top toolbar:<br/>"
				+ "--file: Load a new simulation, view statistics by choice, edit mutations (convert from one mutation to another during infection) and exit the program<br/>"
				+ "--simulation: play (note: possible only when simulation is loaded), pause, stop and set tick per day - how many ticks are currently considered one day<br/>"
				+ "Statistics window: You can filter the results of the statistics by column and key-words. In this window you can add sick people and vaccine doses to a selected locality<br/>"
				+ "information about the creators of the program->Help->About<br/><br/>" + "Enjoy<br/><html>");

		// add to help
		help_p.add(corona_icon);
		help_p.add(l);

		help_dialog.getContentPane().add(help_p);
		help_dialog.pack();
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help_dialog.setVisible(true);
			}
		});

		// about
		JMenuItem about = new JMenuItem("About");
		JDialog about_dialog = new JDialog(this, "About", true);
		about_dialog.setBounds(390, 170, 200, 300);
		JPanel about_p = new JPanel();
		about_p.setLayout(new BoxLayout(about_p, BoxLayout.PAGE_AXIS));
		JLabel lb = new JLabel("<html>Program Name:    Corona Simulation<br/>"
				+ "Production date:                    6.4.2021<br/><br/>" + "Creators: <br/>"
				+ "Bar Sela                              ID:206902355<br/>"
				+ "Betsalel Koginsky         ID:312180789<br/><html>");

		about_p.add(lb);
		about_dialog.getContentPane().add(about_p);
		about_dialog.pack();
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about_dialog.setVisible(true);
			}
		});

		// add to submenu
		submenu_help.add(help);
		submenu_help.addSeparator();
		submenu_help.add(about);

		// add submenu to menu
		menuBar.add(submenu_help);
	}

	public void updateAll() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				/**
				 * Here, we can safely update the GUI because we'll be called from the event
				 * dispatch thread
				 */
				map_panel.repaint();
				StatisticWindow.update_statistics();
			}
		});
	}
}
