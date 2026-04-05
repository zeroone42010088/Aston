package pages;

import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PaymentPage extends BasePage {

    // Табы
    @FindBy(xpath = "//button[contains(text(),'Услуги связи')]")
    private WebElement mobileServicesTab;

    @FindBy(xpath = "//button[contains(text(),'Домашний интернет')]")
    private WebElement homeInternetTab;

    @FindBy(xpath = "//button[contains(text(),'Рассрочка')]")
    private WebElement installmentTab;

    @FindBy(xpath = "//button[contains(text(),'Задолженность')]")
    private WebElement debtTab;

    // Поля
    @FindBy(xpath = "//input[contains(@placeholder,'Номер')]")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[contains(@placeholder,'Сумма')]")
    private WebElement amountInput;

    @FindBy(xpath = "//input[contains(@placeholder,'E-mail')]")
    private WebElement emailInput;

    @FindBy(xpath = "//button[contains(text(),'Продолжить')]")
    private WebElement continueButton;

    // Ошибки
    @FindBy(xpath = "//*[contains(@class,'error') or contains(text(),'Введите')]")
    private WebElement errorMessage;

    // Подтверждение (эти элементы лучше перенести в ConfirmationPage, но оставим пока)
    @FindBy(xpath = "//div[contains(@class,'amount')]")
    private WebElement confirmationAmount;

    @FindBy(xpath = "//div[contains(@class,'phone')]")
    private WebElement confirmationPhone;

    @FindBy(xpath = "//input[contains(@placeholder,'Номер карты')]")
    private WebElement cardFields;

    @FindBy(xpath = "//div[contains(@class,'payment-icons')]")
    private WebElement paymentIcons;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

@Step("Выбрать вкладку: {tabName}")
public void selectTab(String tabName) {
    String xpath = switch (tabName) {
        case "Услуги связи" -> "//button[contains(text(),'Услуги связи')]";
        case "Домашний интернет" -> "//button[contains(text(),'Домашний интернет')]";
        case "Рассрочка" -> "//button[contains(text(),'Рассрочка')]";
        case "Задолженность" -> "//button[contains(text(),'Задолженность')]";
        default -> throw new IllegalArgumentException("Неизвестная вкладка: " + tabName);
    };
    
    // Более надёжный способ поиска
    WebElement tabElement = driver.findElement(By.xpath(xpath));
    tabElement.click();
}

    @Step("Заполнить форму: телефон {phone}, сумма {amount}, email {email}")
    public void fillForm(String phone, String amount, String email) {
        phoneInput.sendKeys(phone);
        amountInput.sendKeys(amount);
        emailInput.sendKeys(email);
    }

    @Step("Нажать кнопку Продолжить")
    public void clickContinue() {
        continueButton.click();
    }

    @Step("Проверить наличие ошибки")
    public boolean isErrorDisplayed() {
        return errorMessage.isDisplayed();
    }

    @Step("Получить сумму подтверждения")
    public String getConfirmationAmount() {
        return confirmationAmount.getText();
    }

    @Step("Получить телефон подтверждения")
    public String getConfirmationPhone() {
        return confirmationPhone.getText();
    }

    @Step("Проверить отображение полей карты")
    public boolean isCardFieldsDisplayed() {
        return cardFields.isDisplayed();
    }

    @Step("Проверить отображение иконок платежей")
    public boolean arePaymentIconsDisplayed() {
        return paymentIcons.isDisplayed();
    }
}