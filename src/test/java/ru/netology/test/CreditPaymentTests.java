package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBHelper;
import ru.netology.page.CreditPaymentPage;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;



public class CreditPaymentTests {


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
    public void getSuccessfulPaymentIfValidCreditCard() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = successPaymentWithApprovedCard();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCreditPaymentStatus());
    }


    //failed
    @Test
    public void getFailedPaymentIfDeclinedCreditCard() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationWhilePayingWithDeclinedCard();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.errorNotification();
        assertEquals("DECLINED", DBHelper.getCreditPaymentStatus());
    }


    //passed
    @Test
    public void getSuccessfulCreditPaymentIfDoubleSurnameInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = successPaymentDoubleSurname();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCreditPaymentStatus());
    }


    //passed
    @Test
    public void getSuccessfulCreditPaymentIfApostropheSurnameInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = successPaymentApostropheSurname();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCreditPaymentStatus());
    }


    //passed
    @Test
    public void getSuccessfulCreditPaymentIfCyrillicNameAndSurnameInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = successPaymentCyrillicNameAndSurname();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.successNotification();
        assertEquals("APPROVED", DBHelper.getCreditPaymentStatus());
    }


    //failed
    @Test
    public void getAllFieldsObligatoryErrorForCreditPaymentIfAllFormFieldsEmpty() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = ErrorAllFormFieldsEmpty();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.allFieldsObligatory();
    }


    //passed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfFifteenSymbolsInCardNumberField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationFifteenSymbolsInCardNumberField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getErrorNotificationForCreditPaymentIfSixteenZerosInCardNumberField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationSixteenZerosInCardNumberField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.errorNotification();
    }


    //failed
    @Test
    public void getObligatoryFieldErrorForCreditPaymentIfCardNumberFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationCardNumberFieldEmpty();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.obligatoryField();
    }


    //failed
    @Test
    public void getIncorrectExpireDateErrorForCreditPaymentIfTwoZerosInMonthField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationTwoZerosInMonthField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectExpireDate();
    }


    //passed
    @Test
    public void getIncorrectExpireDateErrorForCreditPaymentIfNumberThirteenInMonthField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationNumberThirteenInMonthField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectExpireDate();
    }


    //passed
    @Test
    public void getIncorrectExpireDateErrorForCreditPaymentIfNumberNinetyNineInMonthField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationNumberNinetyNineInMonthField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectExpireDate();
    }


    //failed
    @Test
    public void getObligatoryFieldErrorForCreditPaymentIfMonthFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationMonthFieldEmpty();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.obligatoryField();
    }


    //passed
    @Test
    public void getCardExpiredErrorForCreditPaymentIfTwoZerosInYearField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationTwoZerosInYearField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.cardExpired();
    }


    //passed
    @Test
    public void getIncorrectExpireDateErrorForCreditPaymentIfNumberNinetyNineInYearField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationNumberNinetyNineInYearField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectExpireDate();
    }

    //failed
    @Test
    public void getCardExpiredErrorForCreditPaymentIfEnterCurrentMonthAndCurrentYear() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationIfEnterCurrentMonthAndCurrentYear();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.cardExpired();
    }


    //passed
    @Test
    public void getCardExpiredErrorForCreditPaymentIfNumberTwentyTwoInYearField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationNumberTwentyTwoInYearField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.cardExpired();
    }


    //passed
    @Test
    public void getIncorrectExpireDateErrorForCreditPaymentIfNumberFiftyInYearField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationNumberFiftyInYearField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectExpireDate();
    }


    //failed
    @Test
    public void getObligatoryFieldErrorForCreditPaymentIfYearFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationYearFieldEmpty();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.obligatoryField();
    }


    //passed
    @Test
    public void getObligatoryFieldErrorForCreditPaymentIfOwnerFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationOwnerFieldEmpty();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.obligatoryField();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfOneSymbolInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationOneSymbolInOwnerField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfTwoSymbolsInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationTwoSymbolsInOwnerField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfDashSymbolInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationDashSymbolInOwnerField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfEightySymbolsInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationEightySymbolsInOwnerField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfNumbersInOwnerField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationNumbersInOwnerField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //passed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfOneSymbolInCVCField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationOneSymbolInCVCField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //passed
    @Test
    public void getIncorrectFormatErrorForCreditPaymentIfTwoSymbolsInCVCField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationTwoSymbolsInCVCField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.incorrectFormat();
    }


    //failed
    @Test
    public void getErrorNotificationForCreditPaymentIfThreeZerosInCVCField() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationThreeZerosInCVCField();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.errorNotification();
    }


    //failed
    @Test
    public void getObligatoryFieldErrorForCreditPaymentIfCVCFieldEmpty() {
        val mainPage = new MainPage();
        mainPage.buyInCredit();
        val creditPaymentPage = new CreditPaymentPage();
        val cardInfo = errorNotificationCVCFieldEmpty();
        creditPaymentPage.completedForm(cardInfo);
        creditPaymentPage.obligatoryField();
    }
}