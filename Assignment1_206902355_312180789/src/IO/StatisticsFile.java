/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.*;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import country.City;
import country.Kibbutz;
import country.Map;
import country.Settlement;
/**
 * statistic file class
 * the class responsible for saving the staistics in the file.
 */
public class StatisticsFile 
{
	public static String path=null;
	public static FileHandler fh=null;
	public static Logger logger=null;
	public static Originator originator = new Originator();   
	public static Memento memento;
	public static Caretaker caretaker = new Caretaker();
	public static int index=-1; 
	public static void writeCsv(Map world ,File file)
	{
		/**
		 * the function responsible to save all data
		 * @param Map world- contain all data
		 * @param file- saving all data inside 
		 */
		try  
		{
			PrintWriter writer = new PrintWriter(file);
			StringBuilder sb = new StringBuilder();
			sb.append("Settlement Name");
		    sb.append(',');
		    sb.append("Settlement Type");
		    sb.append(',');
		    sb.append("Population");
		    sb.append(',');
		    sb.append("Ramzor color");
		    sb.append(',');
		    sb.append("Sick Percentages");
		    sb.append(',');
		    sb.append("Vaccine doses");
		    sb.append('\n');
		    Iterator<Settlement> iter = world.iterator();
		    while (iter.hasNext())
			{
		    	Settlement s= iter.next();
		    	sb.append(s.getName());
		    	sb.append(',');
	    		if(s instanceof City)
	    			sb.append("City");
	    		else if(s instanceof Kibbutz)
	    			sb.append("Kibbutz");
	    		else
	    			sb.append("Moshav");
	    		sb.append(',');
	    		sb.append(s.getPopulation());
	    		sb.append(',');
	    		sb.append(s.getRamzorColor());
	    		sb.append(',');
	    		sb.append(((double)s.getsick_people().size()/s.getPopulation())*100+"%");
	    		sb.append(',');
	    		sb.append(s.getVaccine_doses());
	    		sb.append('\n');
			}
		    writer.write(sb.toString());
		    writer.close();
		} 
		catch (FileNotFoundException e) 
		{
		      System.out.println(e.getMessage());
		}
	}
	public static void writeLog(Settlement s)
	{
		/**
		 * the function responsible to wrte all date of dead 
		 * @param Settlement s- contain all data
		 */
		logger = Logger.getLogger("");
		if (path != null)
		{
			try {  
		        // This block configure the logger with handler and formatter  

				logger.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  
		        // the following statement is used to log any messages  
		        logger.info(s.getName()+" Number of sick: "+s.getsick_people().size()+" Number of dead: "+s.getdead()+"\n");
		        s.set_num_of_dead_percent();

		    }catch (SecurityException e) {  
		        e.printStackTrace();  
		    }
		}
		 
	}
	public static void loadFileFunc() 
	{
		/**
		 * load new file
		 * @return file
		 */
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return ;
        path=fd.getFile();
        originator.setState(path);
        memento = originator.createMemento(); 
        caretaker.addMemento(memento);
        index++;
        try {
			fh = new FileHandler(path);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
}


