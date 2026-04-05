package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'amount')]")
    private WebElement confirmationAmount;

    @FindBy(xpath = "//div[contains(@class,'phone')]")
    private WebElement confirmationPhone;

    @FindBy(xpath = "//input[contains(@placeholder,'Номер карты')]")
    private WebElement cardFields;

    @FindBy(xpath = "//div[contains(@class,'payment-icons')]")
    private WebElement paymentIcons;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить сумму подтверждения")
    public String getAmount() {
        return confirmationAmount.getText();
    }

    @Step("Получить телефон подтверждения")
    public String getPhone() {
        return confirmationPhone.getText();
    }

    @Step("Проверить видимость полей карты")
    public boolean isCardFieldVisible() {
        return cardFields.isDisplayed();
    }

    @Step("Проверить видимость иконок платежей")
    public boolean arePaymentIconsVisible() {
        return paymentIcons.isDisplayed();
    }
}