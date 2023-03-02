package ru.netology.test;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.CardPaymentPage;
import ru.netology.page.MainPage;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.DataHelper.successPaymentWithApprovedCard;

public class CardPaymentTests {

    @BeforeEach
    void setUp() {
        open(System.getProperty("sut.url"));
    }

    @BeforeEach
    void cleanDB() {
        DBHelper.cleanDatabase();
    }


    @Test
    public void successPaymentValidCard() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = successPaymentWithApprovedCard();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCardPaymentStatus());
    }
}
