package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class Planetoid extends MinorPlanet{
	
	private double distanceTraveled;
	
	private final double initialRadius;

	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.setDistanceTraveled(totalTraveledDistance);
		this.initialRadius = radius;
	}
	
	 /**
	    * @return
	    *  		returns the density of this ship
	    *  		|result =  this.mass/(4/3*(Math.PI*this.radius*this.radius*this.radius))
	    * 
	 */
	 @Basic
	 public double getDensity() {
		 //TODO adjust formula
		 return 0.917*Math.pow(10, 12);   
	 }
	
	public void setDistanceTraveled(double distanceTraveled) {
		  this.distanceTraveled = distanceTraveled;
	}
	
	 /**
	  * 
	  * @return
	  */
	 public double getDistanceTraveled() {
		 return this.distanceTraveled;
	 }
	 /**
	  * 
	  * @param prevRadius
	  */
	 public void updateRadius() {
		 this.setRadius(this.getInitialRadius() - 1/10000*this.getDistanceTraveled());
		 if (this.getRadius() < 5) {
			 this.Terminate(this);
		 }
	 }
	 
	 /**
	  * 
	  * @param distance
	  */
	 public void increaseDistanceTraveled(double distance) {
		 this.distanceTraveled += distance;
	 }
	 
	 public void diminishRadius() {
		 this.radius -= this.getDistanceTraveled()*0.0001/100.0;
	 }
    /**
     * 
     * @return
     */
	public double getInitialRadius() {
		return initialRadius;
	}
	   
}
