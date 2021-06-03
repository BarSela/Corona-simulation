/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package IO;

/**
 * Originator class
 * the class responsible to conect bteewn the memento and caretaker classes.
 */
public class Originator 
{
	private String state; 
	public void setState(String state)
	{
		/**
		 * set the new state
		 * @param state is the path of the file.
		 */
		this.state = state; 
	} 
	public String getState() 
	{ 
		/**
		 * get the current state
		 * @param state is the path of the file.
		 */
		return state;
	} 
	public Memento createMemento() 
	{
		/**
		 * constructor for a memento
		 * @param state is the path of the file.
		 */
		return new Memento(state); 
	} 
	public void setMemento(Memento memento) 
	{
		/**
		 * set a memento
		 * @param state is the path of the file.
		 */
		state = memento.getState(); 
	} 

}
