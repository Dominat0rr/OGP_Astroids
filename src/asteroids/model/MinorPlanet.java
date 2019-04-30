package asteroids.model;

//Name: Dominik Claerman
//Course: Objectgericht Programmeren (Informatica 1e Bachelor)
//Git: https://Dominator_@bitbucket.org/Dominator_/ogp1617-astroids.git

import be.kuleuven.cs.som.annotate.Basic;

/**
* A class for dealing with creating MinorPlanets, 
* handling x, y coordinates of the minorplanet,
* xVelocity, yVelocity of the minorplanet,
* and the radius of the minorplanet
* 
* Super class is: Entity
* 
* @invar	The position must be a valid x and y coordinate for a minorplanet
* 			| isValidPosition(x, y)
* @invar	The velocity must be a valid xVelocity and yVelocity for a minorplanet
* 			| isValidVelocity(x, y)
* @invar	The radius must be a valid radius for a minorplanet
* 			| isValidRadius(radius)
* 
* @version: 1.0
* @authors Dominik Claerman
*
*/

public class MinorPlanet extends Entity {

	/**
	 * Variable to store the boolean value of if a world is terminated or not.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * @param x
	 * 		The given x coordinate.
	 * @param y
	 * 		The given y coordinate.
	 * @param xVelocity
	 * 		The given xVelocity.
	 * @param yVelocity
	 * 		The given yVelocity.
	 * @param radius
	 *      The given radius
	 * 
	 * @pre		The given x and y must be a valid position(x, y) for a minorplanet.
     *       	| isValidPosition(position)
     * @pre		The given xVelocity and yVelocity must be a valid velocity(xVelocity, yVelocity) for aminorplanet
     * 			| isValidVelocity(xVel, yVel)
     * @pre		The given radius must be a valid radius for a minorplanet
     * 			| isValidRadius(radius)
     *
     * @post	The position of this new minorplanet is equal to the given position.
     * 			| new.getPosition() == position(x, y)
     * @post	The velocity of this new minorplanet is equal to the given velocity.
     * 			| new.getVelocity() == velocity(xVel, yVel)
     * @post	The radius of this new minorplanet is equal to the given radius.
     * 			| new.getRadius == radius
	 */
	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}
	
	/**
	 * Terminates a MinorPlanet.
	 * 
	 * @param minorplanet
	 * 		  | The MinorPlanet that will get terminated
	 */
	public void Terminate(MinorPlanet minorplanet) throws NullPointerException {
		if (minorplanet == null) throw new NullPointerException("minorplanet cannot be null");
		if (minorplanet instanceof Planetoid) {
			if (minorplanet.getEntityWorld() != null) {
				World world = minorplanet.getEntityWorld();
				minorplanet.getEntityWorld().removePlanetoidFromWorld((Planetoid) minorplanet);
				if (minorplanet.getRadius() > 30) {
					double newRadius = (minorplanet.getRadius()/2.0);
					double xVel = minorplanet.getVelocity().getX();
					double yVel = minorplanet.getVelocity().getY();
					double oldVel = Math.sqrt(Math.pow(xVel, 2)+ Math.pow(yVel, 2));
					Vector newVel1 = minorplanet.createRandomXYVel(1.5*oldVel);
					Vector newVel2 = new Vector(newVel1.getX()*-1, newVel1.getY()*-1);
					double randomAngle = (Math.random()*2*Math.PI);
					Vector position1 = new Vector(minorplanet.getPosition().getX()+(newRadius*Math.cos(randomAngle)),minorplanet.getPosition().getY()+(newRadius*Math.sin(randomAngle)));
					Vector position2 = new Vector(minorplanet.getPosition().getX()-(newRadius*Math.cos(randomAngle)),minorplanet.getPosition().getY()-(newRadius*Math.sin(randomAngle)));
					Asteroid newAsteroid1 = new Asteroid(position1.getX(), position1.getY(), newVel1.getX(), newVel1.getY(), newRadius);						
					Asteroid newAsteroid2 = new Asteroid(position2.getX(), position2.getY(), newVel2.getX(), newVel2.getY(), newRadius);
					world.addAsteroidToWorld(newAsteroid1);
					world.addAsteroidToWorld(newAsteroid2);
				}
			
		    }
		}
		else if (minorplanet instanceof Asteroid) {
			if (minorplanet.getEntityWorld() != null) {
			minorplanet.getEntityWorld().removeAsteroidFromWorld((Asteroid) minorplanet);
		    }
		}
		minorplanet = null;
		}
	  /**
	   * returns if this MinorPlanet is terminated or not
	   * 
	   * @return | result = this.isTerminated
	   */
	  public boolean isTerminatedMinorPlanet() {
			return this.isTerminated;
	  }

}
