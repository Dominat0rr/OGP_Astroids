package asteroids.model;

//Name: Dominik Claerman
//Course: Objectgericht Programmeren (Informatica 1e Bachelor)
//Git: https://Dominator_@bitbucket.org/Dominator_/ogp1617-astroids.git

/**
* A class for dealing with creating asteroids, 
* handling x, y coordinates of the asteroid,
* xVelocity, yVelocity of the asteroid,
* and the radius of the asteroid
* 
* Super class is: MinorPlanet
* 
* @invar	The position must be a valid x and y coordinate for an asteroid
* 			| isValidPosition(x, y)
* @invar	The velocity must be a valid xVelocity and yVelocity for an asteroid
* 			| isValidVelocity(x, y)
* @invar	The radius must be a valid radius for an asteroid
* 			| isValidRadius(radius)
* 
* @version: 1.0
* @authors Dominik Claerman
*
*/

public class Asteroid extends MinorPlanet {
	/**
	 *  Initialize this new asteroid with given x, y coordinates, x, y velocity and radius.
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
	 * @pre		The given x and y must be a valid position(x, y) for an asteroid.
     *       	| isValidPosition(position)
     * @pre		The given xVelocity and yVelocity must be a valid velocity(xVelocity, yVelocity) for an asteroid
     * 			| isValidVelocity(xVel, yVel)
     * @pre		The given radius must be a valid radius for an asteroid
     * 			| isValidRadius(radius)
     *
     * @post	The position of this new asteroid is equal to the given position.
     * 			| new.getPosition() == position(x, y)
     * @post	The velocity of this new asteroid is equal to the given velocity.
     * 			| new.getVelocity() == velocity(xVel, yVel)
     * @post	The radius of this new asteroid is equal to the given radius.
     * 			| new.getRadius == radius
	 */
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}
	
	
}
