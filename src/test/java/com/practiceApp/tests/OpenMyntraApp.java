package com.practiceApp.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class OpenMyntraApp {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void initialize() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void openMyntraApp() {
        driver.get("https://www.myntra.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.desktop-searchBar")));
    }

    @Parameters({"productName"})
    @Test
    public void searchItem(String productName) {

        driver.get("https://www.myntra.com/");
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.desktop-searchBar")));
        searchBox.sendKeys(productName);

        List<WebElement> searchList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("li.desktop-suggestion")));

        for (WebElement item : searchList) {
            if (item.getText().equalsIgnoreCase(productName)) {
                item.click();
                break;
            }
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
