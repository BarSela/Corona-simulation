package simulation;
import java.io.File;
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
		File file= new File("data.txt");
		try 
		{
			/**
			 * step 1
			 */
			SimulationFile simulationFile=new SimulationFile();
			Map world=simulationFile.loadMap(file);
			/**
			 * step 2
			 */
			IVirus virus=new BritishVariant();
			double[] numContagion=new double[Map.getSize()];//array for number of people that contagion in step 2
			for(int i=0;i<Map.getSize();i++)
			{
				numContagion[i]=world.getSettlement()[i].getPopulation()*0.01;
				for (int j=0;j<numContagion[i];j++)
				{
					world.getSettlement()[i].getPeople().set(j,world.getSettlement()[i].getPeople().get(j).contagion(virus));
				}
			}
			/**
			 * step 3
			 */
			for (int i=0;i<numOfSimulation;i++)//five simulation
			{
				for (int j=0;j<Map.getSize();j++)//run over all settlement
				{	
					for (int k=0;k<numContagion[j];k++)//
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
			for (int i=0;i<Map.getSize();i++)
				for(int j=0;j<world.getSettlement()[i].getPopulation();j++)
					if (world.getSettlement()[i].getPeople().get(j) instanceof Sick)
						System.out.println(world.getSettlement()[i].getPeople().get(j).toString());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
