package com.hellofresh.challenge;
import org.testng.annotations.DataProvider;

/**
 * Data Provider for Checkout test cases
 */

public class WebTestDataProvider {

	@DataProvider(name = "orderCheckout")
	public static Object[][] orderCheckoutDataProvider() {

		Object[][] retObjArr = {
				{"ORDER_CHECKOUT_DRESSNAME", "Faded Short Sleeve T-shirts"},
		};
		
		return retObjArr;
	}

}
