package simulation;
import java.io.IOException;
import java.util.*;

import IO.SimulationFile;
import country.Map;
import population.Healthy;
import population.Person;
import virus.BritishVariant;
import virus.IVirus;

public class Main {

	public static void main(String[] args) throws Exception { 

		SimulationFile.writeTofile();
		try 
		{
			/**
			 * step 1 load map
			 */
			Map world=new Map(SimulationFile.parse());
			for(int i=0;i<Map.getSize();i++)
			{
				int people=world.getSettlement()[i].getPopulation();
				for(int j=0;j<people;j++) 
				{
					Healthy new_person=new Healthy(world.getSettlement()[i].randomLocation(),world.getSettlement()[i]);
					world.getSettlement()[i].AddPerson(new_person);
				}
			}
			/**
			 * step 2
			 */
			IVirus virus=new BritishVariant();
			for(int i=0;i<Map.getSize();i++)
			{
				double numContagion=world.getSettlement()[i].getPopulation()*0.01;
				for (int j=0;j<numContagion;j++)
				{
					world.getSettlement()[i].getPeople().set(j,world.getSettlement()[i].getPeople().get(j).contagion(virus));
					System.out.println(world.getSettlement()[i].getPeople().get(j).toString());
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
