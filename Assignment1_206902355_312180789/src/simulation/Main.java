package simulation;
import java.io.IOException;

import IO.SimulationFile;
import country.Map;

public class Main {

	public static void main(String[] args) { 

		SimulationFile.writeTofile();
		try 
		{
			Map a=new Map(SimulationFile.readFromfile());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
