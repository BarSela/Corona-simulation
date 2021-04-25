/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package simulation;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import IO.SimulationFile;
import IO.StatisticsFile;
import country.Map;
import country.Settlement;
import population.Healthy;
import population.Person;
import population.Sick;
import virus.BritishVariant;
import virus.IVirus;

/**
 * Main class
 */
public class Main {

	private static final int numOfSimulation = 5;
	public static void main(String[] args) throws Exception 
	{ 
		File file=loadFileFunc();
		try 
		{
			/**
			 * step 1: Upload Step: Get the location of the upload file and load the entire map.
			 */
			SimulationFile simulationFile=new SimulationFile();
			Map world=simulationFile.loadMap(file);
			/**
			 * step 2: Initialization stage: Definition of 1% of all persons in settlement as Sick persons in one of the variants.
			 */
			IVirus virus=new BritishVariant();
			double[] numContagion=new double[world.getSettlement().length];//array for number of people that contagion in step 2
			for(int i=0;i<world.getSettlement().length;i++)
			{
				numContagion[i]=world.getSettlement()[i].getPopulation()*0.01;
				for (int j=0;j<numContagion[i];j++)
				{
					world.getSettlement()[i].getsick_people().add(world.getSettlement()[i].gethealthy_people().get(0).contagion(virus));
					world.getSettlement()[i].gethealthy_people().remove(0);
				}
			}
			/**
			 * step 3: Simulationstage: Crossing all settlements,
			 *selection of each Sick persons in the settlement, for which one pick a random selection of six people over The same settelment and try to ifnect them.
			 *in total perform such a simulation of everything 5 times.
			 */
			Main.firstSimulation(world,virus,numContagion);
			Main.secoundSimulation(world);
			//just for check...
			for (int i=0;i<world.getSettlement().length;i++)
				for(int j=0;j<world.getSettlement()[i].getsick_people().size();j++)
					if (world.getSettlement()[i].getsick_people().get(j) instanceof Sick)
						System.out.println(world.getSettlement()[i].getsick_people().get(j).toString());
			StatisticsFile.writeCsv();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	private static File loadFileFunc() 
	{
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        System.out.println(f.getPath());
        return f;
	}
	private static void firstSimulation(Map world, IVirus virus,double[] numContagion) throws Exception
	{
		/**
		 * step 3: Simulationstage: Crossing all settlements,
		 *selection of each Sick persons in the settlement, for which one pick a random selection of six people over The same settelment and try to ifnect them.
		 *in total perform such a simulation of everything 5 times.
		 */
		for (int i=0;i<numOfSimulation;i++)//five simulation
		{
			for (int j=0;j<world.getSettlement().length;j++)//run over all settlement
			{	
				for (int k=0;k<numContagion[j];k++)
				{
					if (world.getSettlement()[j].getsick_people().get(k) instanceof Sick)
						for (int t=0;t<6;t++)
						{
							boolean flag=false;
							Random rand=new Random();
							int x=rand.nextInt(world.getSettlement()[j].gethealthy_people().size()-1);
							flag=virus.tryToContagion(world.getSettlement()[j].getsick_people().get(k), world.getSettlement()[j].gethealthy_people().get(x));
							if (flag==true)
								{
									world.getSettlement()[j].getsick_people().add(x,world.getSettlement()[j].gethealthy_people().get(x).contagion(virus));;
									world.getSettlement()[j].gethealthy_people().remove(x);
								}
						}
				}
				world.getSettlement()[j].setRamzorColor(world.getSettlement()[j].calculateramzorgrade());
			}
			
			Clock.nextTick();
		}
	}
	private static void secoundSimulation(Map world) throws Exception
	{
		double numSick=0;
		Random rand = new Random();
		for (int i=0;i<world.getSettlement().length;i++)
			{
				numSick= world.getSettlement()[i].getsick_people().size()*0.2;
				for (int t=0;t<numSick;t++)
				{
					boolean flag=false;
					int x=rand.nextInt(world.getSettlement()[i].getsick_people().size()-1);
					Sick s=(Sick)world.getSettlement()[i].getsick_people().get(x);
					IVirus virus=s.getVirus();
					for (int j=0;j<3;j++)
					{
						int y=rand.nextInt(world.getSettlement()[i].gethealthy_people().size()-1);
						flag=virus.tryToContagion(s,world.getSettlement()[i].gethealthy_people().get(y));
						if (flag==true)
							{
								world.getSettlement()[i].getsick_people().add(world.getSettlement()[i].gethealthy_people().get(y).contagion(virus));
								world.getSettlement()[i].gethealthy_people().remove(y);
							}
					}
				}
				for(int k=0;k<world.getSettlement()[i].getsick_people().size();k++)
				{
					Sick s=(Sick)world.getSettlement()[i].getsick_people().get(k);
					if(Clock.CalcDays(s.getContagiousTime())>25)
					{
						world.getSettlement()[i].gethealthy_people().add(s.recover());
						world.getSettlement()[i].getsick_people().remove(k);
					}
				}
				for (int transfer=0;transfer<world.getSettlement()[i].getPopulation()*0.03;transfer++)
				{
					List<Person> population=new ArrayList<Person>(world.getSettlement()[i].getPopulation());
					population.addAll(world.getSettlement()[i].gethealthy_people());
					population.addAll(world.getSettlement()[i].getsick_people());
					int people=rand.nextInt(population.size());
					int settl=rand.nextInt(world.getSettlement().length);
					while(settl == i)
					{
						settl=rand.nextInt(world.getSettlement().length);
					}
					world.getSettlement()[i].transferPerson(population.get(people), world.getSettlement()[settl]);
				}
				int count_doses=0;
				for (int vaccine_doses=0;vaccine_doses<world.getSettlement()[i].getVaccine_doses();vaccine_doses++)
				{
					for(int healthy=0;healthy<world.getSettlement()[i].gethealthy_people().size();healthy++)
					{
						if (world.getSettlement()[i].gethealthy_people().get(healthy) instanceof Healthy)
						{
							Healthy h=(Healthy)world.getSettlement()[i].gethealthy_people().get(healthy);
							world.getSettlement()[i].gethealthy_people().set(healthy, h.vaccinate());
							count_doses++;
							break;
						}
					}
				}
				world.getSettlement()[i].setVaccine_doses(world.getSettlement()[i].getVaccine_doses()-count_doses);
			}
		
	}
}
