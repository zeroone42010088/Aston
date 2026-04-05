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
            String currentTab = tab; // для лямбды
            Allure.step("Проверка таба: " + currentTab, () -> {
                payment.selectTab(currentTab);
                payment.clickContinue();
                assertTrue(payment.isErrorDisplayed(), "Нет ошибки на табе: " + currentTab);
                takeScreenshot("Ошибка на табе " + currentTab);
            });
        }

        // Основной сценарий
        Allure.step("Выбираем таб 'Услуги связи'", () -> payment.selectTab("Услуги связи"));

        Allure.step("Заполняем форму платежа", () -> 
            payment.fillForm("297777777", "10", "test@test.com")
        );

        Allure.step("Нажимаем продолжить", () -> payment.clickContinue());

        ConfirmationPage confirm = new ConfirmationPage(driver);

        Allure.step("Проверяем страницу подтверждения", () -> {
            assertTrue(confirm.getAmount().contains("10"), "Сумма некорректна");
            assertTrue(confirm.getPhone().contains("297777777"), "Телефон некорректен");
            assertTrue(confirm.isCardFieldVisible(), "Нет полей карты");
            assertTrue(confirm.arePaymentIconsVisible(), "Нет иконок платежей");
            takeScreenshot("Успешное завершение теста");
        });

    } catch (Exception e) {
        takeScreenshot("Ошибка теста");
        throw e; // чтобы тест упал и Allure отметил ошибку
    }
}
}