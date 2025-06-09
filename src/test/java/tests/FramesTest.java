package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void textInFrame(){
        driver.get("https://the-internet.herokuapp.com/iframe");
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        String iFrameText = driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p")).getText();
        Assert.assertEquals(iFrameText, "Your content goes here.", "Wrong iframe text");
        driver.switchTo().defaultContent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
