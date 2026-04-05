package tests;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;

@ExtendWith(AllureJunit5.class)
public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        basePage = new BasePage(driver);
        basePage.open("https://www.mts.by/");   // ← поменяй, если URL другой
    }

    @AfterEach
    public void tearDown() {
        if (basePage != null) {
            basePage.takeScreenshot("Финальный скриншот теста");
        }
        if (driver != null) {
            driver.quit();
        }
    }
}