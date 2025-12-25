package com.practiceApp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;

import commonUtility.Utilities;

public class CheckoutPage extends Utilities {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	By countrylist = By.cssSelector(".ta-results");
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryfilter;
	@FindBy(css = ".ta-item")
	List<WebElement> countryitem;
	@FindBy(css = ".action__submit")
	WebElement placeorder;

	@FindBy(css = ".hero-primary")
	WebElement successMsg1;

	public void selectCountry(String country) {

		Actions act = new Actions(driver);
		act.sendKeys(countryfilter, country).build().perform();
		waitForElementToBeClickable(countrylist);
		for (WebElement option : countryitem) {

			if (option.getText().equalsIgnoreCase("India")) {

				option.click();
				break;

			}

		}

	}

	public void placeOrder() {

		placeorder.click();

	}

	public String getConfirmationMsg() {

		String successMsg = successMsg1.getText().toLowerCase();
		return successMsg;

	}

}
