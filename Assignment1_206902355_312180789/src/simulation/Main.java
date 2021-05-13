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
	
	//data members
	private static boolean pause=false;
	private static boolean play=false;
	private static boolean initial_play=false;
	private static boolean stop=false;
	
	
	public static void main(String[] args) throws Exception 
	{ 
		Main_window window = new Main_window();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
	public static boolean getPause()
	{
		/**
		 *pause getter
		 *@return pause value
		 */
		return pause;
	}
	public static void setStop(boolean b)
	{
		/**
		 *stop setter
		 *@param b the current state of stop putton 
		 */
		stop=b;
	}
	public static boolean getStop()
	{
		/**
		 *stop getter
		 *@return stop value
		 */
		return stop;
	}


}
