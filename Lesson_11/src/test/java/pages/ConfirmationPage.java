package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    private By confirmationPopup = By.xpath("//div[contains(@class,'confirmation')]");
    private By amountText = By.xpath("//div[contains(@class,'amount')]");
    private By phoneText = By.xpath("//div[contains(@class,'phone')]");
    private By paymentIcons = By.xpath("//div[contains(@class,'payment-icons')]");
    private By cardFields = By.xpath("//input[contains(@placeholder,'Номер карты')]");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что отображается окно подтверждения")
    public boolean isDisplayed() {
        return isDisplayed(confirmationPopup);
    }

    @Step("Получить сумму из окна подтверждения")
    public String getAmount() {
        return getText(amountText);
    }

    @Step("Получить номер телефона из окна подтверждения")
    public String getPhone() {
        return getText(phoneText);
    }

    @Step("Проверить наличие полей для ввода карты")
    public boolean isCardFieldVisible() {
        return isDisplayed(cardFields);
    }

    @Step("Проверить наличие логотипов платежных систем")
    public boolean arePaymentIconsVisible() {
        return isDisplayed(paymentIcons);
    }
}