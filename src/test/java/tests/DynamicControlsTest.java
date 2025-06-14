package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DynamicControlsTest {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        softAssert = new SoftAssert();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void DynamicControlsTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//button[text()='Remove']")).click();
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's gone!"));
        boolean isCheckboxNone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        softAssert.assertEquals(isCheckboxNone, true, "Checkbox on page");
        boolean inputIsDisabled = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input")).isEnabled();
        softAssert.assertEquals(inputIsDisabled, false, "input is not disabled");
        driver.findElement(By.xpath("//*[@id=\"input-example\"]/button")).click();
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's enabled!"));
        boolean inputIsEnableAfterClick = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input")).isEnabled();
        softAssert.assertEquals(inputIsEnableAfterClick, true, "input is disabled");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
