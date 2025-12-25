package com.practiceApp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import commonUtility.Utilities;

public class ProductCatalogue extends Utilities {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	// Used for waiting until products appear
	By productsBy = By.cssSelector(".mb-3");

	// "Add to Cart" button inside each product card	
	By addToCartButton = By.cssSelector(".w-10");

	// Confirmation message toast after adding to cart
	By productAddedToast = By.xpath("//div[contains(text(),'Product Added To Cart')]");

	// Loading/animation overlay that appears after adding to cart
	By loadingAnimation = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	public void addProductsToCart(String[] productsToAdd) {
		List<WebElement> productList = getProductList();

		for (WebElement productElement : productList) {
			String productName = productElement.getText();

			for (String productToAdd : productsToAdd) {
				if (productName.contains(productToAdd)) {

					// Click "Add to Cart" button inside the product card
					productElement.findElement(addToCartButton).click();

					// Wait for "Product Added To Cart" message
					// wait.until(ExpectedConditions.visibilityOfElementLocated(productAddedToast));
					waitForElementToAppear(productAddedToast);

					String confirmationMsg = driver.findElement(productAddedToast).getText();
				//Assert.assertEquals(confirmationMsg, "Product Added To Cart");

					// Wait for loading animation to disappear
					// wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingAnimation));
					waitForElementToDisappear(loadingAnimation);

					break; // Break inner loop after product is added
				}
			}
		}

		new CartPage(driver);
	}

}
