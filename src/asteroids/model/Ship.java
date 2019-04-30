package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import java.lang.Math;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//Name: Dominik Claerman
//Course: Objectgericht Programmeren (Informatica 1e Bachelor)
//Git (bitbucket): https://Dominator_@bitbucket.org/Dominator_/ogp1617-astroids.git

/**
 * A class for dealing with creating ships
 * with a given position, velocity, radius and orientation.
 * Ships can move, thrust, turn and fire bullets.
 * 
 * Super Class is: Entity
 * @invar	The orientation must be a valid orientation for a ship.
 * 			Orientation must always be between 0 and 2*PI (inclusive). 
 * 			| isValidOrientation(orientation)
 * @invar 	The mass must be a valid mass for a ship.
 * 			| isValidMass(getMass())
 * 
 * @version: 1.0
 * @authors Dominik Claerman
 * 
*/

public class Ship extends Entity {
	/**
	* Variable registering the orientation of this ship
	*/
	private double orientation;   
	/**
	* Variable expressing the mass of this ship (kg)
	*/
	//private double mass;   
	/**
	*  Variable representing the maximal a ship can have
	*/
	@SuppressWarnings("unused")
	private static double maxMass =  Double.MAX_VALUE;  
	/**
	* Static variable expressing the minimum density of a ship (kg/m^3)
	*/
	@SuppressWarnings("unused")
	private static double minDensity = 1.42*(10^12);
	/**
	* Boolean that tells if the ships thruster is on (true) of off (false)
	*/
	private boolean thruster = false;
	/**
	*  Hashset that will contain all the bullets that are children of this ship
	*/	  
	private Set<Bullet> bullets = new HashSet<Bullet>();
	/**
	 * Variable program registering the program that owns this ship.
	 */
	private Program program;
	   
	   
	/**
	* Initialize this new ship with given position, velocity, radius
	* and orientation
	*
	* @param  	x
	*         	The x coordinate for this new ship.
	* @param  	y
	*         	The y coordinate for this new ship.
	* @param 	xVelocity
	*         	The velocity in the horizontal direction for this new ship (km/s)
	* @param 	yVelocity
	*  	  	  	The velocity in the horizontal direction for this new ship (km/s)
	* @param 	radius
	*         	The radius in for this new ship (km)
	* @param 	orientation
	*         	The angle at which the ship is facing (radians)
	* @param  	mass
	* 			The mass of this ship expressed in (kg)
	* 
	* @pre		The given x and y must be a valid position(x, y) for a ship.
    *       	| isValidPosition(position)
    * @pre		The given radius must be a valid radius for a ship
    * 			| isValidRadius(radius)
    * @pre		The given xVelocity and yVelocity must be a valid velocity(xVelocity, yVelocity) for a ship
    * 			| isValidVelocity(xVel, yVel)
    * @pre		The given Orientation must be a valid orientation for a ship
    * 			| isValidOrientation(orientation)
    * @pre      The given Mass must be valid for a ship.
    * 			| isValidMass(mass)
    * @post		The position of this new ship is equal to the given position.
    * 			| new.getPosition() == position(x, y)
    * @post		The velocity of this new ship is equal to the given velocity.
    * 			| new.getVelocity() == velocity(xVel, yVel)
    * @post		The radius of this new ship is equal to the given radius.
    * 			| new.getRadius == radius
    * @post		The orientation of this new ship is equal to the given orientation
    * 			| new.getOrientation == orientation
    * @post 	The mass of this is equal to the given mass
    *           | new.getMass == mass
    *           
	* @throws 	IllegalArgumentException 
    */
	@Deprecated
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
			assert isValidPosition(x, y);
			assert isValidRadius(radius);
			this.setOrientation(orientation);	   
	}
	
	/**
	 * Initialize this new ship with position, velocity, radius and orientation
	 * set to default values
     *
     * @throws IllegalArgumentException 
	 *
	 * @effect This new ship is initialized with its position set at (0,0), velocity set at 0, radius set at 10
	 * and orientation at an angle of 0 radians 
	 *                 
	 *       | this(0,0, 0, 0, 10, 0)
	 */	  
	@Deprecated
	public Ship() throws IllegalArgumentException {
		this(0, 0, 0, 0, 10, 0);
  	}
	
	/**
	 * Create a new non-null ship with the given position, velocity, radius,
	 * direction and mass.
	 * 
	 * @param 	x
	 * 		 	The given x position of this new ship.
	 * @param	 y
	 * 		 	The given y position of this new ship.
	 * @param 	xVelocity
	 * 		 	The given x velocity of this new ship.
	 * @param 	yVelocity
	 * 			 The given y velocity of this new ship.
	 * @param 	radius
	 * 		 	The given radius of this new ship.
	 * @param 	orientation
	 * 			The given orientation that this new ship will face.
	 * @param 	mass
	 * 	 		The total mass of a this new ship in kg.
	 * 
	 * @effect 	This new ship is initialized as an Entity with a given x and y position, a given x and y velocity and radius
	 * 		 	| super(x, y, xVelocity, yVelocity, radius)
	 * 
	 * @post	The orientation of this new ship is equal to the given orientation
	 * 			| new.getOrientation == orientation
	 * @post 	The mass of this is equal to the given mass
	 *          | new.getMass == mass
	 * 
	 * @throws IllegalArgumentException
	 * 		 If the given mass is not valid.
	 * 		 | (! isValidMass(mass))
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass) throws IllegalArgumentException {
	    super(x,y, xVelocity, yVelocity, radius);
		this.setOrientation(orientation);
		super.setMass(mass);
	}

	// Orientation: nominally.
	/**
	 * Set the orientation of the ship to a given value (orientation)
	 * an orientation of zero equals to the ship facing directly to the right
	 * 
	 * @param 	orientation
	 * 		    The new orientation of the ship, the angle it is at, given in radians
	 * 
	 * @pre		orientation must be valid
	 * 			| isValidOrientation(orientation)
	 * @post		The orientation of this ship is equal to the given orientation
	 * 			| new.getOrientation() == orientation
	 */
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
		  
		  /*if (orientation < 2*Math.PI && orientation > (-2)*Math.PI)
				this.orientation = orientation;
			else
				this.orientation = orientation % (2*Math.PI);*/
	}
	
	/**
	 * Return the orientation of this ship 
	 *
	 * @return orientation of this ship  
	 *         | result = this.orientation   
	 */ 
	@Basic
	public double getOrientation() {
		return this.orientation;
	}
   
   /**
    * Checks if the given orientation of this ship is valid
    * 
    * @param 	orientation
    * 			The orientation of this ship
    * @return	True if orientation is greater or equals to 0
    * 			and orientation is smaller or equals than 2*PI
    *           result = (orientation >= 0 && orientation <= 2*Math.PI)
    */
   public static boolean isValidOrientation(double orientation) {   
	   return (orientation >= 0 && orientation <= 2*Math.PI);
   }
   
   // Mass: totally.  
   /**
    * Return the mass of this ship 
    * 
    * @return	returns the mass of this ship
    * 			this equals the mass of the ship + the mass of all its bullets
    * 			| result = this.mass + bulletsMass
    */
   @Basic
   @Override
   public double getMass() {
	   double bulletsMass = 0;
	   if (this.bullets != null) {
		   for (Bullet bullet : this.bullets) {
			   bulletsMass += bullet.getMass();
		   }
	   }
	   return this.mass + bulletsMass;
   }
   
   /**
    * Return the acceleration of this ship
    * 
    * @return	returns the acceleration of this ship()
    *   		| result = getThrustForce()/this.getMass()
    */
   @Basic
   public double getAcceleration() {
	   return (this.getThrustForce() / this.getMass());  
   }
     
   // Thrust(er): totally
   /**
    * Thrust this ship
    * 
    * @param 	dt
    * 			the amount off time a force should be applied to the ship to make it accelerate
    * 
    * @post		| this.setVelocity(this.getVelocity()[0]+ (dt*this.getAcceleration()*Math.cos(this.getOrientation())),this.getVelocity()[1]+ (dt*this.getAcceleration()*Math.sin(this.getOrientation())));
    */ 
   public void thrust(double dt)  {
				   this.setVelocity(this.getVelocity().getX()+ (dt*this.getAcceleration()*Math.cos(this.getOrientation())),this.getVelocity().getY()+ (dt*this.getAcceleration()*Math.sin(this.getOrientation())));
   }
   
   /**
    * Enables or disables the thruster that is attached to this ship.
    * 
    * @param 	active
    * 			Boolean that represents the state that will be given to the ships thruster.
    * 
    * @post		if the boolean is true then place the thruster state to true
    * 			| if (active)
    * 			| thruster = true
    * 			else if the boolean is false then place the thruster state to false
    * 			| if (!active)
    * 			| thruster = false 
    */
   public void setThrusterActive(boolean active) {
	   	if (active) {
	   		thruster = true;
	   	}
	   	else {
	   		thruster = false;
	   	}
   }
   
   /**
    * Returns the thruster that is currently attached to this ship.
    * 
    * @return 	returns true if the thruster is enabled and false otherwise
    * 			| result = this.thruster
    */
   public boolean getThrustState() {
	   return thruster;
   }
   
   /**
    * Returns the value this ship has to move by.
    * 
    * @return 	returns the speed a ship moves by each second pass
    * 			if the thruster is enabled.
    * 			| if (getThrustState())
    * 			| result = 1.1 * (10^21)
    * 			else if thruster is disabled
    * 			| if (!getThrustState())
    * 			| result = 0
    */
   public double getThrustForce() {
	   if (this.getThrustState()) {
		   return (1.1 * Math.pow(10,18));
	   }
	   else {
		   return 0;
	   }
   }
   
   // Move: nominally.
   /**
    * Turn this ship
    * 
    * @param 	angle
    * 			The angle in radians in which the ship is going to turn.
    * 
    * @pre		The given angle should be bigger or equal to zero and smaller or equal to 2 times Pi
    * 			| isValidAngle(angle)
    * @effect	The given angle + the old orientation forms the new orientation of this ship
    * 			| setOrientation(this.getOrientation() + angle);	
    */   
   public void turn(double angle)  {
	   assert isValidAngle(angle);
	   setOrientation(this.getOrientation() + angle);	
   }
   
   /**
    * Check whether the given angle is valid
    *  
    * @param  	angle
    * 		   	The angle to check.
    * @return 	True if and only if the given angle is bigger or equal to zero and smaller or equal to two times Pi.
    *           | result = (angle >= 0 && angle <= 2 * Math.PI)
    */   
   public static boolean isValidAngle(double angle) {	   
	   return (angle >= 0 && angle <= 2 * Math.PI);
   }
   

   
   //BULLET: defensively (firebullet must be totally).
   /**
    * Contains all bullets that are currently contained by this ship.
    * 
    * @return	returns all the bullets that are currently contained by this ship.
    * 			| result = bullets
    */
   public Set <Bullet> getBullets() {
	   return this.bullets;
   }
   
   /**
    * Returns the amount of bullets this ship owns
    * 
    * @return
    * 		  | result = this.bullets.size
    */
   public int getNbBullets() {
	   // TODO write specification.
	   return this.bullets.size();
   }
   
   /**
    * Checks if a bullet is containted by this ship.
    * 
    * @param 	bullet
    * 			a bullet that gets checked to see if it is contained by this ships collection of bullets
    * 
    * @return	returns true if the bullet is part of this ships
    */
   public boolean containsBullet(Bullet bullet) {
	   return this.bullets.contains(bullet);
   }
   
   /**
    * Adds a bullet into the set of this ship.
    * 
    * @param 	bullet
    * 			a bullet that get added to the ships collection of bullets
    * 
    * @post		if this ship does not contain this bullet already then add the bullet into the set of bullets
    * 			and set the ship as holder of the bullet in bullet class
    * 			if (!containsBullet(bullet))
    * 			| this.bullets.add(bullet)
    * 			| bullet.setBulletShip(this)
    * 
    * @throws	NullPointerException
    * 			if bullet == null
    * 			IllegalArgumentException
    * 			if (containsBullet(bullet)
    */
   public void addBullet(Bullet bullet) throws NullPointerException, IllegalArgumentException {
	   if (bullet == null) throw new NullPointerException();
	   if (!containsBullet(bullet)) {
		   this.bullets.add(bullet);
		   bullet.setBulletShip(this);
	   }
	   else throw new IllegalArgumentException("Bullet is already holded by this ship");
   }
   
   /**
    *  Adds a collection of bullets into the set of this ship.
    *  
    * @param 	bullets
    * 			A collection of bullets that gets added to this ships collection of bullets
    * 
	* post		loop through the collection of given bullets, and check if each bullet is not contained by this ship
	* 			if this ship does not contain this bullet already then add the bullet into the set of bullets
    * 			and set the ship as holder of the bullet in bullet class
    * 			if (!containsBullet(bullet))
    * 			| this.bullets.add(bullet)
    * 			| bullet.setBulletShip(this)
    * 
    * @throws	NullPointerException
    * 			if bullet == null
    * 			IllegalArgumentException
    * 			if (containsBullet(bullet)
    */
   public void addBullets(Collection<Bullet> bullets) throws NullPointerException, IllegalArgumentException {
	   if (bullets == null) throw new NullPointerException();
	   for (Bullet bullet : bullets) {
		   if (!containsBullet(bullet)) {
			   	this.bullets.add(bullet);
		   		bullet.setBulletShip(this);
		   }
		   else throw new IllegalArgumentException("Bullet is already holded by this ship");
	   }
	   	   
	   /*this.bullets.addAll(bullets);
	   for (Bullet bullet : this.bullets) {
		   bullet.setBulletShip(this);
	   }*/
   }
   
   /**
    * Removes a bullet into the set of this ship.
    * 
    * @param 	bullet
    *     		The bullet that will get removed from this ships collection of bullets
    *  
    * @post		if (this.bullets.contains(bullet))
    * 			| this.bullets.remove(bullet)
    * 
    * @throws	NullPointerException
    * 			if (bullet == null)
    * @throws	IllegalArgumentException
    * 			if bullet doesn't excist in this set of bullets
    */
   public void removeBullet(Bullet bullet)  throws NullPointerException, IllegalArgumentException {
	   if (bullet == null) throw new NullPointerException();
	   if (this.bullets.contains(bullet)) this.bullets.remove(bullet);
	   else throw new IllegalArgumentException("bullet doesn't excist");
   }
   
   // Firebullet: totally.
   /**
    * Fires a bullet
    * 
    * @post
    * 		A bullet from this ships collection of bullets is given a velocity of X and is fired in an orientation that is 
    *       equal to its ships orientation. This only happens if this contains a bullet, the ship firing is located in a world and the bullets new position
    *       is within the boundaries of its world
    *       
    * @effect
    * 		the fired bullet is removed from this ships collection
    * 		|this.removeBullet(bullet)
    *       the source of this bullet is set to its original ship
    *       |bullet.setBulletSource(this);
    *       the world of the bullet is set to the world of the ship firing it and the bullet gets added to that world
    *       |bullet.setEntityWorld(this.getEntityWorld());
    *       |this.getEntityWorld().addBulletToWorld(bullet);
    *       the bullets velocity and position is updated
    *       |bullet.setPosition(xPos, yPos);
    *       |bullet.setVelocity(xVel, yVel);
    *       
    */
   public void fireBullet() {
	   if (this.getNbBullets() > 0 && this.getEntityWorld() != null) {
		   Bullet bullet = this.bullets.iterator().next();
		   double xPos = this.getPosition().getX() + Math.cos(this.getOrientation())*(this.getRadius()*1.4+ bullet.getRadius());
		   double yPos = this.getPosition().getY() + Math.sin(this.getOrientation())*(this.getRadius()*1.4+ bullet.getRadius());
		   bullet.setPosition(xPos, yPos);
		   Entity overlapper = bullet.getOverlappingEntity(this.getEntityWorld());
		   this.removeBullet(bullet);
		   if (!bullet.isWithinBoundaries()) {
			   bullet.Terminate(bullet);
		   }
		   else if (overlapper != null) {
			   bullet.Terminate(bullet);
			   System.out.println("jejije");
		   }   
		   else {
		   double xVel = 250*Math.cos(this.getOrientation());
		   double yVel = 250*Math.sin(this.getOrientation());
		   bullet.setVelocity(xVel, yVel);
		   bullet.removeBulletShip();
		   bullet.setBulletSource(this);
		   bullet.setEntityWorld(this.getEntityWorld());
	       this.getEntityWorld().addBulletToWorld(bullet);
		   }
		   
	   }
   }
	 
   /**
    * Check is this ship overlaps with any other object in a given world
    * 
    * @param	world
    * 			The given world in which u have to check for overlapping with another entity.
    * 
    * @return	for (Entity entity : ((Set<Entity>) world.getEntities())) {
	*		 	if (this.Overlap(entity) && !(((Entity)this).equals(entity)))
	*			| result = true
	*			else
	*			| result = false
    */
	@SuppressWarnings("unchecked")
	public boolean isOverlappingInWorld(World world) {
			 for (Entity entity : ((Set<Entity>) world.getEntities())) {
				 if (this.Overlap(entity) && !(((Entity)this).equals(entity)))
					 return true;
			 }
			 return false;
    }

	 
	 
	 // Program
   	 // TODO 
	 public Program getShipProgram() {
		 return this.program;
	 }
	 
	 public void loadProgramOnShip(Program program) {
		 this.program = program;
		 //program.attachShip(this);
	 }
}
