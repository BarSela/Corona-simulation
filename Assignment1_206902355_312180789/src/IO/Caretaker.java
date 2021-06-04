/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;

import java.util.ArrayList;
import java.util.List;
/**
 * Caretaker class
 * the class responsible for saveing all path for restore.
 */
public class Caretaker {
	private List<Memento> statesList = new ArrayList<Memento>(); 
	public void addMemento(Memento m) {
		/**
		 * add a memento to the list
		 * @param m a memento.
		 */
		statesList.add(m); 
	} 
	public Memento getMemento(int index) {
		/**
		 * get a memento from the list 
		 * @param index for get the last memento.
		 * @return the curent memento
		 */
		return statesList.get(index);
	} 
}
