/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package simulation;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import IO.SimulationFile;
import IO.StatisticsFile;
import country.Map;
import country.Settlement;
import population.Healthy;
import population.Person;
import population.Sick;
import ui.Main_window;
import virus.BritishVariant;
import virus.IVirus;

/**
 * Main class
 */
public class Main {

	
	public static void main(String[] args) throws Exception 
	{ 
		
		try 
		{

			//Main_window window = new Main_window();
	        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Map world=null;
			for (int i=0;i<world.getSettlement().length;i++)
				world.getSettlement()[i].InitialSimulation();
			for (int i=0;i<world.getSettlement().length;i++)
				world.getSettlement()[i].Simulation(world);
			
			
			
			//just for check...
			for (int i=0;i<world.getSettlement().length;i++)
				for(int j=0;j<world.getSettlement()[i].getsick_people().size();j++)
					if (world.getSettlement()[i].getsick_people().get(j) instanceof Sick)
						System.out.println(world.getSettlement()[i].getsick_people().get(j).toString());
			StatisticsFile.writeCsv();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
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
}
