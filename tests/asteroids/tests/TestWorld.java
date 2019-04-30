package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.model.*;

public class TestWorld {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private static double max = Double.MAX_VALUE;
	private World world500_500;
	private World world100_max;
	private World worldnull;
	private Ship ship_750_750_10_0_11_0_20;
	private Ship ship_50_50_0_0_20_0_50;
	private Bullet bullet750_750_10_0_2;
	private Bullet bullet50_50_0_0_5;
	
	
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		world500_500 = new World(500, 500);		
		world100_max = new World(100, max);
		worldnull = null;
		ship_750_750_10_0_11_0_20 = new Ship(750, 750, 10, 0, 11, 0, 20);
		ship_50_50_0_0_20_0_50 = new Ship(50, 50, 0, 0, 20, 0, 50);
		bullet750_750_10_0_2 = new Bullet(750, 750, 10, 0, 2);
		bullet50_50_0_0_5 = new Bullet(50, 50, 0, 0, 5);
		
		world500_500.addShipToWorld(ship_50_50_0_0_20_0_50);
		world500_500.addBulletToWorld(bullet50_50_0_0_5);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	// TerminateWorld
	@Test (expected = NullPointerException.class)
	public void terminateWorld_Null() {
		world500_500.terminateWorld(null);
	}
	
	@Test
	public void terminateWorld() {
		world500_500.terminateWorld(world500_500);
		assertEquals(world500_500.isTerminated(), true);
	}

	
	// isValidSize
	@Test
	public void isValidSize_NormalSize() {
		assertTrue(World.isValidSize(500, 500));
	}
	
	@Test
	public void isValidSize_MaxSize() {
		assertFalse(World.isValidSize(100, max + max));
	}
	
	@Test
	public void isValidSize_NegativeSize() {
		assertFalse(World.isValidSize(-100, 750));
	}
	
	
	// getWorldShips
	@Test (expected = NullPointerException.class)
	public void getWorldShips_Null() {
		worldnull.getWorldShips();
	}
	
	@Test
	public void getWorldShips_ReturnShip() {
		world500_500.getWorldShips();
	}
	
	
	// getWorldBullets
	@Test (expected = NullPointerException.class)
	public void getWorldBullets_Null() {
		worldnull.getWorldBullets();
	}
	
	@Test
	public void getWorldBullets_ReturnBullet() {
		world500_500.getWorldBullets();
	}
	
	
	// addShipToWorld
	@Test (expected = NullPointerException.class)
	public void addShipToWorld_Null() {
		worldnull.addShipToWorld(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addShipToWorld_OutRange() {
		world500_500.addShipToWorld(ship_50_50_0_0_20_0_50);
	}
	
	@Test
	public void addShipToWorld_NormalRange() {
		world100_max.addShipToWorld(ship_750_750_10_0_11_0_20);
	}
	
	
	// removeShipFromWorld
	@Test (expected = NullPointerException.class)
	public void removeShipFromWorld_Null() {
		worldnull.removeShipFromWorld(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeShipFromWorld_DoesntExist() {
		world100_max.removeShipFromWorld(ship_750_750_10_0_11_0_20);
	}
	
	@Test
	public void removeShipFromWorld_Normal() {
		world500_500.removeShipFromWorld(ship_50_50_0_0_20_0_50);
	}
	
	
	// containShip
	@Test
	public void containShip_False() {
		assertFalse(world500_500.containShip(ship_750_750_10_0_11_0_20));
	}
	
	@Test
	public void containShip_True() {
		assertTrue(world500_500.containShip(ship_50_50_0_0_20_0_50));
	}
	
	
	// addBulletToWorld
	@Test (expected = NullPointerException.class)
	public void addBulletToWorld_Null() {
		worldnull.addShipToWorld(null);
	}
	
	@Test
	public void addBulletToWorldd_NormalRange() {
		world500_500.addBulletToWorld(bullet50_50_0_0_5);
	}
	
	
	// removeBulletFromWorld
	@Test (expected = NullPointerException.class)
	public void removeBulletFromWorld_Null() {
		worldnull.removeBulletFromWorld(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeBulletFromWorld_DoesntExist() {
		world100_max.removeBulletFromWorld(bullet50_50_0_0_5);
	}
	
	/*@Test
	public void removeBulletFromWorld_Normal() {
		world500_500.addBulletToWorld(bullet50_50_0_0_5);
		world500_500.removeBulletFromWorld(bullet50_50_0_0_5);
	}*/
	
	
	// containBullet
		@Test
		public void containBullet_False() {
			assertFalse(world500_500.containBullet(bullet50_50_0_0_5));
		}
		
		@Test
		public void containBullet_True() {
			world500_500.addBulletToWorld(bullet50_50_0_0_5);
			assertTrue(world500_500.containBullet(bullet50_50_0_0_5));
		}
	
	
	// getEntityAt
	@Test (expected = NullPointerException.class)
	public void getEntityAt_Null() {
		worldnull.getEntityAt(50, 50);
	}
	
	@Test
	public void getEntityAt_returnNull() {
		assertEquals(null, world100_max.getEntityAt(100, 100));
	}
	
	@Test
	public void getEntitiyAt_ReturnShip50_50() {
		assertEquals(ship_50_50_0_0_20_0_50, world500_500.getEntityAt(50, 50));
	}
	
	
	// getEntities
	@Test (expected = NullPointerException.class)
	public void getEntities_NullPointer() {
		worldnull.getEntities();
	}
	
	@Test
	public void getEntities_Return() {
		assertEquals(ship_50_50_0_0_20_0_50, world500_500.getEntityAt(50, 50));
	}
	
	
	// getTimeToFirstCollision
	@SuppressWarnings("deprecation")
	@Test
	public void getTimeToFirstCollision_inf() {
		assertEquals(world500_500.getTimeToFirstCollision(), Double.POSITIVE_INFINITY, 0.00001);
	}
	
	
	// getTypeOfFirstCollision
	@Test 
	public void getTypeOfFirstCollision_() {
		assertEquals(world500_500.getTypeOfFirstCollision(), 0);
	}
}
