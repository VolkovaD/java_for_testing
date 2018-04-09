import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class Temp {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void Temp() {
        if (!wd.findElement(By.id("96")).isSelected()) {
            wd.findElement(By.id("96")).click();
        }
        if (!wd.findElement(By.xpath("//div[@class='right']//select[normalize-space(.)='test 0 test 1 test 2 test3']//option[4]")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='right']//select[normalize-space(.)='test 0 test 1 test 2 test3']//option[4]")).click();
        }
        wd.findElement(By.name("add")).click();
        wd.findElement(By.linkText("group page \"test3\"")).click();
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
