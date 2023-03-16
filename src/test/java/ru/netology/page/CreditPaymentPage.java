package ru.netology.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPaymentPage {

    public CreditPaymentPage() {
        heading.shouldBe(visible);
    }

    private SelenideElement heading = $$("h3").findBy(Condition.exactText("Кредит по данным карты"));
    private SelenideElement cardNumber = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("input[placeholder='08']");
    private SelenideElement year = $("input[placeholder='22']");
    private SelenideElement owner = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvc = $("input[placeholder='999']");
    private SelenideElement continueButton = $$("button").findBy(Condition.exactText("Продолжить"));

    public void completedForm(CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        cvc.setValue(cardInfo.getCVC());
        continueButton.click();
    }

    public void successNotification() {
        $(".notification_status_ok").shouldBe(visible, Duration.ofMillis(40000));
    }

    public void errorNotification() {
        $(".notification_status_error").shouldBe(visible,Duration.ofMillis(40000));
    }

    public void incorrectFormat() {
        $(".input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible,Duration.ofMillis(12000));
    }

    public void incorrectExpireDate() {
        $(".input__sub").shouldHave(exactText("Неверно указан срок действия карты")).shouldBe(visible,Duration.ofMillis(12000));
    }

    public void obligatoryField() {
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения")).shouldBe(visible,Duration.ofMillis(12000));
    }

    public void allFieldsObligatory() {
        $$(".input__sub").shouldHave(CollectionCondition.size(5)).shouldHave(CollectionCondition.texts("Поле обязательно для заполнения"));
    }

    public void cardExpired() {
        $(".input__sub").shouldHave(exactText("Истёк срок действия карты")).shouldBe(visible,Duration.ofMillis(12000));
    }

}