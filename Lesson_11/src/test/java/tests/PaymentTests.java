package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import pages.PaymentPage;
import pages.ConfirmationPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTests extends BaseTest {

    // метод для скриншота и прикрепления к Allure
    private void takeScreenshot(String name) {
        Allure.addAttachment(name,
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Test
    void mobileServiceFullFlowTest() {
        PaymentPage payment = new PaymentPage(driver);

        try {
            // Проверка табов
            String[] tabs = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
            for (String tab : tabs) {
                payment.selectTab(tab);
                payment.clickContinue();
                assertTrue(payment.isErrorDisplayed(), "Нет ошибки на табе: " + tab);
                takeScreenshot("Ошибка на табе " + tab);
            }

            // Основной сценарий
            payment.selectTab("Услуги связи");
            payment.fillForm("297777777", "10", "test@test.com");
            payment.clickContinue();

            ConfirmationPage confirm = new ConfirmationPage(driver);

            assertTrue(confirm.getAmount().contains("10"), "Сумма некорректна");
            assertTrue(confirm.getPhone().contains("297777777"), "Телефон некорректен");
            assertTrue(confirm.isCardFieldVisible(), "Нет полей карты");
            assertTrue(confirm.arePaymentIconsVisible(), "Нет иконок платежей");

            takeScreenshot("Успешное завершение теста");

        } catch (Exception e) {
            takeScreenshot("Ошибка теста");
            throw e; // пробрасываем, чтобы JUnit отметил тест как упавший
        }
    }
}