package io.awaz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MathUtilsTest {

	MathUtils math;
	TestInfo info;
	TestReporter report;
	@BeforeAll
	static void beforeAllMethods()
	{
		System.out.println("Run before all the methods..");
	}
	@BeforeEach
	void init(TestInfo info, TestReporter report)
	{
		this.info=info;
		this.report=report;
		math = new MathUtils();
	}
	@AfterEach
	void cleanUp()
	{
		System.out.println("Clean up....");
	}
	@Test
	@DisplayName("Tetsing Add Functionality")
	void testAdd() {
		int expected=2;
		int actual = math.add(1,1);
		assertEquals(expected, actual,"The add method should add two numbers");
	}
	@Test
	@DisplayName("Test Driven Method should not run")
	@Tag("Circle")
	@Disabled
	public void testComputeCircleRadius()
	{
		assertEquals(0/*314.1592653589793*/, math.computeCircleArea(10),"Should return right circle area");
	}
	
	@RepeatedTest(3)
	public void testDivide(RepetitionInfo repInfo)
	{
		if(repInfo.getCurrentRepetition()==1)
		{
		assertThrows(ArithmeticException.class, ()->math.div(1, 0), "divide by zero should throw");
		}
		else if(repInfo.getCurrentRepetition()==2)
		{
			assertEquals(6,math.div(36, 6), "divide by zero should throw");
		}
		else if(repInfo.getCurrentRepetition()==repInfo.getTotalRepetitions()) {
			assertEquals(2,math.div(4,2), "divide by zero should throw");
		}
	}
	
	@Test
	@DisplayName("Multipy Method")
	@Tag("Multipy")
	void testMultiply()
	{
		//assertEquals(4, math.mul(2, 2),"This is Multiply");
		//System.out.println("Running "+this.info.getDisplayName()+" with tags "+ this.info.getTags());
		report.publishEntry("Running "+this.info.getDisplayName()+" with tags "+ this.info.getTags());
		assertAll(
				()->assertEquals(4, math.mul(2, 2)),
				()->assertEquals(0, math.mul(2, 0)),
				()->assertEquals(-2, math.mul(2, -1))
				);
	}
	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest{
		@Test
		@DisplayName("when adding two positive number")
		void testAddPositive() {
			int expected=2;
			int actual = math.add(1,1);
			assertEquals(expected, actual,"should return the positive sum");
		}
		@Test
		@DisplayName("when adding two negative number")
		void testAddNegative() {
			int expected=-2;
			int actual = math.add(-1,-1);
			assertEquals(expected, actual,"should return the negative sum");
		}
	}
}
