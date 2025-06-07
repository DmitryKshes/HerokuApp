package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

public class FileUploadTest {
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void fileUpLoadTest(){
        File file = new File("src/test/java/tests/fileForTestFileUpLoad.txt");
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();
        WebElement textFileName = driver.findElement(By.id("uploaded-files"));
        softAssert.assertEquals(textFileName.getText(), "fileForTestFileUpLoad.txt","not file");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
        softAssert.assertAll();
    }
}
