package com.hellofresh.challenge;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Helper Functions
 */

public class WebTestHelper {

	//Getting Random Names/ String Values
	public String generateRandomName() {
		String random = RandomStringUtils.randomAlphabetic(4).toLowerCase();
		return random;
	}

	//check OS where testCase Running
	public String getOS() {
		String OS = System.getProperty("os.name").toLowerCase();
		return OS;
	}

}
