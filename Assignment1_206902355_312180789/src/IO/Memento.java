/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;
/**
 * Memento class
 * the class responsible to create the memento
 */
public class Memento {
	private String state; 
	public Memento(String state){
		/**
		 * constructor for a memento
		 * @param state is the path of the file.
		 */
		this.state = state; 
	} 
	public String getState() 
	{ 
		/**
		 * get the actual state
		 */
		return state;
	} 

}
