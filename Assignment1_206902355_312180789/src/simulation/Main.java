/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package simulation;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFrame;

import country.Map;
import ui.Main_window;


/**
 * Main class
 */
public class Main {
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
				Thread.sleep(5000);
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
						world.getSettlement()[i].Simulation(world,window.getsleeptime());
				}
				if(pause ||stop)
				{
					System.out.println("3");
					Thread.sleep(5000);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
			
		}
	}
	public static File loadFileFunc() 
	{
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        System.out.println(f.getPath());
        return f;
	}
	public static void setPause(boolean b)
	{
		pause=b;
	}
	public static void setStop(boolean b)
	{
		stop=b;
	}
	public static void setPlay(boolean b)
	{
		play=b;
	}

}
