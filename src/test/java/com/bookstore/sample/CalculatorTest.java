/**
 * 
 */
package com.bookstore.sample;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */
public class CalculatorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Inside setUpBeforeClass");
	}


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("Inside setUp");
	}

	/**
	 * Test method for {@link com.bookstore.sample.Calculator#add(int, int)}.
	 */
	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		int operand1 =23;
		int operand2 = 47;
		long expectedResult = 70;
		long result =calculator.add(operand1, operand2);
		assertEquals(expectedResult, result);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Inside tearDownAfterClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("Inside tearDown");
	}


}
