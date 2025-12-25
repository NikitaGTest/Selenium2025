package com.practiceApp.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.practiceApp.CartPage;
import com.practiceApp.CheckoutPage;
import com.practiceApp.LandingPage;
import com.practiceApp.OrderPage;
import com.practiceApp.ProductCatalogue;
import com.practiceApp.testcomponents.BaseTest;

	public class SubmitOrderTest extends BaseTest {
	String[] productsToAdd = { "ZARA COAT 3", "IPHONE 13 PRO" };

	
	@Test(dataProvider = "getData",groups="purchaseOrder")

	public void placeOrder(HashMap<String,String> input) throws IOException {

	//	LandingPage landingpage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// lp.waitForElementToAppear(By.cssSelector(".mb-3"));
		// Wait for products to load
		// List<WebElement> productList = pc.getProductList();
		productCatalogue.addProductsToCart(productsToAdd);

		CartPage cartpage = productCatalogue.navigateToCart();
		cartpage.validateCartDetails(productsToAdd);

		CheckoutPage checkoutPage = cartpage.checkout();
		checkoutPage.selectCountry("India");
		checkoutPage.placeOrder();
		String successMsg=checkoutPage.getConfirmationMsg();
		Assert.assertTrue(successMsg.equalsIgnoreCase("Thankyou for the order."));
		//driver.close();
	}
	
	@Test(dependsOnMethods = {"placeOrder"})
	public void verifyOrderSummary() {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("testuser@gmail.com", "Test@500");
		OrderPage orderPage =productCatalogue.navigateToOrdersPage();
		orderPage.verifyOrderDisplay(productsToAdd);
	}
	
//@DataProvider
	//public Object[][] getData()
//	{
//		
//		return new Object[][]  {{"testuser@gmail.com", "Test@500"},{"testuser@gmail.com", "Test@500"}};
//		
//	}	
	@DataProvider()
	public Object[][] getData() throws IOException{
		
		
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir") + "/src/main/java/com/practiceApp/data/PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
		
		
	}
	
	
	
	
}
