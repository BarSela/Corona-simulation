/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulation.Main;
import virus.BritishVariant;
import virus.ChineseVariant;
import virus.IVirus;
import virus.SouthAfricanVariant;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import IO.SimulationFile;
import IO.StatisticsFile;
import country.City;
import country.Kibbutz;
import country.Map;
import country.Settlement;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.io.File;
import java.util.Random;

import simulation.Clock;


public class Main_window extends JFrame {
	private Map world=null;
	private int sleep_time=10000;
	public Main_window() 
	{
		super("Corona-simulation Main Window");
		GridLayout myGridLayout = new GridLayout(2, 1);
		getContentPane().setLayout(myGridLayout);
		setBounds(390,170,200,300);
		setPreferredSize(new Dimension(550, 450));
		//main window components
		menuBar();
		map_panel();
		simulationSpeedSlider();

		
		this.pack();
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Map getmap()
	{
		return world;
	}
	public int getsleeptime()
	{
		return sleep_time;
	}
	public void simulationSpeedSlider()
	{
		JPanel simulationspeed_p=new JPanel();
		simulationspeed_p.setLayout(new BoxLayout(simulationspeed_p, BoxLayout.LINE_AXIS));
		
		JSlider simulation_speed=new JSlider();
		simulation_speed.setMajorTickSpacing(5);
		simulation_speed.setMinorTickSpacing(1);
		simulation_speed.setMaximum(50);
		simulation_speed.setValue(30);
		simulation_speed.setToolTipText("sec to wait beteen ticks");
		simulation_speed.setPaintLabels(true);
		simulation_speed.setPaintTicks(true);
		JButton b_speed=new JButton("Set");
		b_speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				sleep_time=1000*simulation_speed.getValue();
			}
			});
		
		simulationspeed_p.add(new JLabel("Speed: "));
		simulationspeed_p.add(simulation_speed);
		simulationspeed_p.add(b_speed);
		getContentPane().add(simulationspeed_p);
		
	}
	public void map_panel()
	{
		JPanel map_panel=new JPanel();
		getContentPane().add(map_panel);
	}
	public JTable mutations_table(Map world)
	{    
 
		JCheckBox data[][]=new JCheckBox[3][3];    
	    String column[]={"British Variant","Chinese Variant","SouthAfrican Variant"};
	    for (int i=0;i<3;i++)
	    	for(int j=0;j<3;j++)
	    		{
	    			data[i][j]=new JCheckBox("hey");   
	    			data[i][j].setBounds(150,100, 50,50);
	    		}
	    JTable jt=new JTable(data,column);    
	    jt.setBounds(30,40,200,300);
	    return jt;
	}
	public JDialog statisticWindow(Map world)
	{
		
		JDialog statistic_d=new JDialog(this,"Statistics",false);
		statistic_d.getContentPane().setLayout(new GridLayout(2, 1));

		JPanel bottom_panel=new JPanel(new GridLayout(1, 3));
		bottom_panel.setLayout(new GridLayout(1, 3));
		
		//table
		Settlement[] settlements = world.getSettlement();
        TableMVCStatistic table_model = new TableMVCStatistic(settlements);        
		
		
		//bottom panel:
		
		JButton b_save = new JButton("Save");
		b_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				StatisticsFile.writeCsv(world,Main.loadFileFunc());
			}
			});
	
		JButton b_sick = new JButton("Add Sick");
		b_sick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					table_model.setSick();

				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			});
		JButton b_vaccinate= new JButton("Vaccinate");
		SpinnerModel vaccinate_nodel=new SpinnerNumberModel(1,1,100,1);
		JSpinner spinner = new JSpinner(vaccinate_nodel);
		JPanel p_douses=new JPanel();
		JButton b_dose = new JButton("Add");
		JLabel l_dose = new JLabel("Douses:");
		p_douses.add(l_dose);
		p_douses.add(spinner);
		p_douses.add(b_dose);
		
		b_dose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				int douses = (Integer) spinner.getValue();
				table_model.setDouse(douses);

			}
		});
		JDialog vaccinate=new JDialog(this,"Add vaccinate douses",true);
		vaccinate.getContentPane().add(p_douses);
		vaccinate.pack();
		b_vaccinate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				vaccinate.setVisible(true);
			}
		});
		bottom_panel.add(b_save);
		bottom_panel.add(b_sick);
		bottom_panel.add(b_vaccinate);
		
		statistic_d.getContentPane().add(table_model);
		statistic_d.getContentPane().add(bottom_panel);
		
		statistic_d.pack();
		return statistic_d;
		
	}
	public void menuBar()
	{
		//create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//--file submenu--
		JMenu file = new JMenu("File");
		JMenuItem statistics = new JMenuItem("Statistics");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem play = new JMenuItem("Play");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem stop = new JMenuItem("Stop");
		//load 
		load.setEnabled(true);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/**
				 * Upload Step: Get the location of the upload file and load the entire map.
				 */
				load.setEnabled(false);
				play.setEnabled(true);
				statistics.setEnabled(true);
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
		//statistic
		statistics.setSelected(true);
		statistics.setEnabled(false);
		statistics.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JDialog statistic_d=statisticWindow(world);
				statistic_d.setVisible(true);

			}
		});
		//edit mutations
		
		JMenuItem edit_mutations = new JMenuItem("Edit Mutations");
		//JDialog edit_mutations_d=new JDialog(this,"Edit Mutations",true);
    	IVirus[] variants={new BritishVariant(),new ChineseVariant(),new SouthAfricanVariant() };
    	MutationTable edit_mutations_d = new MutationTable(this,variants);
		//JTable table=mutations_table(world);
		//String row[]={"British Mutation","Chinese Mutation","SouthAfrican Mutation"};
		//RowedTableScroll jt_rowed =new RowedTableScroll(table,row);
		//edit_mutations_d.add(jt_rowed);
		//edit_mutations_d.pack();
		
		
		edit_mutations.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				edit_mutations_d.setVisible(true);
			}
		});

		
		//exit 
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		//add to submenu
		file.add(load);
		file.addSeparator();
		file.add(statistics);
		file.addSeparator();
		file.add(edit_mutations);
		file.addSeparator();
		file.add(exit);
		menuBar.add(file);
		
		//--simulation submenu--
		JMenu submenu_simulation = new JMenu("Simulation");
		
		//play


		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				play.setEnabled(false);
				pause.setEnabled(true);
				stop.setEnabled(true);
				Main.setPlay(true);
				Main.setPause(false);
				Main.setStop(false);

			}
		});
		
		//pause
		pause.setEnabled(false);
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				pause.setEnabled(false);
				play.setEnabled(true);
				stop.setEnabled(true);
				Main.setPlay(false);
				Main.setPause(true);
				Main.setStop(false);

				//*******************************************************
			}
		});
		
		//stop
		stop.setEnabled(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				play.setEnabled(false);
				pause.setEnabled(false);
				stop.setEnabled(false);
				load.setEnabled(true);
				statistics.setEnabled(false);
				Main.setPlay(false);
				Main.setStop(true);
				Main.setPause(false);
				world=null;
				
				//*******************************************************

			}
		});
		
		//set tick
		JMenuItem set_tick = new JMenuItem("Set tick per day");
		SpinnerModel tick_per_day=new SpinnerNumberModel(1,1,100,1);
		JSpinner spinner = new JSpinner(tick_per_day);
		JPanel p_tick=new JPanel();
		JButton b = new JButton("Set");
		JLabel l_tick = new JLabel("ticks:");
		p_tick.add(l_tick);
		p_tick.add(spinner);
		p_tick.add(b);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int spinner_tick = (Integer) spinner.getValue();
				Clock.set_tick_per_day(spinner_tick);
			}
		});
		JDialog set=new JDialog(this,"Set tick per day",true);
		set.getContentPane().add(p_tick);
		set.pack();
		set_tick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				set.setVisible(true);
			}
		});
		
		//add to submenu
		submenu_simulation.add(play);
		submenu_simulation.addSeparator();
		submenu_simulation.add(pause);
		submenu_simulation.addSeparator();
		submenu_simulation.add(stop);
		submenu_simulation.addSeparator();
		submenu_simulation.add(set_tick);
		menuBar.add(submenu_simulation);
		
		//--help submenu--
		JMenu submenu_help = new JMenu("Help");
		
		//help
		JMenuItem help = new JMenuItem("Help");
		JDialog help_dialog=new JDialog(this,"Help",true);
		JPanel help_p=new JPanel();
		help_p.setLayout(new BoxLayout(help_p,BoxLayout.PAGE_AXIS));
		JLabel l = new JLabel("<html>Hello<br/>"
				+ "This is a corona simulation<br/> "
				+ "There is a land where the corona began to develop<br/>"
				+ "Each simulation will allow you to control the development of the disease in the country<br/>"
				+ "The map appears on the main window. There are all the settlements according to their location<br/>"
				+ "Each settlement is painted in color according to the number of patients in the locality in relation to the population<br/>"
				+ "In the main window you can control the speed of the simulation using the slider<br/>"
				+ "Top toolbar:<br/>"
				+ "--file: Load a new simulation, view statistics by choice, edit mutations (convert from one mutation to another during infection) and exit the program<br/>"
				+ "--simulation: play (note: possible only when simulation is loaded), pause, stop and set tick per day - how many ticks are currently considered one day<br/>"
				+ "Statistics window: You can filter the results of the statistics by column and key-words. In this window you can add sick people and vaccine doses to a selected locality<br/>"
				+ "information about the creators of the program->Help->About<br/><br/>"
				+ "Enjoy<html>");

		help_p.add(l);
		
		
		help_dialog.getContentPane().add(help_p);
		help_dialog.pack();
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				help_dialog.setVisible(true);
			}
		});

		
		//about
		JMenuItem about = new JMenuItem("About");
		JDialog about_dialog=new JDialog(this,"About",true);
		JPanel about_p=new JPanel();
		about_p.setLayout(new BoxLayout(about_p,BoxLayout.PAGE_AXIS));
		JLabel lb = new JLabel("<html>Program Name:    Corona Simulation<br/>"
				+ "Production date:                    6.4.2021<br/><br/>"
				+ "Creators: <br/>"
				+ "Bar Sela                              ID:206902355<br/>"
				+ "Betsalel Koginsky         ID:312180789<br/><html>");

		about_p.add(lb);

		about_dialog.getContentPane().add(about_p);
		about_dialog.pack();
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				about_dialog.setVisible(true);
			}
		});
		
		//add to submenu
		submenu_help.add(help);
		submenu_help.addSeparator();
		submenu_help.add(about);
		
		//add submenu to menu
		menuBar.add(submenu_help);
	}
}

