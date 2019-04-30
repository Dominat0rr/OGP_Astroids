package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.model.*;

public class TestBullet {

	private static final double EPSILON = 0.000001;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private double inf = Double.POSITIVE_INFINITY;
	private static double max = Double.MAX_VALUE;
	private Bullet bullet_100_100_5_5_1;
	private Bullet bullet_5_7_2_4_2;
	private Bullet bullet_25_50_2_4_2;
	private Ship ship_25_50_10_20_30_0_100;
	private World world_700_700;
	


	@Before
	public void setUp() throws Exception {
		bullet_100_100_5_5_1 = new Bullet(100, 100, 5, 5, 2);
		bullet_5_7_2_4_2 = new Bullet(5, 7, 2, 4, 2);
		bullet_25_50_2_4_2 = new Bullet(25, 50, 2, 4, 2);
		ship_25_50_10_20_30_0_100 = new Ship(25, 50, 10, 20, 30, 0, 100);	
		world_700_700 = new World(700, 700);
		
		bullet_100_100_5_5_1.setBulletShip(ship_25_50_10_20_30_0_100);
		world_700_700.addBulletToWorld(bullet_25_50_2_4_2);
		world_700_700.addShipToWorld(ship_25_50_10_20_30_0_100);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	// removeBulletShip
	@Test (expected = NullPointerException.class)
	public void remooveBulletShip_Null() {
		bullet_5_7_2_4_2.removeBulletShip();
	}
	
	@Test
	public void removeBulletShip_Valid() {
		bullet_100_100_5_5_1.removeBulletShip();
		assertEquals(bullet_100_100_5_5_1.getBulletShip(), null);
	}
	
	
	// getNbBouces
	@Test
	public void getNbBounces_0() {
		assertEquals(bullet_100_100_5_5_1.getNbBouces(), 0, EPSILON);
	}
	
	@Test
	public void getNbBounces_2() {
		bullet_100_100_5_5_1.increaseNbBounces();
		bullet_100_100_5_5_1.increaseNbBounces();
		
		assertEquals(bullet_100_100_5_5_1.getNbBouces(), 2, EPSILON);
	}
	
	
	// isOverlappingInWorld
	@Test (expected = NullPointerException.class)
	public void isOverlappingInWorld_Null() {
		bullet_100_100_5_5_1.isOverlappingInWorld(null);
	}
	
	@Test
	public void isOverlappingInWorld_False() {
		assertFalse(bullet_100_100_5_5_1.isOverlappingInWorld(world_700_700));
	}

	/*@Test
	public void isOverlappingInWorld_True() {
		assertTrue(bullet_25_50_2_4_2.isOverlappingInWorld(world_700_700));
	}*/
}
