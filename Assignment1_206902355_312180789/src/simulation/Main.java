/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package simulation;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFrame;
import ui.Main_window;


/**
 * Main class
 */
public class Main {
	public static void main(String[] args) throws Exception 
	{ 
		Main_window window = new Main_window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
