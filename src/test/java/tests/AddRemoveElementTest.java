package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementTest {
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(retryAnalyzer = Retry.class)
    public void checkAddRemoveElements(){
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
        softAssert.assertEquals(deleteButtons.size(), 2, "not two elements");
        deleteButtons.get(1).click();
        List<WebElement> buttonsAfterDelete = driver.findElements(By.xpath("//button[text()='Delete']"));
        softAssert.assertEquals(buttonsAfterDelete.size(), 1, "not one element");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
        softAssert.assertAll();
    }
}
