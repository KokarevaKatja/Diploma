package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class MainPage {

    public MainPage() {
        heading.shouldBe(visible);
    }

    private SelenideElement heading = $$("h2").findBy(Condition.exactText("Путешествие дня"));
    private SelenideElement cardButton = $$("button").findBy(Condition.exactText("Купить"));
    private SelenideElement creditButton = $$("button").findBy(Condition.exactText("Купить в кредит"));


    public CardPaymentPage payWithCard() {
        cardButton.click();
        return new CardPaymentPage();
    }

    public CreditPaymentPage buyInCredit() {
        creditButton.click();
        return new CreditPaymentPage();
    }
}


