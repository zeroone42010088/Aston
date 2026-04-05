package tests;

import org.junit.jupiter.api.Test;
import pages.PaymentPage;
import pages.ConfirmationPage;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTests extends BaseTest {

    @Test
    void mobileServiceFullFlowTest() {
        PaymentPage payment = new PaymentPage(driver);

        // Проверка табов
        String[] tabs = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String tab : tabs) {
            payment.selectTab(tab);
            payment.clickContinue();
            assertTrue(payment.isErrorDisplayed(), "Нет ошибки на табе: " + tab);
        }

        // Заполнение формы
        payment.selectTab("Услуги связи");
        payment.fillForm("297777777", "10", "test@test.com");
        payment.clickContinue();

        ConfirmationPage confirm = new ConfirmationPage(driver);

        // Проверки (более гибкие)
        assertTrue(confirm.getAmount().contains("10"), "Сумма некорректна");
        assertTrue(confirm.getPhone().contains("297777777"), "Телефон некорректен");

        assertTrue(confirm.isCardFieldVisible(), "Нет полей карты");
        assertTrue(confirm.arePaymentIconsVisible(), "Нет иконок платежей");
    }
}