package com.practiceApp.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenAmazonApp {

    @Test
    public void openAmazonApp() {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("https://www.amazon.in/");
            String title = driver.getTitle();
            System.out.println("Title is: " + title);
            //Assert.assertTrue(title.contains("uu"), "Title does not contain Amazon");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
