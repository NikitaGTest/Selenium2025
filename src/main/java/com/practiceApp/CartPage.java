package com.practiceApp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.Utilities;

public class CartPage extends Utilities {

	
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
    By loadingAnimation = By.cssSelector(".ng-animating");

	
	By checkout=By.cssSelector(".totalRow button");
	
//	public void valdateCartDetails(String [] productsAddedToCart) {
//		
//		   // Verify products in cart
//        //List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
//        for (String product : productsAddedToCart) {
//            boolean match = cartProducts.stream()
//                    .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));
//            System.out.println(product + " found in cart: " + match);
//        }
		
		
        public boolean validateCartDetails(String[] productsAddedToCart) {
            // Fetch cart products from the page
            List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));

            for (String product : productsAddedToCart) {
                boolean match = cartProducts.stream()
                        .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));
                System.out.println(product + " found in cart: " + match);
                if (!match) {
                    // If any product is not found, return false immediately
                    return false;
                }
            }
            // All products found
            return true;
        }
  
        
        
      
	
	public CheckoutPage checkout() {
		
		
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
        waitForElementToDisappear(loadingAnimation);
        checkoutButton.click();
        CheckoutPage checkoutpage= new CheckoutPage(driver);
        return checkoutpage;
	}
	
	
	
}
