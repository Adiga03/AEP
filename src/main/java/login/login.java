package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class login {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://experience.adobe.com/#/@dollargeneral/sname:dg-sit-uat/platform/home");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='email'])[1]")));
        emailInput.sendKeys("Padiga@dollargeneral.com");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space() = 'Continue' or contains(., 'Continue') or @id='continue' or @name='continue']")));
        continueBtn.click();
        System.out.println("Clicked Continue button");
    }
}
