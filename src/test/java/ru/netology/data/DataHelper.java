package ru.netology.data;

import lombok.Value;

public class DataHelper {

    public DataHelper() {
    }


    public static CardInfo successPaymentWithApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationWhilePayingWithDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442", "09", "24", "IVAN PETROV", "898");
    }

    public static CardInfo successPaymentDoubleSurname() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IVAN PETROV-VODKIN", "898");
    }

    public static CardInfo successPaymentApostropheSurname() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IVAN D’ARTANIYAN", "898");
    }

    public static CardInfo successPaymentCyrillicNameAndSurname() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "ИВАН ПЕТРОВ", "898");
    }

    public static CardInfo ErrorAllFormFielsEmpty() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo errorNotificationFifteenSymbolsInCardNumberField() {
        return new CardInfo("4444 4444 4444 444", "09", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationSixteenZerosInCardNumberField() {
        return new CardInfo("0000 0000 0000 0000", "09", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationCardNumberFieldEmpty() {
        return new CardInfo("", "09", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationTwoZerosInMonthField() {
        return new CardInfo("4444 4444 4444 4441", "00", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationNumberThirteenInMonthField() {
        return new CardInfo("4444 4444 4444 4441", "13", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationNumberNinetyNineInMonthField() {
        return new CardInfo("4444 4444 4444 4441", "99", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationMonthFieldEmpty() {
        return new CardInfo("4444 4444 4444 4441", "", "24", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationTwoZerosInYearField() {
        return new CardInfo("4444 4444 4444 4441", "09", "00", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationNumberNinetyNineInYearField() {
        return new CardInfo("4444 4444 4444 4441", "09", "99", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationNumberTwentyTwoInYearField() {
        return new CardInfo("4444 4444 4444 4441", "09", "22", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationNumberFiftyInYearField() {
        return new CardInfo("4444 4444 4444 4441", "09", "50", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationYearFieldEmpty() {
        return new CardInfo("4444 4444 4444 4441", "09", "", "IVAN PETROV", "898");
    }

    public static CardInfo errorNotificationOwnerFieldEmpty() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "", "898");
    }

    public static CardInfo errorNotificationOneSymbolInOwnerField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "I", "898");
    }

    public static CardInfo errorNotificationTwoSymbolsInOwnerField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IV", "898");
    }

    public static CardInfo errorNotificationDashSymbolInOwnerField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "-", "898");
    }

    public static CardInfo errorNotificationEightySymbolsInOwnerField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "FDGRGFBGFTHRTRHRHGYDSARTYFVGARFGTHYUIJKO FDGRGFBGFTHRTRHRHGYDSARTYFVGARFGTHYUIJK", "898");
    }

    public static CardInfo errorNotificationNumbersInOwnerField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "932497 2343534", "898");
    }

    public static CardInfo errorNotificationOneSymbolInCVCField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IVAN PETROV", "8");
    }

    public static CardInfo errorNotificationTwoSymbolsInCVCField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IVAN PETROV", "89");
    }

    public static CardInfo errorNotificationThreeZerosInCVCField() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IVAN PETROV", "000");
    }

    public static CardInfo errorNotificationCVCFieldEmpty() {
        return new CardInfo("4444 4444 4444 4441", "09", "24", "IVAN PETROV", "");
    }
}