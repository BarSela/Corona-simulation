/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import location.Location;
import location.Point;
import population.Convalescent;
import population.Healthy;
import population.Person;
import population.Sick;
import population.Vaccinated;
import simulation.Clock;
import virus.BritishVariant;
import virus.IVirus;

/*
 * Settlement class
 */

public abstract class Settlement {
	/**
	 * this class represent a settlement on the map
	 */
	
	public Settlement(String name, Location location,int population,int capacity){
		/**
		 * consturctor
		 * @param name          the name of the settlement
		 * @param location      position and size
		 * @param population    amount of peole in the settlement
		 */
		this.name=name;
		this.location=new Location(location.getPosition(),location.getsize());
		this.ramzorColor=RamzorColor.GREEN;
		this.healthy_people=new ArrayList<Person>(population);
		this.sick_people=new ArrayList<Person>();
		this.neighbors=new ArrayList<Settlement>();
		this.capacity=capacity;
	}

	public double contagiousPercent()
	{
		/**
		 * @return the contagious percent in the settlement
		 */
		int sick_people=this.getsick_people().size();
		int population=this.getPopulation();
		double percent=(double)sick_people/population;
		return percent;
	}
	public void setRamzorColor(RamzorColor new_color)
	{
		/**
		 * set the ramzor color of the settlement
		 * @param new_color the new color of the settlement
		 */
		this.ramzorColor=new_color;
	}
	public Point randomLocation()
	{
		/**
		 * @return random location (point) in the boundry of the settlement
		 */
		Random rand = new Random();
		// Generate random integers in range x to 999
        int rand_x = rand.nextInt(this.getLocation().getsize().getWidth())+this.getLocation().getPosition().getPoint_x();
        int rand_y = rand.nextInt(this.getLocation().getsize().getHeight())+this.getLocation().getPosition().getPoint_y();
        Point p=new Point(rand_x,rand_y);
        return p;
	}
	public boolean AddPerson(Person p)
	{
		/**
		 * add person to the settlements
		 * @param p the person that added
		 * @return true if the addition succeeded
		 */
		
		if(this.getPopulation() < this.getCapacity())
		{
			if(p instanceof Healthy || p instanceof Vaccinated || p instanceof Convalescent)
			{
				this.healthy_people.add(p);
			}
			else
			{
				this.sick_people.add(p);
			}
			return true;
		}
		return false;

		
		
	}
	public void KillPerson(Person p)
	{
		/**
		 * kill person and delete him from settlement
		 * @param p the person that killed
		 */
		this.sick_people.remove(p);
		this.dead++;
		
		
	}
	public boolean transferPerson(Person p, Settlement settl)
	{
		/**
		 * transfer person from settlement to another
		 * @param p the person that transferd
		 * @param settl the settlement the person is transferd to
		 * @return true if the transfer succeeded
		 */
		double p1=this.getRamzorColor().getP();
		double p2=settl.getRamzorColor().getP();
		
		if(transferPropabillity(p1,p2))
		{
			if(settl.AddPerson(p))
			{
				if(p instanceof Sick)
				{
					this.sick_people.remove(p);
				}
				else
				{
					this.healthy_people.remove(p);
				}
				return true;
			}
		}
		return false;

		
	}
	@Override
	public String toString()
	{
		/**
		 * @return string representation
		 */
		return this.getName()+" "+this.getLocation().toString()+" population- "+this.getPopulation()+" Max capacity "+this.getCapacity()+" ramzor color- "+this.getRamzorColor()+" Neighbor- "+this.toString_neighbors()+" Vaccine doses- "+this.getVaccine_doses();
	}

	public RamzorColor getRamzorColor()
	{
		/**
		 * @return the ramzor color of the settlement
		 */
		return this.ramzorColor;
	}
	public String getName()
	{
		/**
		 * @return the name of the settlement
		 */
		return this.name;
	}
	public Location getLocation()
	{
		/**
		 * @return the location of the settlement
		 */
		return (Location)location.replicate();
	}
	public int getPopulation() {
		/**
		 * @return amount of people in the settlemnet
		 */
		int population =this.healthy_people.size()+this.sick_people.size();
		return population;
		} 
	public int getdead() {
		/**
		 * @return amount of dead people in the settlemnet
		 */
		return this.dead;
		} 
	public List<Person> gethealthy_people()
	{
		/**
		 * @return the list of the healthy people in the settlement
		 */
		return this.healthy_people;
	}
	public List<Person> getsick_people()
	{
		/**
		 * @return the list of the sick people in the settlement
		 */
		return this.sick_people;
	}
	public List<Settlement> getneighbors()
	{
		/**
		 * @return the list neighbor settlements
		 */
		return this.neighbors;
	}
	public int getCapacity()
	{
		/**
		 * @return the max capacity of the settlement
		 */
		return capacity;
	}
	public int getVaccine_doses()
	{
		/**
		 * @return the number of vaccine_doses
		 */
		return vaccine_doses;
	}
	public void add_vaccine_doses(int douses)
	{
		/**
		 * add vaccine doses to the settlement
		 * @param douses number of douses to add
		 */
		this.vaccine_doses +=douses;
	}
	private boolean transferPropabillity(double p1, double p2)
	{
		/**
		 * @param p1 propabillity of the origin settlement ramzorcolor
		 * @param p2 propabillity of the target settlement ramzorcolor
		 * @return true if the transfer successed else false
		 */
		
		 double chance =p1*p2;
		 return chance >=Math.random();
		 
	}
	private List<String> toString_neighbors()
	{
		/**
		 * @return list that countains the names of settlement neighbors
		 */
		List<String> settlement= new ArrayList<String>();
		if(this.getneighbors().size()!=0)
			{
				for (int i=0;i<this.getneighbors().size();i++)
					{
						settlement.add(this.getneighbors().get(i).getName());
					}
				return settlement;
			}
		settlement.add("NO neigbors");
		return  settlement;
	}
	// abstract 
	public abstract RamzorColor calculateramzorgrade();
	public abstract boolean equals(Object o);
	
	public void InitialSimulation() throws Exception 
	{
		IVirus virus=new BritishVariant();
		double numContagion=0;//array for number of people that contagion in step 2
		numContagion=this.getPopulation()*initialcontagion;
		for (int j=0;j<numContagion;j++)
		{
			this.getsick_people().add(this.gethealthy_people().get(0).contagion(virus));
			this.gethealthy_people().remove(0);
		}
		/**
		 * step 3: Simulationstage: Crossing all settlements,
		 *selection of each Sick persons in the settlement, for which one pick a random selection of six people over The same settelment and try to ifnect them.
		 *in total perform such a simulation of everything 5 times.
		 */
		for (int i=0;i<numOfSimulation;i++)//five simulation
		{	
			for (int k=0;k<numContagion;k++)
			{
				if (this.getsick_people().get(k) instanceof Sick)
					for (int t=0;t<num_of_trys_to_initial_contagion;t++)
					{
						boolean flag=false;
						Random rand=new Random();
						int x=rand.nextInt(this.gethealthy_people().size()-1);
						flag=virus.tryToContagion(this.getsick_people().get(k), this.gethealthy_people().get(x));
						if (flag==true)
						{
							this.getsick_people().add(x,this.gethealthy_people().get(x).contagion(virus));;
							this.gethealthy_people().remove(x);
						}
					}
				}
			this.setRamzorColor(this.calculateramzorgrade());
		}
		Clock.nextTick();
	}
	public void Simulation(Map world,int sleep_time) throws Exception
	{
		double numSick=this.getsick_people().size()*sample_sickPeople;
		Random rand = new Random();
		for (int i=0;i<numSick;i++)
		{
			boolean flag=false;
			int x=rand.nextInt(this.getsick_people().size()-1);
			Sick s=(Sick)this.getsick_people().get(x);
			IVirus virus=s.getVirus();
			for (int j=0;j<num_of_trys_to_contagion;j++)
			{
				int y=rand.nextInt(this.gethealthy_people().size()-1);
				flag=virus.tryToContagion(s,this.gethealthy_people().get(y));
				if (flag)
				{
					this.getsick_people().add(this.gethealthy_people().get(y).contagion(virus));
					this.gethealthy_people().remove(y);
				}
			}
		}
		for(int k=0;k<this.getsick_people().size();k++)
		{
			Sick s=(Sick)this.getsick_people().get(k);
			if(Clock.CalcDays(s.getContagiousTime())>25)
			{
				this.gethealthy_people().add(s.recover());
				this.getsick_people().remove(k);
			}
		}
		for (int transfer=0;transfer<this.getPopulation()*0.03;transfer++)
		{
			List<Person> population=new ArrayList<Person>(this.getPopulation());
			population.addAll(this.gethealthy_people());
			population.addAll(this.getsick_people());
			int people=rand.nextInt(population.size());
			int settl=rand.nextInt(world.getSettlement().length);
			while(world.getSettlement()[settl].getName().equals(this.getName()))
					settl=rand.nextInt(world.getSettlement().length);
			this.transferPerson(population.get(people), world.getSettlement()[settl]);
		}
		int count_doses=0;
		for (int vaccine_doses=0;vaccine_doses<this.getVaccine_doses();vaccine_doses++)
		{
			for(int healthy=0;healthy<this.gethealthy_people().size();healthy++)
			{
				if (this.gethealthy_people().get(healthy) instanceof Healthy)
				{
					Healthy h=(Healthy)this.gethealthy_people().get(healthy);
					this.gethealthy_people().set(healthy, h.vaccinate());
					count_doses++;
					break;
				}
			}
		}
		//update number of doses in the settlement
		this.add_vaccine_doses(this.getVaccine_doses()-count_doses);
		Clock.nextTick();
		Thread.sleep(sleep_time);

	}
	
	//data members
	private String name;
	private Location location;
	private List<Person> healthy_people;
	private List<Person> sick_people;
	private RamzorColor ramzorColor;
	private int capacity;
	private int vaccine_doses=0;
	private int dead=0;
	private List<Settlement> neighbors;
	private static final int numOfSimulation = 5;
	private static final double initialcontagion = 0.01;
	private static final double sample_sickPeople = 0.2;
	private static final int num_of_trys_to_initial_contagion = 6;
	private static final int num_of_trys_to_contagion = 3;
	
	
}
