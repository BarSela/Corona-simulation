/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package simulation;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import country.Map;
import ui.Main_window;
import ui.StatisticWindow;


/**
 * Main class
 */
public class Main {
	
	//data members
	private static boolean pause=false;
	private static boolean play=false;
	private static boolean initial_play=false;
	private static boolean stop=false;
	
	
	public static void main(String[] args) throws Exception 
	{ 
		Main_window window = new Main_window();
		Map world=window.getmap();

		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true)
		{
			
			System.out.println("1");
			if(world==null)
			{
				world=window.getmap();
			}
			try {
				if(play && !initial_play)
				{
					System.out.println("2");
					for (int i=0;i<world.getSettlement().length;i++)
						world.getSettlement()[i].InitialSimulation();
					initial_play=true;
				}

				if(!pause && !stop && play)
				{
					
					for (int i=0;i<world.getSettlement().length;i++)
					{
						world.getSettlement()[i].Simulation(world);
						
					}
					window.updateAll();
					Clock.nextTick();
					Thread.sleep(window.getsleeptime());			
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(window.getsleeptime());		
		}
		
	}
	public static File loadFileFunc() 
	{
		/**
		 * load new file
		 * @return file
		 */
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        return f;
	}
	public static void setPause(boolean b)
	{
		/**
		 *pause setter
		 *@param b the current state of pause putton 
		 */
		pause=b;
	}
	public static void setStop(boolean b)
	{
		/**
		 *stop setter
		 *@param b the current state of stop putton 
		 */
		stop=b;
	}
	public static void setPlay(boolean b)
	{
		/**
		 *play setter
		 *@param b the current state of play putton 
		 */
		play=b;
	}


}
