package login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class trying {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://experience.adobe.com/#/@dollargeneral/sname:dg-sit-uat/platform/home");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='email'])[1]")));
            String email = "Padiga@dollargeneral.com";
            emailInput.sendKeys(email);

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space() = 'Continue' or contains(., 'Continue') or @id='continue' or @name='continue']")));
            continueBtn.click();

            // After clicking Continue -> wait for account chooser and click the account matching the email.
            String[] candidateEmails = {
                    "Padiga@dollargeneral.com",
                    "padiga@dollagerenal.com" // try the misspelled variant if needed
            };

            boolean accountSelected = false;
            for (String target : candidateEmails) {
                try {
                    By accountLocator = By.xpath("//*[normalize-space() = '" + target + "' or contains(normalize-space(.), '" + target + "')]");
                    WebElement accountEl = wait.until(ExpectedConditions.elementToBeClickable(accountLocator));
                    accountEl.click();
                    accountSelected = true;
                    break;
                } catch (Exception ignored) {
                    // try next candidate
                }
            }

            if (!accountSelected) {
                // Fallback: click any visible element that contains the username "Padiga" (case-insensitive)
                try {
                    By fallback = By.xpath("//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'padiga')]");
                    WebElement fallbackEl = wait.until(ExpectedConditions.elementToBeClickable(fallback));
                    fallbackEl.click();
                    accountSelected = true;
                } catch (Exception ignored) {
                    // no account found; handle as needed
                }
            }

            // optional: verify selection succeeded (e.g., wait for next page element)

        } finally {
            driver.quit();
        }
    }
}
