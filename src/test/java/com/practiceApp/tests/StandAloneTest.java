package com.practiceApp.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

    public static void main(String args[]) throws InterruptedException {

        String[] products = { "ZARA COAT 3", "IPHONE 13 PRO" };
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys("testuser@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test@500");
        driver.findElement(By.id("login")).click();

        // Wait for products to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

        for (WebElement e : productList) {
            for (String product : products) {
                if (e.getText().contains(product)) {
                    e.findElement(By.cssSelector(".w-10")).click();
                    wait.until(ExpectedConditions
                            .visibilityOfElementLocated(By.xpath("//div[contains(text(),'Product Added To Cart')]")));
                    String productAddedToCartMsg = driver
                            .findElement(By.xpath("//div[contains(text(),'Product Added To Cart')]")).getText();
                    Assert.assertEquals(productAddedToCartMsg, "Product Added To Cart");

                    // Wait for animation to finish before continuing
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
                }
            }
        }

        // Navigate to Cart
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        // Verify products in cart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
        for (String product : products) {
            boolean match = cartProducts.stream()
                    .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));
            System.out.println(product + " found in cart: " + match);
            Assert.assertTrue(match, "Product not found in cart: " + product);
        }

        // Scroll to checkout button
        WebElement checkoutBtn = driver.findElement(By.cssSelector(".totalRow button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

        // Wait until clickable and loader disappears
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        // Click checkout
        checkoutBtn.click();
        Actions act= new Actions(driver);
        act.sendKeys(driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")),"India").build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
        List<WebElement> options= driver.findElements(By.cssSelector(".ta-item"));
        for(WebElement option:options) {
        	
        	if(option.getText().equalsIgnoreCase("India")) {
        		
        		option.click();
        		break;
        		
        	}
        	
        }
        driver.findElement(By.cssSelector(".action__submit")).click();
        String successMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(successMsg, "Thankyou for the order.");

        
    }
}
