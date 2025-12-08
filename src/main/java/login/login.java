package login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class login {
    public static void main(String[] args) throws InterruptedException, IOException {


        //WebDriver driver = new ChromeDriver(options);
        WebDriver driver = new ChromeDriver();
       // driver.manage().deleteAllCookies();
       // driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://experience.adobe.com/#/@dollargeneral/sname:dg-sit-uat/platform/home");
   driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='email'])[1]")));
        emailInput.sendKeys("Padiga@dollargeneral.com");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space() = 'Continue' or contains(., 'Continue') or @id='continue' or @name='continue']")));
        continueBtn.click();
        System.out.println("Clicked Continue button");
        System.out.println("Login process initiated.");
        System.out.println("Please complete any further steps manually if required.");
        System.out.println("Execute this line");
        Thread.sleep(15000);
        driver.findElement(By.xpath("//*[@id=\"tilesHolder\"]/div[2]/div/div/div/div[2]/div[1]")).click();
       // WebElement loginid = wait.until(
             //   ExpectedConditions.elementToBeClickable(
                //        By.xpath("//input[@name='Username']")));
       // loginid.sendKeys("padiga@dollargeneral.com");
        //driver.findElement(By.xpath("//input[@name='Username']")).sendKeys("padiga@dollargeneral.com");
        //driver.findElement(By.xpath("//input[@name='Initials']")).sendKeys("pa");
        //driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("9adI@T95");
       // driver.findElement(By.xpath("//input[@type='submit']")).click();
        System.out.println("sso page");
        Thread.sleep(40000);
        System.out.println("logged in AEP");
        //driver.findElement(By.xpath("//*[@id=\"react-aria186676507-83\"]/ul/li[4]/a")).click();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //driver.quit();
        WebElement uimenu = driver.findElement(By.xpath("//*[@id=\"shell-left-nav-menu\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        boolean found = false;
        for (int i = 0; i < 10; i++) {

            try {
                // Try to find the Queries element
                WebElement queries = driver.findElement(By.xpath("//a[@data-omega-element='query']"));

                js.executeScript("arguments[0].scrollIntoView(true);", queries);

                wait.until(ExpectedConditions.elementToBeClickable(queries));
                queries.click();
                found = true;
                break;
            } catch (Exception e) {
                // Scroll a bit inside side panel if not found yet
                js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + 200;", uimenu);
            }
        }

        if (!found) {
            System.out.println("Queries menu not found!");
        }
        System.out.println("Completed navigation to Queries section.");
        Thread.sleep(30000);
        System.out.println("Attempting to click Create Query button.");
        WebElement iframe=driver.findElement(By.xpath("//iframe[@title='Main Content']"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//button[@aria-label=\"Create query\"]")).click();
        System.out.println("Clicked on Create Query button.");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@class='spectrum-ClearButton spectrum-ClearButton--medium spectrum-ClearButton--overBackground']")).click();
        System.out.println("Clicked arrow button");
        driver.findElement(By.xpath("//div[@class='cm-activeLine cm-line']")).sendKeys("select Count(*) from dg_customer_dataset");
        System.out.println("Entered SQL query.");
        //Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@aria-label='Run query']")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//*[@id=\"ue_group\"]/div[3]/button")).click();
       driver.findElement(By.xpath("(//button[@aria-label='Collapse right rail'])[2]")).click();
        Thread.sleep(60000);
System.out.println("waited for 40 seconds");
WebElement drag=driver.findElement(By.xpath("//div[@class='SqlEditor__dragBorder__91_JY']"));
Actions actions = new Actions(driver);
actions.dragAndDropBy(drag, 0, -100).perform();

WebElement queryvalue= driver.findElement(By.xpath("//td[2]"));
String results=queryvalue.getText();
System.out.println("Query Result: " + results);



    }
}

