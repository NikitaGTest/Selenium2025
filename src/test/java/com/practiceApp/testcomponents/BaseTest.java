package com.practiceApp.testcomponents;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practiceApp.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver; // ✅ Declare as protected for access in test classes
    public LandingPage landingPage;
    Properties prop;

    public WebDriver initializeDriver() throws IOException {
        prop= new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/practiceApp/resources/GlobalData.properties");
        prop.load(fis);
        String browserName =System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
        System.out.println("browser:"+browserName);
        if (browserName.contains("chrome")) {
        	ChromeOptions options = new ChromeOptions();
        	WebDriverManager.chromedriver().setup();
        	if(browserName.contains("headless")) {
        		options.addArguments("headless");
        	}
            driver = new ChromeDriver(options);
            //driver.manage().window().setSize(new Dimension(1440,990));
           
        } else if (browserName.equalsIgnoreCase("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
        	driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Browser not supported: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver;
    }
    
    
    public String captureScreenshot(String testcaseName,WebDriver driver) throws IOException {
  	  //this.driver=driver;
  	  TakesScreenshot ts= (TakesScreenshot)driver;
  	  File srcfile=ts.getScreenshotAs(OutputType.FILE);
  	  File destFile = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
  	  FileUtils.copyFile(srcfile, destFile);
  	  return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
  	  
    }
    
    
    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
        // ✅ Added missing slash (/) after user.dir
        String jsonContent = FileUtils.readFileToString(
                new File(filepath),
                StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        // ✅ Added missing parentheses at the end of TypeReference constructor
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });

        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo(prop.getProperty("url"));
        return landingPage;
    }

@AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // ✅ Proper cleanup
        }
        
        
    }


}
