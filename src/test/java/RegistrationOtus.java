import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class RegistrationOtus {

    private String name =System.getProperty("name");
    private String password=System.getProperty("password");

    private final static Logger logger = LogManager.getLogger(WindowModeTest.class);
    private  WebDriver driver;

    @BeforeAll
    public static void instDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    public void closeDrive(){
        if(driver !=null){
            driver.quit();
        }
    }
    private WebElement visibilitiElement ( By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    @Test
    public void registrationOtus(){

        driver =new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://otus.ru");

        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        driver.findElement(By.xpath("//div[./input[@name='email']]")).click();
        visibilitiElement(By.cssSelector("input[name='email']"))
            .sendKeys(name);


        driver.findElement(By.xpath("//div[./input[@type='password']]")).click();
        visibilitiElement(By.cssSelector("input[type='password']"))
            .sendKeys(password);;
    
        visibilitiElement(By.xpath("//button[./*[text()='Войти']]")).click();

        WebElement nameUser=visibilitiElement(By.xpath("//span[text()='Test']"));
        Assertions.assertEquals( "Test",nameUser.getText());
        Assertions.assertNotNull( driver.manage().getCookies());
        logger.info( driver.manage().getCookies());
    }
}
