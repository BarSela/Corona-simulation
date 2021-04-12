package simulation;
import java.io.IOException;
import java.util.Random;

import IO.SimulationFile;
import country.Map;
import population.Sick;
import virus.BritishVariant;
import virus.IVirus;

public class Main {

	public static void main(String[] args) throws Exception 
	{ 
		final int numOfSimulation=5;
		try 
		{
			/**
			 * step 1
			 */
			SimulationFile simulationFile=new SimulationFile();
			Map world=simulationFile.loadMap();
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
				}
			}
			/**
			 * step 3
			 */
			for (int i=0;i<numOfSimulation;i++)
			{
				for (int j=0;j<Map.getSize();j++)
				{	
					for (int k=0;k<world.getSettlement()[j].getPopulation();k++)
					{
						if (world.getSettlement()[j].getPeople().get(k) instanceof Sick)
							for (int t=0;t<6;t++)
							{
								boolean flag=false;
								Random rand=new Random();
								int x=rand.nextInt(world.getSettlement()[j].getPopulation()-1);
								if (!(world.getSettlement()[j].getPeople().get(x) instanceof Sick))
									flag=virus.tryToContagion(world.getSettlement()[j].getPeople().get(k), world.getSettlement()[j].getPeople().get(x));
								if (flag==true)
									{
										world.getSettlement()[j].getPeople().set(x,world.getSettlement()[j].getPeople().get(x).contagion(virus));
									}
							}
					}
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
