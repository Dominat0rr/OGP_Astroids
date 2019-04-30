package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;


public class FacadePart1 implements IFacade {
   public FacadePart1() {}

@SuppressWarnings("deprecation")
@Override
public Ship createShip() throws ModelException {
	Ship ship = new Ship();
	return ship;
}

@SuppressWarnings("deprecation")
@Override
public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
		throws ModelException {
	Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, orientation);
	return ship;
}

@Override
public double[] getShipPosition(Ship ship) throws ModelException {
	return ship.getPosition().getXY();
}

@Override
public double[] getShipVelocity(Ship ship) throws ModelException {
	return ship.getVelocity().getXY();
}

@Override
public double getShipRadius(Ship ship) throws ModelException {	
	return ship.getRadius();
}

@Override
public double getShipOrientation(Ship ship) throws ModelException {
	return ship.getOrientation();
}

@Override
public void move(Ship ship, double dt) throws ModelException {
	ship.move(dt);
}

@Override
public void thrust(Ship ship, double amount) throws ModelException {
	ship.thrust(amount);
}

@Override
public void turn(Ship ship, double angle) throws ModelException {
	ship.turn(angle);
	
}

@Override
public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
	return ship1.getDistanceBetween(ship2);
}

@Override
public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
	return ship1.Overlap(ship2);
}

@Override
public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException, NullPointerException {
	return ship1.getTimeToCollision(ship2);
}

@Override
public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
	return ship1.getCollisionPosition(ship2);
}
}
