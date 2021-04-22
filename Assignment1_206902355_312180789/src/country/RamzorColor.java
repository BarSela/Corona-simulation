/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package country;
import java.awt.Color;
public enum RamzorColor {
	/**
	 * the RamzorColor represent the Morbidity level at the settlement
	 */
	GREEN (0.4, Color.green,1.0),
	YELLOW(0.6, Color.yellow,0.8),
	ORANGE(0.8, Color.orange,0.6),
	RED(1.0, Color.red,0.4);
	
    private final double val; 
    private final Color color;  
    private final double p; 
    RamzorColor(double val, Color color,double p) {
    	/**
    	 * constractor
    	 * @param val the value of sickness
    	 * @param color the color to paint the square for the map
    	 * @param Probability of crossing a locality
    	 */
        this.val = val;
        this.color = color;
        this.p =p;
    }
	public double getVal() {
		/**
		 * @return the value of sickness
		 */
		return val;
	}
	public Color getColor() {
		/**
		 * @return the color to paint the square for the map
		 */
		return color;
	}
	public double getP() {
		/**
		 * @return the Probability of crossing a locality
		 */
		return p;
	}
}