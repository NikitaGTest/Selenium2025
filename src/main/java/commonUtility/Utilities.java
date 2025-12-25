package commonUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practiceApp.CartPage;
import com.practiceApp.OrderPage;

public class Utilities {

	
	WebDriver driver;
	
	public Utilities(WebDriver driver) {

	this.driver=driver;
	PageFactory.initElements(driver, this);
	
	}
	

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHead;
	

	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	
	
	
	public void waitForElementToAppear(By findBy) {
	
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    
    
	}
	public void waitForElementToBeClickable(By findBy) {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.elementToBeClickable(findBy));
	    
	    
		}
	public void waitForElementToDisappear(By findBy) {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	    
	    
		}

  public CartPage navigateToCart() {
	  
	  
	  // Navigate to Cart
	  cartHead.click();
	  CartPage cartpage = new CartPage(driver);
	  return cartpage;
  }
  
  
  public OrderPage navigateToOrdersPage() {
	  
	  
	  orders.click();
	  OrderPage orderPage= new OrderPage(driver);
	  return orderPage;
	  
  }
  
 

}
