package com.practiceApp.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import com.practiceApp.CartPage;
import com.practiceApp.ProductCatalogue;
import com.practiceApp.testcomponents.BaseTest;

public class ErrorValidation extends BaseTest {

    @Test(dataProvider = "getData",groups = {"ErrorHandling"})
    public void validatedLoginErrorMsg(HashMap<String,String> input) {
        // The landingPage is initialized in @BeforeMethod of BaseTest
        landingPage.loginApplication(input.get("email"), input.get("password"));

        String errorMsg = landingPage.getErrorMessage();
       // Assert.assertEquals(errorMsg, "Incorrect email or password.", "Error message did not match!");
        Assert.assertTrue(errorMsg.equalsIgnoreCase("Incorrect email or passwo"), "Incorrect email or password.");
        
    }
    
    
    @Test(enabled = false)
    public void addItemsToCart() {
	String[] productsToAdd = { "ZARA COAT 3", "IPHONE 13 PRO" };
	String[] productsToAdd1 = { "ZARA COAT 34", "IPHONE 13 PRO5" };

//	LandingPage landingpage = launchApplication();
	ProductCatalogue productCatalogue = landingPage.loginApplication("testuser@gmail.com", "Test@500");
	// lp.waitForElementToAppear(By.cssSelector(".mb-3"));
	// Wait for products to load
	// List<WebElement> productList = pc.getProductList();
	productCatalogue.addProductsToCart(productsToAdd);

	CartPage cartpage = productCatalogue.navigateToCart();
	cartpage.validateCartDetails(productsToAdd1);
	Assert.assertFalse(cartpage.validateCartDetails(productsToAdd1));
    }
    
	@DataProvider
	public Object[][] getData() throws IOException{
		
		
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir") + "/src/main/java/com/practiceApp/data/PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
		
		
	}
	
    
}
