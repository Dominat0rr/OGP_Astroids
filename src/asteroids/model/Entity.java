package asteroids.model;

//Name: Dominik Claerman
//Course: Objectgericht Programmeren (Informatica 1e Bachelor)
//Git: https://Dominator_@bitbucket.org/Dominator_/ogp1617-astroids.git

import java.util.Random;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.Basic;

public class Entity {
	  /**
	  * Static variable inf is equals to Double.POSITIVE_INFINITY
	  */
	  private static double inf = Double.POSITIVE_INFINITY;
	  /**
	    * Variable to store the boolean value of if a world is terminated or not.
	    */
	   private boolean isTerminated = false;
	  /**
	   * Variable registering the position (x and y coordinates) of this entity
	   */
	  private Vector position = new Vector();
	  /**
	   * Variable registering the velocity (xVelocity and yVelocity) of this entity
	   */
	  private Vector velocity = new Vector();
	  /**
	  * Variable registering the radius of this ship
	  */
	  protected double radius;
	  /**
	   * Variable registering the mass of this ship
	   */
	  protected double mass;
	  /**
	   *  Variable representing the maximal a ship can have
	   */
	  private static double maxMass =  Double.MAX_VALUE; 
	  /**
	   * Variable world (to see to which world a certain entity counts by)
	   */
	  private World world;
	  
	  /**
	   * Initialize this new entity with given x and y coordinate, xVelocity and yVelocity.
	   * @param x
	   * 		The given x coordinate.
	   * @param y
	   * 		The given y coordinate.
	   * @param xVelocity
	   * 		The given xVelocity.
	   * @param yVelocity
	   * 		The given yVelocity.
	   * 
	   * @pre	 The given x and y coordinate must be a valid position(x, y) for a entity.
	   * 		 | isValidPosition(x, y)
	   * @pre	 The given xVelocity and yVelocity must be a valid velocity(x, y) for a entity.
	   * 		 | isValidVelocity(xVelocity, yVelocity)
	   * 
	   * @post	 The position of this new entity is equal to the given position.
	   * 		 | new.getPosition() == position(x, y) 
	   * @post	 The velocity of this new entity is equal to the given velocity.
	    * 		 | new.getVelocity() == velocity(xVel, yVel) 
	   */
	  public Entity(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		setPosition(x, y);
		setVelocity(xVelocity, yVelocity);
		setRadius(radius);
	  }
	  
	  /**
	   * Terminates a given entity
	   * 
	   * @param entity
	   * 		The entity that will get terminated.
	   * 
	   * @post	if (entity instanceof Bullet)
	   * 		| entity.getEntityWorld().removeBulletFromWorld((Bullet) entity);
	   * 		else
	   * 		| entity.getEntityWorld().removeShipFromWorld((Ship) entity);
	   * @post	entity = null
	   */
	  public void Terminate(Entity entity) throws NullPointerException {
		    if (entity == null) throw new NullPointerException("entity cannot be null");
			this.isTerminated = true;
			if (entity instanceof Bullet) {
				if (entity.getEntityWorld() != null) {
			    entity.getEntityWorld().removeBulletFromWorld((Bullet) entity);
				}
			}
			if (entity instanceof Ship) {
				if (entity.getEntityWorld() != null) {
				entity.getEntityWorld().removeShipFromWorld((Ship) entity);
			    }
			}
			if (entity instanceof MinorPlanet) {
				((MinorPlanet) entity).Terminate(((MinorPlanet) entity));
			}
			entity = null;
		}
	  /**
	   * 
	   * @param velocity
	   *        velocity used to an x and y component with given a random direction
	   * @return returns a velocity with a magnitude equal to the given velocity and a random direction
	   * 		 | result = new Vector(velocity*Math.cos(newAngle), velocity*Math.sin(newAngle));
	   */
	  protected Vector createRandomXYVel(double velocity) {
		double newAngle = Math.random()*2*Math.PI;
		double newX = velocity*Math.cos(newAngle);
		double newY = velocity*Math.sin(newAngle);
		Vector newVel = new Vector(newX, newY);
		return newVel;
	}

	/**
	   * Return if this entity is terminated or not.
	   * 
	   * @return result = (isTerminated)
	   */
	  public boolean isTerminatedEntity() {
			return this.isTerminated;
	  }
	  
	  /**
	    * Gets the maximum Velocity a ship can attain.
	    * 
	    * @return   returns the maximum velocity exoressed in km/s the ship is able to attain (double)
	    * 			| result = 300000
	    */
	   public static double getMaxVelocity() {
		   return 300000;
		   
	   }
	  
	   // Position: defensively.
	  /**
	   * Set the values of the Vector Position to a given x at index 0 and a given y at index 1
	   * 
	   * @param x
	   *		The new x-coordinate of the entity (km)
	   * @param y
	   * 		The new y-coordinate of the entity (km)
	   * 
	   * @post	The given x and y must be a valid position(x, y) for a entity
	   * 		| isValidPosition(x, y)
	   * 
	   * @post	The x and y position of this entity is equals to the given x and y position.
	   * 		| new.getPosition() .getX() == x
	   * 		| new.getPosition() .getY() == y
	   * 
	   * @throws ModelException
	   *           The given values are Not a Number-values
	   *           | !isValidPosition(x, y)
	   */
	  protected void setPosition(double x,double y) throws IllegalArgumentException {
		   if (isValidPosition(x,y)) {
			   this.position.setXY(x, y);
		   }
		   else 
			   throw new IllegalArgumentException("coordinates cannot be 'Nan values'");   
	  }
	
	  /**
	   * Returns the current position of this ship 
	   * 
	   * @return  returns a vector with two elements (doubles) which point at the center of the entity, both are real numbers  
	   *  		  | result = this.position2.getXY()    
	   */  
	  @Basic
	  public Vector getPosition() {
		   return this.position;
	  }
	   
	  /**
	   * 
	   * @param	x
	   * 			The x coordinate of this ship
	   * @param 	y
	   * 			The y coordinate of this ship
	   * @return	True if x is not a Not a Number 
	   * 			or if y is not a Not a Number 
	   * 			or if x is not equals to Double.POSITIVE_INFINITY
	   * 			or if y is not equals to Double.POSITIVE_INFINITY
	   * 			else, return False
	   *  			| result = (!Double.isNaN(x) && !Double.isNaN(y) && x != inf && y != inf)
	   */ 
	  public static boolean isValidPosition(double x,double y) { 
		  return (!Double.isNaN(x) && !Double.isNaN(y) && x != inf && y != inf);
	  }
	
	  // Velocity: totally.
	  /**
	    * Set the values of the vector Velocity[] to a given xVel at index 0 and a given yVel at index 1
	    * 
	    * @param 	xVel
	    *		   	The new velocity of the entity in the horizontal direction (km/s)
	    * @param 	yVel
	    * 		   	The new velocity of the entity in the vertical direction (km/s)
	    * 
	    * @post		The given xVel and yVel must be a valid velocity
	    * 			| isValidVelocity(xVel, yVel)
	    * 
	    * @post		The xVel and yVel of this entity are equals to the given xVel and yVel.
	    * 			| new.getVelocity() .getX() = xVel
	    * 			| new.getVelocity() .getY() = yVel
	    */
	  protected void setVelocity(double xVel, double yVel) {
		   if (isValidVelocity(xVel, yVel)) {
		        this.velocity.setXY(xVel, yVel);
		   }
		   else if (this instanceof Ship) {
			   this.velocity.setXY(getMaxVelocity()*Math.cos(((Ship)this).getOrientation()),getMaxVelocity()* Math.sin(((Ship)this).getOrientation()));
		   }

	  }
	  
	  /**
	   * Return the current velocity of this entity 
	   *
	   * @return current velocity of this entity    
	   * 		| result = this.velocity2.getXY()
	   */ 
	  @Basic
	  public Vector getVelocity() {
		 return this.velocity;
	  }
	   
	  /**
	    * Returns if a given velocity is valid or not.
	    * 
	    * @param 	xVel
	    * 			The given xVel of the entity
	    * @param 	yVel
	    * 			The given yVel of the entity
	    * @return 	returns true if the given velocities are valid, returns false if they are greater then getMaxVelocity
	    * 			| result = (Math.sqrt((Math.pow(xVel, 2)+Math.pow(yVel, 2))) <= 300.000 )
	    */
	  
	  public static boolean isValidVelocity(double xVel, double yVel) {
		 return (Math.sqrt((Math.pow(xVel, 2)+Math.pow(yVel, 2))) <=  getMaxVelocity());		 
	  }
	  
	  // Radius: defensively.
	  /**
	    * Set the radius of the ship to a given value.
	    * 
	    * @param 	radius
	    *     		The radius of this ship
	    * @post 	if (isValidRadius(radius))
	    *			| new.getRadius() == radius
	    * @throws	IllegalArgumentException
	    * 			if (!isValidRadius(radius)
	    */ 
	   public void setRadius(double radius) throws IllegalArgumentException {
		    if (this.isValidRadius(radius))
		    	this.radius = radius;
		    else
		    	throw new IllegalArgumentException("Radius is not valid");
		   
		}
	  
	  /**
	   * Return the radius of this entity 
	   * 
	   * @return the radius of this entity, use either the function for a bullet or ship
	   * 		 | if (this instanceof Ship)
	   *         | result = ((Ship) this).redius        
	   *         | else
	   *         | result = ((Bullet) this).redius
	   */ 
	  @Basic
	  public double getRadius() {
		 return radius;
	  }
	  
	  /**
	    * Checks if radius is valid
	    * 
	    * @param 	radius
	    * 			The radius of this ship
	    * @return	True if the radius if greater then 10
	    *           | result = (radius > 10)
	    */  
	   public boolean isValidRadius(double radius) {
		   if (this instanceof Ship)
			   return (radius > 10);
		   else if (this instanceof Bullet)
			   return (radius > 1);
		   else if (this instanceof MinorPlanet)
			   return (radius > 5);
		   else 
			   return false;
	   }
	   
	   // Mass: totally.
	   /**
	    * Set the mass of the ship to a given value (mass)
	    * 
	    * @param 	mass 
	    *   		The mass that this ship will have
	    *   
	    * @pre		mass must be valid
		* 			| isValidMass(mass)
		* @post		The mass of this ship is equal to the given mass
		* 			| new.getMass() == mass
	    */
	   protected void setMass(double mass) {
		   if (isValidMass(mass))
			   this.mass = mass; 
	   }
	  
	   /**
	    * Return the mass of this ship 
	    * 
	    * @return	returns the mass of this ship
	    * 			this equals the mass of the ship + the mass of all its bullets
	    * 			| result = this.mass + bulletsMass
	    */
	   @Basic
	   public double getMass() {
		   if (this instanceof Ship)
			   return ((Ship) this).getMass();
		   if (this instanceof Bullet)
			   return (1.33) * Math.PI * Math.pow(this.getRadius(), 3) * (this.getDensity());
		   if (this instanceof MinorPlanet) {
			   if ((MinorPlanet)this instanceof Planetoid)
				   return ((Planetoid) this).getDensity()*Math.pow(this.getRadius(), 3)*Math.PI*(4/3);
			   if ((MinorPlanet)this instanceof Asteroid)
				   return ((Asteroid) this).getDensity() * Math.pow(this.getRadius(), 3) * Math.PI * (4/3);
		   }
		   return 0;
		   
	   }
	   
	   /**
	    * Checks if the given mass of this ship is valid
	    *  
	    * @param 	mass
	    * 			mass that will be evaluated to be valid or not
	    * @return	returns true if the mass is valid, false otherwise
	    * 			| result = mass > maxMass || mass <= 0
	    */
	   public static boolean  isValidMass(double mass) {
		   //return (mass < maxMass && mass >= 0);
		   return (mass < maxMass && mass > 0);
	   }
	   
	   // Density
	   /**
	    * Return the density of this entity
	    * 
	    * @return	returns the density of this entity
	    *  			| @see implementation
	    */
	   @Basic
	   public double getDensity() {
		   if (this instanceof Ship)
			   return 1.42 * Math.pow(10,  12);
		   if (this instanceof Bullet)
			   return 7.8 * Math.pow(10, 12);
		   if (this instanceof MinorPlanet) {
			   if ((MinorPlanet)this instanceof Planetoid)
				   return 0.917 * Math.pow(10, 12);
			   else if ((MinorPlanet)this instanceof Asteroid)
				   return 2.65 * Math.pow(10, 12);
		   } 
			   return 0;
	   }
	  
	  /**
	    * Gets the current distance between two ships
	    * 
	    * @param 	ship
	    * 			A ship
	    * 
	    * @post		Given distance must be greater or equals to zero
	    * 			| if (distance < 0)
	    * @post			And ships must overlap if the distance is smaller as zero (distance can only be negative if both ships overlap)
	    * 				| if (Overlap(ship))
	    * @return	distance between both ships
	    * 			| double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
		   			| distance = distance - this.getRadius() - ship.getRadius();
	    * @throws NullPointerException
	    * 			| if (ship == null)
	    * @throws IllegalArgumentException
	    * 			| if (distance < 0 && !Overlap(ship))
	    */
	   public double getDistanceBetween(Entity entity) throws NullPointerException, IllegalArgumentException {
		   if (entity == null)
			   throw new NullPointerException();
		   
		   double x1 = this.getPosition().getX();
		   double y1 = this.getPosition().getY();   
		   double x2 = entity.getPosition().getX();
		   double y2 = entity.getPosition().getY();
		   
		   double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
		   distance = distance - this.getRadius() - entity.getRadius();
		   
		   if (entity.equals(this))
			   return 0;
		   if (distance < 0) {
			   if (Overlap(entity))
				   return distance;
			   else
				   throw new IllegalArgumentException();
		   }
		   return distance;
	   }
	   
	  /**
	    * Checks is two given ships got an overlap with each other
	    * 
	    * @param 	entity
	    * 			An entity
	    * 
	    * @return	returns true if the entities are the same, or if the distance between them is smaller then 99% of the sum of the two entities.
	    *           | result = (deltax <= distance && deltay <= distance)
	    * @throws 	NullPointerException
	    * 			| if (entity == null)
	    */
	   public boolean Overlap(Entity entity) throws NullPointerException {
		   if (entity == null)  throw new NullPointerException();
		   double deltax, deltay, distance;
		  
		   deltax = Math.abs(this.getPosition().getX() - entity.getPosition().getX());
		   deltay = Math.abs(this.getPosition().getY() - entity.getPosition().getY());
		   distance = (this.getRadius() + entity.getRadius())*99.0/100.0;
		  
		  if (this.equals(entity)) {
			  return true;
		  }   
		  return (deltax <= distance && deltay <= distance); 
	   }   

	   /**
	    * Calculates how many seconds the two given ships will take to collide
	    * 
	    * @param 	ship
	    * 			A ship to calculate the time to collide for with this ship.
	    * 
	    * @post     when time to collision equals t and is not null, then when the two ships move with their respective velocities
	    * 			and orientations the result of the function overlap applied on these two ships will return true.
	    * 
	    * @post     the time returned is the amount in seconds until the first collision. 
	    * 			There are no other collisions in when time t until the collision is smaller then t.
	    *                 		
		* 
	    * @return	inf if both ships will never collide, return time when they will collide
	    *  
	    * @throws 	NullPointerException
	    * 			| if (ship == null)
	    */ 
	   //@SuppressWarnings("null")
	   public double getTimeToCollision(Entity entity) throws NullPointerException {
		   if (entity == null) throw new NullPointerException();

		   double deltax = (this.getPosition().getX()-entity.getPosition().getX());
		   double deltay = (this.getPosition().getY()-entity.getPosition().getY());
		   double deltaxVel = (this.getVelocity().getX()-entity.getVelocity().getX());
		   double deltayVel = (this.getVelocity().getY()-entity.getVelocity().getY());  
		   double d = (Math.pow(deltaxVel*deltax+deltayVel*deltay, 2)-(Math.pow(deltaxVel, 2)+Math.pow(deltayVel, 2))*(Math.pow(deltax, 2)+Math.pow(deltay, 2)-Math.pow(this.getRadius()+entity.getRadius(), 2)));
		   

		   if (deltaxVel*deltax+deltayVel*deltay >= 0)
			   return inf;
		   else if (d <= 0)
			   return inf;
		   else
			   return -(deltaxVel*deltax+deltayVel*deltay+Math.sqrt(d))/(Math.pow(deltaxVel,2)+Math.pow(deltayVel,2));
		   
		   
	   }
	     
	   /**
	    * Gets the given position two given ships will collide, if they will
	    * 
	    * @param 	ship
	    * 			| A ship
	    * @post		if both ships will never collision return null
	    * 			| if (this.getTimeToCollision(ship) == inf)
	    * 			| return null
	    * @post		if both ship doesn't overlap, give the position where they will collision with eachother
	    * 			| if (!Overlap(ship)) {
	    			|	position.getX() = this.getVelocity().getX() * this.getTimeToCollision(ship);
		   			|	position.getY() = this.getVelocity().getY() * this.getTimeToCollision(ship);
		   			
	    * @return	returns the position of the ship at the time of collision, if there is no collision it returns null;
	    *           | result = position
	    * @throws 	NullPointerException
	    * 			| if (ship == null)
	    */
	   public double[] getCollisionPosition(Entity entity) throws NullPointerException {
		   if (entity == null) throw new NullPointerException();
		   Vector position = new Vector();
		   
		   if (this.getTimeToCollision(entity) == inf) {
			   return null;
		   }
					
		if (!Overlap(entity)) {
		   	position.setX(this.getPosition().getX() + this.getVelocity().getX() * this.getTimeToCollision(entity));
		   	position.setY(this.getPosition().getY() + this.getVelocity().getY() * this.getTimeToCollision(entity));
		    return position.getXY();
		}
		else
			return null;
	   }
	   
	   /**
	    * Checks if this entity is within the boundaries of it's world. result is always true if the entity doesn't belong to a world
	    * 
	    * @return Return true if this entity is within the boundaries of its world. Return false otherwise
	    * 		| result = (!(this.getPosition().getX()-this.getRadius()*99/100 < 0 || this.getPosition().getX()+this.getRadius()*99/100 > this.getEntityWorld().getSize().getX() || this.getPosition().getY()-this.getRadius()*99/100 < 0 || this.getPosition().getY()+this.getRadius*99/100 > this.getEntityWorld().getSize().getY()))
	    */
	   public boolean isWithinBoundaries() {
		  if (this.getEntityWorld() != null) {
		     double radius = this.getRadius()*(99.0/100.0);
		     return (this.getPosition().getX()- radius > 0 && this.getPosition().getX() + radius < this.getEntityWorld().getSize().getX() && this.getPosition().getY()-radius > 0 && this.getPosition().getY()+radius < this.getEntityWorld().getSize().getY());
		  }
		  return true;
		  }
	   
	   /**
	    * Returns the time untill the first EntityCollision this entity will make. 
	    * if there will be no collision, the method return Double.Positive.Infinity
	    * 
	    * @see implementation
	    * 	    
	    */
	   public double getTimeToBoundaryCollision()  {
		  
		   if (this.getVelocity().getX() == 0 && this.getVelocity().getY() == 0) {
			   return inf;
		   }
		   double deltax = 0;
		   double deltay = 0;
		   
		   if (this.getVelocity().getX() > 0) {
			   deltax = (this.getEntityWorld().getSize().getX() - (this.getPosition().getX() + this.getRadius()))/this.getVelocity().getX();
		   }
		   else if (this.getVelocity().getX() < 0) {
			   deltax = (0 - (this.getPosition().getX() - this.getRadius()))/this.getVelocity().getX();
		   }
		   if (deltax <= 0)
				   deltax = inf;
		   if (this.getVelocity().getY() > 0) {
			   deltay = (this.getEntityWorld().getSize().getY() - (this.getPosition().getY() + this.getRadius()))/this.getVelocity().getY();
		   }
		   else if (this.getVelocity().getY() < 0) {
			   deltay = (0 - (this.getPosition().getY() - this.getRadius()))/this.getVelocity().getY();
		   }
		   if (deltay <= 0) 
		          deltay = inf;
		   if (deltay == inf && deltax == inf) {
			   return inf;
		   }
		   else if (deltay - deltax >= 0) {
			   return deltax;
		   }
		   else
			   return deltay;
		   
	   }
	   
	   /**
	    * Returns the position of the first boundaryCollision this entity will make. 
	    * if there will be no collision, the method return null
	    * 
	    * @see implementation
	    * 	    
	    * 
	    */
	   public double[] getBoundaryCollisionPosition() {
		   if (this.getTimeToBoundaryCollision() == inf) {
				return null;
		   }
		    Vector position = new Vector();	
		
		   	position.setX(this.getPosition().getX() + this.getVelocity().getX() * this.getTimeToBoundaryCollision());
		   	position.setY(this.getPosition().getY() + this.getVelocity().getY() * this.getTimeToBoundaryCollision());
		    return position.getXY();
	   }
		   	   
	   /**
	    * Moving the entity in a given time
	    * 
	    * @param 	dt
	    *       	the duration of the force applied to the entity to make it move
	    *     
	    * @post		dt must be valid
	    * 			| isValidDt(dt)
	    * @post		The dt of this entity is equal to the given dt
	    * 			|    this.setPosition((this.getPosition().getX() + (this.getVelocity().getX()*dt)),(this.getPosition().getY() + (this.getVelocity().getY()*dt)));    
	    * @throws IllegalArgumentException
	    *    	the parameter dt can never be less then zero.
	    *    	Given the fact that duration is always positive
	    *    	| dt >= 0
	    * @effect
	    * 		the entity moves a given time 'dt' with its current velocity towards its current orientation.
	    * 		if the velocity of the entity is zero, its position will not change
	    *       if the given duration is zero, the entities position will not change
	    */ 
	   public void move(double dt) throws IllegalArgumentException {
		   if (isValidDt(dt)) {
			   this.setPosition((this.getPosition().getX() + (this.getVelocity().getX()*dt)),(this.getPosition().getY() + (this.getVelocity().getY()*dt)));
		       if (this instanceof Ship) {
		    	   ((Ship) this).thrust(dt);
		       }
		       if (this instanceof MinorPlanet && ((MinorPlanet) this) instanceof Planetoid) {
		    	   double distance = Math.sqrt(Math.pow(this.getVelocity().getX()*dt, 2)+ Math.pow(this.getVelocity().getY()*dt, 2));
		    	   ((Planetoid)((MinorPlanet) this)).increaseDistanceTraveled(distance);
		       }
		   } 
		   else
			   throw new IllegalArgumentException("duration can not be negative");
	   }
	   
	   /**
		 * Check whether the given duration is valid.
		 * 
		 * @param  	dt
		 *         	The duration to check.
		 * @return 	True if and only if the given duration is positive or zero.
		 *       	 result = (dt >= 0)
		 */  
	   public static boolean isValidDt(double dt) {			   
		   return (dt >= 0);
	   }
	   
	   /**
	    * 
	    * @param world
	    * 		 World that this entity will get added to
	    * 
	    * @throws NullPointerException
	    * 		  | if (world == null)
	    * @post
	    * 		  | this.world = world
	    */
	   public void setEntityWorld(World world) {
		   if (world == null) throw new NullPointerException();
		   this.world = world;
	   }
	   
	   /**
		* Return the world in which this bullet is positioned.
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
		public World getEntityWorld() throws NullPointerException {
			return this.world;
		 }
		 
	   /**
		* Removes the world from this entity
		* 
		* @post
		* 	   | this.world = null
		*/
		public void removeEntityWorld() {
			 this.world = null;
		}
		 
		/** 
		 * Function two resolve a collision between this entity and a given entity
		 * 
		 * @param entity
		 * 	      entity for which the collision gets resolved
		 * 
		 * @param collisionListener
		 * 		  CollisionListener used to visualize the collision on a given position
		 * 
		 * @see implementation
		 * 
		 */
		public void resolveEntityCollsion(Entity entity, CollisionListener collisionListener) {
			if (this instanceof Ship && entity instanceof Ship) {
				 updateEntityVelocities(this, entity);
				 
			}
			else if (this instanceof Ship && entity instanceof Bullet) {
				 if (((Bullet) entity).getBulletSource() == ((Ship)this)) 	 {
					((Bullet) entity).setBulletShip((Ship) this);
					((Ship) this).addBullet((Bullet)entity);
				 }
				 else {
					  collisionListener.objectCollision(entity, this, entity.getPosition().getX(), entity.getPosition().getY());
					  entity.Terminate(entity);
					  this.Terminate(this);
			          }
			}
			 else if (this instanceof Bullet && entity instanceof Ship) {
				 if (((Bullet) this).getBulletSource() == ((Ship)entity)) 	 {
						((Bullet) this).setBulletShip((Ship) entity);
						((Ship) entity).addBullet((Bullet)this);
					 }
				 else {
					 collisionListener.objectCollision(entity, this, entity.getPosition().getX(), entity.getPosition().getY());
					 entity.Terminate(entity);
					 this.Terminate(this);
			          }
			 }
			 else if (this instanceof Bullet && entity instanceof Bullet) {
				 collisionListener.objectCollision(entity, this, entity.getPosition().getX(), entity.getPosition().getY());
				 this.Terminate(this);
				 entity.Terminate(entity);
				 
			 }
			 else if (this instanceof MinorPlanet && entity instanceof MinorPlanet) {
				 updateEntityVelocities(this, entity);
				 
			 }
			 else if (this instanceof Bullet) { 
				 collisionListener.objectCollision(entity, this, entity.getPosition().getX(), entity.getPosition().getY());
				 entity.Terminate(entity);
				 this.Terminate(this);
			 }
			 else if (entity instanceof Bullet) { 
				 collisionListener.objectCollision(entity, this, entity.getPosition().getX(), entity.getPosition().getY());
				 entity.Terminate(entity);
				 this.Terminate(this);
			 }
			 
			 else if (this instanceof Ship && entity instanceof Asteroid){
				 collisionListener.objectCollision(entity, this, this.getPosition().getX(), this.getPosition().getY());
				 this.Terminate(this);
			 }
             else if (this instanceof Asteroid && entity instanceof Ship){
            	 collisionListener.objectCollision(entity, this, entity.getPosition().getX(), entity.getPosition().getY());
            	 entity.Terminate(entity);
			 }
             else if (this instanceof Ship && entity instanceof Planetoid){
            	 Random random = new Random();;
            	 double xPos = random.nextDouble()*(this.getEntityWorld().getSize().getX() - 2*this.getRadius())+this.getRadius();
            	 double yPos = random.nextDouble()*(this.getEntityWorld().getSize().getY() - 2*this.getRadius())+this.getRadius();
				 this.setPosition(xPos, yPos);
				 if(this.isOverlappingInWorld(this.getEntityWorld())) {
					this.Terminate(this);
				 }
			 }
             else if (this instanceof Planetoid && entity instanceof Ship){
            	 Random random = new Random();;
            	 double xPos = random.nextDouble()*(entity.getEntityWorld().getSize().getX() - 2*entity.getRadius())+entity.getRadius();
            	 double yPos = random.nextDouble()*(entity.getEntityWorld().getSize().getY() - 2*entity.getRadius())+entity.getRadius();
				 entity.setPosition(xPos, yPos);
				 if(entity.isOverlappingInWorld(entity.getEntityWorld())) {
					entity.Terminate(entity);
				 }
			 }
		 }
		 
		 /**
		  * Update the velocity of this entity when colliding with a boundary
		  * 
		  * @param world
		  * 	   The world in which this entity is located
		  * @param type
		  * 	   The type of collision
		  * 
		  * @effect
		  * 	   if (type == 1 || type == 3) 
	      * 	   this.setVelocity(this.getVelocity().getX()*-1, this.getVelocity().getY())
          *        else 
          *  	   this.setVelocity(this.getVelocity().getX(), this.getVelocity().getY()*-1)
		  */
		public void resolveBoundaryCollision(World world, double type) {
			 if (this instanceof Bullet) {
				  ((Bullet) this).increaseNbBounces();
				  if (((Bullet) this).getNbBouces() > 2) {
					  this.Terminate(this);
				  }
				  }
			   
			 if (type == 1 || type == 3) {
				 this.setVelocity(this.getVelocity().getX()*-1, this.getVelocity().getY());
			 }
			
             else {
            	 this.setVelocity(this.getVelocity().getX(), this.getVelocity().getY()*-1);
			 }
             
		 }	
		/**
		 * Updates the velocity of two colliding entities
		 * 
		 * @param entity1
		 * 		  Entity which velocity gets updated based on its own velocity, mass and the velocity and mass of entity2
		 * @param entity2
		 * 		  Entity which velocity gets updated based on its own velocity, mass and the velocity and mass of entity1
		 * 
		 * @see implementation
		 */
		public static void updateEntityVelocities(Entity entity1, Entity entity2) {
			 double deltax = entity1.getPosition().getX() - entity2.getPosition().getX();
			 double deltay = entity1.getPosition().getY() - entity2.getPosition().getY();
			 double deltaxVel = entity1.getVelocity().getX() - entity2.getVelocity().getX();
			 double deltayVel = entity1.getVelocity().getY() - entity2.getVelocity().getY();
			 double distance = entity1.getRadius() + entity2.getRadius();
			 double delta = (deltax* deltaxVel) + (deltay * deltayVel);
			 double thismass = entity2.getMass();
			 double othermass = entity1.getMass();
     	     double J = (2*thismass * othermass * delta)/(distance * thismass + othermass); 
			 double Jx = (J * deltax)/distance;
			 double Jy = (J * deltay)/distance;
		 
	         double thisnewXvelocity = entity2.getVelocity().getX() + (Jx/thismass);
	         double thisnewYvelocity = entity2.getVelocity().getY() + (Jy/thismass);
	         double othernewXvelocity = entity1.getVelocity().getX() - (Jx/othermass);
	         double othernewYvelocity = entity1.getVelocity().getY() - (Jy/othermass);
      
		     entity2.setVelocity(thisnewXvelocity, thisnewYvelocity);
		     entity1.setVelocity(othernewXvelocity, othernewYvelocity);
		}
		public boolean isOverlappingInWorld(World world) {
			for (Object entity : (world.getEntities())) {
			   if (this.Overlap((Entity)entity) && !(this.equals((Entity)entity))) {
					return true;
				}
			}
				 return false;
	    }
		/**
		  * Checks whether this entity with any other entities in the given world.
		  * 
		  * @param world
		  *    		| the world used to check for overlaps
		  *    
		  * @see implementation
		  */
		public Entity getOverlappingEntity(World world) {
			for (Object entity : (world.getEntities())) {
				   if (this.Overlap((Entity)entity) && !(this.equals((Entity)entity))) {
						return (Entity) entity;
					}
				}
				return null;
		}

}
