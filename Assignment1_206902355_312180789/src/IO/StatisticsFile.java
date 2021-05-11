/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.*;
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
		    for (int i=0;i<world.getSettlement().length;i++)
			{
		    	sb.append(world.getSettlement()[i].getName());
		    	sb.append(',');
	    		if(world.getSettlement()[i] instanceof City)
	    			sb.append("City");
	    		else if(world.getSettlement()[i] instanceof Kibbutz)
	    			sb.append("Kibbutz");
	    		else
	    			sb.append("Moshav");
	    		sb.append(',');
	    		sb.append(world.getSettlement()[i].getPopulation());
	    		sb.append(',');
	    		sb.append(world.getSettlement()[i].getRamzorColor());
	    		sb.append(',');
	    		sb.append(((double)world.getSettlement()[i].getsick_people().size()/world.getSettlement()[i].getPopulation())*100+"%");
	    		sb.append(',');
	    		sb.append(world.getSettlement()[i].getVaccine_doses());
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
	public static void writeLog(Settlement s,String file)
	{
		Logger logger = Logger.getLogger(file);  
	    FileHandler fh;  

	    try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler(file); 
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        // the following statement is used to log any messages  
	        logger.info(s.getName()+" Population: "+s.getPopulation()+" Number of dead: "+s.getdead()+"\n");

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
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
	}
}


