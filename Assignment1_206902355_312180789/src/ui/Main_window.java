/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import simulation.Main;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.io.File;
import simulation.Clock;


public class Main_window extends JFrame {
	private JTextField tbfilter;
	private JLabel resultfilter=null;
	private Map world=null;
	private boolean run=false;
	private boolean loaded=false;
	public Main_window() {
		super("Corona-simulation Main Window");
		GridLayout myGridLayout = new GridLayout(2, 1);
		getContentPane().setLayout(myGridLayout);
		menuBar();
		map_panel();
		simulationSpeedSlider();

		this.pack();
		this.setVisible(true);
	}
	public void simulationSpeedSlider()
	{
		JSlider simulation_speed=new JSlider();
		simulation_speed.setMajorTickSpacing(10);
		simulation_speed.setMinorTickSpacing(1);
		simulation_speed.setPaintLabels(true);
		simulation_speed.setPaintTicks(true);
		simulation_speed.getValue();
		getContentPane().add(simulation_speed);
		
	}
	public void map_panel()
	{
		JPanel map_panel=new JPanel();
		getContentPane().add(map_panel);
	}
	public JPanel table(Map world)
	{    
		JPanel p=new JPanel();    
	    String data[][]=new String[world.getSettlement().length][7];    
	    String column[]={"Settlement Name","Settlement Type","Population","Ramzor color","Sick Percentages","Vaccine doses","Dead People Number"};         
	    for (int i=0;i<world.getSettlement().length;i++)
			{
	    		data[i][0]=world.getSettlement()[i].getName();
	    		if(world.getSettlement()[i] instanceof City)
	    			data[i][1]="City";
	    		else if(world.getSettlement()[i] instanceof Kibbutz)
	    			data[i][1]="Kibbutz";
	    		else
	    			data[i][1]="Moshav";
	    		data[i][2]=world.getSettlement()[i].getPopulation()+"";
	    		data[i][3]=world.getSettlement()[i].getRamzorColor()+"";
	    		data[i][4]=((double)world.getSettlement()[i].getsick_people().size()/world.getSettlement()[i].getPopulation())*100+"%";
	    		data[i][5]=world.getSettlement()[i].getVaccine_doses()+"";
	    		data[i][6]=world.getSettlement()[i].getVaccine_doses()+"";
			}
	    StatisticsFile.writeCsv(world);
	    JTable jt=new JTable(data,column);    
	    jt.setBounds(30,40,200,300);          
	    JScrollPane sp=new JScrollPane(jt);    
	    p.add(sp);          
	    p.setSize(300,400);    
	    p.setVisible(true);
	    return p;
	}
	public JDialog statisticWindow(Map world)
	{
		
		JDialog statistic_d=new JDialog(this,"Statistics",false);
		statistic_d.setLayout(new GridLayout(3, 1));
		
		JPanel top_panel=new JPanel();
		top_panel.setLayout(new GridLayout(1, 2));
		JPanel bottom_panel=new JPanel(new GridLayout(1, 3));
		bottom_panel.setLayout(new GridLayout(1, 3));
		
		//top panel:
		//change to comboBox
		JPanel combo=new JPanel();
		combo.setLayout(new BoxLayout(combo, BoxLayout.PAGE_AXIS));
		combo.add(new JLabel("select column:"));
		
		//filter
		JPanel filter=new JPanel();
		filter.setLayout(new BoxLayout(filter, BoxLayout.LINE_AXIS));
		filter.add(new JLabel("filter:"));
		JPanel lb_text_and_result=new JPanel();
		lb_text_and_result.setLayout(new BoxLayout(lb_text_and_result, BoxLayout.PAGE_AXIS));
		lb_text_and_result.add(new JLabel("filter:"));
		lb_text_and_result.add(tbfilter=new JTextField());
		lb_text_and_result.add(resultfilter=new JLabel(""));
		filter.add(lb_text_and_result);
		JButton b_ok = new JButton("Ok");
		b_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String c =tbfilter.getText().toString();
				boolean col=false;//change to find col name***
				//put c somwhere***
				if(col)
				{

					resultfilter.setText("Filtered");
				}
				else
				{
					resultfilter.setText("Try again");
				}
				
			}
			});
		filter.add(b_ok);
		
		top_panel.add(combo);
		top_panel.add(filter);
		
		//bottom panel:
		
		JButton b_save = new JButton("Save");
		b_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//save changes***
			}
			});
	
		JButton b_sick = new JButton("Add Sick");
		b_sick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//add sick***
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
				//add to settlement   ----add_vaccine_doses(int douses)
			}
		});
		JDialog vaccinate=new JDialog(this,"Add vaccinate douses",true);
		vaccinate.add(p_douses);
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
		
		statistic_d.add(top_panel);
		JPanel table=table(world);
		statistic_d.add(table);
		statistic_d.add(bottom_panel);
		
		statistic_d.pack();
		return statistic_d;
		
	}
	public void menuBar()
	{
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
		statistics.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(loaded)
				{
					JDialog statistic_d=statisticWindow(world);
					statistic_d.setVisible(true);
				}
			}
		});
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
						for (int i=0;i<world.getSettlement().length;i++)
							for (int j=0;j<world.getSettlement()[i].getsick_people().size();j++)
								System.out.println(world.getSettlement()[i].getsick_people().get(j).toString());
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
		set.add(p_tick);
		set.pack();
		set_tick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				set.setVisible(true);
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
		JDialog help_dialog=new JDialog(this,"Help",true);
		JPanel help_p=new JPanel();
		help_p.setLayout(new BoxLayout(help_p,BoxLayout.PAGE_AXIS));
		JLabel l1 = new JLabel("Hello");
		JLabel l2 = new JLabel("This is a corona simulation");
		JLabel lE1 = new JLabel("There is a land where the corona began to develop");
		JLabel lE2 = new JLabel("Each simulation will allow you to control the development of the disease in the country.");
		JLabel l3 = new JLabel("The map appears on the main window. There are all the settlements according to their location");
		JLabel l4 = new JLabel("Each settlement is painted in color according to the number of patients in the locality in relation to the population");
		JLabel l5 = new JLabel("In the main window you can control the speed of the simulation using the slider");
		JLabel l6 = new JLabel("   ");
		JLabel l7 = new JLabel("Top toolbar:");
		JLabel l8 = new JLabel("--file: Load a new simulation, view statistics by choice, edit mutations (convert from one mutation to another during infection) and exit the program.");
		JLabel l9 = new JLabel("--simulation: play (note: possible only when simulation is loaded), pause, stop and set tick per day - how many ticks are currently considered one day");
		JLabel l10 = new JLabel("   ");
		JLabel l11= new JLabel("Statistics window: You can filter the results of the statistics by column and key-words. In this window you can add sick people and vaccine doses to a selected locality.");
		JLabel l12= new JLabel("information about the creators of the program->Help->About");
		JLabel l13 = new JLabel("   ");
		JLabel l14 = new JLabel("Enjoy");
		JLabel l15 = new JLabel("   ");
		help_p.add(l1);
		help_p.add(l2);
		help_p.add(lE1);
		help_p.add(lE2);
		help_p.add(l3);
		help_p.add(l4);
		help_p.add(l5);
		help_p.add(l6);
		help_p.add(l7);
		help_p.add(l8);
		help_p.add(l9);
		help_p.add(l10);
		help_p.add(l11);
		help_p.add(l12);
		help_p.add(l13);
		help_p.add(l14);
		help_p.add(l15);
		
		
		
		help_dialog.add(help_p);
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
		JLabel lb1 = new JLabel("Program Name:    Corona Simulation ");
		JLabel lb2 = new JLabel("Production date:                    6.4.2021 ");
		JLabel lb3 = new JLabel("   ");
		JLabel lb4 = new JLabel("Creators:");
		JLabel lb5 = new JLabel("Bar Sela                              ID:206902355");
		JLabel lb6 = new JLabel("Betsalel Koginsky         ID:312180789");
		JLabel lb7 = new JLabel("    ");

		about_p.add(lb1);
		about_p.add(lb2);
		about_p.add(lb3);
		about_p.add(lb4);
		about_p.add(lb5);
		about_p.add(lb6);
		about_p.add(lb7);
		about_dialog.add(about_p);
		about_dialog.pack();
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				about_dialog.setVisible(true);
			}
		});
		
		submenu_help.add(help);
		submenu_help.addSeparator();
		submenu_help.add(about);
		
		menuBar.add(submenu_help);
	}
}

