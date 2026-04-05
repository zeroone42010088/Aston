package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.qameta.allure.Step;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(d -> d.findElement(locator));
    }

    protected void click(By locator) {
        waitForElement(locator).click();
    }

    protected void type(By locator, String text) {
        waitForElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForElement(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        return waitForElement(locator).isDisplayed();
    }

    @Step("Открыть страницу: {url}")
    public void open(String url) {
        driver.get(url);
    }
}