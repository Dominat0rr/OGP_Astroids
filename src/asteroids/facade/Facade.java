package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.*;
import asteroids.part2.CollisionListener;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;


public class Facade implements IFacade {
	
	/*************************
	 * SHIP: Basic methods *
	 ************************/
	
	/**
	 * This method is deprecated; you should not implement nor use it.
	 */
	@Override
	@Deprecated
	public Ship createShip() throws ModelException {
		Ship ship = new Ship();
		return ship;
	}

	/**
	 * This method is deprecated; you should not implement nor use it.
	 */
	@Override
	@Deprecated
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws ModelException {
		
        Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, orientation);
	    return ship;
		
		
	}
	
	/**
	 * Create a new non-null ship with the given position, velocity, radius,
	 * direction and mass.
	 * 
	 * The thruster of the new ship is initially inactive. The ship is not
	 * located in a world.
	 */
	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass) throws ModelException {
		try {
			Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
			return ship;
		}
		
		catch (IllegalArgumentException Exception) {
			throw new ModelException (Exception);
		}
	}
	
	/**
     * Return the position of ship as an array of length 2, with the
     * x-coordinate at index 0 and the y-coordinate at index 1.
     */
	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPosition().getXY();
	}
	
	/**
     * Return the velocity of ship as an array of length 2, with the velocity
     * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
     */
	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocity().getXY();
	}
	
	/**
     * Return the radius of ship.
     */
	@Override
	public double getShipRadius(Ship ship) throws ModelException {	
		return ship.getRadius();
	}
	
	/**
     * Return the orientation of ship (in radians).
     */
	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}
	
	/**
     * This method is deprecated; you should not implement nor use it.
     *
     *
     * @deprecated Since part 2 of the project. This behavior is now triggered
     *             through the {@link #evolve(World, double, CollisionListener)}
     *             method.
     */
	@Override
	@Deprecated
	public void move(Ship ship, double dt) throws ModelException {
		ship.move(dt);
	}
	
	/**
     * Update the direction of ship by adding angle
     * (in radians) to its current direction. angle may be
     * negative.
     */
	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
		
	}
	
	/**
     * Return the distance between >ship1 and ship2.
     *
     * The absolute value of the result of this method is the minimum distance
     * either ship should move such that both ships are adjacent. Note that the
     * result must be negative if the ships overlap. The distance between a ship
     * and itself is 0.
     */
	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		}
		catch (IllegalArgumentException |NullPointerException Exception){
			throw new ModelException (Exception);}
	}
	
	/**
     * Check whether ship1 and ship2 overlap. A ship
     * always overlaps with itself.
     */
	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.Overlap(ship2);
		}
		catch (NullPointerException Exception){
			throw new ModelException (Exception);}
	}
	
	/**
     * Return the number of seconds until the first collision between
     * ship1 and ship2, or Double.POSITIVE_INFINITY if
     * they never collide. A ship never collides with itself.
     */
	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException, NullPointerException {
		try {
			return ship1.getTimeToCollision(ship2);
		}
		catch (NullPointerException Exception){
			throw new ModelException (Exception);}
	}
	
	/**
     * Return the first position where ship1 and ship2
     * collide, or null if they never collide. A ship never
     * collides with itself.
     *
     * The result of this method is either null or an array of length 2, where
     * the element at index 0 represents the x-coordinate and the element at
     * index 1 represents the y-coordinate.
     */
	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		}
		catch (NullPointerException Exception){
			throw new ModelException (Exception);}
	}
	
	/**
     * Terminate <code>ship</code>.
     */
	@Override
	public void terminateShip(Ship ship) throws ModelException {
		try {
			ship.Terminate(ship);
		}
		catch (NullPointerException Exception){
			throw new ModelException (Exception);}
	
	}
	
	/**
     * Check whether <code>ship</code> is terminated.
     */
	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		//return ship.isTerminatedEntity(ship);
		return ship.isTerminatedEntity();
	}
	
	/**
     * Return the total mass of <code>ship</code> (i.e., including bullets
     * loaded onto the ship).
     */
	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}
	
	/**
     * Return the world of <code>ship</code>.
     */
	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getEntityWorld();
	}
	
	/**
     * Return whether <code>ship</code>'s thruster is active.
     */
	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.getThrustState();
	}
	
	/**
     * Enables or disables <code>ship</code>'s thruster depending on the value
     * of the parameter <code>active</code>.
     */
	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		ship.setThrusterActive(active);
	}
	
	/**
     * Return the acceleration of <code>ship</code>.
     */
	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getAcceleration();
	}
	
	@Override
	@Deprecated
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
	}
	
	
	/*************************
	 * BULLET: Basic methods *
	 ************************/
	
	/**
     * Create a new non-null bullet with the given position, velocity and
     * radius,
     *
     * The bullet is not located in a world nor loaded on a ship.
     */
	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		try {
			Bullet bullet = new Bullet(x, y, xVelocity, yVelocity, radius);
			return bullet;
		}
		catch (IllegalArgumentException Exception) {
			throw new ModelException (Exception);
		}
		
	}
	
	/**
     * Terminate <code>bullet</code>.
     */
	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		try {
			bullet.Terminate(bullet);
		}
		catch (NullPointerException Exception){
			throw new ModelException (Exception);}
	
	
	}
	
	/**
     * Check whether <code>bullet</code> is terminated.
     */
	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminatedEntity();
	}
	
	/**
     * Return the position of <code>ship</code> as an array containing the
     * x-coordinate, followed by the y-coordinate.
     */
	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition().getXY();
	}
	
	/**
     * Return the velocity of <code>ship</code> as an array containing the
     * velocity along the X-axis, followed by the velocity along the Y-axis.
     */
	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getVelocity().getXY();
	}
	
	/**
     * Return the radius of <code>bullet</code>.
     */
	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}
	
	/**
     * Return the mass of <code>bullet</code>.
     */
	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}
	
	/**
     * Return the world in which <code>bullet</code> is positioned.
     */
	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getEntityWorld();
	}
	
	/**
     * Return the ship in which <code>bullet</code> is positioned.
     */
	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
	    return bullet.getBulletShip();	
	}
	
	/**
     * Return the ship that fired <code>bullet</code>.
     */
	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getBulletSource();
	}
	
	
	/************************
	 * WORLD: Basic methods *
	 ************************/
	
	/**
     * Create a new world with the given <code>width</code> and
     * <code>height</code>.
     */
	@Override
	public World createWorld(double width, double height) throws ModelException {
		World world = new World(width, height);
		return world;
	}
	
	/**
     * Terminate <code>world</code>.
     */
	@Override
	public void terminateWorld(World world) throws ModelException {
		try {
			world.terminateWorld(world);
		}
		catch (NullPointerException Exception){
			throw new ModelException (Exception);}
	}
	
	/**
     * Check whether <code>world</code> is terminated.
     */
	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}
	
	/**
     * Return the size of <code>world</code> as an array containing the width,
     * followed by the height.
     */
	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return world.getSize().getXY();
	}
	
	/**
     * Return all ships located within <code>world</code>.
     */
	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getWorldShips();
	}
	
	 /**
     * Return all bullets located in <code>world</code>.
     */
	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getWorldBullets();
	}
	
	/**
     * Add <code>ship</code> to <code>world</code>.
     */
	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addShipToWorld(ship);
		}
		catch (IllegalArgumentException |NullPointerException Exception){
			throw new ModelException (Exception);}
	   
	}
	
	/**
     * Remove <code>ship</code> from <code>world</code>.
     */
	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {
			world.removeShipFromWorld(ship);
		}
		catch (IllegalArgumentException |NullPointerException Exception){
			throw new ModelException (Exception);}
	}
	
	 /**
     * Add <code>bullet</code> to <code>world</code>.
     */
	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addBulletToWorld(bullet);
	}
	
	/**
     * Remove <code>ship</code> from <code>world</code>.
     */
	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.removeBulletFromWorld(bullet);
		}
		catch (IllegalArgumentException |NullPointerException Exception){
			throw new ModelException (Exception);}
		
	}
	
	
	/*********************************************/
	/** SHIP: Methods related to loaded bullets **/
	/*********************************************/
	
	/**
     * Return the set of all bullets loaded on <code>ship</code>.
     */
	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets();
	}
	
	/**
     * Return the number of bullets loaded on <code>ship</code>.
     */
	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBullets();
	}
	
	/**
     * Load <code>bullet</code> on <code>ship</code>.
     */
	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		//bullet.loadOnShip(ship);
		ship.addBullet(bullet);
	}
	
	/**
     * Load <code>bullet</code> on <code>ship</code>.
     */
	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		ship.addBullets(bullets);
	}
	
	/**
     * Remove <code>ship</code> from <code>ship</code>.
     */
	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBullet(bullet);
	}
	
	/**
     * <code>ship</code> fires a bullet.
     */
	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}
	
	
	/******************
	 *   COLLISIONS   *
	 *****************/
	
	/**
     * Return the shortest time in which the given entity will collide with the
     * boundaries of its world.
     */
	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getTimeToBoundaryCollision();
	}
	
	/**
     * Return the first position at which the given entity will collide with the
     * boundaries of its world.
     */
	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getBoundaryCollisionPosition();
	}
	
	/**
     * Return the shortest time in which the first entity will collide with the
     * second entity.
     */
	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return ((Entity) entity1).getTimeToCollision(((Entity) entity2));
	}
	
	/**
     * Return the first position at which the first entity will collide with the
     * second entity.
     */
	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return ((Entity) entity1).getCollisionPosition(((Entity) entity2));
	}
	
	/**
     * Return the time that must pass before a boundary collision or an entity
     * collision will take place in the given world. Positive Infinity is
     * returned if no collision will occur.
     */
	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeToFirstCollision();
	}
	
	/**
     * Return the position of the first boundary collision or entity collision
     * that will take place in the given world. Null is returned if no collision
     * will occur.
     */
	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getPositionNextCollision();
	}
	
	/**
     * Advance <code>world</code> by <code>dt<code> seconds.
     *
     * To enable explosions within the UI, notify <code>collisionListener</code>
     * whenever an entity collides with a boundary or another entity during this
     * method. <code>collisionListener</code> may be null. If
     * <code>collisionListener</code> is <code>null</code>, do not call its
     * notify methods.
     */
	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt, collisionListener);
		}
		catch (IllegalStateException | IllegalArgumentException Exception) {
			throw new ModelException(Exception);
		}
	}
	
	/**
     * Return the entity at the given <code>position</code> in the given
     * <code>world</code>.
     */
	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return getEntityAt(world, x, y);
	}
	
	/**
     * Return a set of all the entities in the given world.
     */
	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntities();
	}
	
	
	/**************
	 * WORLD: Asteroids and planetoids
	 *************/
	
	/**
	 * Return all asteroids located in <code>world</code>.
	 */
	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return world.getWorldAsteroids();
	}

	/**
	 * Add <code>asteroid</code> to <code>world</code>.
	 */
	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.addAsteroidToWorld(asteroid);
		}
		catch (IllegalArgumentException | NullPointerException Exception) {
			throw new ModelException(Exception);
		}
	}

	/**
	 * Remove <code>asteroid</code> from <code>world</code>.
	 */
	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		world.removeAsteroidFromWorld(asteroid);
	}

	/**
	 * Return all planetoids located in <code>world</code>.
	 */
	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return world.getWorldPlanetoids();
	}

	/**
	 * Add <code>planetoid</code> to <code>world</code>.
	 */
	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.addPlanetoidToWorld(planetoid);
		}
		catch (IllegalArgumentException |NullPointerException Exception){
			throw new ModelException (Exception);}
	}

	/**
	 * Remove <code>planetoid</code> from <code>world</code>.
	 */
	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		world.removePlanetoidFromWorld(planetoid);
	}
	
	
	/**************
	 * ASTEROID: Basic methods
	 *************/

	/**
	 * Create a new non-null asteroid with the given position, velocity and
	 * radius.
	 * 
	 * The asteroid is not located in a world.
	 */
	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		try {
			Asteroid asteroid = new Asteroid(x, y, xVelocity, yVelocity, radius);
			return asteroid;
		}
		catch (IllegalArgumentException Exception) {
			throw new ModelException (Exception);
		}
		
	}

	/**
	 * Terminate <code>asteroid</code>.
	 */
	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.Terminate(asteroid);
	}

	/**
	 * Check whether <code>asteroid</code> is terminated.
	 */
	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminatedMinorPlanet();
	}

	/**
	 * Return the position of <code>asteroid</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return asteroid.getPosition().getXY();		
	}

	/**
	 * Return the velocity of <code>asteroid</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return asteroid.getVelocity().getXY();
	}

	/**
	 * Return the radius of <code>asteroid</code>.
	 */
	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	/**
	 * Return the mass of <code>asteroid</code>.
	 */
	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	/**
	 * Return the world in which <code>asteroid</code> is positioned.
	 */
	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getEntityWorld();
	}
	
	
	/**************
	 * PLANETOID: Basic methods
	 *************/

	/**
	 * Create a new non-null planetoid with the given position, velocity,
	 * radius, and total traveled distance.
	 * 
	 * The planetoid is not located in a world.
	 */
	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance) throws ModelException {
		try {
			Planetoid planetoid = new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
			return planetoid;
		}
		catch (IllegalArgumentException Exception) {
			throw new ModelException (Exception);
		}
	}

	/**
	 * Terminate <code>planetoid</code>.
	 */
	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.Terminate(planetoid);
	}

	/**
	 * Check whether <code>planetoid</code> is terminated.
	 */
	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isTerminatedMinorPlanet();
	}

	/**
	 * Return the position of <code>planetoid</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		return planetoid.getPosition().getXY();
	}

	/**
	 * Return the velocity of <code>planetoid</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		return planetoid.getVelocity().getXY();
	}

	/**
	 * Return the radius of <code>planetoid</code>.
	 */
	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
	}

	/**
	 * Return the mass of <code>planetoid</code>.
	 */
	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}

	/**
	 * Return the total traveled distance of <code>planetoid</code>.
	 */
	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getDistanceTraveled();
	}

	/**
	 * Return the world in which <code>planetoid</code> is positioned.
	 */
	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getEntityWorld();
	}
	
	
	/**********
	 * PROGRAMS
	 **********/

	/**
	 * Return the program loaded on the given ship.
	 */
	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getShipProgram();
	}

	/**
	 * Load the given program on the given ship.
	 */
	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		ship.loadProgramOnShip(program);
	}

	/**
	 * Execute the program loaded on the given ship during the given period of
	 * time. The ship is positioned in some world. Returns null if the program
	 * is not completely executed. Otherwise, returns the objects that have been
	 * printed.
	 * 
	 * This method is only used in the tests. The GUI never calls this method,
	 * only the
	 * {@link #evolve(World, double, asteroids.part2.CollisionListener)} method.
	 */
	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new program factory.
	 */
	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	/********
	 * 
	 *******/
	/**
	 * Return the number of students in your team (used to adapt the tests for
	 * single-student groups).
	 * 
	 * @return 1 or 2
	 */
	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}	
}
