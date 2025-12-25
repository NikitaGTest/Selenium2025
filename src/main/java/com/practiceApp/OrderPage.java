package com.practiceApp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.Utilities;

public class OrderPage extends Utilities {

	
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	
	public boolean verifyOrderDisplay(String[] productNames) {
	    for (String product : productNames) {
	        boolean found = orderedProducts.stream()
	                .anyMatch(p -> p.getText().equalsIgnoreCase(product));
	        if (!found) {
	            return false; // If any product is not found, return false
	        }
	    }
	    return true; // All products matched
	}

	
	
}
