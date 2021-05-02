/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;

import java.io.*;

import country.City;
import country.Kibbutz;
import country.Map;
/**
 * statistic file class
 * the class responsible for saving the staistics in the file.
 */
public class StatisticsFile 
{
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
	
}


