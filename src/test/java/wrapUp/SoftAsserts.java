package wrapUp;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
	SoftAssert softAssert = new SoftAssert();

	@Test
	public void test1() {
		int i = 100;
		int j = 200;
		
		System.out.println("First Assertion");
		softAssert.assertEquals(i, j,"I AND J ARE NOT EQUAL");
		System.out.println("Second Assertion");
		softAssert.assertNotEquals(i, j);
		System.out.println("Third Assertion");
		softAssert.assertTrue(i>j,"I IS NOT GREATER THAN J");
		softAssert.assertAll();
	}

}
