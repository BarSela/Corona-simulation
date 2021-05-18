/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

import IO.StatisticsFile;
import location.Location;
import location.Point;
import population.Convalescent;
import population.Healthy;
import population.Person;
import population.Sick;
import population.Vaccinated;
import simulation.Clock;
import virus.BritishVariant;
import virus.ChineseVariant;
import virus.IVirus;
import virus.SouthAfricanVariant;

/*
 * Settlement class
 */

public abstract class Settlement implements Runnable {
	/**
	 * this class represent a settlement on the map
	 */

	public Settlement(String name, Location location, int population, int capacity) {
		/**
		 * consturctor
		 * @param name       the name of the settlement
		 * @param location   position and size
		 * @param population amount of peole in the settlement
		 * @param capacity of the settlment
		 */
		this.map=null;
		this.name = name;
		this.location = new Location(location.getPosition(), location.getsize());
		this.ramzorColor = RamzorColor.GREEN;
		this.healthy_people = new ArrayList<Person>(population);
		this.sick_people = new ArrayList<Person>();
		this.neighbors = new ArrayList<Settlement>();
		this.capacity = capacity;
	}
	public void setMapPointer(Map world)
	{
		/**
		 * set map pointer
		 * @param the map
		 */
		this.map=world;
	}

	public double contagiousPercent() {
		/**
		 * @return the contagious percent in the settlement
		 */
		int sick_people = this.getsick_people().size();
		int population = this.getPopulation();
		double percent = (double) sick_people / population;
		return percent;
	}

	public void setRamzorColor(RamzorColor new_color) {
		/**
		 * set the ramzor color of the settlement
		 * 
		 * @param new_color the new color of the settlement
		 */
		this.ramzorColor = new_color;
	}

	public Point randomLocation() {
		/**
		 * @return random location (point) in the boundry of the settlement
		 */
		Random rand = new Random();
		// Generate random integers in range x to 999
		int rand_x = rand.nextInt(this.getLocation().getsize().getWidth())
				+ this.getLocation().getPosition().getPoint_x();
		int rand_y = rand.nextInt(this.getLocation().getsize().getHeight())
				+ this.getLocation().getPosition().getPoint_y();
		Point p = new Point(rand_x, rand_y);
		return p;
	}

	public boolean AddPerson(Person p) {
		/**
		 * add person to the settlements
		 * 
		 * @param p the person that added
		 * @return true if the addition succeeded
		 */

		if (this.getPopulation() < this.getCapacity()) {
			if (p instanceof Healthy || p instanceof Vaccinated || p instanceof Convalescent) {
				this.healthy_people.add(p);
			} else {
				this.sick_people.add(p);
			}
			return true;
		}
		return false;

	}

	public void KillPerson(Person p) {
		/**
		 * kill person and delete him from settlement
		 * 
		 * @param p the person that killed
		 */
		this.sick_people.remove(p);
		this.dead++;

	}

	public boolean transferPerson(Person p, Settlement settl) {
		/**
		 * transfer person from settlement to another
		 * 
		 * @param p     the person that transferd
		 * @param settl the settlement the person is transferd to
		 * @return true if the transfer succeeded
		 */
		double p1 = this.getRamzorColor().getP();
		double p2 = settl.getRamzorColor().getP();

		if (transferPropabillity(p1, p2)) {
			if (settl.AddPerson(p)) {
				if (p instanceof Sick) {
					this.sick_people.remove(p);
				} else {
					this.healthy_people.remove(p);
				}
				return true;
			}
		}
		return false;

	}

	@Override
	public String toString() {
		/**
		 * @return string representation
		 */
		return this.getName() + " " + this.getLocation().toString() + " population- " + this.getPopulation()
				+ " Max capacity " + this.getCapacity() + " ramzor color- " + this.getRamzorColor() + " Neighbor- "
				+ this.toString_neighbors() + " Vaccine doses- " + this.getVaccine_doses();
	}

	public RamzorColor getRamzorColor() {
		/**
		 * @return the ramzor color of the settlement
		 */
		return this.ramzorColor;
	}

	public String getName() {
		/**
		 * @return the name of the settlement
		 */
		return this.name;
	}

	public Location getLocation() {
		/**
		 * @return the location of the settlement
		 */
		return (Location) location.replicate();
	}

	public int getPopulation() {
		/**
		 * @return amount of people in the settlemnet
		 */
		int population = this.healthy_people.size() + this.sick_people.size();
		return population;
	}

	public int getdead() {
		/**
		 * @return amount of dead people in the settlemnet
		 */
		return this.dead;
	}

	public void addDead() {
		/**
		 * add one dead
		 */
		this.dead++;
	}

	public List<Person> gethealthy_people() {
		/**
		 * @return the list of the healthy people in the settlement
		 */
		return this.healthy_people;
	}

	public List<Person> getsick_people() {
		/**
		 * @return the list of the sick people in the settlement
		 */
		return this.sick_people;
	}

	public List<Settlement> getneighbors() {
		/**
		 * @return the list neighbor settlements
		 */
		return this.neighbors;
	}

	public int getCapacity() {
		/**
		 * @return the max capacity of the settlement
		 */
		return capacity;
	}

	public int getVaccine_doses() {
		/**
		 * @return the number of vaccine_doses
		 */
		return vaccine_doses;
	}

	public synchronized void add_vaccine_doses(int douses) {
		/**
		 * add vaccine doses to the settlement
		 * 
		 * @param douses number of douses to add
		 */
		this.vaccine_doses += douses;
	}

	public void reduce_vaccine_doses(int douses) {
		/**
		 * reduce vaccine doses to the settlement
		 * 
		 * @param douses number of douses to reduce
		 */
		this.vaccine_doses -= douses;
	}

	private boolean transferPropabillity(double p1, double p2) {
		/**
		 * @param p1 propabillity of the origin settlement ramzorcolor
		 * @param p2 propabillity of the target settlement ramzorcolor
		 * @return true if the transfer successed else false
		 */

		double chance = p1 * p2;
		return chance >= Math.random();

	}

	private List<String> toString_neighbors() {
		/**
		 * @return list that countains the names of settlement neighbors
		 */
		List<String> settlement = new ArrayList<String>();
		if (this.getneighbors().size() != 0) {
			for (int i = 0; i < this.getneighbors().size(); i++) {
				settlement.add(this.getneighbors().get(i).getName());
			}
			return settlement;
		}
		settlement.add("NO neigbors");
		return settlement;
	}

	// abstract
	public abstract RamzorColor calculateramzorgrade();

	public abstract boolean equals(Object o);

	public void InitialSimulation() throws Exception {
		/**
		 * initial simulation contage 0.01 drom population
		 */
		Random rand = new Random();
		IVirus virus = null;
		int x1 = rand.nextInt();
		if (x1 % 3 == 0)
			virus = new BritishVariant();
		else if (x1 % 3 == 1)
			virus = new ChineseVariant();
		else
			virus = new SouthAfricanVariant();
		double numContagion = 0;// array for number of people that contagion in step 2
		numContagion = this.getPopulation() * initialcontagion;
		for (int j = 0; j < numContagion; j++) {
			if (this.gethealthy_people().size() != 0)
				if (this.gethealthy_people().get(0).contagion(virus) instanceof Sick) {
					this.getsick_people().add(this.gethealthy_people().get(0).contagion(virus));
					this.gethealthy_people().remove(0);
				}
		}
	}
	
	@Override
	public void run() {
		/**
		 * run the simmulation for one settlment
		 */
		try {
			this.InitialSimulation();
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
		while (!map.isStop()) {
			synchronized (map) {
				while (map.isPause()) {
					try {
						map.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
			try {
				this.Simulation(map);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				map.cycle.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void tryTotransfer() 
	{
		/**
		 * try to transfer person to another settlment
		 */
		Random rand = new Random();
		Person p=null;
		Object o1,o2; 
		int hash1=System.identityHashCode(this);
		
		for (int transfer = 0; transfer < this.getPopulation() * 0.03; transfer++) {
			p=this.getPerson();
			if(p!=null)
			{
				int settl = rand.nextInt(this.getneighbors().size());
				int hash2=System.identityHashCode(this.getneighbors().get(settl));
				if(Math.max(hash1,hash2)==hash1)
				{
					o1=this;
					o2=this.getneighbors().get(settl);
				}
				else
				{
					o2=this;
					o1=this.getneighbors().get(settl);
				}
				synchronized(o1) {
					synchronized(o2) {
						this.transferPerson(p, this.getneighbors().get(settl));
					}
				}
			}
		}
		
	}
	public synchronized void tryToRecover()
	{
		/**
		 * try to recover
		 */
		for (int k = 0; k < this.getsick_people().size(); k++) {
			Sick s = (Sick) this.getsick_people().get(k);
			if (s!=null)
			{
				if (Clock.CalcDays(s.getContagiousTime()) > 25) {
					this.gethealthy_people().add(s.recover());
					this.getsick_people().remove(k);
				}
			}
		}
	}
	public synchronized void tryTokill()
	{
		/**
		 * try to kill
		 */
		if( this.getsick_people().size() != 0)
		{
			for (int k = 0; k < this.getsick_people().size(); k++) {
				Sick s = (Sick) this.getsick_people().get(k);
				if(s != null)
				{
					if (s.tryTODie()) {
						this.getsick_people().remove(s);
						this.addDead();
					}
				}
				
			}
		}

		if (this.getdead() >= this.getPopulation() * 0.01 && StatisticsFile.path != null) {
			StatisticsFile.writeLog(this, StatisticsFile.path);
		}
	}
	public synchronized void tryToVacinate()
	{
		/**
		 * try to vaccinated
		 */
		int count_doses = 0;
		for (int vaccine_doses = 0; vaccine_doses < this.getVaccine_doses(); vaccine_doses++) {
			for (int healthy = 0; healthy < this.gethealthy_people().size(); healthy++) {
				if (this.gethealthy_people().get(healthy) instanceof Healthy) {
					Healthy h = (Healthy) this.gethealthy_people().get(healthy);
					this.gethealthy_people().set(healthy, h.vaccinate());
					count_doses++;
					break;
				}
			}
		}
		// update number of doses in the settlement
		this.reduce_vaccine_doses(count_doses);
	}
	public synchronized void simulationContagion() throws Exception
	{
		/**
		 * simulation contage population
		 */
		double numSick = this.getsick_people().size() * sample_sickPeople;
		Random rand = new Random();
		for (int i = 0; i < numSick; i++) {
			boolean flag = false;
			if (this.getsick_people().size() > 1) {
				int x = rand.nextInt(this.getsick_people().size() - 1);
				Sick s = (Sick) this.getsick_people().get(x);
				if(s!=null)
				{
					IVirus virus = s.getVirus();
					for (int j = 0; j < num_of_trys_to_contagion; j++) {
						if (this.gethealthy_people().size() != 0) {
							int y = 0;
							if (this.gethealthy_people().size() > 0) {
								y = rand.nextInt(this.gethealthy_people().size());
								flag = virus.tryToContagion(s, this.gethealthy_people().get(y));
							}
							if (flag) {
								if (this.gethealthy_people().get(y).contagion(virus) instanceof Sick) {
									this.getsick_people().add(this.gethealthy_people().get(y).contagion(virus));
									this.gethealthy_people().remove(y);
								}
							}
						}
					}
				}

			}
		}
	}
	public void Simulation(Map world) throws Exception {
		/**
		 * simulation steps for one settlement
		 */
		this.simulationContagion();
		// try to kill
		this.tryTokill();
		// try to recover
		this.tryToRecover();
		// try to transfer
		this.tryTotransfer();
		// try to vaccine
		this.tryToVacinate();
		this.setRamzorColor(this.calculateramzorgrade());

	}
	public synchronized void addSick(IVirus virus,int x)
	{
		/**
		 * @param virus- the virus that countaige the 0.01 
		 * @param x a random person 
		 */
		this.getsick_people().add(this.gethealthy_people().get(x).contagion(virus));
		this.gethealthy_people().remove(x);
	}
	private synchronized Person getPerson() {
		/**
		 * @return a random person from the settlment 
		 */
		Random rand = new Random();
		if(this.getneighbors().size()==0)
			return null;
		List<Person> population = new ArrayList<Person>(this.getPopulation());
		population.addAll(this.gethealthy_people());
		population.addAll(this.getsick_people());
		int people = rand.nextInt(population.size()-1);
		return population.get(people); 
	}
	// data members
	private Map map;
	private String name;
	private Location location;
	private List<Person> healthy_people;
	private List<Person> sick_people;
	private RamzorColor ramzorColor;
	private int capacity;
	private int vaccine_doses = 0;
	private int dead = 0;
	private List<Settlement> neighbors;
	private static final double initialcontagion = 0.01;
	private static final double sample_sickPeople = 0.2;
	private static final int num_of_trys_to_contagion = 3; 

}
