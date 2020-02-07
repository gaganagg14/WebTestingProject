package com.hellofresh.challenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * LoginPage PageFactory class
 */

public class LoginPage {
	WebDriver driver;

	@FindBy(how = How.ID, using = "email")
	WebElement email;
	@FindBy(how = How.ID, using = "passwd")
	WebElement password;
	@FindBy(how = How.ID, using = "SubmitLogin")
	WebElement loginButton;

	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	public LoginPage() {
		
	}
	


}
