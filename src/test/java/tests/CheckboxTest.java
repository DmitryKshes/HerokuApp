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

public class CheckboxTest {
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(testName = "Выбор чекбоксов", description = "Проверка выбора чекбоксов", priority = 0, groups = "checkBoxPage")
    public void checkStateCheckbox(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).click();
        driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).click();
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        softAssert.assertEquals(checkboxes.get(0).isSelected(), true, "input is unchecked");
        softAssert.assertEquals(checkboxes.get(1).isSelected(), false, "input is checked");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
        softAssert.assertAll();
    }
}
