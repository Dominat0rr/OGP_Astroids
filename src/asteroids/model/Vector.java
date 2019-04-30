package asteroids.model;
/**
 * This class represents a Vector containing an x and y coordinate.
 *
 * @value
 *  		This is a helper class
 * 
 */

public class Vector {
	/**
	* variable that represents the x-component of the vector
    */
    private double x;
    /**
	* variable that represents the y-component of the vector
    */
    private double y;
	
	/**
     * Creates a new Position with both coordinates set to zero.
     */
	public Vector() {
	    this(0,0);
	}
	
	 /**
     * Creates a new Vector with the specified coordinates.
     *
     * @param x
     *            the X coordinate
     * @param y
     *            the Y coordinate
     */
	public Vector(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	

	/**
     * Sets the X coordinate.
     *
     * @param x
     *            the X coordinate
     */
     public void setX(double x) {
    	 this.x = x;
     }
     /**
      * Sets the Y coordinate.
      *
      * @param y
      *            the Y coordinate
      */
     public void setY(double y) {
    	 this.y = y;
     }
     /**
      * Sets the X and Y coordinate.
      *
      * @param y
      *            the Y coordinate
      * @param x
      *            the X coordinate
      */
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	 /**
     * Gets the X coordinate.
     *
     * @return the X coordinate
     */
	public double getX() {
		return this.x;
	}
	  /**
     * Gets the Y coordinate.
     *
     * @return the Y coordinate
     */
	public double getY() {
		return this.y;
	}
	  /**
     * Gets the X and Y coordinate.
     *
     * @return a two-dimensional array conatining the two coordinates of the vector.
     */
	public double[] getXY() {
		double[] coords = new double[2];
		coords[0] = this.getX();
		coords[1] = this.getY();
		return coords;
		
	}
	
	}