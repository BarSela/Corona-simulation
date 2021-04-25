/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;

import java.io.*;

public class StatisticsFile 
{
	public static void writeCsv()
	{
		try  
		{
			PrintWriter writer = new PrintWriter(new File("test.csv"));
			StringBuilder sb = new StringBuilder();
		    sb.append("id,");
		    sb.append(',');
		    sb.append("Name");
		    sb.append('\n');
		    sb.append("1");
		    sb.append(',');
		    sb.append("Prashant Ghimire");
		    sb.append('\n');
		    writer.write(sb.toString());
		    writer.close();
		} 
		catch (FileNotFoundException e) 
		{
		      System.out.println(e.getMessage());
		}
	}
	
}


