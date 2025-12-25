package com.practiceApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.Utilities;

public class LandingPage extends Utilities{

	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(id="userEmail")
    WebElement username;
	

	
	@FindBy(id="userPassword")
    WebElement password;
	

	
	@FindBy(id="login")
    WebElement login;
	
	@FindBy(css="[role='alert']")
	WebElement alertMsg;
	
	By alert =By.cssSelector("[role='alert']");
	
	public ProductCatalogue loginApplication(String user,String pass) {
		
		username.sendKeys(user);
		password.sendKeys(pass);
		login.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public void goTo(String url) {
		
		
        driver.get(url);
		
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(alert);
		return alertMsg.getText();
		
		
	}
}
