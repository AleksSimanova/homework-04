

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class WindowModeTest{

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
    public void openDriverHeadless(){
        ChromeOptions pOptions= new ChromeOptions();
        pOptions.addArguments("headless");
        
        driver=new ChromeDriver(pOptions);
        // driver=new ChromeDriver();
        
        driver.get(" https://duckduckgo.com/");

        WebElement element =driver.findElement(By.cssSelector("input[type='text']"));
        element.click();
        element.sendKeys("ОТУС");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement search=visibilitiElement(By.xpath("//ol/li[1]//h2//span"));
        String textActual= search.getText();
        String expectedText="Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        Assertions.assertEquals(expectedText, textActual);
    }

}
