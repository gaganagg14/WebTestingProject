package com.hellofresh.challenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * SigninPage PageFactory class
 */

public class SigninPage {
	WebDriver driver;

	@FindBy(how = How.ID, using = "email_create")
	WebElement email;
	@FindBy(how = How.ID, using = "SubmitCreate")
	WebElement submitButton;
	@FindBy(how = How.ID, using = "customer_firstname")
	WebElement firstName;
	@FindBy(how = How.ID, using = "customer_lastname")
	WebElement lastName;
	@FindBy(how = How.ID, using = "passwd")
	WebElement password;
	@FindBy(how = How.ID, using = "days")
	WebElement days;
	@FindBy(how = How.ID, using = "months")
	WebElement months;
	@FindBy(how = How.ID, using = "years")
	WebElement years;
	@FindBy(how = How.ID, using = "company")
	WebElement company;
	@FindBy(how = How.ID, using = "address1")
	WebElement address1;
	@FindBy(how = How.ID, using = "address2")
	WebElement address2;
	@FindBy(how = How.ID, using = "city")
	WebElement city;
	@FindBy(how = How.ID, using = "id_state")
	WebElement state;
	@FindBy(how = How.ID, using = "postcode")
	WebElement postcode;
	@FindBy(how = How.ID, using = "other")
	WebElement other;
	@FindBy(how = How.ID, using = "phone")
	WebElement phone;
	@FindBy(how = How.ID, using = "phone_mobile")
	WebElement phone_mobile;
	@FindBy(how = How.ID, using = "alias")
	WebElement alias;
	@FindBy(how = How.ID, using = "submitAccount")
	WebElement submitAccount;
	@FindBy(how = How.CSS, using = "h1")
	WebElement heading;
	@FindBy(how = How.CLASS_NAME, using = "account")
	WebElement accountName;
	@FindBy(how = How.CLASS_NAME, using = "info-account")
	WebElement infoAccountName;
	@FindBy(how = How.CLASS_NAME, using = "logout")
	WebElement logout;
	
	public SigninPage(WebDriver driver){
		this.driver = driver;
	}

}
