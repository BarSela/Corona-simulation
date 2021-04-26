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
		
		Main_window window = new Main_window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
		StatisticsFile.writeCsv();
		
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
