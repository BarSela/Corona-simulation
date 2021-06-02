/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import country.City;
import country.Kibbutz;
import country.Moshav;
import country.Settlement;
import location.Point;
import simulation.Clock;
import virus.BritishVariant;
import virus.ChineseVariant;
import virus.IVirus;
import virus.SouthAfricanVariant;
import virus.VirusManager;

public abstract class Person {
	/*
	 * Person abstract class
	 */
	public Person(Point p, Settlement s, int age) {
		/*
		 * Person constructor
		 * 
		 * @param p the point
		 * 
		 * @param s Settlement info
		 * 
		 * @param age
		 */
		this.age = age;
		this.location = p;
		if (s instanceof City)
			this.settlement = s;
		else if (s instanceof Kibbutz)
			this.settlement = s;
		else if (s instanceof Moshav)
			this.settlement = s;
	}

	public Person(Person p) {
		/**
		 * copy constructor
		 * 
		 * @param p the person to copy his details
		 */
		this.age = p.getAge();
		this.location = p.getLocation();
		this.settlement = p.getSettlement();
	}

	public Person contagion(IVirus iv) {
		/**
		 * contagion a healthy person
		 * 
		 * @param iv the virus that the man is sick
		 * @return a sick person
		 */
		Sick s;
		s = new Sick(this.getLocation(), this.getSettlement(), this.getAge(), Clock.now(), VirusManager.contagion(iv));
		return s;
	}

	public String toString() {
		/**
		 * @return the String representation of the person
		 */
		return "age: " + this.getAge() + " location:" + this.getLocation().toString() + " Settlement:"
				+ this.getSettlement().toString();
	}

	public int getAge() {
		/**
		 * @return the age of the person
		 */
		return this.age;
	}

	public Point getLocation() {
		/**
		 * @return the location of the person
		 */
		return this.location.getPoint();
	}

	public Settlement getSettlement() {
		/**
		 * @return the settlement that the person lives in
		 */
		return this.settlement;
	}

	// abstract
	public abstract Object replicate();

	public abstract boolean equals(Object o);

	public abstract double contagionProbability();

	// data members
	private int age;
	private Point location;
	private Settlement settlement;

}