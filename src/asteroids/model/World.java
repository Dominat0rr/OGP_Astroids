package asteroids.model;

import asteroids.model.Ship;
import asteroids.model.Vector;
import asteroids.part2.CollisionListener;

import java.util.Set;
import java.util.HashSet;
import be.kuleuven.cs.som.annotate.*;

//Name: Dominik Claerman
//Course: Objectgericht Programmeren (Informatica 1e Bachelor)
//No code Repository

/**
 * A class for dealing with creating worlds, 
 * handling width,height of the world,
 * adding ships and bullets to the world,
 * removing ships and bullets to the world,
 * returning an Entity at a given location,
 * returning all entities in a set.
 * 
 * @invar	The size must be a valid width and height for a world
 * 			| isValidSize(width, height)
 * 
 * @version: 1.0
 * @authors Dominik Claerman
 *
 */

public class World {
	   /**
	   * Static variable max is equals to Double.MAX_VALUE.
	   */
	   private static double max = Double.MAX_VALUE;
	   /**
	   * Variable registering the size (x(0, width) and y(0, height)) of this ship.
	   */
	   private final Vector size = new Vector();
	   /**
	    * Variable to store the boolean value of if a world is terminated or not.
	    */
	   private boolean isTerminated = false;
	   /**
	    * Variable set to keep all the ships of this world.
	    */
	   private Set<Ship> ships = new HashSet<Ship>();
	   /**
	    * Variable set to keep all the bullets of this world.
	    */
	   private Set<Bullet> bullets = new HashSet<Bullet>();
	   /**
	    * Variable set to keep all the asteroids of this world.
	    */
	   private Set<Asteroid> asteroids = new HashSet<Asteroid>();
	   /**
	    * Variable set to keep all the planetoids of this world.
	    */
	   private Set<Planetoid> planetoids = new HashSet<Planetoid>();
	   
	   
	   /**
	    * Initialize this new world with given width and height.
	    * 
	    * @param width
	    * 		 The width of this new world.
	    * @param height
	    * 		 The height of this new world.
	    * 
	    * @pre	 The given width and height must be a valid size(width, height) for a world.
	    * 		 | isValidSize(width, height)
	    * 
	    * @post	 The size of this new world is equal to the given size.
	    * 		 | new.getSize() == size(width, height) 
	    */
	   public World(double width, double height) {
		   if (isValidSize(width, height))
			   this.setSize(width,height);
	   }
	   
	   /**
		* Check whether this World is terminated.
		* 
		* @return | result = this.isTerminated
		*/
		@Basic @Raw
		public boolean isTerminated() {
			return this.isTerminated;
		}
		/**
		 * Terminates a world. this is the same as removing every entity this world contains, but not destroying the entities themselves.
		 * 
		 * @param world
		 * 		the world that will get terminated
		 * @throws NullPointerException
		 *      this method throws a NullPointerException if the given world is null
		 *      | if (world == null) 
		 * @throws IllegalArgumentException
		 * 		this method throws an IllegalArgumentException is the world is already terminated
		 * 		| if (world.isTerminated())
		 * 
		 */
		public void terminateWorld(World world) throws NullPointerException, IllegalArgumentException {
			if (world == null) throw new NullPointerException("World cannot be null");
			if (!world.isTerminated()) {
        		for (Ship ship : this.ships) {	
                   this.removeShipFromWorld(ship);
	            }
	            for (Bullet bullet : this.bullets) {	
	            	this.removeBulletFromWorld(bullet);
	            }
	            for (Asteroid asteroid : this.asteroids) {	
	            	this.removeAsteroidFromWorld(asteroid);
	            }
	            for (Planetoid planetoid : this.planetoids) {	
	            	this.removePlanetoidFromWorld(planetoid);
	            }
	            this.isTerminated = true;
			}
			else {
				throw new IllegalArgumentException("This world is already terminated");
			}
		}
	   
	   /**
	    * Set the value of the Vector Size to a given x and y coordinate
	    * 
	    * @param width
	    * 		 The new width of the ship (km)
	    * @param height
	    * 		 The new height of the ship (km)
	    * 
	    * @pre 	 The given width and height must be a valid size(width, height) for a world.
	    * 		 | isValidSize(width, height)
	    * @post	 The width and height of this world is equals to the given width and height.
	    * 		 | new.size.getXY() == width, height
	    */
	   private void setSize(double width,double height) {
		   if (isValidSize(width, height)) {
			   this.size.setXY(width, height);
		   }
	   }
	   
	   /**
	    * Returns the current width and height of this world
	    * 
	    * @return	returns a vector with two elements (doubles), both are real numbers
	    * 			| result = this.size
	    */
	   @Basic
	   public Vector getSize() {
		   return this.size;
	   }
	   
	   /**
	    * Checks if the given width and height are valid.
	    * 
	    * @param width
	    * 		 The width of this world.
	    * @param height
	    * 		 The height of this world.
	    * 
	    * @return 	True if width is smaller or equals to max and width greater or equals to zero
	    * 			and height smaller or equals to max and height greater or equals to zero
	    * 			| result = (width <= max && width >= 0 && height <= max && height >= 0)  
	    */
	   public static boolean isValidSize(double width, double height) {
		   return (width <= max && width >= 0 && height <= max && height >= 0);
	   }
	   
	   /**
	    * Returns all the ships from this world
	    * 
	    * @param world
	    * 		 The given world.
	    * 
	    * @return | result = (this.ships)
	    *
	    */
	   public Set <? extends Ship> getWorldShips() {
		   return this.ships;
	   }
	   
	   /**
	    * Returns all the bullets from this world
	    * 
	    * 
	    * @return | result = (this.bullets)
	    * 
	 
	    */
	   public Set <? extends Bullet> getWorldBullets() {
		   return this.bullets;
	   }
	   
	   /**
	    * Returns all the asteroids from this world
	    * 
	    * @return | result = (this.asteroids)
	    * 		
	    * 
	 
	    */
	   public Set <? extends Asteroid> getWorldAsteroids() {
		   return this.asteroids;
	   }
	   
	   /**
	    * Returns all the planetoids from this world
	    *
	    * @return | result = (this.planetoids)
	    * 		
	    */
	   public Set <? extends Planetoid> getWorldPlanetoids() {
		   return this.planetoids;
	   }
	   
	   /**
	    * Adds a ship to this world.
	    * 
	    * @param ship
	    * 		 The given ship.
	    * @post	 if (ship.isOverlappingInWorld(this))
	    * 		 | ship.Terminate(ship);
	    * @post	 if (!containShip(ship) && ship.getEntityWorld() == null)
	    * 		 | this.ships.add(ship);
		         | ship.setEntityWorld(this);
	    * @throws NullPointerException
	    * 		 if (ship == null)
	    * @throws IllegalArgumentException 
	    * 		 if (!Ship.isWithinBoundaries)
	    * @throws IllegalArgumentException
	    * 		 if (containShip(ship) || !ship.getEntityWorld() == null)
	    */
	   public void addShipToWorld(Ship ship) throws NullPointerException, IllegalArgumentException {
		   if (ship.equals(null)) throw new NullPointerException();	   
		   if (!ship.isWithinBoundaries()) throw new IllegalArgumentException("ship is not within boundaries");
		   if (ship.isOverlappingInWorld(this)) {
			   ship.Terminate(ship);
			   return;
		   }
		   if (!this.containShip(ship) && ship.getEntityWorld() == null)
		   {
			   this.ships.add(ship);
		       ship.setEntityWorld(this);
		   }
		   else {
			   throw new IllegalArgumentException("World already contains this ship or ship is already located in another world");
		   }
	   }
	   
	   /**
	    * Removes a ship from a given world.
	    * 
	    * @param world
	    * 		 The given world.
	    * @param ship
	    * 		 The given ship.
	    * @post	 if (world.containShip(ship))
	    * 		 | this.ships.remove(ship)
	    *        | ship.removeEntityWorld()
	    * @throws NullPointerException
	    * 		 if (world == null || ship == null)
	    * @throws IllegalArgumentException
	    * 		 if (!this.containShip(ship))
	    */
	   public void removeShipFromWorld(Ship ship) throws NullPointerException, IllegalArgumentException {
		   if (ship == null) throw new NullPointerException();
		   if (this.containShip(ship)) {
			   this.ships.remove(ship) ;
			   ship.removeEntityWorld();
		   }
			   
		   else throw new IllegalArgumentException("Ship doesn't exist");
	   }   
	  
	   /**
	    * Return if a given ship is contained by this world
	    * 
	    * @param ship
	    * 		 The given ship.
	    * 
	    * @return True if ships.containt(ship)
	    * 		  | result = (ships.contains(ship))
	    */
	   public boolean containShip(Ship ship) throws NullPointerException {	
		   if (ship == null) throw new NullPointerException("ship cannot be null");
		   return ships.contains(ship);
		}
	   
	   /**
	    * Adds a bullet to this world.
	    * 
	    * @param bullet
	    * 		 The given bullet.
	    * @post	 if (bullet.isOverlappingInWorld(this))
	    * 		 | bullet.Terminate(bullet);
	    * @post	 if (!containBullet(bullet) && bullet.getEntityWorld() == null)
	    * 		 | this.bullets.add(bullet);
		         | bullet.setEntityWorld(this);
	    * @throws NullPointerException
	    * 		 if (bullet == null)
	    * @throws IllegalArgumentException 
	    * 		 if (!bullet.isWithinBoundaries)
	    * @throws IllegalArgumentException
	    * 		 if (containBullet(bullet) || !bullet.getEntityWorld() == null)
	    */
	   public void addBulletToWorld(Bullet bullet) throws NullPointerException, IllegalArgumentException {
		   if (bullet == null) throw new NullPointerException();
		   if (!bullet.isWithinBoundaries()) throw new IllegalArgumentException("coordinate ligt buiten de wereld");
		   if (bullet.isOverlappingInWorld(this)) {
			   bullet.Terminate(bullet);
			   return;
		   }
		   if (!this.containBullet(bullet) && bullet.getBulletShip() == null)
		   {
			   this.bullets.add(bullet);
		       bullet.setEntityWorld(this);
		   }
		   else {
			   throw new IllegalArgumentException("World already contains this bullet or bullet is already located in another world");
		   }
	   } 
	   
	   
	   /**
	    * Removes a given bullet from this world.
	    * 
	    * @param bullet
	    * 		 The given bullet.
	    * @post	 if (world.containBullet(bullet)
	    * 		 | world.bullets.remove(bullet)
	    * @throws NullPointerException
	    * 		 if (world == null || bullet == null)
	    * @throws IllegalArgumentException
	    *  		 if (!world.containBullet(bullet)
	    */
	   public void removeBulletFromWorld(Bullet bullet) throws NullPointerException, IllegalArgumentException {
		   if (bullet == null) throw new NullPointerException();
		   if (this.containBullet(bullet)) {
			   this.bullets.remove(bullet);
			   bullet.removeEntityWorld();
		   } 
		   else throw new IllegalArgumentException("Bullet doesn't exist");
	   }   
	   
	   /**
	    * Return if a given bullet is contained by this world
	    * 
	    * @param bullet
	    * 		 The given bullet.
	    * 
	    * @return True if bullets.containt(bullet)
	    * 		  | result = (bullets.contains(bullet)) 
	    */
	   public boolean containBullet(Bullet bullet) throws NullPointerException {
		   if (bullet == null) throw new NullPointerException("bullet cannot be null");
		   return this.bullets.contains(bullet);
	   }
	   
	   /**
	    * Adds a planetoid to this world.
	    * 
	    * @param planetoid
	    * 		 The given planetoid.
	    * @post	 if (planetoid.isOverlappingInWorld(this))
	    * 		 | planetoid.Terminate(planetoid);
	    * @post	 if (!containPlanetoid(planetoid) && planetoid.getEntityWorld() == null)
	    * 		 | this.planetoids.add(planetoid);
		         | planetoid.setEntityWorld(this);
	    * @throws NullPointerException
	    * 		 if (planetoid == null)
	    * @throws IllegalArgumentException 
	    * 		 if (!planetoid.isWithinBoundaries)
	    * @throws IllegalArgumentException
	    * 		 if (containPlanetoid(planetoid) || !planetoid.getEntityWorld() == null)
	    */
	   public void addPlanetoidToWorld(Planetoid planetoid) throws NullPointerException, IllegalArgumentException {
		   if (planetoid == null) throw new NullPointerException();	   
		   if (!planetoid.isWithinBoundaries()) throw new IllegalArgumentException("planetoid is not within boundaries");
		   if (planetoid.isOverlappingInWorld(this)) {
			   planetoid.Terminate(planetoid);
			   return;
		   }
		   if (!containPlanetoid(planetoid) && planetoid.getEntityWorld() == null)
		   {
			   
			   this.planetoids.add(planetoid);
		       planetoid.setEntityWorld(this);
		   }
		   else {
			   throw new IllegalArgumentException("World already contains this planetoid or planetoid is already located in another world");
		   }
	   }
	   
	   /**
	    * Removes a given planetoid this world.
	    * 
	    * @param planetoid
	    * 		 The given planetoid.
	    * @post	 if (world.containBullet(planetoid)
	    * 		 | world.bullets.remove(planetoid)
	    * @throws NullPointerException
	    * 		 if (world == null || planetoid == null)
	    * @throws IllegalArgumentException
	    *  		 if (!world.containPlanetoid(planetoid)
	    */
	   public void removePlanetoidFromWorld(Planetoid planetoid) throws NullPointerException, IllegalArgumentException {
		   if (planetoid == null) throw new NullPointerException();
		   if (this.containPlanetoid(planetoid)) {
			   this.planetoids.remove(planetoid);
			   planetoid.removeEntityWorld();
		   } 
		   else throw new IllegalArgumentException("Planetoid doesn't exist");
	   }   
	   
	   /**
	    * Return if a given planetoid is contained by this world
	    * 
	    * @param planetoid
	    * 		 The given planetoid.
	    * 
	    * @return True if this.planetoids.contains(planetoid)
	    * 		  | result = (planetoids.contains(planetoid))
	    */
	   private boolean containPlanetoid(Planetoid planetoid) throws NullPointerException {	
		   if (planetoid == null) throw new NullPointerException("planetoid cannot be null");
		   return planetoids.contains(planetoid);
		}
	   
	   /**
	    * Adds a asteroid to this world.
	    * 
	    * @param asteroid
	    * 		 The given asteroid.
	    * @post	 if (asteroid.isOverlappingInWorld(this))
	    * 		 | asteroid.Terminate(asteroid);
	    * @post	 if (!containAsteroid(asteroid) && asteroid.getEntityWorld() == null)
	    * 		 | this.asteroids.add(asteroid);
		         | asteroid.setEntityWorld(this);
	    * @throws NullPointerException
	    * 		 if (asteroid == null)
	    * @throws IllegalArgumentException 
	    * 		 if (!asteroid.isWithinBoundaries)
	    * @throws IllegalArgumentException
	    * 		 if (containAsteroid(asteroid) || !asteroid.getEntityWorld() == null)
	    */
	   public void addAsteroidToWorld(Asteroid asteroid) throws NullPointerException, IllegalArgumentException {
		   if (asteroid == null) throw new NullPointerException();	   
		   if (!asteroid.isWithinBoundaries()) throw new IllegalArgumentException("asteroid is not within boundaries");
		   if (asteroid.isOverlappingInWorld(this)) {
			   asteroid.Terminate(asteroid);
			   return;
		   }
		   if (!containAsteroid(asteroid) && asteroid.getEntityWorld() == null)
		   {
			   this.asteroids.add(asteroid);
		       asteroid.setEntityWorld(this);
		   }
		   else {
			   throw new IllegalArgumentException("World already contains this asteroid or asteroid is already located in another world");
		   }
	   }
	   
	   /**
	    * Removes a given asteroid from this world.
	    * 
	    * @param asteroid
	    * 		 The given asteroid.
	    * @post	 if (world.containAsteroid(asteroid)
	    * 		 | world.asteroids.remove(asteroid)
	    * @throws NullPointerException
	    * 		 if (world == null || asteroid == null)
	    * @throws IllegalArgumentException
	    *  		 if (!world.containAsteroid(asteroid)
	    */
	   public void removeAsteroidFromWorld(Asteroid asteroid) throws NullPointerException, IllegalArgumentException {
		   if (asteroid == null) throw new NullPointerException();
		   if (this.containAsteroid(asteroid)) {
			   this.asteroids.remove(asteroid);
			   asteroid.removeEntityWorld();
		   } 
		   else throw new IllegalArgumentException("asteroid doesn't exist");
	   }   
	   
	   /**
	    * Return if a given asteroid is contained by this world
	    * 
	    * @param ship
	    * 		 The given asteroid.
	    * 
	    * @return True if ships.containt(ship)
	    * 		  | result = (asteroids.contains(ship))
	    */
	   private boolean containAsteroid(Asteroid asteroid) throws NullPointerException {		
		   if (asteroid == null) throw new NullPointerException("asteroid cannot be null");
		   return asteroids.contains(asteroid);
		}
	   
	   /**
	    * Return the entity at the given position in this world.
	    * 
	    * @param x
	    * 		 The given x coordinate.
	    * @param y
	    * 	 	 The given y coordinate.
	    * 
	    * @return	object
	    * 			| result = (the object located at the given position and contained by this world. If no object is located
	    *             at the given position, null is returned)
	    * 
	    */
	   public Object getEntityAt(double x, double y) {		   
		   for (Ship ship: ships) {
			   if (ship.getPosition().getX() == x && ship.getPosition().getY() == y)
				   return ship;
		   }

		   for (Bullet bullet: bullets) {
				if (bullet.getPosition().getX() == x && bullet.getPosition().getY() == y)
				   return bullet;
		   }
		   	   
	       for (Asteroid asteroid: asteroids) {
			   if (asteroid.getPosition().getX() == x && asteroid.getPosition().getY() == y)
			      return asteroid;
		   
	       }	
	       for (Planetoid planetoid: planetoids) {
			   if (planetoid.getPosition().getX() == x && planetoid.getPosition().getY() == y)
			      return planetoid;
	       }	 
	       return null;
	   }
	   
	   /**
	    * Return a set of all the entities in this world.
	    * 
	    * @return entities
	    * 		  result = (set of all ships and bullets)
	    * @throws NullPointerException
	    *  		  if (world == null)
	    */
	   public Set<? extends Object> getEntities() {		   
		   Set<Entity> entities = new HashSet<Entity>();

		   for (Ship ship: this.ships)
			   entities.add(ship);
		   
		   for (Bullet bullet: this.bullets)
			   entities.add(bullet);
		   
		   for (Asteroid asteroid: this.asteroids)
			   entities.add(asteroid);
		   
		   for (Planetoid planetoid: this.planetoids)
			   entities.add(planetoid);
		   
		   return entities;
	   }
	   
	   /**
	    * Advance world by dt seconds.
	    * 
	    * @param dt
	    * 		 The given time.
	    * @param collisionListener
	    * 		 The given collisionListener.
	    * @throws IllegalArgumentException
	    *        | used to catch exceptions thrown by methods called in the body of this method
	    */  
	   
	  public void evolve(double dt, CollisionListener collisionListener) throws IllegalStateException, IllegalArgumentException {
		   //greater then or greater then or equal?
		   while ((this.getTimeToFirstCollision() <= dt)){
			   double time = this.getTimeToFirstCollision();
			   Entity collider1 = this.getCollidersOfFirstCollision()[0];
			   Entity collider2 = this.getCollidersOfFirstCollision()[1];
			   int type = this.getTypeOfFirstCollision();
			   dt = dt - time;
			   for (Object entity : this.getEntities()) {
				    ((Entity) entity).move(time);
	    	  }
			  if (type == 0) {
				  collider1.resolveEntityCollsion(collider2, collisionListener);
			  }
			  else {
				 
				  collider1.resolveBoundaryCollision(this, type);
			  }
			  
		   }
		   for (Object entity : this.getEntities()) {
			((Entity)entity).move(dt);
			if (((Entity)entity) instanceof MinorPlanet && ((MinorPlanet)((Entity)entity) instanceof Planetoid)) {
				(((Planetoid) ((MinorPlanet)((Entity) entity)))).updateRadius();
			}
   	       }
	   }
	   /**
	    * 
	    * @return position
	    *         the position of the next collision in this world gets returned.
	    *         result can either be the position of the first collision between two entities, or the position
	    *         of the first collision between an entity and a boundary. depending on which one happens first.
	    *         If no collision will happen, the function returns null.
	    *         | result = (Entity1)entity.getCollisionPosition((Entity)entity2) or
	    *                    (Entity1)entity.getTBoundaryCollisionPosition()
	    *      
	    */
	   
	   public double[] getPositionNextCollision() {
		      double min = Double.POSITIVE_INFINITY;
		      Entity collider1 = null;
		      Entity collider2 = null;
		   
		      for (Object entity : this.getEntities()) {
		    	  for (Object entity2 : this.getEntities()) {
		    		  if (!entity.equals(entity2)) {
		    			  if (((Entity)entity).getTimeToCollision((Entity)entity2) < min) {
		    				 min = ((Entity)entity).getTimeToCollision((Entity)entity2);
		    				 collider1 = ((Entity)entity);
		    				 collider2 = ((Entity)entity2);
		    			  }
		    		  }
		    	  }
		    	  
		    	  if (((Entity)entity).getTimeToBoundaryCollision() < min) {
		    		  min = ((Entity)entity).getTimeToBoundaryCollision();
	    				 collider1 = ((Entity)entity);
	    				 collider2 = null;
		    	  }
		      }
		      if (collider2 != null) return collider1.getCollisionPosition(collider2);
		      else return collider1.getBoundaryCollisionPosition();
	   }
	   
	   /**
	    * 
	    * @return returns the time until the first collision in this world
	    *         result can either be the time till the first collision between two entities, or the time
	    *         till the first collision between an entity and a boundary. depending on which one happens first.
	    *         If no collision will happen, the function returns Double.Positive.infinity.
	    *         | result = (Entity1)entity.getTimeToCollision((Entity)entity2) or
	    *                    (Entity1)entity.getTimeToBoundaryCollision()
	    */
	   public double getTimeToFirstCollision() {
		   double timeToFirstCollision = Double.POSITIVE_INFINITY;
		   for (Object entity1 : this.getEntities()){
			   for (Object entity2 : this.getEntities()){
			   if (!entity1.equals(entity2) ) {
			   if (((Entity) entity1).getTimeToCollision((Entity) entity2) < timeToFirstCollision) {
				   timeToFirstCollision = ((Entity)entity1).getTimeToCollision((Entity)entity2);
			   }			   
			   }
		   }
			   if (((Entity)entity1).getTimeToBoundaryCollision() < timeToFirstCollision) {
				   timeToFirstCollision = ((Entity)entity1).getTimeToBoundaryCollision();
			   }
		   }
		   return timeToFirstCollision;
	   }
	   /**
	    * Returns the type of the first Collision that will happen in this world
	    *
	    * @throws IllegalStateException
	    *         if there is no collision happening in this world
	    *         
	    * @see implementation
	    */
	   public int getTypeOfFirstCollision() {
		   Entity collider1 = this.getCollidersOfFirstCollision()[0];
		   Entity collider2 = this.getCollidersOfFirstCollision()[1];
		   if (collider2 == null) {
			   if (collider1.getBoundaryCollisionPosition()[0] - collider1.getRadius()*101/100  <= 0) {
				   return 1;
			   }
			   else if (collider1.getBoundaryCollisionPosition()[1] - collider1.getRadius()*101/100  <= 0) {
				   return 2;
			   }
			   else if (collider1.getBoundaryCollisionPosition()[0] + collider1.getRadius()*101/100  >= this.getSize().getX()) {
				   return 3;
			   }
			   else if (collider1.getBoundaryCollisionPosition()[1] + collider1.getRadius()*101/100  >= this.getSize().getY()) {
				   return 4;
			   }
		   }
		   else {
			   return 0;
		   }
		   throw new IllegalStateException("Er kon geen type berekend worden voor de eerstvolgende botsing");
	   }
	   /**
	    * 
	    * @return the two entities involved in the first collision in this world
	    *         | result = the entities whos timeTillCollision are the smallest in this world and will collide with each other.
	    *           or the entity whos timeToBoundaryCollision are the smallest in this world and null as the second entity
	    *           
	    * @throws IllegalStateException
	    *         if there is no collision happening in this world
	    */
	   @SuppressWarnings("unchecked")
	public Entity[] getCollidersOfFirstCollision() {
		  Entity[] colliders = new Entity[2];
		  for (Entity collider1 : (Set<Entity>) this.getEntities()) {
			  for (Entity collider2 : (Set<Entity>) this.getEntities()) {
			  if (collider1.getTimeToCollision(collider2) == this.getTimeToFirstCollision() && collider2.getTimeToCollision(collider1) == this.getTimeToFirstCollision()) {
				  colliders[0] = collider1;
				  colliders[1] = collider2;
				  return colliders;
			  }
			  }
			  if (collider1.getTimeToBoundaryCollision() == this.getTimeToFirstCollision()) {
				  colliders[0] = collider1;
				  colliders[1] = null;
				  return colliders;
			  }
		  }
		  throw new IllegalStateException("De colliders voor de eerstvolgende botsing konden niet berekend worden");
		   
	   }

}
