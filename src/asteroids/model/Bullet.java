package asteroids.model;

import java.util.Set;

import asteroids.model.Ship;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

//Name: Dominik Claerman
//Course: Objectgericht Programmeren (Informatica 1e Bachelor)
//Git: https://Dominator_@bitbucket.org/Dominator_/ogp1617-astroids.git

/**
* A class for dealing with creating bullets, 
* handling x, y coordinates of the bullet,
* xVelocity, yVelocity of the bullet,
* and the radius of the bullet
* 
* Super Class is: Entity
* 
 * @invar	The position must be a valid x and y coordinate for a bullet
 * 			| isValidPosition(x, y)
 * @invar	The velocity must be a valid xVelocity and yVelocity for a bullet
 * 			| isValidVelocity(x, y)
 * @invar	The radius must be a valid radius for a bullet
 * 			| isValidRadius(radius)
* 
* @version: 1.0
* @authors Dominik Claerman
*
*/

public class Bullet extends Entity {
	  /**
	  * Static variable max is equals to Double.MAX_VALUE
	  */
	  private static double max = Double.MAX_VALUE;	
	  /**
	   * Variable ship registering the ship that owns this bullet.
	   */
	  private Ship ship;
	  /**
	   * Variable source registering the ship that fired this bullet.
	   */
	  private Ship source;
	  /**
	   * Variable nbBounces registering the amount of times this bullet collided with a boundary.
	   */
	  private double nbBounces;
	  
	 
	/**
	 *  Initialize this new bullet with given x, y coordinates, x, y velocity and radius.
	 *  
	 * @param x
	 * 		  The given x coordinate.
	 * @param y
	 * 		  The given y coordinate.
	 * @param xVelocity
	 * 		  The given xVelocity.
	 * @param yVelocity
	 * 		  The given yVelocity.
	 * @param radius
	 * 		  The given radius.
	 * 
	 * @pre		The given x and y must be a valid position(x, y) for a bullet.
     *       	| isValidPosition(position)
     * @pre		The given xVelocity and yVelocity must be a valid velocity(xVelocity, yVelocity) for a bullet
     * 			| isValidVelocity(xVel, yVel)
     * @pre		The given radius must be a valid radius for a bullet
     * 			| isValidRadius(radius)
     *
     * @post	The position of this new bullet is equal to the given position.
     * 			| new.getPosition() == position(x, y)
     * @post	The velocity of this new bullet is equal to the given velocity.
     * 			| new.getVelocity() == velocity(xVel, yVel)
     * @post	The radius of this new bullet is equal to the given radius.
     * 			| new.getRadius == radius
	 */
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException{
		super(x, y, xVelocity, yVelocity, radius);
	}
	 
	 /**
	    * Gets the maximum Velocity a bullet can attain.
	    * 
	    * @return   returns the maximum velocity exoressed in km/s the bullet is able to attain (double)
	    * 			| result = 30000
	    */
	   static public double getMaxVelocity() {
		   return 300000;
		   
	   }
	 
	
	 
	 /**
	  * Return the ship in which the bullet is positioned.
	  * 
	  * @param bullet
	  * 	   The given bullet.
	  * 
	  * @return
	  * 
	  * @throws NullPointerException
	  * 		if bullet is equals to null.
	  * 		| if (bullet == null)
	  */
	 public Ship getBulletShip() {		 
		 return this.ship;
	 }
	 
	 /**
	  * Assign a ship to this bullet
	  * 
	  * @param ship
	  * 	   the given ship
	  * 
	  * @throws NullPointerException
	  * 		| if ship == null
	  * 
	  * @post |this.ship = ship;
	  *	       this.source = null;
	  *	       this.getEntityWorld().removeBulletFromWorld(this);
	  */
	 public void setBulletShip(Ship ship) {
		 if (ship == null) throw new NullPointerException();
		 this.ship = ship;
		 this.source = null;

		 if (this.getEntityWorld() != null) {
		 this.getEntityWorld().removeBulletFromWorld(this);
		 }
		 //else throw new IllegalArgumentException("Bullet associated with a world can't be assigned to a ship");
	 }
	 
	 /**
	  * Remove the ship assigned to this bullet
	  * 
	  * @post
	  *      | this.sip = null
	  *      
	  * @throws NullPointerException
	  * 		| if (this.ship == null)
	  * 
	  */
	 public void removeBulletShip() {
		 if (this.ship != null) {
			 this.ship = null;
		 }
		 else throw new NullPointerException("Bullet doesn't belong to a ship");
	 }
	 
	 /**
	  * Return the ship that fired the bullet.
	  * 
	  * @return result = ship
	  * 
	  */
	 public Ship getBulletSource() {
		 return this.source;
	 }	
	 
	 /**
	  * 
	  * @param ship
	  * 
	  * @throws NullPointerException
	  *         | if ship == null
	  * @post
	  * 		| this.source = ship
	  */
	 public void setBulletSource(Ship ship) {
		 if (ship == null) throw new NullPointerException();
		 if (this.ship == null) {
			 this.source = ship;
		 }
		
	 }
	 /**
	  * Returns the amount of time this bullet has collided with the boundaries
	  * @return 
	  * 		| result = this.nbBounces
	  */
	 public double getNbBouces() {
		 return this.nbBounces;
	 }
	 /**
	  * Increases the amount of times this bullet has collided with the boundaries by 1
	  *  @post
	  *  		| this.nbBounces += 1
	  */
	 public void increaseNbBounces() {
		 this.nbBounces += 1;
	 }
	 
	 @SuppressWarnings("unchecked")
	 @Override
	 /**
	  * Checks whether this bullet with any other entities in the given world.
	  * 
	  * @param world
	  *    		| the world used to check for overlaps
	  *    
	  * @see implementation
	  */
	 public boolean isOverlappingInWorld(World world) {
		     if (world == null) throw new NullPointerException();
			 for (Entity entity : ((Set<Entity>) world.getEntities())) {
				 if (entity instanceof Ship && (this.getBulletSource() == (Ship) entity)) {
					 break;
				 }
				 if ((this.Overlap(entity) && !this.equals(entity) && (entity instanceof Ship && !(this.getBulletSource() == (Ship) entity) && !(this.getBulletShip() == (Ship) entity))) || (this.Overlap(entity) && !this.equals(entity) && entity instanceof Bullet )) {
					 return true;
				 }
			 }
		 return false;
	 }
}
