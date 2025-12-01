package login;


// File: src/main/java/login/login.java


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Comparator;

public class  newlogin {
    public static void main(String[] args) throws Exception {
        Path profileDir = Files.createTempDirectory("chrome-profile-");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=" + profileDir.toAbsolutePath().toString());
        options.addArguments("--no-first-run", "--no-default-browser-check");

        WebDriver driver = new ChromeDriver(options);
        try {
            // open a fresh tab and navigate there to avoid any default profile page
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://experience.adobe.com/#/@dollargeneral/sname:dg-sit-uat/platform/home");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='email'])[1]")))
                    .sendKeys("Padiga@dollargeneral.com");

            wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space() = 'Continue' or contains(., 'Continue') or @id='continue' or @name='continue']")))
                    .click();

            System.out.println("Clicked Continue button");
            System.out.println("Login process initiated.");
        } finally {
           // driver.quit();
            // cleanup temp profile directory
            try {
                Files.walk(profileDir)
                        .sorted(Comparator.reverseOrder())
                        .forEach(p -> {
                            try { Files.deleteIfExists(p); } catch (Exception ignored) {}
                        });
            } catch (Exception ignored) {}
        }
    }
}

