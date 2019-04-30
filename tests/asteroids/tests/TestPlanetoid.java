package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.model.*;

public class TestPlanetoid {

	private static final double EPSILON = 0.000001;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private Planetoid planetoid_14_74_25_12_6_2;
	private Planetoid planetoid_50_37_14_56_16_450;
	


	@Before
	public void setUp() throws Exception {
		planetoid_14_74_25_12_6_2 = new Planetoid(14, 74, 25, 12, 6, 2);
		planetoid_50_37_14_56_16_450 = new Planetoid(50, 37, 14, 56, 16, 450);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	// getDensity
	@Test
	public void getDensity() {
		assertEquals(planetoid_14_74_25_12_6_2.getDensity(), 9.17E11, EPSILON);
	}
	
	
	// getDistanceTraveled
	@Test
	public void getDistanceTraveled_2() {
		assertEquals(planetoid_14_74_25_12_6_2.getDistanceTraveled(), 2, EPSILON);
	}
	
	@Test
	public void getDistanceTraveled_() {
		assertEquals(planetoid_50_37_14_56_16_450.getDistanceTraveled(), 450, EPSILON);
	}
}
