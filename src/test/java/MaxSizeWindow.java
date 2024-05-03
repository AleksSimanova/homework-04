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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MaxSizeWindow{
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
    
    private boolean isStateElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }

    @Test
    public void maxSizeWindow()throws InterruptedException {
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        Actions action=new Actions(driver);
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");

        boolean isInstal= isStateElement(By.cssSelector("div.pp_pic_holder"));
        logger.info(isInstal);
        WebElement img=driver.findElement(By.cssSelector("a[href='assets/images/p1.jpg']"));
        action.moveToElement(img).click().build().perform();
        boolean isActual=driver.findElement(By.cssSelector("div.pp_pic_holder")).isDisplayed();
        logger.info(isActual);
        Assertions.assertEquals(isActual,isInstal);
    }
}
