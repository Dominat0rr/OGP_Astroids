package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.model.*;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class TestShip {
	
	IFacade facade;
	private static final double EPSILON = 0.000001;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	// different test cases for constructor
	// Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
	// 
	private double inf = Double.POSITIVE_INFINITY;
	private Ship ship_25_50_10_20_30_0_100;
	private Ship ship_75_50_0_0_50_0_50;
	private Ship ship_100_100_30_min15_20_0_400;
	private Ship ship_10_10_0_0_20_0_225;
	private Ship ship_10_10_0_0_20_1_min50;	
	private Ship ship_75_50_10_20_30_0_400;
	
	private Bullet bullet_100_100_5_5_2;
	private World world_700_700;
	
		
	@Before
	public void setUp() throws Exception {
		ship_25_50_10_20_30_0_100 = new Ship(25, 50, 10, 20, 30, 0, 100);
		ship_75_50_0_0_50_0_50 = new Ship(75, 50, 0, 0, 50, 0.5, 50);
		ship_100_100_30_min15_20_0_400 = new Ship(100, 100, 30, -15, 20, 0, 400);
		ship_10_10_0_0_20_0_225 = new Ship(10, 10, 0, 0, 20, 0, 225);
		ship_10_10_0_0_20_1_min50 = new Ship(10, 10, 0, 0, 20, 1, -50);
		ship_75_50_10_20_30_0_400 = new Ship(75, 50, 10, 20, 30, 0, 400);
		
		bullet_100_100_5_5_2 = new Bullet(100, 100, 5, 5, 2);
		world_700_700 = new World(700, 700);
		
		ship_75_50_10_20_30_0_400.addBullet(bullet_100_100_5_5_2);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	// isValidOrientation
	@Test
	public void isValidOrientation_NegativeOrientation() {
		assertFalse(Ship.isValidOrientation(-3));
	}
	
	@Test
	public void isValidOrientation_OrientationToLarge() {
		assertFalse(Ship.isValidOrientation(7.5));
	}
		
	@Test
	public void isValidOrientation_OrientationInRange() {
		assertTrue(Ship.isValidOrientation(5));
	}
	
	
	// getMass
	@Test
	public void getMass_NoBullets() {
		assertEquals(ship_25_50_10_20_30_0_100.getMass(), 100, EPSILON);
	}
	
	/*@Test
	public void getMass_ShipPlusBullet() {
		assertEquals(ship_75_50_10_20_30_0_400.getMass(), 402, EPSILON);
	}*/
	
	
	// getAcceleration
	@Test
	public void getAcceleration_ship_25() {
		assertEquals(ship_25_50_10_20_30_0_100.getAcceleration(), 0, EPSILON);
	}
	
	@Test
	public void getAcceleration_ship_75() {
		assertEquals(ship_75_50_10_20_30_0_400.getAcceleration(), 0, EPSILON);
	}
	
	
	// thrust
	@Test
	public void thrust_NegativeAmount() {
		ship_75_50_10_20_30_0_400.thrust(-1);
		
		assertEquals(75, ship_75_50_10_20_30_0_400.getPosition().getX(), EPSILON);
		assertEquals(50, ship_75_50_10_20_30_0_400.getPosition().getY(), EPSILON);
		assertEquals(10, ship_75_50_10_20_30_0_400.getVelocity().getX(), EPSILON);
		assertEquals(20, ship_75_50_10_20_30_0_400.getVelocity().getY(), EPSILON);
	}	
	
	@Test
	public void thrust_PositiveAmount() {
		ship_25_50_10_20_30_0_100.thrust(10);
		
		assertEquals(25, ship_25_50_10_20_30_0_100.getPosition().getX(), EPSILON);
		assertEquals(50, ship_25_50_10_20_30_0_100.getPosition().getY(), EPSILON);
	}
	
	
	// Turn
	@Test
	public void turn_OrientationInRange() {
		ship_75_50_10_20_30_0_400.turn(Math.PI);
		assertEquals(Math.PI, ship_75_50_10_20_30_0_400.getOrientation(), EPSILON);
	}
	
	@Test 
	public void turn_OrientationTooLarge() {
		ship_25_50_10_20_30_0_100.turn(10);
		assertEquals(10, ship_25_50_10_20_30_0_100.getOrientation(), EPSILON);
	}
	
	@Test 
	public void turn_NegativeOrientation() {
		ship_25_50_10_20_30_0_100.turn(-10);
		assertEquals(-10, ship_25_50_10_20_30_0_100.getOrientation(), EPSILON);
	}
	
	
	// isValidAngle
	@Test
	public void isValidAngle_Valid() {
		assertTrue(Ship.isValidAngle(5));
	}
	
	@Test
	public void isValidAngle_TooLarge() {
		assertFalse(Ship.isValidAngle(15));
	}
	
	@Test
	public void isValidAngle_Negative() {
		assertFalse(Ship.isValidAngle(-1));
	}
	
	
	// getBullets
	@Test
	public void getBullets() {
		assertEquals(ship_75_50_10_20_30_0_400.getBullets(), bullet_100_100_5_5_2);
	}
	
	
	// getNbBullets
	@Test
	public void getNbBullets() {
		assertEquals(ship_75_50_10_20_30_0_400.getNbBullets(), 1);
	}
	
	
	// containsBullet
	@Test
	public void containsBullet() {
		assertTrue(ship_75_50_10_20_30_0_400.containsBullet(bullet_100_100_5_5_2));
	}
	
	
	// addBullet
	@Test (expected = NullPointerException.class)
	public void addBullet_Null() {
		ship_75_50_10_20_30_0_400.addBullet(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addBullet_BulletAlreadyInSet() {
		ship_75_50_10_20_30_0_400.addBullet(bullet_100_100_5_5_2);
	}
	
	@Test
	public void addBullet_AddValidBullet() {
		ship_25_50_10_20_30_0_100.addBullet(bullet_100_100_5_5_2);
	}
	
	
	// removeBullet
	@Test (expected = NullPointerException.class)
	public void removeBulet_Null() {
		ship_75_50_10_20_30_0_400.removeBullet(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeBulet_BulletNotInSet() {
		ship_25_50_10_20_30_0_100.removeBullet(bullet_100_100_5_5_2);
	}
	
	@Test
	public void removeBullet_RemoveValidBullet() {
		ship_75_50_10_20_30_0_400.removeBullet(bullet_100_100_5_5_2);
	}
	
	
	// isOverlappingInWorld
	@Test
	public void isOverlappingInWorld_False() {
		assertFalse(ship_75_50_10_20_30_0_400.isOverlappingInWorld(world_700_700));
	}
}
