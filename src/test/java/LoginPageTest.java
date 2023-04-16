import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class LoginPageTest {
    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void _1_getTitle(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String actualTitle = driver.getTitle();
        String expectedTitle = "OrangeHRM";
        System.out.println(actualTitle);
        Assert.assertEquals(expectedTitle,actualTitle);
    }


    @Test
    public void _2_checkIfImageExists() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        List<WebElement> imageElem= driver.findElements(By.tagName("img"));
        Thread.sleep(2000);
        Assert.assertTrue(imageElem.get(0).isDisplayed());

        //Utils.explicitWaitForTheElement(driver, imageElem.get(2), 32 );
        Thread.sleep(2000);
        Assert.assertTrue(imageElem.get(2).isDisplayed());

    }


   @Test
    public void  _3_loginWithValidCreds() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
        userName.sendKeys("Admin");

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("admin123");
        password.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        WebElement employeeName = driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']"));
       // String ProfileInformation = employeeName.getText();
       Assert.assertTrue(employeeName.isDisplayed());

    }


    @Test
    public void  _4_loginWithInvalidCreds() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
        userName.sendKeys("Adm");

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("admin123");
        password.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        WebElement invalidElement = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']"));
        String invalidText = invalidElement.getText();
        Assert.assertEquals("Invalid credentials",invalidText);


    }





    @After
    public void close(){
        driver.quit();
    }
}
