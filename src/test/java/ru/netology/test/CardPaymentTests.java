package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBHelper;
import ru.netology.page.CardPaymentPage;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;



public class CardPaymentTests {


    @BeforeEach
    void setUp() {
        open(System.getProperty("sut.url"));
    }


    @BeforeEach
    void cleanDB() {
        DBHelper.cleanDatabase();
    }


    //passed
    @Test
    public void getSuccessfulPaymentValidCard() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = successPaymentWithApprovedCard();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCardPaymentStatus());
    }


    //failed
    @Test
    public void getFailedPaymentDeclinedCard() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationWhilePayingWithDeclinedCard();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.errorNotification();
        assertEquals("DECLINED", DBHelper.getCardPaymentStatus());
    }


    //passed
    @Test
    public void getSuccessfulPaymentDoubleSurname() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = successPaymentDoubleSurname();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCardPaymentStatus());
    }


    //passed
    @Test
    public void getSuccessfulPaymentApostropheSurname() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = successPaymentApostropheSurname();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCardPaymentStatus());
    }


    //passed
    @Test
    public void getSuccessfulPaymentCyrillicNameAndSurname() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = successPaymentCyrillicNameAndSurname();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCardPaymentStatus());
    }


    //failed
    @Test
    public void getAllFieldsObligatoryErrorIfAllFormFieldsEmpty() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = ErrorAllFormFieldsEmpty();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.allFieldsObligatory();
    }


    //passed
    @Test
    public void getIncorrectFormatErrorIfFifteenSymbolsInCardNumberField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationFifteenSymbolsInCardNumberField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //passed //через две минуты появляется сообщение "Успешно"
    @Test
    public void getErrorNotificationIfSixteenZerosInCardNumberField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationSixteenZerosInCardNumberField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.errorNotification();
    }


    //failed
    @Test
    public void getObligatoryFieldErrorIfCardNumberFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationCardNumberFieldEmpty();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.obligatoryField();
    }


    //failed
    @Test
    public void getIncorrectExpireDateErrorIfTwoZerosInMonthField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationTwoZerosInMonthField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectExpireDate();
    }


    //passed
    @Test
    public void getIncorrectExpireDateErrorIfNumberThirteenInMonthField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationNumberThirteenInMonthField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectExpireDate();
    }


    //passed
    @Test
    public void getIncorrectExpireDateErrorIfNumberNinetyNineInMonthField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationNumberNinetyNineInMonthField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectExpireDate();
    }


    //failed
    @Test
    public void getObligatoryFieldErrorIfMonthFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationMonthFieldEmpty();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.obligatoryField();
    }


    //passed
    @Test
    public void getCardExpiredErrorIfTwoZerosInYearField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationTwoZerosInYearField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.cardExpired();
    }


    //failed
    @Test
    public void getIncorrectExpireDateErrorIfNumberNinetyNineInYearField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationNumberNinetyNineInYearField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectExpireDate();
    }


    //passed
    @Test
    public void getCardExpiredErrorIfNumberTwentyTwoInYearField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationNumberTwentyTwoInYearField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.cardExpired();
    }


    //failed
    @Test
    public void getCardExpiredErrorIfEnterCurrentMonthAndCurrentYear() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationIfEnterCurrentMonthAndCurrentYear();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.cardExpired();
    }


    //passed
    @Test
    public void getIncorrectExpireDateErrorIfNumberFiftyInYearField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationNumberFiftyInYearField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectExpireDate();
    }


    //failed
    @Test
    public void getObligatoryFieldErrorIfYearFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationYearFieldEmpty();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.obligatoryField();
    }


    //passed
    @Test
    public void getObligatoryFieldErrorIfOwnerFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationOwnerFieldEmpty();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.obligatoryField();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorIfOneSymbolInOwnerField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationOneSymbolInOwnerField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorIfTwoSymbolsInOwnerField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationTwoSymbolsInOwnerField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorIfDashSymbolInOwnerField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationDashSymbolInOwnerField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorIfEightySymbolsInOwnerField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationEightySymbolsInOwnerField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorIfNumbersInOwnerField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationNumbersInOwnerField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //passed
    @Test
    public void getIncorrectFormatErrorIfOneSymbolInCVCField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationOneSymbolInCVCField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //passed
    @Test
    public void getIncorrectFormatErrorIfTwoSymbolsInCVCField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationTwoSymbolsInCVCField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getErrorNotificationIfThreeZerosInCVCField() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationThreeZerosInCVCField();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.errorNotification();
    }


    //passed // текст "Поле обязательно для заполнения" появляется не под полем "CVC/CVV", а под полем "Владелец". Под полем "CVC/CVV" отображается сообщение "Неверный формат"
    @Test
    public void getObligatoryFieldErrorIfCVCFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.payWithCard();
        val cardPaymentPage = new CardPaymentPage();
        val cardInfo = errorNotificationCVCFieldEmpty();
        cardPaymentPage.completedForm(cardInfo);
        cardPaymentPage.obligatoryField();
    }
}
