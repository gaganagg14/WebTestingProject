package com.hellofresh.challenge;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Reusable Webdriver functions
 */

public class WebPageUtils {
	public static LoggerInfo logger = LoggerInfo.getInstance();
;

	public static void explicitWaitByVisibilityOfElement(WebDriver driver,WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver,10,50);
		int attempts=0;
		while(attempts<2){
			try {
				wait.until(ExpectedConditions.visibilityOf(el));
				break;
			} catch(StaleElementReferenceException e) {
				logger.Error("Exception: "+ e);
			}
			attempts++;
		}
	}

	//To avoid stale element exception
	public boolean retryingFindClick(WebElement we) {
		boolean result = false;
		int attempts = 0;
		while(attempts < 2) {
			try {
				we.click();
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
				logger.Error(e.getMessage());
			}catch(WebDriverException e){
				logger.Error(e.getMessage());
			}

			attempts++;
		}
		return result;
	}
	
	public static void selectByValue(WebElement selectBox,String value) {
		Select id = new Select(selectBox);
		id.selectByValue(value);
	}
	
	public static void selectByVisibleText(WebElement selectBox,String value) {
		Select id = new Select(selectBox);
		id.selectByVisibleText(value);
	}

}
