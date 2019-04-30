package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.model.*;

public class TestEntity {
	
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
	private static double max =  Double.MAX_VALUE;
	private Ship ship_75_50_10_20_30_0_400;
	private Ship ship_800_900_100_50_30_0_400;
	private Ship ship_100_100_30_min15_20_0;
	private Bullet bullet_100_100_5_5_2;
	private Asteroid asteroid_50_50_25_10_6;
	private Planetoid planetoid_45_45_50_10_8_5;
	private World world_700_700;
	
		
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		world_700_700 = new World(700, 700);
		ship_75_50_10_20_30_0_400 = new Ship(75, 50, 10, 20, 30, 0, 400);
		ship_800_900_100_50_30_0_400 = new Ship(800, 900, 100, 50, 30, 0, 400);
		ship_100_100_30_min15_20_0 = new Ship(100, 100, 30, -15, 20, 0);
		bullet_100_100_5_5_2 = new Bullet(100, 100, 5, 5, 2);
		asteroid_50_50_25_10_6 = new Asteroid(50, 50, 25, 10, 6);
		planetoid_45_45_50_10_8_5 = new Planetoid(45, 45, 50, 10, 8, 5);
	
		world_700_700.addShipToWorld(ship_75_50_10_20_30_0_400);
		ship_75_50_10_20_30_0_400.setEntityWorld(world_700_700);
		
		world_700_700.addShipToWorld(ship_800_900_100_50_30_0_400);
		
		world_700_700.addBulletToWorld(bullet_100_100_5_5_2);
		bullet_100_100_5_5_2.setEntityWorld(world_700_700);
		
		world_700_700.addAsteroidToWorld(asteroid_50_50_25_10_6);
		asteroid_50_50_25_10_6.setEntityWorld(world_700_700);
		
		world_700_700.addPlanetoidToWorld(planetoid_45_45_50_10_8_5);
		planetoid_45_45_50_10_8_5.setEntityWorld(world_700_700);
	}

	@After
	public void tearDown() throws Exception {
	}	
	
	
	// Terminate
	@Test
	public void Terminate_terminateShip() {
		ship_75_50_10_20_30_0_400.Terminate(ship_75_50_10_20_30_0_400);
		assertEquals(ship_75_50_10_20_30_0_400.getEntityWorld(), null);
	}
	
	@Test
	public void Terminate_terminateBullet() {
		bullet_100_100_5_5_2.Terminate(bullet_100_100_5_5_2);
		assertEquals(bullet_100_100_5_5_2.getEntityWorld(), null);
	} 
	
	/*@Test
	// TODO check
	public void Terminate_terminateAsteroid() {
		asteroid_50_50_25_10_6.Terminate(asteroid_50_50_25_10_6);
		assertEquals(asteroid_50_50_25_10_6.getEntityWorld(), null);
	} */
	
	/*@Test
	// TODO check
	public void Terminate_terminatePlanetoid() {
		planetoid_45_45_50_10_8_5.Terminate(planetoid_45_45_50_10_8_5);
		assertEquals(planetoid_45_45_50_10_8_5.getEntityWorld(), null);
	}*/ 
	
	
	// isValidPosition
	@Test
	public void isValidPosition_PositionNaN() {
		assertFalse(Entity.isValidPosition(0.0 / 0.0, Math.sqrt(-1)));
	}
	
	@Test
	public void isValidPosition_PositionDouble_POSITIVE_INFINITY() {
		assertFalse(Entity.isValidPosition(inf, 30));
	}
	
	@Test
	public void isValidPosition_PositionInRange() {
		assertTrue(Entity.isValidPosition(10, 250));
	}
	
	
	// isValidVelocity
	@Test
	public void isValidVelocity_VelocityToFast() {
		assertFalse(Entity.isValidVelocity(250000, 225000));
	}
	
	@Test
	public void isValidVelocity_VelocityValid() {
		assertTrue(Entity.isValidVelocity(50, 85));
	}
	
	
	// isValidRadius
	@Test
	public void isValidRadius_RadiusToSmall() {
		assertFalse(ship_75_50_10_20_30_0_400.isValidRadius(3));
	}
	
	@Test
	public void isValidRadius_NegativeRadius() {
		assertFalse(bullet_100_100_5_5_2.isValidRadius(-11));
	}
	
	@Test
	public void isValidRadius_RadiusInRange() {
		assertTrue(asteroid_50_50_25_10_6.isValidRadius(70));
	}
	
	
	// isValidMass
	@Test
	public void isValidMass_ToSmall() {
		assertFalse(Entity.isValidMass(0));
	}
	
	@Test
	public void isValidMass_NegativeMass() {
		assertFalse(Entity.isValidMass(-10));
	}
	
	@Test
	public void isValidMass_Valid() {
		assertTrue(Entity.isValidMass(10));
	}
	
	@Test
	public void isValidMass_MAX() {
		assertFalse(Entity.isValidMass(max));
	}
	
	
	// getDensity
	@Test
	public void getDensity_Ship() {
		assertEquals(ship_75_50_10_20_30_0_400.getDensity(), 1.42E12, EPSILON);
	}
	
	@Test
	public void getDensity_Bullet() {
		assertEquals(bullet_100_100_5_5_2.getDensity(), 7.8E12, EPSILON);
	}
	
	@Test
	public void getDensity_Asteroid() {
		assertEquals(asteroid_50_50_25_10_6.getDensity(), 2.65E12, EPSILON);
	}
	
	@Test
	public void getDensity_Planetoid() {
		assertEquals(planetoid_45_45_50_10_8_5.getDensity(), 9.17E11, EPSILON);
	}
	
	
	// getDistanceBetween
	@Test (expected = NullPointerException.class)
	public void getDistanceBetween_Null() {
		ship_75_50_10_20_30_0_400.getDistanceBetween(null);
	}
	
	@Test
	public void getDistanceBetween_ShipAndBullet() {
		assertEquals(ship_75_50_10_20_30_0_400.getDistanceBetween(bullet_100_100_5_5_2), 23.90169943749474, EPSILON);
	}
	
	@Test
	public void getDistanceBetween_Overlapping() {
		assertEquals(asteroid_50_50_25_10_6.getDistanceBetween(asteroid_50_50_25_10_6), 0, EPSILON);
	}
	
	@Test
	public void getDistanceBetween_ZeroDistance() {
		assertEquals(0, ship_75_50_10_20_30_0_400.getDistanceBetween(ship_75_50_10_20_30_0_400), EPSILON);
        assertTrue(ship_75_50_10_20_30_0_400.Overlap(ship_75_50_10_20_30_0_400));
	}
	
	
	// Overlap
	@Test
	public void overlap_Self() {
		assertTrue(bullet_100_100_5_5_2.Overlap(bullet_100_100_5_5_2));
	}
	
	@Test
	public void overlap_None() {
		assertFalse(ship_75_50_10_20_30_0_400.Overlap(bullet_100_100_5_5_2));
	}
	
	@Test
	public void overlap_Overlap() {
		assertTrue(planetoid_45_45_50_10_8_5.Overlap(asteroid_50_50_25_10_6));
	}
	
	@Test (expected = NullPointerException.class)
	public void overlap_NullPointerException() {
		ship_75_50_10_20_30_0_400.getTimeToCollision(null);
	}
	
	
	// getTimeToCollision
	@Test
	public void getTimeToCollision_INF() {
		assertEquals(inf, bullet_100_100_5_5_2.getTimeToCollision(asteroid_50_50_25_10_6), EPSILON);
	}
	
	@Test
	public void getTimeToCollision_1_5sec() {
		assertEquals(1.5388778722374274, ship_75_50_10_20_30_0_400.getTimeToCollision(bullet_100_100_5_5_2), EPSILON);
	}
	
	@Test (expected = NullPointerException.class)
	public void getTimeToCollision_Overlap() throws NullPointerException {
		ship_75_50_10_20_30_0_400.getTimeToCollision(null);
	}
	
	
	// getPositionCollision
	@Test
	public void getCollisionPosition_INF() {
		double[] position = bullet_100_100_5_5_2.getCollisionPosition(asteroid_50_50_25_10_6);
	
		assertEquals(null, position);
	}
	
	@Test
	public void getCollisionPosition_NormalPosition() {
		double[] position = ship_75_50_10_20_30_0_400.getCollisionPosition(bullet_100_100_5_5_2);
		
		assertEquals(90.38877872237427, position[0], EPSILON);
		assertEquals(80.77755744474855, position[1], EPSILON);
	}
	
	@Test (expected = NullPointerException.class)
	public void getCollisionPosition_NullPointerException() {
		ship_75_50_10_20_30_0_400.getTimeToCollision(null);
	}
	
	
	// isWithinBoundaries
	@Test
	public void isWithinBoundaries_True() {
		assertTrue(ship_75_50_10_20_30_0_400.isWithinBoundaries());
	}
	
	@Test
	public void isWithinBoundaries_False() {
		assertFalse(ship_800_900_100_50_30_0_400.isWithinBoundaries());
	}
	
	
	// getTimeToBoundaryCollision
	@Test
	public void getTimeToBoundaryCollision_INF() {
		assertEquals(ship_800_900_100_50_30_0_400.getTimeToBoundaryCollision(), inf, EPSILON);
	}
	
	@Test
	public void getTimeToBoundaryCollision_31() {
		assertEquals(ship_75_50_10_20_30_0_400.getTimeToBoundaryCollision(), 31.0, EPSILON);
	}
	
	
	// getBoundaryCollisionPosition
	@Test
	public void getBoundaryCollisionPosition_Null() {
		assertEquals(ship_800_900_100_50_30_0_400.getBoundaryCollisionPosition(), null);
	}
	
	/*@Test
	public void getBoundaryCollisionPosition() {
		assertEquals(asteroid_50_50_25_10_6.getBoundaryCollisionPosition(), 0);
	}*/
	
	
	// Move
	@Test (expected = IllegalArgumentException.class)
	public void move_NegaticeDt() {
		ship_75_50_10_20_30_0_400.move(-1);
	}
	
	@Test
	public void move_PositiveDt() {
		ship_100_100_30_min15_20_0.move(10);
		assertEquals(100 + ship_100_100_30_min15_20_0.getVelocity().getX() * 10, ship_100_100_30_min15_20_0.getPosition().getX(), EPSILON);
		assertEquals(100 + ship_100_100_30_min15_20_0.getVelocity().getY() * 10, ship_100_100_30_min15_20_0.getPosition().getY(), EPSILON);	
	}
	
	
	// isValidDt
	@Test
	public void isValidDt_Valid() {
		assertTrue(Entity.isValidDt(5));
	}
	
	@Test
	public void isValidDt_Zero() {
		assertTrue(Entity.isValidDt(0));
	}
	
	@Test
	public void isValidDt_Negative() {
		assertFalse(Entity.isValidDt(-5));
	}
	
	
	// isOverlappingInWorld
	@Test
	public void isOverlappingInWorld_True() {
		assertTrue(planetoid_45_45_50_10_8_5.isOverlappingInWorld(world_700_700));
	}
	
	@Test
	public void isOverlappingInWorld_False() {
		assertFalse(ship_75_50_10_20_30_0_400.isOverlappingInWorld(world_700_700));
	}
	
	
	// getOverlappingEntity
	@Test
	public void getOverlappingEntity_Null() {
		assertEquals(ship_75_50_10_20_30_0_400.getOverlappingEntity(world_700_700), null);
	}
	
	@Test
	public void getOverlappingEntity_Entity() {
		assertEquals(planetoid_45_45_50_10_8_5.getOverlappingEntity(world_700_700), ship_75_50_10_20_30_0_400);
	}
}
